<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\Response;
use App\Helpers\jwtAuth;
use Illuminate\Support\Facades\DB;

class NoticeController extends Controller
{
    public function getNotification(){
		
		$notices = DB::select('SELECT noticies.*, categorys.name FROM `noticies` INNER JOIN categorys ON noticies.id_category = categorys.id ORDER BY noticies.id DESC');
		
		$data = array(
                'status' => 'success',
                'code' => '200',
                'notices' => $notices
        );
		
		return response()->json($data, $data['code']);
	}
}
