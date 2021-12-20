package com.example.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalTime;

public class Inicio extends AppCompatActivity {
    private EditText name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        initWidgets();
    }

    //Enlazo los elementos del xml con las cvariables
    public void initWidgets() {
        name = findViewById(R.id.usuario);
        password = findViewById(R.id.password);


    }

    public void Ingresar(View v) {

        AdminSQLiteServer admin = new AdminSQLiteServer(this);
        SQLiteDatabase database = admin.getWritableDatabase();
        String nombreBd = name.getText().toString();
        String passwordBd = password.getText().toString();

        Cursor fila =  database.rawQuery("SELECT nombre, password FROM cuenta WHERE nombre = '"+nombreBd+"' ", null);

        if (fila.moveToFirst()){

            String user = fila.getString(0);
            String pass = fila.getString(1);

            if (user.equals(nombreBd) && pass.equals(passwordBd)) {

                Intent siguiente = new Intent(this, CreacionDeCalendario.class);
                startActivity(siguiente);
                database.close();
            }else{
                Intent siguiente = new Intent(this, CrearCuenta.class);
                startActivity(siguiente);
                database.close();
            }
        }
    }

    public void Cuenta(View v){
        Intent siguiente = new Intent(this, CrearCuenta.class);
        startActivity(siguiente);
    }
}