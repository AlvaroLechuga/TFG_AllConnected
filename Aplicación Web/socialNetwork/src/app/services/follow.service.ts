import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';
import { User } from '../models/user';

@Injectable()
export class FollowService{
	public url: string;

	constructor(
		private _http: HttpClient
	){
		this.url = global.url;
	}

	follow(id, token): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);

		return this._http.post(this.url+'follow/'+id, {headers: headers});
	}

	getFollow(id, token): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);

		return this._http.get(this.url+'getfollow/'+id, {headers:headers});
	}

	unFollow(id, token): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);

		return this._http.delete(this.url+'unfollow/'+id, {headers: headers});
	}

	numberFollowers(id): Observable<any>{
		return this._http.get(this.url+'numberfollows/'+id);
	}

	numberFollowings(id): Observable<any>{
		return this._http.get(this.url+'numberfollowers/'+id);
	}

}