package com.example.red_social.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.red_social.R;
import com.example.red_social.Util.Global;
import com.squareup.picasso.Picasso;


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

    String nick, name, surname, imagen, pais, ciudad, biografia;
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
                break;
            case R.id.btnEModificar:
                break;

        }
    }
}
