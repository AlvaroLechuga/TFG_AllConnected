import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css'],
	providers: [UserService]
})
export class LoginComponent implements OnInit {

	public user: User;
	public status: string;
	public token: string;
	public identity;

	constructor(private _userService: UserService, private _router: Router, private _route: ActivatedRoute) {
		this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');
	}

	ngOnInit() {

		this.token = this._userService.getToken();

		if (this.token) {
			this._router.navigate(['/index']);
		}

		// Se ejecuta siempre y cierra sesión solo cuando le llega el parámetro sure por la url
		this.logout();
	}

	iniciarUsuario(form) {
		this._userService.signup(this.user).subscribe(
			response => {
				// Esto devuelve el token del usuario
				if (response.status != 'error') {
					this.status = 'success';
					this.token = response;
					// Objeto usuario identificado

					this._userService.signup(this.user, true).subscribe(
						response => {
							this.identity = response;

							// Persistir los datos del usuario identificado
							localStorage.setItem('token', this.token);
							localStorage.setItem('identity', JSON.stringify(this.identity));
							this._router.navigate(['inicio']);
						},
						error => {
							this.status = 'error';
							console.log(<any>error);
						}
					);

				} else {
					this.status = 'error';
				}
			},
			error => {
				this.status = 'error';
				console.log(<any>error);
			}
		);
	}

	logout() {
		this._route.params.subscribe(params => {
			let logout = +params['sure'];

			if (logout == 1) {
				localStorage.removeItem('identity');
				localStorage.removeItem('token');

				console.log('Entra');

				this.identity = null;
				this.token = null;

				// Redirección a inicio

				this._router.navigate(['inicio']);
			}

		});
	}

}
