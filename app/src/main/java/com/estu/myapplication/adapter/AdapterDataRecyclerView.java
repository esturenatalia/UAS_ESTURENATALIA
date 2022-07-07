package com.estu.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.estu.myapplication.R;
import com.estu.myapplication.model.Data;

import java.util.ArrayList;

public class AdapterDataRecyclerView extends RecyclerView.Adapter<AdapterDataRecyclerView.ViewHolder> {

    private ArrayList<Data> daftarData;
    private Context context;
    FirebaseDataListener listener;

    public AdapterDataRecyclerView(ArrayList<Data> datas, Context ctx){
        daftarData = datas;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_datadiri);
            cvMain = (CardView) v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarData.get(position).getNama();
        System.out.println("Data Diri "+position+daftarData.size());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        return daftarData.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Data data, int position);
    }
}
