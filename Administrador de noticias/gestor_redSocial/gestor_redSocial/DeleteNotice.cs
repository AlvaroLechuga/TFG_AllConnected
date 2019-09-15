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
	public partial class DeleteNotice : Form
	{
		BaseDatos b = new BaseDatos("SERVER=localhost;DATABASE=red_social;UID=root;PWD=''");
		List<noticie> listaNoticias;

		public DeleteNotice()
		{
			InitializeComponent();

			b.Conectar();

			listaNoticias = b.ListaPublicaciones();

			foreach (noticie noticie in listaNoticias)
			{
				cbNoticias.Items.Add(noticie.Title);
			}

			b.Desconectar();
		}

		private void btnEliminarNoticia_Click(object sender, EventArgs e)
		{
			if(cbNoticias.SelectedIndex == -1)
			{
				MessageBox.Show("Selecciona una noticia", "Error valor", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
			else
			{
				DialogResult result = MessageBox.Show("¿Estás seguro de eliminar la noticia? No podrá recuperarse", "Eliminar noticia", MessageBoxButtons.YesNoCancel, MessageBoxIcon.Exclamation);

				switch (result)
				{
					case DialogResult.Yes:
						b.Conectar();

						if (b.EliminarPublicacion(listaNoticias[cbNoticias.SelectedIndex].Id))
						{
							MessageBox.Show("Se ha eliminado la noticia correctamente", "Eliminación correcta", MessageBoxButtons.OK, MessageBoxIcon.Information);

							cbNoticias.Items.Clear();

							listaNoticias = b.ListaPublicaciones();

							foreach (noticie noticie in listaNoticias)
							{
								cbNoticias.Items.Add(noticie.Title);
							}

						}
						else
						{
							MessageBox.Show("A ocurrido un error al borrar la noticia", "Eliminación incorrecta", MessageBoxButtons.OK, MessageBoxIcon.Error);
						}

						break;
					default:
						MessageBox.Show("Operación cancelada");
						break;
				}

				b.Desconectar();
			}
		}

        private void DeleteNotice_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyValue == 13)
            {
                btnEliminarNoticia_Click(sender, e);
            }
        }
    }
}
