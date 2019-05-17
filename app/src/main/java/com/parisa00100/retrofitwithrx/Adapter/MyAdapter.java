package com.parisa00100.retrofitwithrx.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parisa00100.retrofitwithrx.Model.MyModel;
import com.parisa00100.retrofitwithrx.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<MyModel> myModels;

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView txt_content;
        TextView txt_title;
        TextView txt_author;

        public MyViewHolder(View view) {
            super(view);
            txt_content = (TextView) view.findViewById(R.id.txt_content);
            txt_title = view.findViewById(R.id.txt_title);

            txt_author=view.findViewById(R.id.txt_author);




        }


    }


    public MyAdapter(List<MyModel> products, Context context) {
        this.context = context;
        this.myModels = products;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model, parent, false);

        return new MyAdapter.MyViewHolder(itemView);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        MyModel tmpValue = myModels.get(position);


        holder.txt_title.setText(tmpValue.getTitle());
        holder.txt_content.setText(tmpValue.getBody());
        holder.txt_author.setText(tmpValue.getUserId().toString());

    }


    @Override
    public int getItemCount() {
        return myModels.size();
    }
}
