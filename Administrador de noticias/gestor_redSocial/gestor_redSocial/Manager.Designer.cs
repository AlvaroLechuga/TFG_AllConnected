namespace gestor_redSocial
{
	partial class Manager
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
            this.btnLogout = new System.Windows.Forms.Button();
            this.txtBienvenida = new System.Windows.Forms.Label();
            this.btnAddCategory = new System.Windows.Forms.Button();
            this.btnAddNotice = new System.Windows.Forms.Button();
            this.btnModifyNotice = new System.Windows.Forms.Button();
            this.btnDeleteNotice = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnLogout
            // 
            this.btnLogout.BackColor = System.Drawing.Color.Red;
            this.btnLogout.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnLogout.ForeColor = System.Drawing.Color.White;
            this.btnLogout.Location = new System.Drawing.Point(702, 12);
            this.btnLogout.Name = "btnLogout";
            this.btnLogout.Size = new System.Drawing.Size(86, 39);
            this.btnLogout.TabIndex = 0;
            this.btnLogout.Text = "Logout";
            this.btnLogout.UseVisualStyleBackColor = false;
            this.btnLogout.Click += new System.EventHandler(this.btnLogout_Click);
            // 
            // txtBienvenida
            // 
            this.txtBienvenida.AutoSize = true;
            this.txtBienvenida.Font = new System.Drawing.Font("Palatino Linotype", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtBienvenida.ForeColor = System.Drawing.Color.White;
            this.txtBienvenida.Location = new System.Drawing.Point(13, 12);
            this.txtBienvenida.Name = "txtBienvenida";
            this.txtBienvenida.Size = new System.Drawing.Size(98, 21);
            this.txtBienvenida.TabIndex = 2;
            this.txtBienvenida.Text = "Bienvenido: ";
            // 
            // btnAddCategory
            // 
            this.btnAddCategory.BackColor = System.Drawing.Color.Green;
            this.btnAddCategory.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnAddCategory.ForeColor = System.Drawing.Color.White;
            this.btnAddCategory.Location = new System.Drawing.Point(314, 65);
            this.btnAddCategory.Name = "btnAddCategory";
            this.btnAddCategory.Size = new System.Drawing.Size(181, 78);
            this.btnAddCategory.TabIndex = 3;
            this.btnAddCategory.Text = "Añadir Categoría";
            this.btnAddCategory.UseVisualStyleBackColor = false;
            this.btnAddCategory.Click += new System.EventHandler(this.btnAddCategory_Click);
            // 
            // btnAddNotice
            // 
            this.btnAddNotice.BackColor = System.Drawing.Color.Green;
            this.btnAddNotice.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Bold);
            this.btnAddNotice.ForeColor = System.Drawing.Color.White;
            this.btnAddNotice.Location = new System.Drawing.Point(314, 149);
            this.btnAddNotice.Name = "btnAddNotice";
            this.btnAddNotice.Size = new System.Drawing.Size(181, 78);
            this.btnAddNotice.TabIndex = 4;
            this.btnAddNotice.Text = "Añadir Noticia";
            this.btnAddNotice.UseVisualStyleBackColor = false;
            this.btnAddNotice.Click += new System.EventHandler(this.btnAddNotice_Click);
            // 
            // btnModifyNotice
            // 
            this.btnModifyNotice.BackColor = System.Drawing.Color.Gold;
            this.btnModifyNotice.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Bold);
            this.btnModifyNotice.ForeColor = System.Drawing.Color.White;
            this.btnModifyNotice.Location = new System.Drawing.Point(314, 233);
            this.btnModifyNotice.Name = "btnModifyNotice";
            this.btnModifyNotice.Size = new System.Drawing.Size(181, 78);
            this.btnModifyNotice.TabIndex = 5;
            this.btnModifyNotice.Text = "Modificar Noticia";
            this.btnModifyNotice.UseVisualStyleBackColor = false;
            this.btnModifyNotice.Click += new System.EventHandler(this.btnModifyNotice_Click);
            // 
            // btnDeleteNotice
            // 
            this.btnDeleteNotice.BackColor = System.Drawing.Color.Red;
            this.btnDeleteNotice.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Bold);
            this.btnDeleteNotice.ForeColor = System.Drawing.Color.White;
            this.btnDeleteNotice.Location = new System.Drawing.Point(314, 317);
            this.btnDeleteNotice.Name = "btnDeleteNotice";
            this.btnDeleteNotice.Size = new System.Drawing.Size(181, 78);
            this.btnDeleteNotice.TabIndex = 6;
            this.btnDeleteNotice.Text = "Eliminar Noticia";
            this.btnDeleteNotice.UseVisualStyleBackColor = false;
            this.btnDeleteNotice.Click += new System.EventHandler(this.btnDeleteNotice_Click);
            // 
            // Manager
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Indigo;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(800, 423);
            this.Controls.Add(this.btnDeleteNotice);
            this.Controls.Add(this.btnModifyNotice);
            this.Controls.Add(this.btnAddNotice);
            this.Controls.Add(this.btnAddCategory);
            this.Controls.Add(this.txtBienvenida);
            this.Controls.Add(this.btnLogout);
            this.DoubleBuffered = true;
            this.Name = "Manager";
            this.Text = "Manager";
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Button btnLogout;
		private System.Windows.Forms.Label txtBienvenida;
		private System.Windows.Forms.Button btnAddCategory;
		private System.Windows.Forms.Button btnAddNotice;
		private System.Windows.Forms.Button btnModifyNotice;
		private System.Windows.Forms.Button btnDeleteNotice;
	}
}