using gestor_redSocial.Recursos;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace gestor_redSocial
{
	public partial class AddNotice : Form
	{
		BaseDatos b = new BaseDatos("SERVER=localhost;DATABASE=red_social;UID=root;PWD=''");
		List<category> listaCategorias;
		StreamReader ficheroLectura;
		int id_user = 0;

		public AddNotice()
		{
			InitializeComponent();
			b.Conectar();
			listaCategorias = b.ListaCategorias();

			foreach (category categoria in listaCategorias)
			{
				cbCategoria.Items.Add(categoria.Name);
			}

			ficheroLectura = File.OpenText("user");
			string linea = ficheroLectura.ReadLine();

			int pos = linea.IndexOf('|');

			id_user = Convert.ToInt32(linea.Substring(0, pos));

			ficheroLectura.Close();

			b.Desconectar();

		}

		private void btnInsertarNoticia_Click(object sender, EventArgs e)
		{

			if(txtTitulo.Text.ToString() == "" || cbCategoria.SelectedIndex == -1 || txtTitulo.Text.ToString() == "" || txtImage.Text.ToString() == "")
			{
				MessageBox.Show("Introduzca todos los campos", "Error en los campos", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
			else
			{
				b.Conectar();

				string titulo = txtTitulo.Text.ToString();
				string text = txtTexto.Text.ToString();
				string url = txtImage.Text.ToString();
				int id_categoria = listaCategorias[cbCategoria.SelectedIndex].Id;

				noticie n = new noticie(titulo, id_categoria, text, url, id_user);

				if (b.InsertarPublicacion(n))
				{
					MessageBox.Show("Se ha insertado correctamente", "Insertado correcto", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				else
				{
					MessageBox.Show("Ha ocurrido un error al insertar", "Error al insertar", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}

			b.Desconectar();

			txtTitulo.Text = "";
			txtImage.Text = "";
			txtTexto.Text = "";

		}

	}
}
