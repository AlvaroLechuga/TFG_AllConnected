using gestor_redSocial.Recursos;
using System;
using System.IO;
using System.Windows.Forms;

namespace gestor_redSocial
{
	public partial class Form1 : Form
	{
		BaseDatos b = new BaseDatos("SERVER=localhost;DATABASE=red_social;UID=root;PWD=''");
		StreamWriter ficheroEscritura;

		public Form1()
		{
			InitializeComponent();
		}

		private void btnLogin_Click(object sender, EventArgs e)
		{
			string usuario = txtUser.Text.ToString();
			string password = txtPassword.Text.ToString();

			if(usuario == "" || password == "")
			{
				MessageBox.Show("Introduzca usuario y contraseña por favor", "Error de login", MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
			else
			{
				b.Conectar();

				user u = b.ComprobarUsuario(usuario, password);



				if(u.Nombre != "")
				{
					MessageBox.Show("Bienvenido: "+u.Nombre);
					b.Desconectar();

					ficheroEscritura = File.CreateText("user");
					ficheroEscritura.WriteLine(u.Id+"|"+u.Nombre);
					ficheroEscritura.Close();

					Manager manager = new Manager();
					manager.Show();
					this.Hide();
				}
				else
				{
					MessageBox.Show("Ha ocurrido un error al hacer login", "Error de login", MessageBoxButtons.OK, MessageBoxIcon.Error);
					b.Desconectar();
				}

			}

		}

		private void btnExit_Click(object sender, EventArgs e)
		{
			ficheroEscritura = File.CreateText("user");
			ficheroEscritura.WriteLine("");
			ficheroEscritura.Close();
			Application.Exit();
		}

		private void Form1_FormClosing(object sender, FormClosingEventArgs e)
		{
			ficheroEscritura = File.CreateText("user");
			ficheroEscritura.WriteLine("");
			ficheroEscritura.Close();
			Application.Exit();
		}

		public void Estado(bool estado)
		{
			if (!estado)
			{
				this.Hide();
			}
			else
			{
				this.Show();
			}
		}

        private void Form1_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.KeyValue == 13)
            {
                btnLogin_Click(sender, e);
            }
        }
    }
}
