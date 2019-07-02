using System;
using System.IO;
using System.Windows.Forms;

namespace gestor_redSocial
{
	public partial class Manager : Form
	{
		string nombre;
		StreamWriter ficheroEscritura;
		StreamReader ficheroLectura;

		public Manager()
		{
			InitializeComponent();

			ControlBox = false;

			ficheroLectura = File.OpenText("user");
			string linea = ficheroLectura.ReadLine();
			ficheroLectura.Close();

			int pos = linea.IndexOf('|');

			nombre = Convert.ToString(linea.Substring(pos+1)); ;

			txtBienvenida.Text += nombre+" seleccione una opción ";
		}

		private void btnLogout_Click(object sender, EventArgs e)
		{
			ficheroEscritura = File.CreateText("user");
			ficheroEscritura.WriteLine("");
			ficheroEscritura.Close();

			Form1 form1 = new Form1();
			form1.Estado(true);
			this.Close();
		}

		private void btnAddCategory_Click(object sender, EventArgs e)
		{
			AddCategory addCategory = new AddCategory();
			addCategory.ShowDialog();
		}

		private void btnAddNotice_Click(object sender, EventArgs e)
		{
			AddNotice addNotice = new AddNotice();
			addNotice.ShowDialog();
		}

		private void btnModifyNotice_Click(object sender, EventArgs e)
		{
			ModifyNotice modifyNotice = new ModifyNotice();
			modifyNotice.ShowDialog();
		}

		private void btnDeleteNotice_Click(object sender, EventArgs e)
		{
			DeleteNotice deleteNotice = new DeleteNotice();
			deleteNotice.ShowDialog();
		}
	}
}
