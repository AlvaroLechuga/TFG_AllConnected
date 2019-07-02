using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace gestor_redSocial.Recursos
{
	class user
	{
		private int id;
		private string nombre;

		public user()
		{

		}

		public user(int id, string nombre)
		{
			this.id = id;
			this.nombre = nombre;
		}

		public int Id { get => id; set => id = value; }
		public string Nombre { get => nombre; set => nombre = value; }
	}
}
