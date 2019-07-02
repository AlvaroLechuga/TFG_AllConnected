import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';

@Injectable()
export class LikeService{
	public url: string;

	constructor(
		private _http: HttpClient
	){
		this.url = global.url;
	}

	like(){

	}

	dislike(){

	}

	numberLikes(id): Observable<any>{
		return this._http.get(this.url+'numberlikes/'+id);
	}
}