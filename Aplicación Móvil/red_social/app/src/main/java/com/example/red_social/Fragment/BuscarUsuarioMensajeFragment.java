package com.example.red_social.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
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
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Usuario;
import com.example.red_social.Util.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BuscarUsuarioMensajeFragment extends Fragment implements View.OnClickListener {

    EditText txtUsuario;
    TextView txtBuscar;
    ListView listaBuscados;

    ProgressDialog progreso;

    final List<Usuario> usuarios = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar_usuario_mensaje, container, false);

        txtUsuario = view.findViewById(R.id.usuario);
        txtBuscar = view.findViewById(R.id.buscar);
        listaBuscados = view.findViewById(R.id.listaBuscados);

        txtBuscar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buscar:
                if(!txtUsuario.getText().toString().equals("")){
                    Busqueda(txtUsuario.getText().toString());
                }
                break;
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
                        MostrarUsuarios(response);
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

    private void MostrarUsuarios(String response) {
        try {
            JSONObject reader = new JSONObject(response);
            JSONArray listUsers =  reader.getJSONArray("users");

            for(int i = 0; i < listUsers.length(); i++){
                JSONObject userObject = listUsers.getJSONObject(i);
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
            }


        } catch (JSONException e) { Log.i("errorInsertado", "Error"); }

        listaBuscados.setAdapter(new UsuariosAdapter(getActivity(),R.layout.fragment_user, usuarios));

        listaBuscados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("id_chat_usuario", usuarios.get(position).getId());
                editor.putString("datos_chat_usuario", usuarios.get(position).getName()+" "+usuarios.get(position).getSurname());
                editor.commit();

                ListaChatFragment fragment = new ListaChatFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.contenedor, fragment, "fragment_meters");
                ft.commit();
            }
        });
    }

    class UsuariosAdapter extends ArrayAdapter {

        private List<Usuario> usuarios;

        public UsuariosAdapter(Context context, int resource, List<Usuario> usuarios) {
            super(context, resource, usuarios);
            this.usuarios = usuarios;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_user,null);
            TextView txt1 = v.findViewById(R.id.txtUserFind);
            txt1.setText(usuarios.get(position).getName()+" "+usuarios.get(position).getSurname());

            TextView txt2 = v.findViewById(R.id.txtNickFind);
            txt2.setText("@"+usuarios.get(position).getNick());

            ImageView img = v.findViewById(R.id.imageUserFind);

            Global global = new Global();
            String url = global.url;

            Picasso.get()
                    .load(url+"user/avatar/"+usuarios.get(position).getImage())
                    .resize(300, 300)
                    .centerCrop()
                    .into(img);

            return v;
        }
    }
}
