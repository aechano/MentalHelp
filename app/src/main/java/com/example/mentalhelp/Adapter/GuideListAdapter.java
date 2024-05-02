package com.example.mentalhelp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhelp.Model.GuideListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.ViewHolder> {

    private ArrayList<GuideListModel> guideListModels;
    private Context context;
    private OnItemClickListener listener;

    public GuideListAdapter(ArrayList<GuideListModel> guideListModels, Context context, OnItemClickListener listener) {
        this.guideListModels = guideListModels;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final GuideListModel guideListModel = guideListModels.get(position);
        holder.gtitle.setText(guideListModel.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(guideListModel);
            }
        });
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

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(GuideListModel guideListModel);
    }
}
