package com.example.mentalhelp.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

        // Set long press listener
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Show dialog box with edit and delete options
                showEditDeleteDialog(journalListModel);
                return true;
            }
        });

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

    private void showEditDeleteDialog(final JournalListModel journalListModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Options");
        builder.setItems(new CharSequence[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Edit
                        // Implement edit logic
                        Toast.makeText(context, "Edit clicked for: " + journalListModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1: // Delete
                        // Implement delete logic
                        Toast.makeText(context, "Delete clicked for: " + journalListModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        builder.create().show();
    }
}
