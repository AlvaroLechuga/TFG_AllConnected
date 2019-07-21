package com.example.red_social.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.red_social.R;
import com.example.red_social.Util.Global;
import com.squareup.picasso.Picasso;


public class UsuarioPerfilFragment extends Fragment implements View.OnClickListener{

    ImageView imageProfile;
    Button btnEdit;
    TextView txtNombre;
    TextView txtNick;
    TextView txtDescripcion;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario_perfil, container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        String nombre = sharedPreferences.getString("name", "");
        String apellidos = sharedPreferences.getString("surname", "");
        String nick = sharedPreferences.getString("nick", "");
        String description = sharedPreferences.getString("description", "");
        String image = sharedPreferences.getString("image", "");

        imageProfile = view.findViewById(R.id.imagePProfile);
        btnEdit = view.findViewById(R.id.btnPPEdit);
        txtNombre = view.findViewById(R.id.txtPPNameSur);
        txtNick = view.findViewById(R.id.txtPPNick);
        txtDescripcion = view.findViewById(R.id.txtPPDescription);

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

        return view;
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
