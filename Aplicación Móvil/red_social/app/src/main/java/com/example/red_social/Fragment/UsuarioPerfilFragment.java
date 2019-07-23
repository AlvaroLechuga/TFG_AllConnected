package com.example.red_social.Fragment;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.red_social.R;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UsuarioPerfilFragment extends Fragment implements View.OnClickListener{

    ImageView imageProfile;
    Button btnEdit;
    TextView txtNombre, txtNick, txtDescripcion, txtSiguiendo, txtSeguidores, txtPublicaciones, txtLikes;

    int nPublicaciones, nLikes, nFollows, nFollowers;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario_perfil, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        String nombre = sharedPreferences.getString("name", "");
        String apellidos = sharedPreferences.getString("surname", "");
        String nick = sharedPreferences.getString("nick", "");
        String description = sharedPreferences.getString("description", "");
        String image = sharedPreferences.getString("image", "");
        int id = sharedPreferences.getInt("id", 0);

        imageProfile = view.findViewById(R.id.imagePProfile);
        btnEdit = view.findViewById(R.id.btnPPEdit);
        txtNombre = view.findViewById(R.id.txtPPNameSur);
        txtNick = view.findViewById(R.id.txtPPNick);
        txtDescripcion = view.findViewById(R.id.txtPPDescription);
        txtSeguidores = view.findViewById(R.id.txtPSeguidores);
        txtSiguiendo = view.findViewById(R.id.txtPSiguiendo);
        txtLikes = view.findViewById(R.id.txtPLikes);
        txtPublicaciones = view.findViewById(R.id.txtPPublicaciones);

        btnEdit.setOnClickListener(this);

        Global global = new Global();
        String url = global.url;

        Picasso.get()
                .load(url+"user/avatar/"+image)
                .resize(300, 300)
                .centerCrop()
                .into(imageProfile);

        txtNombre.setText(nombre+" "+apellidos);
        txtNick.setText("@"+nick);
        txtDescripcion.setText(description);

        SacarValores(id);

        return view;
    }

    private void SacarValores(int id) {
        SacarnPublicaciones(id);
        SacarnFollowers(id);
        SacarnFollows(id);
        SacarnLikes(id);
    }

    private void SacarnPublicaciones(int id) {

        Global global = new Global();
        String url = global.url;
        url = url+"numberpublications/"+id;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            nPublicaciones = reader.getInt("npublicaciones");
                            txtPublicaciones.setText(nPublicaciones+"");
                        } catch (JSONException e) { }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Error al realizar la publicación", Toast.LENGTH_LONG).show();
                    }
                }
        );

        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);

    }

    private void SacarnFollowers(int id) {

        Global global = new Global();
        String url = global.url;
        url = url+"numberfollowers/"+id;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            nFollowers = reader.getInt("nfollowers");
                            txtSeguidores.setText(nFollowers+"");
                        } catch (JSONException e) { }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Error al realizar la publicación", Toast.LENGTH_LONG).show();
                    }
                }
        );

        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);

    }

    private void SacarnFollows(int id) {

        Global global = new Global();
        String url = global.url;
        url = url+"numberfollows/"+id;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            nFollows = reader.getInt("nfollows");
                            txtSiguiendo.setText(nFollows+"");
                        } catch (JSONException e) { }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Error al realizar la publicación", Toast.LENGTH_LONG).show();
                    }
                }
        );

        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);

    }

    private void SacarnLikes(int id) {

        Global global = new Global();
        String url = global.url;
        url = url+"numberlikes/"+id;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            nLikes = reader.getInt("nlikes");
                            txtLikes.setText(nLikes+"");
                        } catch (JSONException e) { }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Error al realizar la publicación", Toast.LENGTH_LONG).show();
                    }
                }
        );

        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPPEdit:
                EditProfileFragment fragment = new EditProfileFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, fragment, "fragment_meters");
                ft.commit();
                break;
        }
    }
}
