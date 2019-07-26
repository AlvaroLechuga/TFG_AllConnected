<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Follow;
use App\Notification;
use App\User;

class FollowController extends Controller {

    public function getFollow($id, Request $request) {

        $token = $request->header('Authorization');
        $jwtAuth = new \JwtAuth();

        $checkToken = $jwtAuth->checkToken($token);

        if ($checkToken) {
            $user = $jwtAuth->checkToken($token, true);
            $user_followed = User::find($id);

            $follow = Follow::where([
                        ['user', '=', $user->sub],
                        ['followed', '=', $id]
                    ])->count();

            if ($follow == 1) {
                $data = array(
                    'status' => 'success',
                    'code' => '200',
                    'message' => 'UnFollow'
                );
            } else if ($follow == 0) {
                $data = array(
                    'status' => 'success',
                    'code' => '200',
                    'message' => 'Follow'
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

    public function followUser($id, Request $request) {
        $token = $request->header('Authorization');
        $jwtAuth = new \JwtAuth();

        $checkToken = $jwtAuth->checkToken($token);

        if ($checkToken) {

            $user = $jwtAuth->checkToken($token, true);
            $follow = new Follow();
            $follow->user = $user->sub;
            $follow->followed = $id;

            $follow->save();

            $notification = new Notification();
            $notification->id_user_emmit = $user->sub;
            $notification->id_user_recep = $id;
            $notification->description = "te está siguiendo";

            $notification->save();

            $data = array(
                'status' => 'sucess',
                'code' => '200',
                'message' => 'Se ha seguido correctamente',
                'follow' => $follow
            );
        } else {
            $data = array(
                'status' => 'error',
                'code' => '400',
                'message' => 'No hay token'
            );
        }

        return response()->json($data, $data['code']);
    }

    public function unFollowUser($id, Request $request) {
        $token = $request->header('Authorization');
        $jwtAuth = new \JwtAuth();

        $checkToken = $jwtAuth->checkToken($token);

        if ($checkToken) {
            $user = $jwtAuth->checkToken($token, true);
            $follow = new Follow();
            $follow->user = $user->sub;
            $follow->followed = (int) $id;

            Notification::where('id_user_emmit', $follow->user)->where('id_user_recep', $follow->followed)->delete();
            Follow::where('user', $follow->user)->where('followed', $follow->followed)->delete();

            $data = array(
                'status' => 'success',
                'code' => '200',
                'message' => 'Se ha dejado de seguir'
            );
        } else {
            $data = array(
                'status' => 'error',
                'code' => '400',
                'message' => 'El usuario no está identificado'
            );
        }

        return response()->json($data, $data['code']);
    }

    public function getNumberFollows($id) {

        $nFollows = Follow::where('user', $id)->count();

        $data = array(
            'status' => 'success',
            'code' => '200',
            'nfollows' => $nFollows
        );

        return response()->json($data, $data['code']);
    }

    public function getNumberFollowers($id) {

        $nFollowers = Follow::where('followed', $id)->count();

        $data = array(
            'status' => 'success',
            'code' => '200',
            'nfollowers' => $nFollowers
        );

        return response()->json($data, $data['code']);
    }

}
