namespace gestor_redSocial
{
	partial class AddCategory
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
            this.txtCategoria = new System.Windows.Forms.TextBox();
            this.btnInsertarCategoria = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(34, 26);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(120, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Nombre de la Categoría";
            // 
            // txtCategoria
            // 
            this.txtCategoria.Location = new System.Drawing.Point(37, 42);
            this.txtCategoria.Name = "txtCategoria";
            this.txtCategoria.Size = new System.Drawing.Size(177, 20);
            this.txtCategoria.TabIndex = 1;
            this.txtCategoria.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddCategory_KeyDown);
            // 
            // btnInsertarCategoria
            // 
            this.btnInsertarCategoria.BackColor = System.Drawing.Color.Green;
            this.btnInsertarCategoria.Font = new System.Drawing.Font("Verdana", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnInsertarCategoria.ForeColor = System.Drawing.Color.White;
            this.btnInsertarCategoria.Location = new System.Drawing.Point(139, 68);
            this.btnInsertarCategoria.Name = "btnInsertarCategoria";
            this.btnInsertarCategoria.Size = new System.Drawing.Size(75, 37);
            this.btnInsertarCategoria.TabIndex = 2;
            this.btnInsertarCategoria.Text = "Insertar";
            this.btnInsertarCategoria.UseVisualStyleBackColor = false;
            this.btnInsertarCategoria.Click += new System.EventHandler(this.btnInsertarCategoria_Click);
            // 
            // AddCategory
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.Indigo;
            this.ClientSize = new System.Drawing.Size(245, 117);
            this.Controls.Add(this.btnInsertarCategoria);
            this.Controls.Add(this.txtCategoria);
            this.Controls.Add(this.label1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "AddCategory";
            this.Text = "Añadir Categoría";
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.AddCategory_KeyDown);
            this.ResumeLayout(false);
            this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox txtCategoria;
		private System.Windows.Forms.Button btnInsertarCategoria;
	}
}