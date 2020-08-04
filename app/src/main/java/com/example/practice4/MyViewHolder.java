package com.example.practice4;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView txtListMenuName;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        txtListMenuName = itemView.findViewById(R.id.txtListMenuName);
    }
}
