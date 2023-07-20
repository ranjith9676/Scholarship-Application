package com.example.vus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapater extends RecyclerView.Adapter<MyAdapater.MyViewHolder> {
    static Context context;
    static ArrayList<Stud> studArrayList;

    public MyAdapater(Context context, ArrayList<Stud> studArrayList)
    {
        this.context = context;
        this.studArrayList = studArrayList;
    }

    @NonNull
    @Override
    public MyAdapater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapater.MyViewHolder holder, int position) {
        final Stud stud=studArrayList.get(position);
        holder.reg1.setText(stud.getReg_No());
        holder.name1.setText(stud.getName());
        holder.Scholar_sem1.setText(stud.getScholar_ship_sem());
        holder.acc_name1.setText(stud.getAcc_name());
        holder.acc_no1.setText(stud.getAcc_no());
        holder.ifsca1.setText(stud.getIFSC());
        holder.banka1.setText(stud.getBank_Name());
        holder.branch1.setText(stud.getBranch());
        holder.mob1.setText(stud.getPh_no());
        holder.status1.setText(stud.getStatus());

    }

    @Override
    public int getItemCount() {
        return studArrayList.size();
    }
    public void filteredList(ArrayList<Stud> filteredList)
    {
        studArrayList=filteredList;
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView reg1,name1,Scholar_sem1,acc_name1,acc_no1,ifsca1,banka1,branch1,mob1,status1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reg1=(TextView) itemView.findViewById(R.id.reg1);
            name1=(TextView) itemView.findViewById(R.id.name1);
            Scholar_sem1=(TextView) itemView.findViewById(R.id.Scholar_sem1);
            acc_name1=(TextView) itemView.findViewById(R.id.acc_name1);
            acc_no1=(TextView) itemView.findViewById(R.id.acc_no1);
            ifsca1=(TextView) itemView.findViewById(R.id.ifsca1);
            banka1=(TextView) itemView.findViewById(R.id.banka1);
            branch1=(TextView) itemView.findViewById(R.id.branch1);
            mob1=(TextView) itemView.findViewById(R.id.mob1);
            status1=(TextView) itemView.findViewById(R.id.status1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i= new Intent(context,MainActivity8.class);
                    i.putExtra("reg1",studArrayList.get(getAdapterPosition()).getReg_No());
                    context.startActivity(i);
                }
            });
        }
    }
}
