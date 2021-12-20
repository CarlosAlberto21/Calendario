package com.example.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CrearCuenta extends AppCompatActivity {
    private EditText name, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        initWidgets();
    }

    //Enlazo los elementos del xml con las cvariables
    public void initWidgets(){
        name = findViewById(R.id.usuario);
        password = findViewById(R.id.Pasword);
        password2 = findViewById(R.id.Pasword2);

    }

    public void saveEventAction(View view) {
        AdminSQLiteServer admin = new AdminSQLiteServer(this);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String nameBd = name.getText().toString();
        String passwordBd = password.getText().toString();
        String pasword_txt = password2.getText().toString();

        if(passwordBd.equals(pasword_txt)){
            ContentValues registro = new ContentValues();

            registro.put("nombre", nameBd);
            registro.put("password", passwordBd);

            baseDeDatos.insert("cuenta", null, registro);

            baseDeDatos.close();

            name.setText("");
            password.setText("");
            password2.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish();
        }else{

            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();

        }
    }
}