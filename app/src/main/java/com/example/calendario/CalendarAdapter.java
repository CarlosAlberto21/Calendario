package com.example.calendario;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class    CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{
    private final ArrayList<LocalDate> dias;
    private final OnItemListener onItemListener;


    public CalendarAdapter(ArrayList<LocalDate> dias, CalendarAdapter.OnItemListener onItemListener) {
        this.dias = dias;
        this.onItemListener = onItemListener;
    }

    //Nos enlaza este adaptador con el archivo calendario_celdas.xml
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendarcell, parent, false);
        ViewGroup.LayoutParams layoutParams= view.getLayoutParams();
        if (dias.size() > 15 ){
            layoutParams.height = (int) (parent.getHeight() * 0.1666666666);
        }else{
            layoutParams.height = (int) (parent.getHeight());
        }

        return new CalendarViewHolder(view, onItemListener,dias );
    }

    //Metodo para enlazar las clases CalendarAdapter y CalendarViewHolder
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        final LocalDate date = dias.get(position);
       if (date == null){
           holder.diaDelMes.setText("");
       }else{
           holder.diaDelMes.setText(String.valueOf(date.getDayOfMonth()));
           if (date.equals(CalendarUtils.selectedDate)){
                holder.paretView.setBackgroundColor(Color.LTGRAY);
           }
       }


    }

    @Override
    public int getItemCount() {
        return dias.size();
    }

    public interface OnItemListener{
        void onIntemClick(int position, LocalDate date);
    }
}
