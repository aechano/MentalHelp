package com.example.mentalhelp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhelp.Model.GuideListModel;
import com.example.mentalhelp.Model.JournalListModel;
import com.example.mentalhelp.Model.MusicListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class GuideListAdapter extends RecyclerView.Adapter <GuideListAdapter.ViewHolder>{

    private ArrayList<GuideListModel> guideListModels;
    private Context context;

    public GuideListAdapter(ArrayList<GuideListModel> guideListModels, Context context) {
        this.guideListModels = guideListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public GuideListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideListAdapter.ViewHolder holder, int position) {
        final GuideListModel guideListModel =guideListModels.get(position);
        holder.gtitle.setText(guideListModel.getTitle());

    }
    @Override
    public int getItemCount() {
        return guideListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView gtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            gtitle = itemView.findViewById(R.id.title);
        }
    }
}
