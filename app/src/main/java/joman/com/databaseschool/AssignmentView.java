package joman.com.databaseschool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssignmentView {
    private String subject, asg;
    private LocalDate deadline;

    public AssignmentView(String subject, String asg, String deadline) {
        this.subject = subject;
        this.asg = asg;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.deadline = LocalDate.parse(deadline, formatter);
    }

    public String getSubject() {
        return subject;
    }

    public String getAsg() {
        return asg;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
