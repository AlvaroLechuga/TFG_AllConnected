package com.example.red_social.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.red_social.R;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Usuario;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment implements View.OnClickListener{


    EditText txtNombre, txtApellidos, txtDireccion, txtPais, txtCumpleanos, txtNick, txtEmail, txtPassword;
    TextView txtIniciar;
    CheckBox cbTerminos;
    Button btnRegistrar;
    ProgressDialog progreso;

    RequestQueue request;

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
        txtEmail = view.findViewById(R.id.txtLEmail);
        txtPassword = view.findViewById(R.id.txtLPassword);

        txtIniciar = view.findViewById(R.id.txtRIniciar);

        btnRegistrar = view.findViewById(R.id.btnLLogin);
        cbTerminos = view.findViewById(R.id.cbRTerminos);

        btnRegistrar.setOnClickListener(this);
        txtIniciar.setOnClickListener(this);

        request = Volley.newRequestQueue(getContext());

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLLogin:
                RegisterUser();
                break;
            case R.id.txtRIniciar:
                CambiarIniciar();
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

        boolean terminos = cbTerminos.isChecked();

        if(nombre.equals("") || apellidos.equals("") || direccion.equals("") || direccion.equals("") || pais.equals("") || cumpleanos.equals("") || nick.equals("") || email.equals("") || password.equals("") || !terminos){
            Toast.makeText(getContext(), "Rellene todos los campos", Toast.LENGTH_LONG).show();
        }else{
            Usuario usuario = new Usuario();

            usuario.setName(nombre);
            usuario.setSurname(apellidos);
            usuario.setDirection(direccion);
            usuario.setCountry(pais);
            usuario.setBirthday(cumpleanos);
            usuario.setNick(nick);
            usuario.setEmail(email);
            usuario.setPassword(password);

            RegistrarUsuario(usuario);
        }

    }

    public void RegistrarUsuario(Usuario usuario){
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"register";

        Gson gson = new Gson();
        final String json = gson.toJson(usuario);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progreso.hide();
                        Toast.makeText(getContext(), "Se ha registrado el usuario correctamente", Toast.LENGTH_LONG).show();
                        txtNombre.setText("");
                        txtApellidos.setText("");
                        txtDireccion.setText("");
                        txtPais.setText("");
                        txtCumpleanos.setText("");
                        txtNick.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Ha ocurrido un error al insertar", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("json", json);

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);
    }

    private void CambiarIniciar() {
        LoginFragment fragment = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, fragment, "fragment_meters");
        ft.commit();
    }
}
