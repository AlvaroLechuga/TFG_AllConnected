package com.example.red_social.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.red_social.Util.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ListaNoticiasFragment extends Fragment {

    ListView listaNoticias;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_noticias, container, false);

        listaNoticias = view.findViewById(R.id.listaNoticias);

        SacarNoticias();

        return view;
    }

    private void SacarNoticias() {
        Global global = new Global();
        String url = global.url;
        url = url+"getnotices/";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       Noticias(response);
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

    private void Noticias(String noticiasJson) {
        final List<Noticia> noticias = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(noticiasJson);
            JSONArray listUsers =  reader.getJSONArray("notices");

            for(int i = 0; i < listUsers.length(); i++){
                JSONObject noticeObject = listUsers.getJSONObject(i);
                Noticia noticia = new Noticia(
                        noticeObject.getInt("id"),
                        noticeObject.getString("title"),
                        noticeObject.getInt("id_category"),
                        noticeObject.getString("text"),
                        noticeObject.getString("image"),
                        noticeObject.getInt("id_user"),
                        noticeObject.getString("name"));

                noticias.add(noticia);
            }

            listaNoticias.setAdapter(new NoticiasAdapter(getActivity(),R.layout.fragment_user, noticias));

        } catch (JSONException e) { Log.i("errorInsertado", "Error"); }

    }

    class NoticiasAdapter extends ArrayAdapter {

        private List<Noticia> noticias;

        public NoticiasAdapter(Context context, int resource, List<Noticia> noticias) {
            super(context, resource, noticias);
            this.noticias = noticias;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.fragment_noticia,null);

            TextView txt1 = v.findViewById(R.id.txtNTitular);
            txt1.setText(noticias.get(position).getTitle());

            TextView txt2 = v.findViewById(R.id.txtNCategoria);
            txt2.setText(noticias.get(position).getNameTitle());

            TextView txt3 = v.findViewById(R.id.txtNNoticia);
            txt3.setText(noticias.get(position).getText());

            ImageView img = v.findViewById(R.id.imageNoticia);

            Picasso.get()
                    .load(noticias.get(position).getImage())
                    .resize(300, 300)
                    .centerCrop()
                    .into(img);

            return v;
        }
    }
}
