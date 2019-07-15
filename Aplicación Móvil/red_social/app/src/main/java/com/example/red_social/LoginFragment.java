package com.example.red_social;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment implements View.OnClickListener {


    EditText txtEmail;
    EditText txtPassword;

    Button btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtEmail = view.findViewById(R.id.txtLEmail);
        txtPassword = view.findViewById(R.id.txtLPassword);
        btnLogin = view.findViewById(R.id.btnLLogin);
        btnLogin.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLLogin:
                IniciarSesion();
                break;
        }
    }

    private void IniciarSesion() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}
