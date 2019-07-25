package com.example.red_social.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.red_social.R;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Noticia;
import com.example.red_social.Util.Publicacion;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UsuarioPerfilFragment extends Fragment implements View.OnClickListener{

    ImageView imageProfile;
    Button btnEdit;
    TextView txtNombre, txtNick, txtDescripcion, txtSiguiendo, txtSeguidores, txtPublicaciones, txtLikes;

    String nombre, apellidos, nick, image, token;

    ListView listaPublicaciones;

    int nPublicaciones, nLikes, nFollows, nFollowers, id;

    ProgressDialog progreso;

    SharedPreferences sharedPreferences;

    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario_perfil, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        context = getContext();

        nombre = sharedPreferences.getString("name", "");
        apellidos = sharedPreferences.getString("surname", "");
        nick = sharedPreferences.getString("nick", "");
        String description = sharedPreferences.getString("description", "");
        image = sharedPreferences.getString("image", "");
        id = sharedPreferences.getInt("id", 0);
        token = sharedPreferences.getString("token", "");

        imageProfile = view.findViewById(R.id.imagePFind);
        btnEdit = view.findViewById(R.id.btnPPEdit);
        txtNombre = view.findViewById(R.id.txtPPNameSur);
        txtNick = view.findViewById(R.id.txtPPNick);
        txtDescripcion = view.findViewById(R.id.txtPPDescription);
        txtSeguidores = view.findViewById(R.id.txtPSeguidores);
        txtSiguiendo = view.findViewById(R.id.txtPSiguiendo);
        txtLikes = view.findViewById(R.id.txtPLikes);
        txtPublicaciones = view.findViewById(R.id.txtPPublicaciones);

        listaPublicaciones = view.findViewById(R.id.listaPublicaciones);

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
        SacarPublicaciones(id);
        SacarnPublicaciones(id);
        SacarnFollowers(id);
        SacarnFollows(id);
        SacarnLikes(id);
    }

    private void SacarPublicaciones(int id){
        Global global = new Global();
        String url = global.url;
        url = url+"publications/"+id;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObtenerPublicaciones(response);
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

    private void ObtenerPublicaciones(String publicationsJson) {
        Log.i("errorInsertado", publicationsJson);

        final List<Publicacion> publicaciones = new ArrayList<>();
        final List<String> tiempo = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(publicationsJson);
            JSONArray listPublications = reader.getJSONArray("publications");
            JSONArray listTime = reader.getJSONArray("tiempo");


            for(int i = 0; i < listPublications.length(); i++) {
                JSONObject publicationeObject = listPublications.getJSONObject(i);

                String response_id = (publicationeObject.isNull("response_id") ? "null" : ""+publicationeObject.getInt("response_id"));

                Publicacion publicacion = new Publicacion(
                        publicationeObject.getInt("id"),
                        publicationeObject.getInt("id_user"),
                        publicationeObject.getString("text"),
                        response_id,
                        ""
                );

                publicaciones.add(publicacion);

                String asd = listTime.getString(i);

                tiempo.add(asd);

            }

            listaPublicaciones.setAdapter(new PublicationsAdapter(getActivity(), R.layout.fragment_publication, publicaciones, tiempo));

        } catch (JSONException e) { Log.i("errorInsertado", "Error"); }

    }

    class PublicationsAdapter extends ArrayAdapter {

        private List<Publicacion> publicacions;
        private List<String> tiempo;

        public PublicationsAdapter(Context context, int resource, List<Publicacion> publicacions, List<String> tiempo) {
            super(context, resource, publicacions);
            this.publicacions = publicacions;
            this.tiempo = tiempo;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_publication,null);

            TextView txt1 = v.findViewById(R.id.txtPLInfo);
            txt1.setText(nombre+" "+apellidos+" - @"+nick+" - "+tiempo.get(position));

            TextView txt3 = v.findViewById(R.id.txtPLText);
            txt3.setText(publicacions.get(position).getText());

            ImageButton btnEliminar = v.findViewById(R.id.btnPLDelete);
            ImageButton btnResponse = v.findViewById(R.id.btnPLResponse);

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
                    dialogo1.setTitle("Eliminar Publicación");
                    dialogo1.setMessage("¿ Desea eliminar la publicación ?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            EliminarPublicacion(publicacions.get(position).getId());
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            dialogo1.cancel();
                        }
                    });
                    dialogo1.show();
                }
            });

            btnResponse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("response_publication", publicacions.get(position).getText());
                    editor.putInt("response_idPublication", publicacions.get(position).getId());
                    editor.commit();

                    ResposeFragment fragment = new ResposeFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.contenedor, fragment, "fragment_meters");
                    ft.commit();

                }
            });

            ImageView img = v.findViewById(R.id.imageProfileL);

            Global global = new Global();
            String url = global.url;

            Picasso.get()
                    .load(url+"user/avatar/"+image)
                    .resize(150, 150)
                    .centerCrop()
                    .into(img);

            return v;
        }
    }

    private void EliminarPublicacion(int idp) {

        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"publication/delete/"+idp;

        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progreso.hide();
                        Toast.makeText(getContext(), "Se ha eliminado correctamente", Toast.LENGTH_LONG).show();
                        SacarValores(id);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "No se ha pidido eliminar la publicación", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
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
