export class User{
	constructor(
		public id: number,
		public name: string,
		public surname: string,
		public direction: string,
		public country: string,
		public birthday: any,
		public nick: string,
		public email: string,
		public password: string,
		public role: string,
		public description: string,
		public image: string,
		public created_at: any
	){}
}