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
	public partial class ModifyNotice : Form
	{

		BaseDatos b = new BaseDatos("SERVER=localhost;DATABASE=red_social;UID=root;PWD=''");
		List<category> listaCategorias;
		List<noticie> listaNoticias;

		public ModifyNotice()
		{
			InitializeComponent();

			b.Conectar();

			listaCategorias = b.ListaCategorias();

			b.Desconectar();

			b.Conectar();

			listaNoticias = b.ListaPublicaciones();

			foreach (category categoria in listaCategorias)
			{
				cbCategoria.Items.Add(categoria.Name);
			}

			foreach (noticie noticie in listaNoticias)
			{
				cbNoticias.Items.Add(noticie.Title);
			}

			b.Desconectar();

		}

		private void btnModificar_Click(object sender, EventArgs e)
		{
			if(txtTitulo.Text == "" || txtTexto.Text == "" || txtImage.Text == "" || txtTitulo.Text == "" || txtId_user.Text == "")
			{
				MessageBox.Show("Completa todos los campos", "Campos incompletos", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
			else
			{

				noticie modificada = new noticie();
				modificada.Id = Convert.ToInt32(txtIdNoticia.Text);
				modificada.Title = txtTitulo.Text;
				modificada.Text = txtTexto.Text;
				modificada.Image = txtImage.Text;
				modificada.Category = listaCategorias[cbCategoria.SelectedIndex].Id;
				modificada.Id_user = Convert.ToInt32(txtId_user.Text);

				b.Conectar();

				if (b.ModificarPublicacion(modificada))
				{
					MessageBox.Show("Se ha modificado correctamente", "Modificación comletada", MessageBoxButtons.OK, MessageBoxIcon.Information);
					cbNoticias.Items.Clear();

					listaNoticias = b.ListaPublicaciones();

					foreach (noticie noticie in listaNoticias)
					{
						cbNoticias.Items.Add(noticie.Title);
					}

				}
				else
				{
					MessageBox.Show("Ha ocurrido un error al modificar", "Error al modificar", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}

				b.Desconectar();

				txtIdNoticia.Text = "";
				txtTitulo.Text = "";
				txtTexto.Text = "";
				txtImage.Text = "";
				txtId_user.Text = "";
				cbCategoria.SelectedIndex = -1;
			}
		}

		private void cbNoticias_SelectedIndexChanged(object sender, EventArgs e)
		{
			int valor = cbNoticias.SelectedIndex;

			txtIdNoticia.Text = Convert.ToString(listaNoticias[valor].Id);
			txtTitulo.Text = listaNoticias[valor].Title;
			txtTexto.Text = listaNoticias[valor].Text;
			txtImage.Text = listaNoticias[valor].Image;
			txtId_user.Text = Convert.ToString(listaNoticias[valor].Id_user);

			int codCategoria = listaNoticias[valor].Category;

			for(int i = 0; i < listaCategorias.Capacity; i++)
			{
				if(listaCategorias[i].Id == codCategoria)
				{
					cbCategoria.SelectedIndex = i;
					break;
				}
			}

			
		}

        private void ModifyNotice_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyValue == 13)
            {
                btnModificar_Click(sender, e);
            }
        }
    }
}
