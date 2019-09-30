import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { Publication } from '../../models/publication';
import { UserService } from '../../services/user.service';
import { PublicationService } from '../../services/publication.service';
import { FollowService } from '../../services/follow.service';
import { global } from '../../services/global';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  providers: [UserService, PublicationService, FollowService]
})
export class IndexComponent implements OnInit {

	public user: User;
  public publication: Publication;
	public identity;
	public token;
  public url;

  public publications;
  public infoUsers;

  public nPublications;
  public nFollowers;
  public nFollowing;

  public indice = 0;

  public id_p;

  public time;

    constructor(private _userService: UserService, private _publicationService: PublicationService, private _followService: FollowService ,private _router: Router) {
      this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');
      this.publication = new Publication(1, 1, '', '', '');

      this.identity = this._userService.getIdentity();
      this.token = this._userService.getToken();
      
      if(this.identity != null){
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
      }


      this.url = global.url;

      if(!this.token){
        this._router.navigate(['/inicio']);
      }

      this.getPublications();

    }

  	ngOnInit() {
      this.numberPublication();
      this.numberFollowers();
      this.numberFollowings();
  	}

    getPublications(){
      this._publicationService.getPublicationFollow(this.token, this.identity.sub).subscribe(
        response => {
          this.time = response.tiempo;
          this.publications = response.publications;
          this.infoUsers = response.users;
        },
        error => {
          console.log(<any>error);
        }

      );
    }

    numberPublication(){
      this._publicationService.numberPublication(this.user.id).subscribe(
        response => {
          this.nPublications = response.npublicaciones;
        },
        error => {
        }
      );
    }

    numberFollowers(){
      this._followService.numberFollowers(this.user.id).subscribe(
        response => {
          this.nFollowers = response.nfollows;
        },
        error => {
        }
      );
    }

    numberFollowings(){
      this._followService.numberFollowings(this.user.id).subscribe(
        response => {
          this.nFollowing = response.nfollowers;
        },
        error => {
        }
      );
    }

    submitPublication(form){
      this.publication.id_user = this.user.id;
      this._publicationService.create(this.token, this.publication).subscribe(
        response => {
          form.reset();
        },
        error => {
          console.log(<any>error);
        }
      );
    }

    submitResponse(responseuser){
      this.publication.id_user = this.user.id;
      this.id_p = this.publications[this.indice].id;
      this.publication.text = responseuser;
      
      this._publicationService.responseUser(this.token, this.publication, this.id_p).subscribe(
        response => {
          this.getPublications();
          this.numberPublication();
        },
        error => {
        }

      );
      
    }

    deletePublication(id){
      this._publicationService.deletePublication(id, this.token).subscribe(
        response => {
          this.getPublications();
        },
        error => {
          console.log(<any>error);
        }

      );
    }

    responsePublication(id){
      this._router.navigate(['/responder/'+id]);
    }
}
