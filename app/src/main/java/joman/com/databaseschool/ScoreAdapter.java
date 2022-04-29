package joman.com.databaseschool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private final Context mCtx;
    private final ArrayList<ScoreView> listScore;

    public ScoreAdapter(Context mCtx, ArrayList<ScoreView> listScore) {
        this.mCtx = mCtx;
        this.listScore = listScore;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.score_list_layout, null);

        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreView score = listScore.get(position);

        holder.subjectName.setText(score.getSubjectName());
        holder.score.setText(String.valueOf(score.getScore()));
    }

    @Override
    public int getItemCount() {
        return listScore.size();
    }

    static class ScoreViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName, score;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectName = itemView.findViewById(R.id.score_subject_name);
            score = itemView.findViewById(R.id.score_score);
        }
    }

}
