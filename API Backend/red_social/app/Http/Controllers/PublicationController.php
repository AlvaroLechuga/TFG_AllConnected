<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Publication;
use App\User;
use App\Notification;
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
                
                if($publication->id == $user->sub){
					$notification = new Notification();
					$notification->id_user_emmit = $user->sub;
					$notification->id_user_recep = $publication->id_user;
					$notification->description = "Ha respondido una publicación";
					
					$notification->save();
				}

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
                    
                    if($publication->response_id != null){
                        Notification::where('id_user_emmit', $user->sub)->where('id_user_recep', $publication->id_user)->delete();
                    }
                    
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
            }else{
				$data = array(
                        'status' => 'error',
                        'code' => '404',
                        'message' => 'No existe esa publicación',
                );
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
			
			$total = sizeof($publications);
			
			$array_tiempo = array();
			
			for ($i = 0; $i < $total; $i++) {
				
				$tiempo = \FormatTime::LongTimeFilter($publications[$i]->created_at);
				array_push($array_tiempo, $tiempo);
			}
			
			
            $data = array(
                'status' => 'success',
                'code' => '200',
                'publications' => $publications,
				'tiempo' => $array_tiempo
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
            
			$publications = DB::table('publications')
			->join('follows', 'publications.id_user', '=', 'follows.followed')
			->where('follows.user', "$id")
			->orWhere('publications.id_user', '=', "$id")
			->select('publications.*')
			->orderBy('publications.id', 'DESC')->get();
			
			
            if(sizeof($publications) == 0){
				$publications = Publication::where('id', $id)->orderBy('id', 'DESC');
            }
			
			$total = sizeof($publications);
			
			$array_tiempo = array();
			$array_usuarios = array();
			
			for ($i = 0; $i < $total; $i++) {
				$usuario = User::where('id', $publications[$i]->id_user)->get();				
				array_push($array_usuarios, $usuario);
				
				$publication = Publication::find($publications[$i]->id);
				
				$tiempo = \FormatTime::LongTimeFilter($publication->created_at);
				array_push($array_tiempo, $tiempo);
			}
			
            $data = array(
                'status' => 'success',
                'code' => '200',
                'publications' => $publications,
				'users' => $array_usuarios,
				'tiempo' => $array_tiempo
                
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
