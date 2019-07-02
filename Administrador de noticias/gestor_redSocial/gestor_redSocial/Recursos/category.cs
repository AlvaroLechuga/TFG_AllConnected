namespace gestor_redSocial.Recursos
{
	class category
	{
		private int id;
		private string name;

		public category()
		{
		}

		public category(int id, string name)
		{
			this.id = id;
			this.name = name;
		}

		public int Id { get => id; set => id = value; }
		public string Name { get => name; set => name = value; }
	}
}
