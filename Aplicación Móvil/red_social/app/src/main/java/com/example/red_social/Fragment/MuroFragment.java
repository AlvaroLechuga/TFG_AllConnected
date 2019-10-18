package com.example.red_social.Fragment;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.red_social.R;
import com.example.red_social.Util.CircleTransform;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Publicacion;
import com.example.red_social.Util.Usuario;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MuroFragment extends Fragment {

    ListView publicacionesMuro;

    int identificador;
    String token;

    ProgressDialog progreso;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muro, container, false);

        publicacionesMuro = view.findViewById(R.id.publicacionesMuro);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        identificador = sharedPreferences.getInt("id", 0);
        token = sharedPreferences.getString("token", "");

        SacarPublicaciones();

        return view;
    }

    private void SacarPublicaciones() {
        Global global = new Global();
        String url = global.url;
        url = url+"publicationfollowers/"+identificador;

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
                        Toast.makeText(getContext(), "No se encuentran las publicaciones", Toast.LENGTH_LONG).show();
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

    private void ObtenerPublicaciones(String publicationsJson) {

        final List<Publicacion> publicaciones = new ArrayList<>();
        final List<Usuario> usuarios = new ArrayList<>();
        final List<String> tiempo = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(publicationsJson);
            JSONArray listPublications = reader.getJSONArray("publications");
            JSONArray listUsers = reader.getJSONArray("users");
            JSONArray listTime = reader.getJSONArray("tiempo");

            for(int i = 0; i < listPublications.length(); i++) {
                JSONObject publicationeObject = listPublications.getJSONObject(i);

                String usuariosJson = listUsers.get(i).toString().substring(1, listUsers.get(i).toString().length() - 1);

                JSONObject userObject = new JSONObject(usuariosJson);

                String response_id = (publicationeObject.isNull("response_id") ? "null" : ""+publicationeObject.getInt("response_id"));

                Publicacion publicacion = new Publicacion(
                        publicationeObject.getInt("id"),
                        publicationeObject.getInt("id_user"),
                        publicationeObject.getString("text"),
                        response_id,
                        ""
                );

                publicaciones.add(publicacion);

                Usuario usuario = new Usuario(
                        userObject.getInt("id"),
                        userObject.getString("name"),
                        userObject.getString("surname"),
                        userObject.getString("direction"),
                        userObject.getString("country"),
                        userObject.getString("birthday"),
                        userObject.getString("nick"),
                        userObject.getString("email"),
                        "",
                        userObject.getString("image"),
                        userObject.getString("description"));

                usuarios.add(usuario);

                String asd = listTime.getString(i);

                tiempo.add(asd);

            }

            publicacionesMuro.setAdapter(new PublicationsAdapter(getActivity(), R.layout.fragment_publication, publicaciones, usuarios, tiempo));

        } catch (JSONException e) { Log.i("errorInsertado", "Error"); }
    }

    class PublicationsAdapter extends ArrayAdapter {

        private List<Publicacion> publicacions;
        private List<Usuario> usuarios;
        private List<String> tiempo;

        public PublicationsAdapter(Context context, int resource, List<Publicacion> publicacions, List<Usuario> usuarios, List<String> tiempo) {
            super(context, resource, publicacions);
            this.publicacions = publicacions;
            this.usuarios = usuarios;
            this.tiempo = tiempo;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_publication,null);

            TextView txt1 = v.findViewById(R.id.txtPLInfo);
            txt1.setText(usuarios.get(position).getName()+" "+usuarios.get(position).getSurname()+" - @"+usuarios.get(position).getNick()+" - "+tiempo.get(position));

            TextView txt3 = v.findViewById(R.id.txtPLText);
            txt3.setText(publicacions.get(position).getText());

            ImageButton btnLike = v.findViewById(R.id.btnPLLike);

            btnLike.setVisibility(View.GONE);

            ImageButton btnEliminar = v.findViewById(R.id.btnPLDelete);
            if(identificador != publicacions.get(position).getId_user()){
                btnEliminar.setVisibility(View.GONE);
            }

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EliminarPublicacion(publicacions.get(position).getId());
                }
            });

            ImageButton btnResponse = v.findViewById(R.id.btnPLResponse);

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
                    .load(url+"user/avatar/"+usuarios.get(position).getImage())
                    .resize(150, 150)
                    .centerCrop()
                    .transform(new CircleTransform())
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
                        SacarPublicaciones();
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
}