package com.example.red_social.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.red_social.R;


public class ListaPublicacionesFragment extends Fragment {

    ListView listaPublicaciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_publicaciones, container, false);


        listaPublicaciones = view.findViewById(R.id.listaPublicaciones);

        return view;
    }


}
