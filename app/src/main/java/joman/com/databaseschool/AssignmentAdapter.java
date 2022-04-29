package joman.com.databaseschool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private final Context mCtx;
    private final ArrayList<AssignmentView> listAssignment;

    public AssignmentAdapter(Context mCtx, ArrayList<AssignmentView> listAssignment) {
        this.mCtx = mCtx;
        this.listAssignment = listAssignment;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.list_layout, null);

        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AssignmentViewHolder holder, int position) {
        AssignmentView assignment = listAssignment.get(position);

        holder.subjectName.setText(assignment.getSubject());
        holder.assignment.setText(assignment.getAsg());

        LocalDate deadlineDate = assignment.getDeadline();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        String deadlineStr = deadlineDate.format(formatter);

        holder.deadline.setText(deadlineStr);
    }

    @Override
    public int getItemCount() {
        return listAssignment.size();
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName, assignment, deadline;

        public AssignmentViewHolder(View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.ass_subject_name);
            assignment = itemView.findViewById(R.id.ass_assignment);
            deadline = itemView.findViewById(R.id.ass_deadline);
        }
    }

}
