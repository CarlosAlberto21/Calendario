package com.example.calendario;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUtils {

    public static  LocalDate selectedDate;

    public static String formattedDate(LocalDate date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return  date.format(formatter);
    }

    public static String formattedTime(LocalTime time) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        return  time.format(formatter);
    }

    public static String formattedShortTime(LocalTime time) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return  time.format(formatter);
    }

    //Metodo para que me devuelva la fecha en dato tipo String
    public static String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return  date.format(formatter);
    }

    public static String monthDayFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d");
        return  date.format(formatter);
    }

    //Metodo que me devuelve todos los dias de cada mes
    public static ArrayList<LocalDate> diasDelmesArray(LocalDate date){
        ArrayList<LocalDate> diasDelMesArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int diasDelMes = yearMonth.lengthOfMonth();
        LocalDate primerDiaDelMes = CalendarUtils.selectedDate.withDayOfMonth(1);
        int diaDeLaSemana = primerDiaDelMes.getDayOfWeek().getValue();

        for (int i = 1; i <=  42; i++)
        {
            if (i <=  diaDeLaSemana || i > diasDelMes + diaDeLaSemana)
            {
                diasDelMesArray.add(null);
            }else
            {
                diasDelMesArray.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - diaDeLaSemana
                ));
            }
        }
        return diasDelMesArray;
    }

    //Metodo que devuelve los dias de la semana
    public static ArrayList<LocalDate> diasDeLaSemanaArray(LocalDate selectedDate) {
        ArrayList<LocalDate> dias = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        while (current.isBefore(endDate)){
            dias.add(current);
            current = current.plusDays(1);
        }

        return dias;
    }

    //Metodo para comenzar la semana siempre el domingo
    private static LocalDate sundayForDate(LocalDate current) {
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo)){
            if (current.getDayOfWeek() == DayOfWeek.SUNDAY) {

                return current;

            }
            current = current.minusDays(1);


        }

        return null;
    }

}
