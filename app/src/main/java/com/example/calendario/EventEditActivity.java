package com.example.calendario;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class EventEditActivity extends AppCompatActivity {

    private EditText eventNamET, eventTelefono, eventDireccion;
    private TextView eventDateTV, eventTimeTv;
    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        initWidgets();
        time = LocalTime.now();
        //Aqui mostramos la fecha y hora en que se guarda la cita
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));


    }
    //Enlazo los elementos del xml con las cvariables
    public void initWidgets(){
        eventTimeTv = findViewById(R.id.eventTimeTv);
        eventDateTV = findViewById(R.id.eventDateTv);
        eventNamET = findViewById(R.id.eventNameET);
        eventDireccion = findViewById(R.id.eventDireccion);
        eventTelefono = findViewById(R.id.eventTelefono);
    }




    public void Mostrar(){
        AdminSQLiteServer admin = new AdminSQLiteServer(this);
        SQLiteDatabase database = admin.getWritableDatabase();

        Cursor fila =  database.rawQuery("SELECT nombre, direccion, telefono, tiempo FROM apoiment WHERE id = (SELECT MAX(id) FROM apoiment)  " , null);

            if (fila.moveToFirst()){

                String nombre = fila.getString(0);
                String direccion = fila.getString(1);
                long numero = Long.parseLong(fila.getString(2));
                //LocalTime tiempo = LocalTime.of(4, 45);
             LocalTime tiempo = LocalTime.parse(fila.getString(3));

                Event newEvent = new Event(nombre, direccion, numero, CalendarUtils.selectedDate, tiempo);
                Event.eventsList.add(newEvent);


                Toast.makeText(this, CalendarUtils.formattedTime(LocalTime.of(02, 43)), Toast.LENGTH_SHORT).show();
           }
    }


    //Aqui guardo los datos de la cita con este boton
    public void saveEventAction(View view) {

        AdminSQLiteServer admin = new AdminSQLiteServer(this);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        
        String nameBd = eventNamET.getText().toString();
        String direccionBd = eventDireccion.getText().toString();
        String telefonoBd = eventTelefono.getText().toString();
        String dateBd = CalendarUtils.formattedDate(CalendarUtils.selectedDate);
        String timeBd = eventTimeTv.getText().toString();

        if(!nameBd.isEmpty() && !timeBd.isEmpty() && !dateBd.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("nombre", nameBd);
            registro.put("direccion", direccionBd);
            registro.put("telefono", telefonoBd);
            registro.put("tiempo", timeBd);
            registro.put("fecha", dateBd);

            baseDeDatos.insert("apoiment", null, registro);

            baseDeDatos.close();

            eventNamET.setText("");
            eventDireccion.setText("");
            eventTimeTv.setText("");
            eventTelefono.setText("");


            Mostrar();
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish();
        }else{

            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();

        }
    }


    public void horas(View view) {
        Calendar reloj = Calendar.getInstance();
        int hora = reloj.get(Calendar.HOUR_OF_DAY);
        int  minuto = reloj.get(Calendar.MINUTE);

        TimePickerDialog tmp = new TimePickerDialog(EventEditActivity.this, R.style.DialogTheme,  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if (hourOfDay > 9){
                    if (minute > 9) {

                        eventTimeTv.setText(hourOfDay + ":" + minute);

                    }else{
                        eventTimeTv.setText(hourOfDay + ":" + "0" + minute);
                    }

                    }else {
                    if (minute > 9) {

                        eventTimeTv.setText("0" + hourOfDay + ":" + minute);

                    }else{
                        eventTimeTv.setText("0" + hourOfDay + ":" + "0" + minute);
                    }
                }

            }
        } , hora, minuto, false);
        tmp.show();
    }
}