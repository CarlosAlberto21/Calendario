package com.example.calendario;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

//Clase
public class CalendarViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    private final ArrayList<LocalDate> dias;
    public final  View paretView;
    public final TextView diaDelMes;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> dias) {
        super(itemView);

        this.dias = dias;
        paretView = itemView.findViewById(R.id.parentView);
        diaDelMes = itemView.findViewById(R.id.cellday);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    //Metodo Abstracto de una interfaz nos permite cambiar los dias del respectivo mes
    @Override
    public void onClick(View v) {
        onItemListener.onIntemClick(getAdapterPosition(), dias.get(getAdapterPosition()))   ;
    }
}
