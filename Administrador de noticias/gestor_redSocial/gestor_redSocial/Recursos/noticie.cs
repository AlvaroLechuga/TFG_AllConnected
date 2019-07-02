namespace gestor_redSocial.Recursos
{
	class noticie
	{
		private int id;
		private string title;
		private int category;
		private string text;
		private string image;
		private int id_user;

		public noticie()
		{
		}

		public noticie(string title, int category, string text, string image, int id_user)
		{
			this.title = title;
			this.category = category;
			this.text = text;
			this.image = image;
			this.id_user = id_user;
		}

		public noticie(int id, string title, int category, string text, string image, int id_user)
		{
			this.id = id;
			this.title = title;
			this.category = category;
			this.text = text;
			this.image = image;
			this.id_user = id_user;
		}

		public int Id { get => id; set => id = value; }
		public string Title { get => title; set => title = value; }
		public int Category { get => category; set => category = value; }
		public string Text { get => text; set => text = value; }
		public string Image { get => image; set => image = value; }
		public int Id_user { get => id_user; set => id_user = value; }
	}
}
