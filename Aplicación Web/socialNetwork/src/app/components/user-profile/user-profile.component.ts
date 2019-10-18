import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { Publication } from '../../models/publication';
import { UserService } from '../../services/user.service';
import { PublicationService } from '../../services/publication.service';
import { FollowService } from '../../services/follow.service';
import { LikeService } from '../../services/like.service';
import { global } from '../../services/global';
import { Router, ActivatedRoute, Params } from '@angular/router';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [UserService, PublicationService, FollowService, LikeService]
})
export class UserProfileComponent implements OnInit {

  public user: User;
  public status: string;
  public identity;
  public token;
  public url;
  public publication: Publication;
  public publications;

  public nPublications;
  public nFollowers;
  public nFollowing;
  public nLikes;

  public time;

  public indice = 0;

  public id_p;

  public likes;

  constructor(private _userService: UserService, private _publicationService: PublicationService, private _followService: FollowService, private _likeService: LikeService, private _router: Router, private _route: ActivatedRoute) {
    this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');
    this.publication = new Publication(1, 1, '', '', '');
    this.identity = this._userService.getIdentity();
    this.token = this._userService.getToken();
    this.user = this.identity;
    this.url = global.url;

    if (!this.token) {
      this._router.navigate(['/inicio']);
    }

  }

  ngOnInit() {
    this.getPublications(this.identity.sub);
    this.numberPublication();
    this.numberFollowers();
    this.numberFollowings();
    this.numberLikes();
    this.getLikes();
  }

  numberPublication() {
    this._publicationService.numberPublication(this.identity.sub).subscribe(
      response => {
        this.nPublications = response.npublicaciones;
      },
      error => {
      }
    );
  }

  numberFollowers() {
    this._followService.numberFollowers(this.identity.sub).subscribe(
      response => {
        this.nFollowers = response.nfollows;
      },
      error => {
      }
    );
  }

  numberFollowings() {
    this._followService.numberFollowings(this.identity.sub).subscribe(
      response => {
        this.nFollowing = response.nfollowers;
      },
      error => {
      }
    );
  }

  numberLikes() {
    this._likeService.numberLikes(this.identity.sub).subscribe(
      response => {
        this.nLikes = response.nlikes;
      },
      error => {
      }
    );
  }

  deletePublication(id) {
    this._publicationService.deletePublication(id, this.token).subscribe(
      response => {
        this.getPublications(this.identity.sub);
      },
      error => {
        console.log(<any>error);
      }

    );
  }

  getPublications(id) {
    this._publicationService.getPublications(id).subscribe(
      response => {
        this.publications = response.publications;
        this.time = response.tiempo;
      },
      error => {
        console.log(<any>error);
      }

    );
  }

  getLikes() {
    this._likeService.getLikes(this.token, this.identity.sub).subscribe(
      response => {
        this.likes = response.likes;
        this.asignarLikes();
      },
      error => {
        console.log(<any>error)
      }
    );
  }

  asignarLikes() {
    for(let i = 0; i < this.publications.length; i++){
      for(let j = 0; j < this.likes.length; j++){
        if(this.publications[i].id == this.likes[j].id_publication){
          this.publications[i].like = true;
        }else{
          this.publications[i].like = false;
        }
      }
    }

    console.log(this.publications);

  }

  like(id) {
    this._likeService.like(this.token, id).subscribe(
      response => {
        this.getLikes();
      },
      error => {
        console.log(error);
      }

    );
  }

  dislike(id) {
    this._likeService.dislike(this.token, id).subscribe(
      response => {
        this.getLikes();
      },
      error => {
        console.log(error);
      }

    );
  }

  responsePublication(id) {
    this._router.navigate(['/responder/' + id]);
  }

}
