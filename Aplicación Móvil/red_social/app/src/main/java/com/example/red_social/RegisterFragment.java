package com.example.red_social;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterFragment extends Fragment implements View.OnClickListener {


    EditText txtNombre;
    EditText txtApellidos;
    EditText txtDireccion;
    EditText txtPais;
    EditText txtCumpleanos;
    EditText txtNick;
    EditText txtEmail;
    EditText txtPassword;

    Button btnRegistrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_register, container, false);

        txtNombre = view.findViewById(R.id.txtRNombre);
        txtApellidos = view.findViewById(R.id.txtRApellidos);
        txtDireccion = view.findViewById(R.id.txtRDireccion);
        txtPais = view.findViewById(R.id.txtRPais);
        txtCumpleanos = view.findViewById(R.id.txtRCumpleanos);
        txtNick = view.findViewById(R.id.txtRNick);
        txtEmail = view.findViewById(R.id.txtLPassword);
        txtPassword = view.findViewById(R.id.txtLPassword);


        btnRegistrar = view.findViewById(R.id.btnLLogin);
        btnRegistrar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLLogin:
                RegisterUser();
                break;
        }
    }

    private void RegisterUser() {

        String nombre = txtNombre.getText().toString();
        String apellidos = txtApellidos.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String pais = txtPais.getText().toString();
        String cumpleanos = txtCumpleanos.getText().toString();
        String nick = txtNick.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(nombre.equals("") || apellidos.equals("") || direccion.equals("") || direccion.equals("") || pais.equals("") || cumpleanos.equals("") || nick.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(getContext(), "Rellene todos los campos", Toast.LENGTH_LONG).show();
        }

    }
}
