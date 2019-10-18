package com.example.red_social.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.red_social.Util.Usuario;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class EditProfileFragment extends Fragment implements View.OnClickListener{

    EditText txtNombre;
    EditText txtApellidos;
    EditText txtDireccion;
    EditText txtPais;
    EditText txtNick;
    EditText txtBiografia;

    TextView tvImagen;

    ImageView image;

    Button btnSelectImage;
    Button btnModificar;

    SharedPreferences sharedPreferences;

    ProgressDialog progreso;

    String nick, name, surname, imagen, pais, ciudad, biografia, token;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        txtNombre = view.findViewById(R.id.txtENombre);
        txtApellidos = view.findViewById(R.id.txtEApellidos);
        txtDireccion = view.findViewById(R.id.txtEDireccion);
        txtPais = view.findViewById(R.id.txtEPais);
        txtNick = view.findViewById(R.id.txtENick);
        txtBiografia = view.findViewById(R.id.txtEBiografia);
        image = view.findViewById(R.id.imageEProfile);
        btnSelectImage = view.findViewById(R.id.btnEImagen);
        btnModificar = view.findViewById(R.id.btnEModificar);
        tvImagen = view.findViewById(R.id.tvImagen);

        btnSelectImage.setOnClickListener(this);
        btnModificar.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        nick = sharedPreferences.getString("nick", "");
        name = sharedPreferences.getString("name", "");
        surname = sharedPreferences.getString("surname", "");
        imagen = sharedPreferences.getString("image", "");
        id = sharedPreferences.getInt("id", 0);
        ciudad = sharedPreferences.getString("direction", "");
        pais = sharedPreferences.getString("country", "");
        biografia = sharedPreferences.getString("description", "");
        token = sharedPreferences.getString("token", "");

        txtNombre.setText(name);
        txtApellidos.setText(surname);
        txtNick.setText(nick);
        txtPais.setText(pais);
        txtDireccion.setText(ciudad);
        txtBiografia.setText(biografia);
        tvImagen.setText(imagen);

        Global global = new Global();
        String url = global.url;

        Picasso.get()
                .load(url+"user/avatar/"+imagen)
                .resize(250, 250)
                .centerCrop()
                .into(image);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnEImagen:
                BuscarImagen();
                break;
            case R.id.btnEModificar:
                ModificarUsuario();
                break;

        }
    }

    private void BuscarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicación"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Uri path = data.getData();
            image.getLayoutParams().height = 250;
            image.getLayoutParams().width = 250;

            image.setImageURI(path);
        }
    }

    private void Pruebas(){

    }

    private void SubirArchivo(final String img) {
        Global global = new Global();
        String url = global.url;
        url = url+"user/upload";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("errorInsertado", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Ha ocurrido un error al insertar", Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<>();
                params.put("file0", img);

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getIntanciaVolley(getContext()).addToRequestQueue(postRequest);
    }

    private void ModificarUsuario(){
        String name = txtNombre.getText().toString();
        String surname = txtApellidos.getText().toString();
        String nick = txtNick.getText().toString();
        String country = txtPais.getText().toString();
        String direction = txtDireccion.getText().toString();
        String description = txtBiografia.getText().toString();
        String image = tvImagen.getText().toString();

        if(name.equals("") || surname.equals("") || nick.equals("") || country.equals("") || direction.equals("") || description.equals("") || image.equals("")){
            Toast.makeText(getContext(), "No has completado todos los campos", Toast.LENGTH_LONG).show();
        }else{
            Usuario usuario = new Usuario();
            usuario.setName(name);
            usuario.setSurname(surname);
            usuario.setNick(nick);
            usuario.setCountry(country);
            usuario.setDirection(direction);
            usuario.setDescription(description);
            usuario.setImage(image);

            Modificar(usuario);
        }

    }

    private void Modificar(Usuario usuario) {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Cargando...");
        progreso.show();

        Global global = new Global();
        String url = global.url;
        url = url+"user/update";

        Gson gson = new Gson();
        final String json = gson.toJson(usuario);

        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progreso.hide();
                        Toast.makeText(getContext(), "Se ha modicado correctamente", Toast.LENGTH_LONG).show();

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("name", name);
                        editor.putString("surname", surname);
                        editor.putString("nick", nick);
                        editor.putString("image", imagen);
                        editor.putString("description", biografia);
                        editor.putString("direction", ciudad);
                        editor.putString("country", pais);
                        editor.commit();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progreso.hide();
                        Log.i("errorInsertado", error.toString());
                        Toast.makeText(getContext(), "Ha ocurrido un error al insertar", Toast.LENGTH_LONG).show();
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
