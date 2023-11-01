package com.example.firebaseproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
              private Context context;
              private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recimage);
        holder.rectitle.setText(dataList.get(position).getDataTitle());
        holder.recdes.setText(dataList.get(position).getDataDesc());
       // holder.reclang.setText(dataList.get(position).getDataLang());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("image",dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description",dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Title",dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Language",dataList.get(holder.getAdapterPosition()).getDataLang());
                intent.putExtra("key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public  void searchDataList(ArrayList<DataClass> searchList)
    {
        dataList=searchList;
        notifyDataSetChanged();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView recimage;
        TextView rectitle,recdes;
        CardView recCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recimage= itemView.findViewById(R.id.recimage);
            rectitle=itemView.findViewById(R.id.recTitle);
            recdes=itemView.findViewById(R.id.recDes);
            //reclang=itemView.findViewById(R.id.reclang);
            recCard=itemView.findViewById(R.id.recCard);
        }
    }
}
