import { Component, OnInit, DoCheck } from '@angular/core';
import { UserService } from './services/user.service';
import { global } from './services/global';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService]
})
export class AppComponent implements OnInit, DoCheck{
	public identity;
	public token;
	public url;

	constructor(private _userService: UserService, private _router: Router){
		this.loadUser();
		this.url = global.url;
	}

	ngOnInit(){

	}

	ngDoCheck(){
		this.loadUser();
	}

	loadUser(){
		this.token = this._userService.getToken();
		this.identity = this._userService.getIdentity();
	}

	buscarUsuario(value){
		if(value != ''){
			this._router.navigate(['/usuarios/'+value]);
		}
	}

}
