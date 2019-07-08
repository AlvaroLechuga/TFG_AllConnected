import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';

@Injectable()
export class NoticeService{
	public url: string;

	constructor(
		private _http: HttpClient
	){
		this.url = global.url;
	}

	getNotices(): Observable<any>{
		return this._http.get(this.url+'getnotices');
	}
}