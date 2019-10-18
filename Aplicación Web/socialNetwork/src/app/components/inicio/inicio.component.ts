import { Component, OnInit } from '@angular/core';
import { NoticeService } from '../../services/notice.service';

@Component({
	selector: 'app-inicio',
	templateUrl: './inicio.component.html',
	styleUrls: ['./inicio.component.css'],
	providers: [NoticeService]
})
export class InicioComponent implements OnInit {

	public noticies;

	constructor(private _noticeService: NoticeService) { }

	ngOnInit() {
		this.sacarNoticias();
	}

	sacarNoticias() {
		this._noticeService.getNotices().subscribe(
			response => {
				this.noticies = response.notices;
			},
			error => {
			}
		);
	}

}
