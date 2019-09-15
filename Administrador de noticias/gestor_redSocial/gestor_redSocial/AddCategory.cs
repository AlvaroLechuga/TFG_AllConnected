using gestor_redSocial.Recursos;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace gestor_redSocial
{
	public partial class AddCategory : Form
	{

		BaseDatos b = new BaseDatos("SERVER=localhost;DATABASE=red_social;UID=root;PWD=''");
		List<category> listaCategorias;
		bool error = false;

		public AddCategory()
		{
			InitializeComponent();
		}

		private void btnInsertarCategoria_Click(object sender, EventArgs e)
		{
			string categoria = txtCategoria.Text.ToString();

			if(categoria == "")
			{
				MessageBox.Show("Introduzca el nombre de una categoría", "Error de login", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
			else
			{
				b.Conectar();

				listaCategorias = b.ListaCategorias();

				string categoriaTemporal = categoria.ToLower();

				foreach (category cat in listaCategorias)
				{
					if(categoriaTemporal == cat.Name.ToLower())
					{
						error = true;
						break;
					}
				}

				if (!error)
				{
					if (b.InsertarCategoria(categoria))
					{
						MessageBox.Show("Se ha insertado correctamente");
					}
					else
					{
						MessageBox.Show("Ha ocurrido un error al insertar la categoría", "Error al insertar", MessageBoxButtons.OK, MessageBoxIcon.Error);
					}
				}
				else
				{
					MessageBox.Show("Ya existe esa categoría", "Categoría repetida", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}

				b.Desconectar();

				txtCategoria.Text = "";

			}
		}

        private void AddCategory_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyValue == 13)
            {
                btnInsertarCategoria_Click(sender, e);
            }
        }
    }
}
