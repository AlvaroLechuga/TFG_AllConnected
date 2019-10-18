import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { global } from '../../services/global';
import { ViewChild } from '@angular/core';
import { AngularFileUploaderComponent } from "angular-file-uploader";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css'],
  providers: [UserService]
})
export class UserEditComponent implements OnInit {

  public user: User;
  public status: string;
  public identity;
  public token;
  public url;
  public afuConfig = {
    multiple: false,
    formatsAllowed: ".jpg,.png,.jpeg,.gif",
    maxSize: "1",
    uploadAPI: {
      url: global.url + 'user/upload',
      headers: {
        "Authorization": this._userService.getToken()
      }
    },
    theme: "attachPin",
    hideProgressBar: false,
    hideResetBtn: true,
    hideSelectBtn: false,
    attachPinText: "Sube la imagen de usuario"
  };

  constructor(private _userService: UserService) {
    this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');
    this.identity = this._userService.getIdentity();
    this.token = this._userService.getToken();
    this.user = new User(
      this.identity.sub,
      this.identity.name,
      this.identity.surname,
      this.identity.direction,
      this.identity.country,
      '',
      this.identity.nick,
      this.identity.email,
      '',
      this.identity.role,
      this.identity.description,
      '', '');

    this.url = global.url;

  }

  ngOnInit() {
  }

  avatarUpload(datos) {
    let data = JSON.parse(datos.response);
    this.user.image = data.image;
  }

  modificarUsuario(form) {
    this._userService.update(this.token, this.user).subscribe(
      response => {
        if (response.status == 'success') {
          this.status = 'success';

          // Actualizar usuario en sesión

          if (response.changes.name) {
            this.user.name = response.changes.name;
          }

          if (response.changes.surname) {
            this.user.surname = response.changes.surname;
          }

          if (response.changes.direction) {
            this.user.direction = response.changes.direction;
          }

          if (response.changes.country) {
            this.user.country = response.changes.country;
          }

          if (response.changes.nick) {
            this.user.nick = response.changes.nick;
          }

          if (response.changes.description) {
            this.user.description = response.changes.description;
          }

          if (response.changes.image) {
            this.user.image = response.changes.image;
          }

          this.identity = this.user;
          localStorage.setItem('identity', JSON.stringify(this.identity));

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

}
