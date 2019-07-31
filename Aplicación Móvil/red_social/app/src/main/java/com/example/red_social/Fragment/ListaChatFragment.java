package com.example.red_social.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.red_social.MainActivity;
import com.example.red_social.R;
import com.example.red_social.Util.CircleTransform;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Mensaje;
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

public class ListaChatFragment extends Fragment implements View.OnClickListener {

    ListView listaChat;
    TextView txtInfoUser;
    TextView txtEnviar;
    EditText txtMensaje;

    List<Mensaje> mensajes = new ArrayList<>();

    SharedPreferences sharedPreferences;

    ProgressDialog progreso;

    String token;
    int id_usuario;
    int identificador;
    String datosUsuarioCon;

    Contador contador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_chat, container, false);

        listaChat = view.findViewById(R.id.listaChat);
        txtInfoUser = view.findViewById(R.id.txtInfoUser);
        txtEnviar = view.findViewById(R.id.post);
        txtMensaje = view.findViewById(R.id.comentario);

        txtEnviar.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        token = sharedPreferences.getString("token", "");
        id_usuario = sharedPreferences.getInt("id_chat_usuario", 0);
        identificador = sharedPreferences.getInt("id", 0);
        datosUsuarioCon = sharedPreferences.getString("datos_chat_usuario", "");

        txtInfoUser.setText(datosUsuarioCon);

        contador = new Contador(10000,1000);
        contador.start();

        SacarConversacion();

        return view;
    }

    private void SacarConversacion() {
        Global global = new Global();
        String url = global.url;
        url = url+"obtenermensajesbyuser/"+id_usuario;

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

    private void SacarConver(String conversacionJson){
        mensajes = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(conversacionJson);
            JSONArray listConversations = reader.getJSONArray("messages");

            for(int i = 0; i < listConversations.length(); i++){
                JSONObject conversationObject = listConversations.getJSONObject(i);

                Mensaje mensaje = new Mensaje(
                        conversationObject.getInt("id_emmit"),
                        conversationObject.getInt("id_recep"),
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

            listaChat.setAdapter(new ConversacionAdapter(getActivity(), R.layout.fragment_chat,  mensajes));

        } catch (JSONException e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.post:
                if(!txtMensaje.getText().equals("")){
                    EnviarChat();
                }
                break;
        }
    }

    private void EnviarChat() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"insertmessage/"+id_usuario;

        Mensaje mensaje = new Mensaje();
        mensaje.setText(txtMensaje.getText().toString());

        Gson gson = new Gson();
        final String json = gson.toJson(mensaje);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progreso.hide();
                        txtMensaje.setText("");
                        SacarConversacion();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "No se ha podido enviar el mensaje", Toast.LENGTH_LONG).show();
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

    class ConversacionAdapter extends ArrayAdapter {

        private List<Mensaje> mensajes;

        public ConversacionAdapter(Context context, int resource, List<Mensaje> mensajes) {
            super(context, resource, mensajes);
            this.mensajes = mensajes;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_chat,null);

            LinearLayout convBorde = v.findViewById(R.id.convBorde);

            TextView txt1 = v.findViewById(R.id.txtMensaje);

            String image = "";

            if(mensajes.get(position).getId_emmit() != identificador){
                image = mensajes.get(position).getImage_recep();
            }else{
                image = mensajes.get(position).getImage_emmit();
            }

            txt1.setText(mensajes.get(position).getText());

            ImageView img = v.findViewById(R.id.imageConversationUser);

            if(identificador != mensajes.get(position).getId_emmit()){
                txt1.setGravity(Gravity.RIGHT);
                convBorde.setBackgroundResource(R.drawable.layout_border_green);
                img.setVisibility(View.GONE);
            }else{
                Global global = new Global();
                String url = global.url;

                Picasso.get()
                        .load(url+"user/avatar/"+image)
                        .resize(200, 200)
                        .centerCrop()
                        .transform(new CircleTransform())
                        .into(img);
            }
            return v;
        }
    }

    public class Contador extends CountDownTimer {

        public Contador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {/////CUANDO SE TERMINA EL CONTEO DEL TIEMPO
            SacarConversacion();
            contador = new Contador(10000,1000);
            contador.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int segundos = (int) (millisUntilFinished / 1000);///CADA VEZ QUE PASA UN SEGUNDO LLEGA ACA
        }

    }
}
