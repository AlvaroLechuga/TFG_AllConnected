<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Publication extends Model
{
    protected $table = 'publications';
	
    public function id_user(){
        return $this->hasMany('App\User');
    }
}
