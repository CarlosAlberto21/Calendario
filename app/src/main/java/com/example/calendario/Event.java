package com.example.calendario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Event {


    public static ArrayList<Event> eventsList = new ArrayList<>();

    public static ArrayList<Event> eventsForDate(LocalDate date){
        ArrayList<Event> events = new ArrayList<>();
        for (Event event: eventsList){
            if (event.getDate().equals(date)){
                events.add(event);
            }
        }
        return events;
    }

    public static ArrayList<Event> eventsForDateAndTime(LocalDate date, LocalTime time){
        ArrayList<Event> events = new ArrayList<>();
        for (Event event: eventsList){

            int eventHour = event.time.getHour();
            int cellHour = time.getHour();
            if (event.getDate().equals(date) && eventHour == cellHour){
                events.add(event);
            }
        }
        return events;
    }


    private String name;
    private LocalDate date;
    private String direccion;
    private long numero;
    private LocalTime time;





    public Event(String name, String direccion, long numero, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.direccion = direccion;
        this.numero = numero;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
}
