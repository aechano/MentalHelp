package com.example.mentalhelp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhelp.Model.JournalListModel;
import com.example.mentalhelp.R;

import java.util.ArrayList;

public class JournalListAdapter extends RecyclerView.Adapter <JournalListAdapter.ViewHolder> {

    private ArrayList<JournalListModel> journalListModels;
    private Context context;

    public JournalListAdapter(ArrayList<JournalListModel> journalListModels, Context context) {
        this.journalListModels = journalListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public JournalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journalist_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalListAdapter.ViewHolder holder, int position) {
        final JournalListModel journalListModel =journalListModels.get(position);
        holder.jtitle.setText(journalListModel.getTitle());
        holder.jdate.setText(journalListModel.getDate());
        holder.jcontent.setText(journalListModel.getContent());

    }

    @Override
    public int getItemCount() {
        return journalListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView jtitle, jdate, jcontent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            jtitle = itemView.findViewById(R.id.title);
            jdate = itemView.findViewById(R.id.date);
            jcontent = itemView.findViewById(R.id.content);
        }
    }
}
