import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { Publication } from '../../models/publication';
import { Follow } from '../../models/follow';
import { global } from '../../services/global';
import { UserService } from '../../services/user.service';
import { PublicationService } from '../../services/publication.service';
import { FollowService } from '../../services/follow.service';
import { LikeService } from '../../services/like.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [UserService, PublicationService, FollowService, LikeService]
})
export class ProfileComponent implements OnInit {

	public user;
	public url;
	public identity;
	public token;
	public publications: Publication;
	public idIdentity;
	public follow;

	public nPublications;
  	public nFollowers;
  	public nFollowing;
  	public nLikes;

  	constructor(private _userService: UserService, 
      private _publicationService: PublicationService, 
      private _followService: FollowService,
      private _likeService: LikeService,
      private _router: Router, 
      private _route: ActivatedRoute) {
  		this.url = global.url;
		this.user = new User(1, '', '', '', '', '', '', '', '', 'user', '', '', '');
  		this.identity = this._userService.getIdentity();
  		this.token = this._userService.getToken();
  	}

  	ngOnInit() {
  		this._route.params.subscribe(params => {
	  		this.idIdentity = params.id;
			
			if(this.idIdentity == this.identity.sub){
				this._router.navigate(['/perfil']);
			}
			
	  		this.getIdentity(this.idIdentity);
	  		this.getPublications(this.idIdentity);
			
			this.getFollow(this.idIdentity, this.token);
			
      this.numberPublication(this.idIdentity);
      this.numberFollowers(this.idIdentity);
      this.numberFollowings(this.idIdentity);
      this.numberLikes(this.idIdentity);

  		});

  		
  	}
	
	getFollow(id, token){
		this._followService.getFollow(id, token).subscribe(
			response => {
				this.follow = response.message;
			},
			error => {
				this.follow = <any>error.error.message;
			}
		);
	}

  	getIdentity(id){
  		this._userService.getProfile(id).subscribe(
  			response => {
  				this.user = response.user;
  			},
  			error => {
  				console.log(<any>error);
  			}

  		);
  	}

  	deletePublication(id){
      this._publicationService.deletePublication(id, this.token).subscribe(
        response => {
          this.getPublications(this.idIdentity)
        },
        error => {
          console.log(<any>error);
        }

      );
    }

  	getPublications(id){
  		this._publicationService.getPublications(id).subscribe(
  			response => {
  				this.publications = response.publications;
  			},
  			error => {
  				console.log(<any>error);
  			}

  		);
  	}

  	numberPublication(id){
      this._publicationService.numberPublication(id).subscribe(
        response => {
          this.nPublications = response.npublicaciones;
        },
        error => {
        }
      );
    }

    numberFollowers(id){
      this._followService.numberFollowers(id).subscribe(
        response => {
          this.nFollowers = response.nfollows;
        },
        error => {
        }
      );
    }

    numberFollowings(id){
      this._followService.numberFollowings(id).subscribe(
        response => {
          this.nFollowing = response.nfollowers;
        },
        error => {
        }
      );
    }

    numberLikes(id){
      this._likeService.numberLikes(id).subscribe(
        response => {
          this.nLikes = response.nlikes;
        },
        error => {
        }
      );
    }
	
	followUser(){
		this._followService.follow(this.user.id, this.token).subscribe(
			response => {
				console.log(response);
			},
			error => {
				console.log(<any>error);
			}
		);
	}
	
	unFollowUser(){
		this._followService.unFollow(this.user.id, this.token).subscribe(
			response => {
				this.follow = "Follow";
			},
			error => {
				console.log(<any>error);
			}
		);
	}

}
