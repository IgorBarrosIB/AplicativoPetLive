package com.example.petlife.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

  TextView titulo;
  TextView ano;
  TextView genero;

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(View itemView){
      super(itemView);
    }
  }
}
