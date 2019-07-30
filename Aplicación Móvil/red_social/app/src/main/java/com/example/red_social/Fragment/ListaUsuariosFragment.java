package com.example.red_social.Fragment;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.red_social.R;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Usuario;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuariosFragment extends Fragment {

    ListView listaUsuarios;
    int identificador;
    String nick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_usuarios, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        String usuariosJson = sharedPreferences.getString("listausuarios", "");
        identificador = sharedPreferences.getInt("id", 0);
        nick = sharedPreferences.getString("nick", "");

        final List<Usuario> usuarios = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(usuariosJson);
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

        listaUsuarios = view.findViewById(R.id.listausuarios);

        listaUsuarios.setAdapter(new UsuariosAdapter(getActivity(),R.layout.fragment_user, usuarios));

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!nick.equals("")){
                    if(usuarios.get(position).getId() != identificador){
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("finduser_name", usuarios.get(position).getName());
                        editor.putString("finduser_surname", usuarios.get(position).getSurname());
                        editor.putString("finduser_nick", usuarios.get(position).getNick());
                        editor.putString("finduser_descripcion", usuarios.get(position).getDescription());
                        editor.putString("finduser_image", usuarios.get(position).getImage());
                        editor.putInt("finduser_id", usuarios.get(position).getId());
                        editor.commit();


                        UserfFragment fragment = new UserfFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contenedor, fragment, "fragment_meters");
                        ft.commit();
                    }else{
                        UsuarioPerfilFragment fragment = new UsuarioPerfilFragment();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.contenedor, fragment, "fragment_meters");
                        ft.commit();
                    }
                }else{
                    Toast.makeText(getContext(), "Inicia sesión para ver el perfil", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
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
