package com.example.calendario;

import static com.example.calendario.CalendarUtils.diasDelmesArray;
import static com.example.calendario.CalendarUtils.monthYearFromDate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();

        setMonthView();

    }

    // Metodo para enlazar los elementos xml con sus variables
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendar);
        monthYearText = findViewById(R.id.id_fecha);
    }

    //Metodo que devuelve el ano y mes actual y muestra lo que contiene el Recycler
    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = diasDelmesArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    //Metodo que hace que el calendario retroceda de mes
    public void ActionReverse(View v){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void ActionNext(View v){
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    //Metodo permite seleccionar los dias del mes dandole click
    @Override
    public void onIntemClick(int position, LocalDate date) {
        if (date != null){
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    //Metodo para enviar a la activity WeekViewActivity
    public void WeeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }


}
