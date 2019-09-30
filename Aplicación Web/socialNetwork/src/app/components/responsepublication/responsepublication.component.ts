import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { global } from '../../services/global';
import { UserService } from '../../services/user.service';
import { PublicationService } from '../../services/publication.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-responsepublication',
  templateUrl: './responsepublication.component.html',
  styleUrls: ['./responsepublication.component.css'],
  providers: [UserService, PublicationService]
})
export class ResponsepublicationComponent implements OnInit {

  public user;
  public url;
  public identity;
  public token;
  public publication;

  public id_p;

  constructor(
    private _userService: UserService,
    private _publicationService: PublicationService,
    private _router: Router,
    private _route: ActivatedRoute) {
      this.url = global.url;
		  this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');
  		this.identity = this._userService.getIdentity();
  		this.token = this._userService.getToken();
   }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.id_p = params.id;
    
      if(this.token == null){
        this._router.navigate(['/inicio']);
      }
    });
    this.getPublication();
  }

  getPublication(){
    this._publicationService.getPublication(this.id_p).subscribe(
			response => {
        this.publication = response.publication;
			},
			error => {
				console.log(<any>error);
			}
		);
  }

}
