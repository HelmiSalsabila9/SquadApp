package com.example.squadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squadapp.R;
import com.example.squadapp.database.entity.Publisher;

import java.util.List;

public class PublisherAdapter extends RecyclerView.Adapter<PublisherAdapter.ViewAdapter> {
    private List<Publisher> list;
    private Context context;
    private Dialog dialog;
    public interface Dialog {
        void onClick(int position);
    }
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public PublisherAdapter(Context context, List<Publisher> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rowpublisher, parent, false);
        return new ViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nama_publisher.setText(list.get(position).nama_publisher);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama_publisher;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nama_publisher = itemView.findViewById(R.id.nama_publisher);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}