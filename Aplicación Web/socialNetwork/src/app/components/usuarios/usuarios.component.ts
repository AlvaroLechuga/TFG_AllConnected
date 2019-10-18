import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { global } from '../../services/global';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
	selector: 'app-usuarios',
	templateUrl: './usuarios.component.html',
	styleUrls: ['./usuarios.component.css'],
	providers: [UserService]
})
export class UsuariosComponent implements OnInit {

	public busqueda: string;
	public users: User;
	public url;
	public token;

	constructor(private _userService: UserService, private _router: Router, private _route: ActivatedRoute) {
		this.url = global.url;
		this.token = this._userService.getToken();
	}

	ngOnInit() {
		this._route.params.subscribe(params => {
			this.busqueda = params.id;
		});

		if (this.token == null) {
			this._router.navigate(['/inicio']);
		} else {
			this.obtenerUsuarios();
		}


	}

	obtenerUsuarios() {
		this._userService.getUsers(this.busqueda).subscribe(
			response => {
				console.log(response);
				this.users = response.users;
			},
			error => {
				console.log(<any>error);
			}
		);
	}

}
