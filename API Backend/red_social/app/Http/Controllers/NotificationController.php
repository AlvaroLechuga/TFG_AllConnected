<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Helpers\jwtAuth;
use Illuminate\Support\Facades\DB;

class NotificationController extends Controller
{
    public function getNotification($id, Request $request){
        $jwtAuth = new JwtAuth();
        $token = $request->header('Authorization', null);

        if ($token) {
            $user = $jwtAuth->checkToken($token, true);
            
            $notifications = DB::select("SELECT e.name AS name_emmit, e.surname AS surname_emmit, r.name AS name_recep, r.surname AS surname_recep, notifications.id_user_emmit, notifications.description, notifications.updated_at FROM notifications JOIN users e ON e.id = notifications.id_user_emmit JOIN users r ON r.id = notifications.id_user_recep WHERE notifications.id_user_recep = $id ORDER BY notifications.id ASC");
            
            $data = array(
                'status' => 'success',
                'code' => '200',
                'notificaciones' => $notifications,
            );
            
        }else{
            $data = array(
                'status' => 'error',
                'code' => '404',
                'message' => 'No hay token',
            );
        }
        
        return response()->json($data, $data['code']);
    }
}
