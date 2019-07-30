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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.red_social.R;
import com.example.red_social.Util.CircleTransform;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Mensaje;
import com.example.red_social.Util.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ListaConversacionesFragment extends Fragment {

    ListView listaConversaciones;

    SharedPreferences sharedPreferences;

    int identificador;
    String nick;

    List<Mensaje> mensajes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_conversaciones, container, false);

        listaConversaciones = view.findViewById(R.id.listaConversaciones);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        identificador = sharedPreferences.getInt("id", 0);
        nick = sharedPreferences.getString("nick", "");

        SacarConversaciones();

        return view;
    }

    private void SacarConversaciones() {
        Global global = new Global();
        String url = global.url;
        url = url+"getmessages/"+identificador;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SacarConver(response);
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

    private void SacarConver(String conversacionJson){
        mensajes = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(conversacionJson);
            JSONArray listConversations = reader.getJSONArray("messages");

            for(int i = 0; i < listConversations.length(); i++){
                JSONObject conversationObject = listConversations.getJSONObject(i);

                Mensaje mensaje = new Mensaje(
                        conversationObject.getString("name_emmit"),
                        conversationObject.getString("surname_emmit"),
                        conversationObject.getString("nick_emmit"),
                        conversationObject.getString("name_recep"),
                        conversationObject.getString("surname_recep"),
                        conversationObject.getString("nick_recep"),
                        conversationObject.getInt("emmiter"),
                        conversationObject.getInt("reciver"),
                        conversationObject.getString("text"),
                        conversationObject.getString("image_emmit"),
                        conversationObject.getString("image_reciver")
                );

                mensajes.add(mensaje);
            }

            List<String> nicks = new ArrayList<>();

            for(int i = 0; i < mensajes.size(); i++){
                if(mensajes.get(i).getNick_recep().equals(nick)){
                    nicks.add(mensajes.get(i).getNicK_emmit());
                }else{
                    nicks.add(mensajes.get(i).getNick_recep());
                }
            }

            if(nicks.size() > 1){
                for(int i = 0; i < nicks.size()-1; i++){
                    for(int j = i+1; j <nicks.size(); j++){
                        if(nicks.get(i).equals(nicks.get(j))){
                            mensajes.remove(i);
                        }
                    }
                }
            }

            Log.i("errorInsertado", nicks.toString());

            listaConversaciones.setAdapter(new ConversacionesAdapter(getActivity(), R.layout.fragment_conversacion,  mensajes));

            listaConversaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), ""+position, Toast.LENGTH_LONG).show();
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class ConversacionesAdapter extends ArrayAdapter {

        private List<Mensaje> mensajes;

        public ConversacionesAdapter(Context context, int resource, List<Mensaje> mensajes) {
            super(context, resource, mensajes);
            this.mensajes = mensajes;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_conversacion,null);
            TextView txt1 = v.findViewById(R.id.txtInfoConv);

            String nombre = "";
            String image = "";

            if(mensajes.get(position).getNick_recep().equals(nick)){
                nombre = mensajes.get(position).getName_emmit()+" "+mensajes.get(position).getSurname_emmit()+" @"+mensajes.get(position).getNicK_emmit();
                image = mensajes.get(position).getImage_emmit();
            }else{
                nombre = mensajes.get(position).getName_recep()+" "+mensajes.get(position).getSurname_emmit()+" @"+mensajes.get(position).getNick_recep();
                image = mensajes.get(position).getImage_recep();
            }

            txt1.setText(nombre);

            ImageView img = v.findViewById(R.id.imageConversation);

            Global global = new Global();
            String url = global.url;

            Picasso.get()
                    .load(url+"user/avatar/"+image)
                    .resize(200, 200)
                    .centerCrop()
                    .transform(new CircleTransform())
                    .into(img);

            return v;
        }
    }

}
