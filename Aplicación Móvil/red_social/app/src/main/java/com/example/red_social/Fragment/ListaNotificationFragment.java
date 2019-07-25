package com.example.red_social.Fragment;

import android.app.Activity;
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
import com.example.red_social.Util.Noticia;
import com.example.red_social.Util.Notification;
import com.example.red_social.Util.Usuario;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListaNotificationFragment extends Fragment {

    ListView listaNotificaciones;
    int id;
    String token;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_notification, container, false);

        listaNotificaciones = view.findViewById(R.id.listaNotifications);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        id = sharedPreferences.getInt("id", 0);
        token = sharedPreferences.getString("token", "");

        SacarNotificaciones(id);

        return view;
    }

    private void SacarNotificaciones(int id) {
        Global global = new Global();
        String url = global.url;
        url = url+"getnotifications/"+id;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Notificaciones(response);
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

    private void Notificaciones(String notificationJson) {
        final List<Notification> notificaciones = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(notificationJson);
            JSONArray listNotif =  reader.getJSONArray("notificaciones");

            for(int i = 0; i < listNotif.length(); i++){
                JSONObject notificationObject = listNotif.getJSONObject(i);
                Notification notification = new Notification(
                        notificationObject.getString("name_emmit"),
                        notificationObject.getString("surname_emmit"),
                        notificationObject.getString("description"),
                        notificationObject.getInt("id_user_emmit"));

                notificaciones.add(notification);
            }

            listaNotificaciones.setAdapter(new NotificationAdapter(getActivity(),R.layout.fragment_user, notificaciones));

            listaNotificaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SacarUsuario(notificaciones.get(position).getId_user_emmit());
                }
            });

        } catch (JSONException e) { Log.i("errorInsertado", "Error"); }
    }

    private void SacarUsuario(int id_user_emmit) {
        Global global = new Global();
        String url = global.url;
        url = url+"user/detail/"+id_user_emmit;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            JSONObject userObject = reader.getJSONObject("user");

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

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("finduser_name", usuario.getName());
                            editor.putString("finduser_surname", usuario.getSurname());
                            editor.putString("finduser_nick", usuario.getNick());
                            editor.putString("finduser_descripcion", usuario.getDescription());
                            editor.putString("finduser_image", usuario.getImage());
                            editor.putInt("finduser_id", usuario.getId());
                            editor.commit();

                            UserfFragment fragment = new UserfFragment();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.contenedor, fragment, "fragment_meters");
                            ft.commit();

                        } catch (JSONException e) { Log.i("errorInsertado", "Error"); }
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

    class NotificationAdapter extends ArrayAdapter {

        private List<Notification> notifications;

        public NotificationAdapter(Context context, int resource, List<Notification> notifications) {
            super(context, resource, notifications);
            this.notifications = notifications;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_notification,null);

            TextView txt1 = v.findViewById(R.id.txtNotification);
            txt1.setText(notifications.get(position).getName_emmit()+" "+notifications.get(position).getSurname_emmit()+" "+notifications.get(position).getDescription());


            return v;
        }
    }


}
