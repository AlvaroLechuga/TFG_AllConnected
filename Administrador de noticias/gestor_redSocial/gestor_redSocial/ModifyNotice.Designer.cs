namespace gestor_redSocial
{
	partial class ModifyNotice
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label1 = new System.Windows.Forms.Label();
			this.cbNoticias = new System.Windows.Forms.ComboBox();
			this.label2 = new System.Windows.Forms.Label();
			this.txtIdNoticia = new System.Windows.Forms.TextBox();
			this.label3 = new System.Windows.Forms.Label();
			this.txtTitulo = new System.Windows.Forms.TextBox();
			this.label4 = new System.Windows.Forms.Label();
			this.cbCategoria = new System.Windows.Forms.ComboBox();
			this.label5 = new System.Windows.Forms.Label();
			this.txtTexto = new System.Windows.Forms.TextBox();
			this.label6 = new System.Windows.Forms.Label();
			this.txtImage = new System.Windows.Forms.TextBox();
			this.label7 = new System.Windows.Forms.Label();
			this.txtId_user = new System.Windows.Forms.TextBox();
			this.btnModificar = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(34, 26);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(115, 13);
			this.label1.TabIndex = 0;
			this.label1.Text = "Selecciona una noticia";
			// 
			// cbNoticias
			// 
			this.cbNoticias.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.cbNoticias.FormattingEnabled = true;
			this.cbNoticias.Location = new System.Drawing.Point(37, 42);
			this.cbNoticias.Name = "cbNoticias";
			this.cbNoticias.Size = new System.Drawing.Size(406, 21);
			this.cbNoticias.TabIndex = 1;
			this.cbNoticias.SelectedIndexChanged += new System.EventHandler(this.cbNoticias_SelectedIndexChanged);
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(34, 80);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(16, 13);
			this.label2.TabIndex = 2;
			this.label2.Text = "Id";
			// 
			// txtIdNoticia
			// 
			this.txtIdNoticia.Enabled = false;
			this.txtIdNoticia.Location = new System.Drawing.Point(37, 96);
			this.txtIdNoticia.Name = "txtIdNoticia";
			this.txtIdNoticia.Size = new System.Drawing.Size(53, 20);
			this.txtIdNoticia.TabIndex = 3;
			// 
			// label3
			// 
			this.label3.AutoSize = true;
			this.label3.Location = new System.Drawing.Point(34, 119);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(33, 13);
			this.label3.TabIndex = 4;
			this.label3.Text = "Titulo";
			// 
			// txtTitulo
			// 
			this.txtTitulo.Location = new System.Drawing.Point(37, 135);
			this.txtTitulo.MaxLength = 25;
			this.txtTitulo.Name = "txtTitulo";
			this.txtTitulo.Size = new System.Drawing.Size(406, 20);
			this.txtTitulo.TabIndex = 5;
			// 
			// label4
			// 
			this.label4.AutoSize = true;
			this.label4.Location = new System.Drawing.Point(36, 158);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(54, 13);
			this.label4.TabIndex = 6;
			this.label4.Text = "Categoría";
			// 
			// cbCategoria
			// 
			this.cbCategoria.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
			this.cbCategoria.FormattingEnabled = true;
			this.cbCategoria.Location = new System.Drawing.Point(37, 174);
			this.cbCategoria.Name = "cbCategoria";
			this.cbCategoria.Size = new System.Drawing.Size(247, 21);
			this.cbCategoria.TabIndex = 7;
			// 
			// label5
			// 
			this.label5.AutoSize = true;
			this.label5.Location = new System.Drawing.Point(36, 198);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(40, 13);
			this.label5.TabIndex = 8;
			this.label5.Text = "Noticia";
			// 
			// txtTexto
			// 
			this.txtTexto.Location = new System.Drawing.Point(37, 214);
			this.txtTexto.MaxLength = 255;
			this.txtTexto.Multiline = true;
			this.txtTexto.Name = "txtTexto";
			this.txtTexto.Size = new System.Drawing.Size(264, 107);
			this.txtTexto.TabIndex = 9;
			// 
			// label6
			// 
			this.label6.AutoSize = true;
			this.label6.Location = new System.Drawing.Point(36, 324);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(42, 13);
			this.label6.TabIndex = 10;
			this.label6.Text = "Imagen";
			// 
			// txtImage
			// 
			this.txtImage.Location = new System.Drawing.Point(37, 340);
			this.txtImage.Name = "txtImage";
			this.txtImage.Size = new System.Drawing.Size(406, 20);
			this.txtImage.TabIndex = 11;
			// 
			// label7
			// 
			this.label7.AutoSize = true;
			this.label7.Location = new System.Drawing.Point(36, 363);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(68, 13);
			this.label7.TabIndex = 12;
			this.label7.Text = "Id propietario";
			// 
			// txtId_user
			// 
			this.txtId_user.Enabled = false;
			this.txtId_user.Location = new System.Drawing.Point(39, 379);
			this.txtId_user.Name = "txtId_user";
			this.txtId_user.Size = new System.Drawing.Size(56, 20);
			this.txtId_user.TabIndex = 13;
			// 
			// btnModificar
			// 
			this.btnModificar.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this.btnModificar.Location = new System.Drawing.Point(363, 411);
			this.btnModificar.Name = "btnModificar";
			this.btnModificar.Size = new System.Drawing.Size(80, 23);
			this.btnModificar.TabIndex = 14;
			this.btnModificar.Text = "Mofificar";
			this.btnModificar.UseVisualStyleBackColor = true;
			this.btnModificar.Click += new System.EventHandler(this.btnModificar_Click);
			// 
			// ModifyNotice
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(477, 452);
			this.Controls.Add(this.btnModificar);
			this.Controls.Add(this.txtId_user);
			this.Controls.Add(this.label7);
			this.Controls.Add(this.txtImage);
			this.Controls.Add(this.label6);
			this.Controls.Add(this.txtTexto);
			this.Controls.Add(this.label5);
			this.Controls.Add(this.cbCategoria);
			this.Controls.Add(this.label4);
			this.Controls.Add(this.txtTitulo);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.txtIdNoticia);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.cbNoticias);
			this.Controls.Add(this.label1);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
			this.Name = "ModifyNotice";
			this.Text = "Modificar Noticia";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.ComboBox cbNoticias;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox txtIdNoticia;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.TextBox txtTitulo;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.ComboBox cbCategoria;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.TextBox txtTexto;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.TextBox txtImage;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.TextBox txtId_user;
		private System.Windows.Forms.Button btnModificar;
	}
}