import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [UserService]
})
export class RegisterComponent implements OnInit {

	public user: User;
	public status: string;
  public token;

  constructor(private _userService: UserService, private _router: Router) {
  	this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');		
  }

    ngOnInit() {
      this.token = this._userService.getToken();

      if(this.token){
        this._router.navigate(['/index']);
      }

    }

  registrarUsuario(form){
  	console.log(this.user);
  	this._userService.register(this.user).subscribe(
  		response => {
  			if(response.status == 'success'){
  				form.reset();
  				this.status = 'success';
  			}else{
  				this.status = 'error';
  			}
  		},
  		error => {
  			this.status = 'error';
  			console.log(<any>error);
  		}
  	);
  		
  }

}
