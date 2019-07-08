<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

// Cargando clases

use App\Http\Middleware\ApiAuthMiddleware;

// Ruta por defecto
Route::get('/', function () {
    return view('welcome');
});

// Rutas del controlador de Usuario
Route::post('api/register', 'UserController@register');
Route::post('api/login', 'UserController@login');
Route::put('api/user/update', 'UserController@update');
Route::post('api/user/upload', 'UserController@upload');
Route::get('api/user/avatar/{filename}', 'UserController@getImage');
Route::get('api/user/detail/{id}', 'UserController@detail');
Route::get('api/user/search/{search?}', 'UserController@getUser');

// Rutas del controlador de Publicaciones
Route::post('api/publicate', 'PublicationController@store');
Route::post('api/publicateresponse/{id}', 'PublicationController@responseUser');
Route::delete('api/publication/delete/{id}', 'PublicationController@delete');
Route::get('api/publications/{id}', 'PublicationController@getPublicationbyUser');
Route::get('api/numberpublications/{id}', 'PublicationController@getNumberPublications');
Route::get('api/publicationfollowers/{id}', 'PublicationController@getPublicationFollowers');

// Rutas del controlador de Follow
Route::post('api/follow/{id}', 'FollowController@followUser');
Route::get('api/getfollow/{id}', 'FollowController@getFollow');
Route::delete('api/unfollow/{id}', 'FollowController@unFollowUser');
Route::get('api/numberfollows/{id}', 'FollowController@getNumberFollows');
Route::get('api/numberfollowers/{id}', 'FollowController@getNumberFollowers');

//Rutas del controlador de Like
Route::post('/api/like/{id_publication}', 'LikeController@like');
Route::delete('/api/dislike/{id_publication}', 'LikeController@dislike');
Route::get('/api/numberlikes/{id}', 'LikeController@getNumberLikes');
Route::get('api/getlikes/{id}', 'LikeController@getLikes');

// Rutas del controlador de Message
Route::post('api/insertmessage/{id}', 'MessageController@InsertarMensaje');
Route::get('api/getmessages/{id}', 'MessageController@ObtenerMensajes');

// Rutas del controlador de Notifications
Route::get('api/getnotifications/{id}', 'NotificationController@getNotification');

// Rutas del controlador de Notices
Route::get('api/getnotices', 'NoticeController@getNotification');