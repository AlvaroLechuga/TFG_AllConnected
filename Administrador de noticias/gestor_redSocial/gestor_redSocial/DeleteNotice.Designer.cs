namespace gestor_redSocial
{
	partial class DeleteNotice
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
            this.btnEliminarNoticia = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(23, 26);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(87, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Noticia a eliminar";
            // 
            // cbNoticias
            // 
            this.cbNoticias.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbNoticias.FormattingEnabled = true;
            this.cbNoticias.Location = new System.Drawing.Point(26, 42);
            this.cbNoticias.Name = "cbNoticias";
            this.cbNoticias.Size = new System.Drawing.Size(284, 21);
            this.cbNoticias.TabIndex = 1;
            this.cbNoticias.KeyDown += new System.Windows.Forms.KeyEventHandler(this.DeleteNotice_KeyDown);
            // 
            // btnEliminarNoticia
            // 
            this.btnEliminarNoticia.BackColor = System.Drawing.Color.Red;
            this.btnEliminarNoticia.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnEliminarNoticia.ForeColor = System.Drawing.Color.White;
            this.btnEliminarNoticia.Location = new System.Drawing.Point(235, 69);
            this.btnEliminarNoticia.Name = "btnEliminarNoticia";
            this.btnEliminarNoticia.Size = new System.Drawing.Size(75, 36);
            this.btnEliminarNoticia.TabIndex = 2;
            this.btnEliminarNoticia.Text = "Eliminar";
            this.btnEliminarNoticia.UseVisualStyleBackColor = false;
            this.btnEliminarNoticia.Click += new System.EventHandler(this.btnEliminarNoticia_Click);
            // 
            // DeleteNotice
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Indigo;
            this.ClientSize = new System.Drawing.Size(343, 117);
            this.Controls.Add(this.btnEliminarNoticia);
            this.Controls.Add(this.cbNoticias);
            this.Controls.Add(this.label1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "DeleteNotice";
            this.Text = "Eliminar Noticia";
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.DeleteNotice_KeyDown);
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.ComboBox cbNoticias;
		private System.Windows.Forms.Button btnEliminarNoticia;
	}
}