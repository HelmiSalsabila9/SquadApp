package com.example.squadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.squadapp.R;
import com.example.squadapp.database.entity.Lagu;
import com.example.squadapp.database.model.Relasi;
import com.example.squadapp.database.model.RelasiDua;

import java.util.List;

public class LaguAdapter extends RecyclerView.Adapter<LaguAdapter.ViewAdapter> {
    private List<Relasi> listartist;
    private List<RelasiDua> listdua;
    private Context context;
    private LaguAdapter.Dialog dialog;

    public LaguAdapter(Context applicationContext, List<Relasi> listArtist) {
        this.context = applicationContext;
        this.listartist = listArtist;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;

    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlagu, parent, false);
        return new ViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nama_lagu.setText(listartist.get(position).lagu.nama_lagu);
        holder.artistId.setText(listartist.get(position).lagu.artistId);
        holder.genreId.setText(listartist.get(position).lagu.genreId);
        holder.publisherId.setText(listartist.get(position).lagu.publisherId);

    }
    @Override
    public int getItemCount() {
        return listartist.size();
    }
    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView nama_lagu, artistId, genreId, publisherId;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nama_lagu = itemView.findViewById(R.id.nama_lagu);
            artistId = itemView.findViewById(R.id.artistId);
            genreId = itemView.findViewById(R.id.genreId);
            publisherId = itemView.findViewById(R.id.publisherId);
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
