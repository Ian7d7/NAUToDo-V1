package com.cs386.NAUToDo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by ian on 11/15/17.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private Clickable listener;
    private List<ListItem> data = Collections.emptyList();

    RecyclerAdapter(Context context, List<ListItem> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

        holder.icon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setClickable(Clickable listener) {
        this.listener = listener;
    }

    /*public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }*/

    //public void add(int position){}

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.item_title);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);

        }

        @Override
        public void onClick(View view) {
            if(listener != null)
            {
                listener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface Clickable{
        public void itemClicked(View v, int position);
    }
}
