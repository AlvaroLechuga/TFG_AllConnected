<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Message;
use App\Helpers\jwtAuth;
use Illuminate\Support\Facades\DB;

class MessageController extends Controller {

    public function InsertarMensaje($id, Request $request) {
        $json = $request->input('json', null);
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

                $message = new Message();
                $message->emmiter = $user->sub;
                $message->reciver = $id;
                $message->text = $params_array['text'];

                $message->save();

                $data = array(
                    'status' => 'sucess',
                    'code' => '200',
                    'message' => 'Se ha enviado el mensaje'
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

    public function ObtenerMensajes($id, Request $request) {
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', null);

        if ($token) {
            $user = $jwtAuth->checkToken($token, true);

            $messages = DB::select("SELECT * FROM messages WHERE emmiter = $user->sub AND reciver = $id OR emmiter = $id AND reciver = $user->sub ORDER BY id ASC");


            $data = array(
                'status' => 'success',
                'code' => '200',
                'messages' => $messages,
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
