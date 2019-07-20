package com.example.red_social;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.red_social.Util.Global;
import com.example.red_social.Util.Publicacion;
import com.example.red_social.Util.VolleySingleton;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class BuscarFragment extends Fragment implements View.OnClickListener{

    ProgressDialog progreso;
    RequestQueue request;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        request = Volley.newRequestQueue(getContext());

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
