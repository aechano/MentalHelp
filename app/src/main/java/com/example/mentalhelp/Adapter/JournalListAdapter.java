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

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class JournalListAdapter extends RecyclerView.Adapter<JournalListAdapter.ViewHolder> {

    private ArrayList<JournalListModel> journalListModels;
    private OnClick onClick;
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
        final JournalListModel journalListModel = journalListModels.get(position);
        holder.jtitle.setText(journalListModel.getTitle());

        holder.jdate.setText(String.valueOf(millisToDate(journalListModel.getDateCreated())));
        holder.jcontent.setText(journalListModel.getContent());

        // Set long press listener
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Show dialog box with edit and delete options
                showEditDeleteDialog(journalListModel, position);
                return true;
            }
        });

    }

    private String millisToDate(Long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Convert numeric month to month name
        String[] monthNames = new DateFormatSymbols(Locale.ENGLISH).getMonths();
        String monthName = monthNames[mMonth];

        // Construct the date string in desired format

        return String.format(Locale.getDefault(), "%s %d, %d", monthName, mDay, mYear);
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

    public void setOnClickListener(OnClick onClick){
        this.onClick = onClick;
    }

    private void showEditDeleteDialog(JournalListModel journalListModel, Integer position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(String.format("%s", journalListModel.getTitle()));
        builder.setItems(new CharSequence[]{"Edit", "Delete"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Edit
                        // Implement edit logic
                        onClick.onEdit(journalListModel);
                        break;
                    case 1: // Delete
                        // Implement delete logic
                        onClick.onDelete(journalListModel, position);
                        break;
                }
            }
        });
        builder.create().show();
    }

    public interface OnClick{
        void onClick(JournalListModel journalListModel);
        void onEdit(JournalListModel journalListModel);
        void onDelete(JournalListModel journalListModel, Integer position);
    }
}
