import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { NotificationService } from '../../services/notification.service';
import { global } from '../../services/global';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-notificaciones',
  templateUrl: './notificaciones.component.html',
  styleUrls: ['./notificaciones.component.css'],
  providers: [UserService, NotificationService]
})
export class NotificacionesComponent implements OnInit {

	public user: User;
	public identity;
	public token;
  	public url;
	public notifications;

  	constructor(private _userService: UserService, private _notificationService: NotificationService, private _router: Router) { }

  	ngOnInit() {
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
	        this.identity.image, '');


      	this.url = global.url;

	    if(!this.token){
	        this._router.navigate(['/inicio']);
	    }

	    this.sacarNotificationes();

  	}

  	sacarNotificationes(){
  		this._notificationService.sacarNotificaciones(this.token, this.identity.sub).subscribe(
        response => {
          this.notifications = response.notificaciones;
        },
        error => {
        }
      );
  	}

}
