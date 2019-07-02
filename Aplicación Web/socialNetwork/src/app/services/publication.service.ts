import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Publication } from '../models/publication';
import { global } from './global';

@Injectable()
export class PublicationService{

	public url: string;

	constructor(
		private _http: HttpClient
	){
		this.url = global.url;
	}

	create(token, publication): Observable<any>{
		let json = JSON.stringify(publication);
		let params = 'json='+json;

		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);
		return this._http.post(this.url+'publicate', params, {headers: headers});
	}

	getPublications(id): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/json');
		
		return this._http.get(this.url+'publications/'+id, {headers: headers});
	}

	deletePublication(id, token): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);

		return this._http.delete(this.url+'publication/delete/'+id, {headers: headers});
	}

	getPublicationFollow(token, id): Observable<any>{
		let headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded').set('Authorization', token);
		return this._http.get(this.url+'publicationfollowers/'+id, {headers: headers});
	}

	numberPublication(id): Observable<any>{ 
		return this._http.get(this.url+'numberpublications/'+id);
	}
}