<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Like;
use App\User;
use App\Publication;
use App\Helpers\jwtAuth;

class LikeController extends Controller {

    public function like($id, Request $request) {
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', null);

        if ($token) {
            $user = $jwtAuth->checkToken($token, true);

            $publication = Publication::find($id);

            if ($publication) {
                $like = new Like();
                $like->id_publication = $id;
                $like->id_user_emmiter = $user->sub;
                $like->id_user_reciver = $publication->id_user;

                $like->save();

                $data = array(
                    'status' => 'success',
                    'code' => '200',
                    'message' => 'Like',
                    'like' => $like
                );
            } else {
                $data = array(
                    'status' => 'error',
                    'code' => '404',
                    'message' => 'No se ha encontrado la publicación',
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

    public function dislike($id, Request $request) {
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', null);

        if ($token) {
            $user = $jwtAuth->checkToken($token, true);

            $publication = Publication::find($id);

            if ($publication) {
                $like = new Like();
                $like->id_publication = (int) $id;
                $like->id_user_emmiter = $user->sub;
                $like->id_user_reciver = $publication->id_user;

                Like::where('id_publication', $like->id_publication)->where('id_user_emmiter', $like->id_user_emmiter)->where('id_user_reciver', $like->id_user_reciver)->delete();

                $data = array(
                    'status' => 'success',
                    'code' => '200',
                    'message' => 'Dislike',
                );
            } else {
                $data = array(
                    'status' => 'error',
                    'code' => '404',
                    'message' => 'No se ha encontrado la publicación',
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

    public function getNumberLikes($id) {

        $nLikes = Like::where('id_user_emmiter', $id)->count();

        $data = array(
            'status' => 'success',
            'code' => '200',
            'nlikes' => $nLikes
        );

        return response()->json($data, $data['code']);
    }

}