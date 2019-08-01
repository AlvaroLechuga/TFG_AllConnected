package com.example.red_social.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.example.red_social.Util.Publicacion;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class BuscarFragment extends Fragment implements View.OnClickListener{

    ProgressDialog progreso;

    Button btnBuscar;
    EditText txtUsuario;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        btnBuscar = view.findViewById(R.id.btnBBuscar);
        txtUsuario = view.findViewById(R.id.txtBUsuario);

        btnBuscar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBBuscar:
                BuscarUsuario();
                break;
        }
    }

    private void BuscarUsuario() {
        String datos = txtUsuario.getText().toString();

        if(datos.equals("")){
            Toast.makeText(getContext(), "No has insertado nada para buscar", Toast.LENGTH_LONG).show();
        }else{
            Busqueda(datos);
        }
    }

    private void Busqueda(String datos) {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"user/search/"+datos;


        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progreso.hide();

                        if(response.equals("{\"status\":\"success\",\"code\":\"200\",\"users\":[]}")){
                            Toast.makeText(getContext(), "No hay usuarios", Toast.LENGTH_LONG).show();
                        }else{

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("listausuarios", response);
                            editor.commit();

                            ListaUsuariosFragment fragment = new ListaUsuariosFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.contenedor, fragment, "fragment_meters");
                            ft.commit();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Ha ocurrido un error al insertar", Toast.LENGTH_LONG).show();
                    }
                });

        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);
    }
}
