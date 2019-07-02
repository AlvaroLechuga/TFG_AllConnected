<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\User;

class UserController extends Controller {

    public function register(Request $request) {

        // Recoger los datos del usuario por post

        $json = $request->input('json', null);

        // Decodificar el JSON

        $params = json_decode($json); // Objeto
        $params_array = json_decode($json, true); //Array

        if (!empty($params) && !empty($params_array)) {

            // Limpiar datos
            $params_array = array_map('trim', $params_array);

            // Validar datos

            $validate = \Validator::make($params_array, [
                        'name' => 'required|max:50|alpha',
                        'surname' => 'required|max:100|alpha',
                        'direction' => 'required|max:255',
                        'country' => 'required|max:255|alpha',
                        'birthday' => 'required',
                        'nick' => 'required|max:100',
                        'email' => 'required|max:100|unique:users|email',
                        'password' => 'required|max:10',
            ]);

            if ($validate->fails()) {
                // La validación ha fallado
                $data = array(
                    'status' => 'error',
                    'code' => '404',
                    'message' => 'El usuario no se ha creado',
                    'errors' => $validate->errors()
                );
            } else {
                // La validación es correcta
                // Cifrar la contraseña

                $password = hash('sha256', $params->password);

                // Crear el usuario

                $user = new User();
                $user->name = $params_array['name'];
                $user->surname = $params_array['surname'];
                $user->direction = $params_array['direction'];
                $user->country = $params_array['country'];
                $user->birthday = $params_array['birthday'];
                $user->nick = $params_array['nick'];
                $user->email = $params_array['email'];
                $user->password = $password;
                $user->role = 'user';
                $user->description = "";
                $user->image = "default.png";


                // Guardar el usario

                $user->save();

                $data = array(
                    'status' => 'sucess',
                    'code' => '200',
                    'message' => 'El usuario se ha creado',
                    'user' => $user
                );
            }
        } else {
            $data = array(
                'status' => 'error',
                'code' => '404',
                'message' => 'Los datos enviados no son correctos'
            );
        }
        return response()->json($data, $data['code']);
    }

    public function login(Request $request) {

        $jwtAuth = new \JwtAuth();

        // Recibir datos por post

        $json = $request->input('json', null);
        $params = json_decode($json);
        $params_array = json_decode($json, true);

        // Validar esos datos

        $validate = \Validator::make($params_array, [
                    'email' => 'required|email',
                    'password' => 'required|max:10',
        ]);

        if ($validate->fails()) {
            // La validación ha fallado
            $signup = array(
                'status' => 'error',
                'code' => '404',
                'message' => 'El usuario no ha podido iniciar sesión',
                'errors' => $validate->errors()
            );
        } else {
            // Cifrar la password

            $pwd = hash('sha256', $params->password);

            // Devolver token o datos

            $signup = $jwtAuth->signup($params->email, $pwd);

            if (!empty($params->getToken)) {
                $signup = $jwtAuth->signup($params->email, $pwd, true);
            }
        }

        return response()->json($signup, 200);
    }

    public function update(Request $request) {
        $token = $request->header('Authorization');
        $jwtAuth = new \JwtAuth();

        $checkToken = $jwtAuth->checkToken($token);

        // Recoger los datos por POST

        $json = $request->input('json', null);

        // Decodificar el JSON

        $params = json_decode($json); // Objeto
        $params_array = json_decode($json, true); //Array


        if ($checkToken && !empty($params_array)) {

            // Validar los datos
            // Sacar usuario identificado

            $user = $jwtAuth->checkToken($token, true);

            $validate = \Validator::make($params_array, [
                        'name' => 'required|max:50|alpha',
                        'surname' => 'required|max:100|alpha',
                        'direction' => 'required|max:255',
                        'country' => 'required|max:255|alpha',
                        'nick' => 'required|max:100',
                        'description' => 'required'
            ]);

            if ($validate->fails()) {
                // La validación ha fallado
                $data = array(
                    'status' => 'error',
                    'code' => '404',
                    'message' => 'Errores de validación',
                    'errors' => $validate->errors()
                );
            } else {
                // Quitar los campos que no quiero actualizar

                unset($params_array['id']);
                unset($params_array['birthday']);
                unset($params_array['password']);
                unset($params_array['created_at']);
                unset($params_array['remember_token']);

                // Actualizar usuario en la base de datos

                $user_update = User::where('id', $user->sub)->update($params_array);

                // Devolver un array con resultado

                $data = array(
                    'status' => 'success',
                    'code' => '200',
                    'user' => $user,
                    'changes' => $params_array
                );
            }
        } else {

            $data = array(
                'status' => 'error',
                'code' => '400',
                'message' => 'El usuario no está identificado'
            );
        }

        return response()->json($data, $data['code']);
    }

    public function upload(Request $request) {

        // Recoger datos de la petición

        $image = $request->file('file0');

        // Validación de la imagen

        $validate = \Validator::make($request->all(), [
                    'file0' => 'required|image'
        ]);

        if ($validate->fails()) {
            // La validación ha fallado
            $data = array(
                'status' => 'error',
                'code' => '404',
                'message' => 'Errores de validación',
                'errors' => $validate->errors()
            );
        } else {

            if ($image) {
                // Subir la imagen

                $image_path_name = time() . $image->getClientOriginalName();
                \Storage::disk('users')->put($image_path_name, \File::get($image));

                $data = array(
                    'status' => 'success',
                    'code' => '200',
                    'image' => $image_path_name
                );
            } else {
                $data = array(
                    'status' => 'error',
                    'code' => '400',
                    'message' => 'Error al subir la imagen'
                );
            }
        }

        return response()->json($data, $data['code']);
    }

    public function getImage($filename) {

        $exits = \Storage::disk('users')->exists($filename);

        if ($exits) {
            $file = \Storage::disk('users')->get($filename);
            return new Response($file, 200);
        } else {
            $data = array(
                'status' => 'error',
                'code' => '400',
                'message' => 'No existe la imagen'
            );

            return response()->json($data, $data['code']);
        }
    }

    public function detail($id) {
        $user = User::find($id);

        if (is_object($user)) {
            $data = array(
                'status' => 'success',
                'code' => '200',
                'user' => $user
            );
        } else {
            $data = array(
                'status' => 'error',
                'code' => '400',
                'message' => 'No existe el usuario'
            );
        }

        return response()->json($data, $data['code']);
    }

    public function getUser($search = null) {
        if (!empty($search)) {
            $users = User::where('nick', 'LIKE', '%' . $search . '%')
                    ->orWhere('name', 'LIKE', '%' . $search . '%')
                    ->orWhere('surname', 'LIKE', '%' . $search . '%')
                    ->orderBy('id', 'desc')
                    ->paginate(10);

            $data = array(
                'status' => 'success',
                'code' => '200',
                'users' => $users
            );
        } else {
            $users = User::orderBy('id', 'desc')->paginate(10);
            $data = array(
                'status' => 'success',
                'code' => '200',
                'users' => $users
            );
        }

        return response()->json($data, $data['code']);
    }

}
