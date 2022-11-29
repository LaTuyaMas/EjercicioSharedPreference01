package com.example.sharedpreferentes.ejerciciosharedpreference01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences spDatos;

    private TextView lblCorreo;
    private TextView lblPassword;
    private Button btnEliminar;
    private Button btnGuardar;

    private List<ContactoMatricula> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        contactos = new ArrayList<>();

        Toast.makeText(this, "Logeado correctamente", Toast.LENGTH_SHORT).show();

        sharedPreferences = getSharedPreferences(Constantes.user, MODE_PRIVATE);
        lblCorreo = findViewById(R.id.lblCorreoApp);
        lblPassword = findViewById(R.id.lblPasswordApp);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnGuardar = findViewById(R.id.btnGuardar);

        String correo = sharedPreferences.getString(Constantes.correo, null);
        String password = sharedPreferences.getString(Constantes.password, null);
        password = Constantes.decodificaPassword(password);

        lblCorreo.setText(correo);
        lblPassword.setText(password);

        spDatos = getSharedPreferences(Constantes.datos_persistencia, MODE_PRIVATE);

        if (!spDatos.contains(Constantes.datos)){
            crearContactos();
        }
        else {
            String contactosJSON = spDatos.getString(Constantes.datos, "");
            // Con TypeToken genero la estructura del tipo de datos codificado en el String
            Type tipo = new TypeToken<ArrayList<ContactoMatricula>>(){}.getType();
            List<ContactoMatricula> temp = new Gson().fromJson(contactosJSON, tipo);
            contactos.addAll(temp);
            Toast.makeText(this, "Datos cargados desde JSON", Toast.LENGTH_SHORT).show();
        }

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(Constantes.correo);
                editor.remove(Constantes.password);
                editor.apply();
                startActivity(new Intent(AppActivity.this, MainActivity.class));
                finish();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = spDatos.edit();
                String datos = new Gson().toJson(contactos);
                Log.d("JSON", datos);
                editor.putString(Constantes.datos, datos);
                editor.apply();
            }
        });
    }

    private void crearContactos() {
        for (int i = 1; i < 11; i++) {
            contactos.add(new ContactoMatricula("Nombre "+i, "Ciclo "+i, "Email "+i, "Telefono "+i));
        }
    }
}