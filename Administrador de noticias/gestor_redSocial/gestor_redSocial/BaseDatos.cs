using gestor_redSocial.Recursos;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;

namespace gestor_redSocial
{
	class BaseDatos
	{
		private MySqlConnection cnx;

		public BaseDatos(String cadenaCnx)
		{
			cnx = new MySqlConnection(cadenaCnx);
		}

		public int Conectar()
		{
			int resultado = -1;
			try
			{
				cnx.Open();
				if (cnx.State == ConnectionState.Open)
					resultado = 1;
			}
			catch (MySqlException ex)
			{

			}
			return resultado;
		}

		public int Desconectar()
		{
			int resultado = -1;
			if (cnx.State == ConnectionState.Open)
			{
				cnx.Close();
				resultado = 1;
			}
			return resultado;
		}

		public user ComprobarUsuario(string username, string password)
		{

			MySqlCommand c = new MySqlCommand();
			MySqlDataReader d;

			user user = new user();

			try
			{
				c.Connection = cnx;
				c.CommandText = "SELECT * FROM users WHERE email = '" + username + "' OR nick = '" + username + "' AND password = sha2('" + password + "', 256) AND role = 'admin'";

				d = c.ExecuteReader();

				if (d.HasRows)
				{
					if (d.HasRows)
					{
						while (d.Read())
							user = new user(d.GetInt32(0), d.GetString(6));
					}
				}

				return user;

			}
			catch(MySqlException ex)
			{
				return user;
			}

			
		}

		public bool InsertarCategoria(string nombre)
		{
			MySqlCommand c = new MySqlCommand();
			
			try
			{
				c.Connection = cnx;
				c.CommandText = "INSERT INTO `categorys` (`id`, `name`) VALUES(NULL, '"+nombre+"')"; 
				
				if(c.ExecuteNonQuery() == 1)
				{
					return true;
				}
				else
				{
					return false;
				}

			}
			catch (MySqlException ex)
			{
				return false;
			}
		}

		public List<category> ListaCategorias()
		{
			MySqlCommand c = new MySqlCommand();
			MySqlDataReader d;
			List<category> listado = new List<category>();

			try
			{
				c.Connection = cnx;
				c.CommandText = "SELECT * FROM `categorys`";
				d = c.ExecuteReader();
				if (d.HasRows)
				{
					while (d.Read())
						listado.Add(new category(d.GetInt32(0), d.GetString(1)));
				}

			}
			catch (MySqlException ex) { }

			return listado;
		}

		public bool InsertarPublicacion(noticie noticia)
		{

			MySqlCommand c = new MySqlCommand();

			try
			{
				c.Connection = cnx;
				c.CommandText = "INSERT INTO `noticies` (`id`, `title`, `id_category`, `text`, `image`, `id_user`) VALUES (NULL, '" + noticia.Title + "', '" + noticia.Category + "', '" + noticia.Text + "', '" + noticia.Image + "', '" + noticia.Id_user + "')";
				
				if (c.ExecuteNonQuery() == 1)
				{
					return true;
				}
				else
				{
					return false;
				}

			}
			catch (MySqlException ex)
			{
				return false;
			}

		}

		public List<noticie> ListaPublicaciones()
		{
			MySqlCommand c = new MySqlCommand();
			MySqlDataReader d;

			List<noticie> listaNoticias = new List<noticie>();

			try
			{
				c.Connection = cnx;
				c.CommandText = "SELECT * FROM `noticies`";
				d = c.ExecuteReader();
				if (d.HasRows)
				{
					while (d.Read())
						listaNoticias.Add(new noticie(d.GetInt32(0), d.GetString(1), d.GetInt32(2), d.GetString(3), d.GetString(4), d.GetInt32(5)));
				}

			}
			catch (MySqlException ex) { }

			return listaNoticias;
		}

		public bool ModificarPublicacion(noticie noticie)
		{
			MySqlCommand c = new MySqlCommand();
			MySqlDataReader d;
			try
			{

				c.Connection = cnx;
				c.CommandText = "UPDATE noticies SET title = '"+noticie.Title+ "', id_category = '"+noticie.Category+"', text = '"+noticie.Text+"', image = '"+noticie.Image+"' WHERE id = '"+noticie.Id+"'";
				if(c.ExecuteNonQuery() == 1)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (MySqlException ex)
			{
				return false;
			}
		}

		public bool EliminarPublicacion(int id)
		{
			MySqlCommand c = new MySqlCommand();

			try
			{
				c.Connection = cnx;
				c.CommandText = "DELETE FROM `noticies` WHERE id =" + id;

				if(c.ExecuteNonQuery() == 1)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (MySqlException ex)
			{
				return false;
			}
		}
	}
}
