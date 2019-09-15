namespace gestor_redSocial
{
	partial class AddNotice
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
            this.txtTitulo = new System.Windows.Forms.TextBox();
            this.cbCategoria = new System.Windows.Forms.ComboBox();
            this.txtTexto = new System.Windows.Forms.RichTextBox();
            this.txtImage = new System.Windows.Forms.TextBox();
            this.btnInsertarNoticia = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // txtTitulo
            // 
            this.txtTitulo.Location = new System.Drawing.Point(37, 42);
            this.txtTitulo.MaxLength = 25;
            this.txtTitulo.Name = "txtTitulo";
            this.txtTitulo.Size = new System.Drawing.Size(264, 20);
            this.txtTitulo.TabIndex = 0;
            this.txtTitulo.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddNotice_KeyDown);
            // 
            // cbCategoria
            // 
            this.cbCategoria.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbCategoria.FormattingEnabled = true;
            this.cbCategoria.Location = new System.Drawing.Point(37, 81);
            this.cbCategoria.Name = "cbCategoria";
            this.cbCategoria.Size = new System.Drawing.Size(264, 21);
            this.cbCategoria.TabIndex = 1;
            this.cbCategoria.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddNotice_KeyDown);
            // 
            // txtTexto
            // 
            this.txtTexto.Location = new System.Drawing.Point(37, 121);
            this.txtTexto.MaxLength = 255;
            this.txtTexto.Name = "txtTexto";
            this.txtTexto.Size = new System.Drawing.Size(264, 107);
            this.txtTexto.TabIndex = 2;
            this.txtTexto.Text = "";
            this.txtTexto.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddNotice_KeyDown);
            // 
            // txtImage
            // 
            this.txtImage.Location = new System.Drawing.Point(37, 247);
            this.txtImage.Name = "txtImage";
            this.txtImage.Size = new System.Drawing.Size(264, 20);
            this.txtImage.TabIndex = 3;
            this.txtImage.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddNotice_KeyDown);
            // 
            // btnInsertarNoticia
            // 
            this.btnInsertarNoticia.BackColor = System.Drawing.Color.Green;
            this.btnInsertarNoticia.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnInsertarNoticia.ForeColor = System.Drawing.Color.White;
            this.btnInsertarNoticia.Location = new System.Drawing.Point(226, 273);
            this.btnInsertarNoticia.Name = "btnInsertarNoticia";
            this.btnInsertarNoticia.Size = new System.Drawing.Size(75, 39);
            this.btnInsertarNoticia.TabIndex = 4;
            this.btnInsertarNoticia.Text = "Insertar";
            this.btnInsertarNoticia.UseVisualStyleBackColor = false;
            this.btnInsertarNoticia.Click += new System.EventHandler(this.btnInsertarNoticia_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(34, 26);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(33, 13);
            this.label1.TabIndex = 5;
            this.label1.Text = "Titulo";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.ForeColor = System.Drawing.Color.White;
            this.label2.Location = new System.Drawing.Point(34, 65);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(54, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Categoría";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.ForeColor = System.Drawing.Color.White;
            this.label3.Location = new System.Drawing.Point(34, 105);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(55, 13);
            this.label3.TabIndex = 7;
            this.label3.Text = "Contenido";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.ForeColor = System.Drawing.Color.White;
            this.label4.Location = new System.Drawing.Point(34, 231);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(42, 13);
            this.label4.TabIndex = 8;
            this.label4.Text = "Imagen";
            // 
            // AddNotice
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Indigo;
            this.ClientSize = new System.Drawing.Size(352, 334);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnInsertarNoticia);
            this.Controls.Add(this.txtImage);
            this.Controls.Add(this.txtTexto);
            this.Controls.Add(this.cbCategoria);
            this.Controls.Add(this.txtTitulo);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "AddNotice";
            this.Text = "Añadir Noticia";
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddNotice_KeyDown);
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.TextBox txtTitulo;
		private System.Windows.Forms.ComboBox cbCategoria;
		private System.Windows.Forms.RichTextBox txtTexto;
		private System.Windows.Forms.TextBox txtImage;
		private System.Windows.Forms.Button btnInsertarNoticia;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
	}
}