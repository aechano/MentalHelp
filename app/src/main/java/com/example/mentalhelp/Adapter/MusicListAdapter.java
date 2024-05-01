package com.example.mentalhelp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private ArrayList<MusicListModel> musicListModels;
    private OnSongClick onSongClick;

    public MusicListAdapter(ArrayList<MusicListModel> musicListModels) {
        this.musicListModels = musicListModels;
    }

    @NonNull
    @Override
    public MusicListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MusicListModel musicListModel =musicListModels.get(position);
        holder.mtitle.setText(musicListModel.getTitle());
        holder.itemView.setOnClickListener(v -> onSongClick.songClick(musicListModel));
    }

    @Override
    public int getItemCount() {
        return musicListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mtitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.title);
        }
    }

    public interface OnSongClick {
        void songClick(MusicListModel musicListModel);
    }

    public void setOnSongClickListener(OnSongClick onSongClick) {
        this.onSongClick = onSongClick;
    }
}
