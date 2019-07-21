package com.example.red_social.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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

public class PublicateFragment extends Fragment implements View.OnClickListener {

    Button btnPublicar;
    EditText txtPublicacion;

    ProgressDialog progreso;
    RequestQueue request;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_publicate, container, false);

        btnPublicar = view.findViewById(R.id.btnPPublicate);
        txtPublicacion = view.findViewById(R.id.txtPPublication);

        btnPublicar.setOnClickListener(this);

        request = Volley.newRequestQueue(getContext());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPPublicate:
                RealizarPublicacion();
                break;
        }
    }

    private void RealizarPublicacion() {

        String texto = txtPublicacion.getText().toString();

        if(texto.equals("")){
            Toast.makeText(getContext(), "No has escrito ninguna publicación", Toast.LENGTH_LONG).show();
        }else{

            String token = sharedPreferences.getString("token", "");

            Publicacion publicacion1 = new Publicacion();
            publicacion1.setText(texto);

            Publicar(publicacion1, token);
        }

    }

    private void Publicar(Publicacion publicacion1, final String token) {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"publicate";

        Gson gson = new Gson();
        final String json = gson.toJson(publicacion1);

        Log.i("errorInsertado", json);
        Log.i("errorInsertado", url);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtPublicacion.setText("");
                        progreso.hide();
                        Toast.makeText(getContext(), "Se ha realizado la publicación", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Error al realizar la publicación", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("json", json);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", token);

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);
    }
}
