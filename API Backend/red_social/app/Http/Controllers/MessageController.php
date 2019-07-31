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
	
	public function getMessagesUser($id){
		
		$messages = DB::select("SELECT e.id AS id_emmit, r.id AS id_recep, e.name AS name_emmit, e.surname AS surname_emmit, e.nick AS nick_emmit, r.name AS name_recep, r.surname AS surname_recep, r.nick AS nick_recep, messages.emmiter, messages.reciver, messages.text, e.image AS image_emmit, r.image AS image_reciver FROM messages JOIN users e ON e.id = messages.emmiter JOIN users r ON r.id = messages.reciver WHERE messages.emmiter = $id OR messages.reciver = $id GROUP BY messages.emmiter, messages.reciver");

		$data = array(
            'status' => 'success',
            'code' => '200',
            'messages' => $messages,
        );
		
		return response()->json($data, $data['code']);
	}

    public function ObtenerMensajesbyUser($id, Request $request) {
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', true);

        if ($token) {
            $user = $jwtAuth->checkToken($token, true);

            $messages = DB::select("SELECT e.id AS id_emmit, r.id AS id_recep, e.name AS name_emmit, e.surname AS surname_emmit, e.nick AS nick_emmit, r.name AS name_recep, r.surname AS surname_recep, r.nick AS nick_recep, messages.emmiter, messages.reciver, messages.text, e.image AS image_emmit, r.image AS image_reciver FROM messages JOIN users e ON e.id = messages.emmiter JOIN users r ON r.id = messages.reciver WHERE messages.emmiter = $user->sub AND messages.reciver = $id OR messages.emmiter = $id AND messages.reciver = $user->sub ORDER BY messages.id DESC");

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
