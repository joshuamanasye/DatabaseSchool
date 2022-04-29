package joman.com.databaseschool;

public class ScoreView {
    String subjectName;
    int score;

    public ScoreView(String subjectName, int score) {
        this.subjectName = subjectName;
        this.score = score;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getScore() {
        return score;
    }
}
