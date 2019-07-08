import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';

@Injectable()
export class NotificationService{
	public url: string;

	constructor(
		private _http: HttpClient
	){
		this.url = global.url;
	}
	

	sacarNotificaciones(token, id): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);

		return this._http.get(this.url+'getnotifications/'+id, {headers: headers});
	}

}