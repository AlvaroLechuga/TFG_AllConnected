package com.example.red_social.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment implements View.OnClickListener {


    EditText txtEmail, txtPassword;
    TextView txtCrear;
    Button btnLogin;

    ProgressDialog progreso;
    RequestQueue request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtEmail = view.findViewById(R.id.txtLEmail);
        txtPassword = view.findViewById(R.id.txtLPassword);

        txtCrear = view.findViewById(R.id.txtLRegistrar);

        btnLogin = view.findViewById(R.id.btnLLogin);

        btnLogin.setOnClickListener(this);
        txtCrear.setOnClickListener(this);

        request = Volley.newRequestQueue(getContext());

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLLogin:
                IniciarSesion();
                break;
            case R.id.txtLRegistrar:
                CrearCuenta();
                break;
        }
    }



    private void IniciarSesion() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }else{
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setPassword(password);

            Inicio(usuario);
        }
    }

    private void Inicio(final Usuario usuario) {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"login";

        Gson gson = new Gson();
        final String json = gson.toJson(usuario);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SacarUsuario(response, usuario);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
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

    private void SacarUsuario(final String token, Usuario usuario) {
        Global global = new Global();
        String url = global.url;
        url = url+"login";

        usuario.setGetToken(token);

        Gson gson = new Gson();
        final String json = gson.toJson(usuario);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtEmail.setText("");
                        txtPassword.setText("");

                        String email = "", name = "", surname = "", nick = "", image = "", description = "", country = "", direction = "";
                        int id = 0;

                        try {
                            JSONObject reader = new JSONObject(response);
                            email = reader.getString("email");
                            name = reader.getString("name");
                            surname = reader.getString("surname");
                            nick = reader.getString("nick");
                            id = reader.getInt("sub");
                            image = reader.getString("image");
                            description = reader.getString("description");
                            direction = reader.getString("direction");
                            country = reader.getString("country");

                        } catch (JSONException e) { }

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("token", token);
                        editor.putString("email", email);
                        editor.putString("name", name);
                        editor.putString("surname", surname);
                        editor.putString("nick", nick);
                        editor.putInt("id", id);
                        editor.putString("image", image);
                        editor.putString("description", description);
                        editor.putString("direction", direction);
                        editor.putString("country", country);
                        editor.commit();

                        progreso.hide();

                        IndexFragment fragment = new IndexFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contenedor, fragment, "fragment_meters");
                        ft.commit();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
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

    private void CrearCuenta() {
        RegisterFragment fragment = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contenedor, fragment, "fragment_meters");
        ft.commit();
    }
}
