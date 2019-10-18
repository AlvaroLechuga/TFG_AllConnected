export class Publication {
	constructor(
		public id: number,
		public id_user: number,
		public text: string,
		public file: string,
		public created_at: any,
		public like?: boolean
	) { }
}