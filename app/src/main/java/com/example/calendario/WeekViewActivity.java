package com.example.calendario;

import static com.example.calendario.CalendarUtils.diasDeLaSemanaArray;

import static com.example.calendario.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    private TextView mesDelAno_text;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        initWidgets();
        setWeekView();
    }

    // Metodo para enlazar los elementos xml con sus variables
    private void initWidgets(){
        calendarRecyclerView = findViewById(R.id.calendar);
        mesDelAno_text = findViewById(R.id.id_fecha);
        eventListView = findViewById(R.id.eventListView);

    }

    //Metodo que devuelve el ano y mes actual y muestra lo que contiene el Recycler
    private void setWeekView(){
        mesDelAno_text.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> dias = diasDeLaSemanaArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(dias, this);
        //Devuelve los dias del mes en forma de grilla
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext  (), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }


    //Boton de reversa de semana
    public void ActionReverseWeeky(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    //Boton de siguiente  semana
    public void ActionNextWeeky(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    //Boton para seleccionar el dia de la semana
    @Override
    public void onIntemClick(int position, LocalDate date) {
          CalendarUtils.selectedDate = date;
          setWeekView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setEventAdapter();
    }

    //Mostramos la cita que se guardo
    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void eventAction(View view) {

        startActivity(new Intent(this, EventEditActivity.class));
    }

    public void dailyAction(View view) {
        startActivity(new Intent(this, DailyCalendarActivity.class));
    }
}