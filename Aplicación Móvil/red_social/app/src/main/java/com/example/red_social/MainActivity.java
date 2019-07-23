package com.example.red_social;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.red_social.Fragment.BuscarFragment;
import com.example.red_social.Fragment.EditProfileFragment;
import com.example.red_social.Fragment.IndexFragment;
import com.example.red_social.Fragment.ListaNoticiasFragment;
import com.example.red_social.Fragment.LoginFragment;
import com.example.red_social.Fragment.PublicateFragment;
import com.example.red_social.Fragment.RegisterFragment;
import com.example.red_social.Fragment.UsuarioPerfilFragment;
import com.example.red_social.Util.Global;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Contador contador;

    SharedPreferences sharedPreferences;

    String email, name, surname, nick, image;

    Context context;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new IndexFragment()).commit();

        context = getApplicationContext();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        contador = new Contador(500,1000);
        contador.start();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        int id = item.getItemId();

        switch (id){
            case R.id.action_settings:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new EditProfileFragment()).commit();
                break;
            case R.id.action_findUser:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new BuscarFragment()).commit();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new ListaNoticiasFragment()).commit();
                break;
            case R.id.nav_login:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new LoginFragment()).commit();
                break;
            case R.id.nav_register:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new RegisterFragment()).commit();
                break;
            case R.id.nav_profile:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new UsuarioPerfilFragment()).commit();
                break;
            case R.id.nav_publicate:
                fragmentManager.beginTransaction().replace(R.id.contenedor, new PublicateFragment()).commit();
                break;
            case R.id.nav_logout:
                cerrarSesion();
                break;
            case R.id.action_findUser:

                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    public class Contador extends CountDownTimer {

        public Contador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {/////CUANDO SE TERMINA EL CONTEO DEL TIEMPO

            email = sharedPreferences.getString("email", "");
            nick = sharedPreferences.getString("nick", "");
            name = sharedPreferences.getString("name", "");
            surname = sharedPreferences.getString("surname", "");
            image = sharedPreferences.getString("image", "");

            if(email.equals("")){
                contador = new Contador(500,1000);
                contador.start();
            }else{
                hideItem();
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int segundos = (int) (millisUntilFinished / 1000);///CADA VEZ QUE PASA UN SEGUNDO LLEGA ACA
        }

    }

    private void hideItem() {
        navigationView = findViewById(R.id.nav_view);

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.menuLR).setVisible(false);
        nav_Menu.findItem(R.id.menuInfo).setVisible(true);
        nav_Menu.findItem(R.id.menuPublication).setVisible(true);

        EstablecerValores();

    }

    private void EstablecerValores() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername =  headerView.findViewById(R.id.navUsername);
        TextView navEmail =  headerView.findViewById(R.id.navEmail);
        ImageView navImage = headerView.findViewById(R.id.navImage);
        navUsername.setText(name+" "+surname+" - @"+nick);
        navEmail.setText(email);

        Global global = new Global();

        String url = global.url;

        Picasso.get()
                .load(url+"user/avatar/"+image)
                .resize(200, 200)
                .centerCrop()
                .into(navImage);
    }

    private void cerrarSesion(){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Cerrar Sesión");
        dialogo1.setMessage("¿ Desea cerrar sesión ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                navigationView = (NavigationView) findViewById(R.id.nav_view);

                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.menuLR).setVisible(true);
                nav_Menu.findItem(R.id.menuInfo).setVisible(false);
                nav_Menu.findItem(R.id.menuPublication).setVisible(false);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", "");
                editor.putString("surname", "");
                editor.putInt("id", 0);
                editor.putString("nick", "");
                editor.putString("email", "");
                editor.putString("token", "");

                email = "";
                nick = "";
                name = "";
                surname = "";
                image = "";

                editor.commit();

                NavigationView navigationView = findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername =  headerView.findViewById(R.id.navUsername);
                TextView navEmail =  headerView.findViewById(R.id.navEmail);
                ImageView navImage = headerView.findViewById(R.id.navImage);
                navUsername.setText("Inicia sesión");
                navEmail.setText("");
                navImage.setImageResource(R.mipmap.ic_launcher);

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedor, new IndexFragment()).commit();

                contador = new Contador(500,1000);
                contador.start();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.cancel();
            }
        });
        dialogo1.show();
    }
}
