<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Publication;
use App\Helpers\jwtAuth;
use Illuminate\Support\Facades\DB;

class PublicationController extends Controller {

    public function store(Request $request) {
        $json = $request->input('json', null);
        $params = json_decode($json);
        $params_array = json_decode($json, true);

        $validate = \Validator::make($params_array, [
                    'text' => 'required|max:255'
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
            $jwtAuth = new JwtAuth();
            $token = $request->header('Authorization', null);

            if ($token) {
                $user = $jwtAuth->checkToken($token, true);

                $publication = new Publication();
                $publication->id_user = $user->sub;
                $publication->text = $params_array['text'];

                $publication->save();

                $data = array(
                    'status' => 'sucess',
                    'code' => '200',
                    'message' => 'Se ha enviado la publicación'
                );
            } else {
                $data = array(
                    'status' => 'error',
                    'code' => '404',
                    'message' => 'No hay token',
                );
            }
        }

        return response()->json($data, $data['code']);
    }

    public function responseUser($id, Request $request) {

        $json = $request->input('json', null);
        $params = json_decode($json);
        $params_array = json_decode($json, true);

        $validate = \Validator::make($params_array, [
                    'text' => 'required|max:255'
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
            $jwtAuth = new JwtAuth();
            $token = $request->header('Authorization', null);

            if ($token) {
                $user = $jwtAuth->checkToken($token, true);

                $publication = new Publication();
                $publication->id_user = $user->sub;
                $publication->text = $params_array['text'];
                $publication->response_id = $id;

                $publication->save();

                $data = array(
                    'status' => 'sucess',
                    'code' => '200',
                    'message' => 'Se ha enviado la publicación'
                );
            } else {
                $data = array(
                    'status' => 'error',
                    'code' => '404',
                    'message' => 'No hay token',
                );
            }
        }

        return response()->json($data, $data['code']);
    }

    public function delete($id, Request $request) {
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', null);

        if ($token) {
            $user = $jwtAuth->checkToken($token, true);

            $publication = Publication::find($id);

            if ($publication) {
                if ($publication->id_user == $user->sub) {
                    $publication->delete();
                    $data = array(
                        'status' => 'success',
                        'code' => '200',
                        'message' => 'Se a eliminado la publicación',
                    );
                } else {
                    $data = array(
                        'status' => 'error',
                        'code' => '404',
                        'message' => 'El id del usuario no corresponde con el de la publicación',
                    );
                }
            }
        } else {
            $data = array(
                'status' => 'error',
                'code' => '404',
                'message' => 'No hay token',
            );
        }

        return response()->json($data, $data['code']);
    }

    public function getPublicationbyUser($id) {
        $publications = Publication::where('id_user', $id)->orderBy('id', 'desc')->get();

        if ($publications) {
            $data = array(
                'status' => 'success',
                'code' => '200',
                'publications' => $publications,
            );
        } else {
            $data = array(
                'status' => 'error',
                'code' => '200',
                'message' => 'El usuario no tiene publicaciones',
            );
        }
        return response()->json($data, $data['code']);
    }

    public function getNumberPublications($id) {
        $nPublications = Publication::where('id_user', $id)->count();

        $data = array(
            'status' => 'success',
            'code' => '200',
            'npublicaciones' => $nPublications
        );

        return response()->json($data, $data['code']);
    }

    public function getPublicationFollowers($id, Request $request) {
        
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', null);
        
        if ($token) {
            $user = $jwtAuth->checkToken($token, true);
            
            $publications = DB::select("SELECT p.* FROM publications p INNER JOIN follows f ON p.id_user = f.followed WHERE f.user = $id OR p.id_user = $id ORDER BY p.id ASC");            
            $username = DB::select("SELECT u.* FROM users u INNER JOIN publications p ON u.id = p.id_user INNER JOIN follows f ON u.id = f.user WHERE f.user = $id OR f.followed = $id");
            
            if(sizeof($publications) == 0){
                $publications = DB::select("SELECT * FROM publications WHERE id_user = $id ORDER BY id ASC");
                $username = DB::select("SELECT * FROM users WHERE id = $id");
            }
			
            $data = array(
                'status' => 'success',
                'code' => '200',
                'publications' => $publications,
                'users' => $username
            );
            
        } else {
            $data = array(
                'status' => 'error',
                'code' => '404',
                'message' => 'No hay token',
            );
        }

        return response()->json($data, $data['code']);
    }

}
