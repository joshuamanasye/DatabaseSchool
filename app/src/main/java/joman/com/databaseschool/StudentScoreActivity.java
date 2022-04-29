package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentScoreActivity extends AppCompatActivity {

    ImageView homeImg;
    RecyclerView recyclerView;

    ArrayList<ScoreView> listScore;

    ScoreAdapter adapter;

    private void loadScore(Student student) {
        String[] field = new String[1];
        field[0] = "NISN";

        String[] data = new String[1];
        data[0] = student.getNisn();

        PutData putData = new PutData("https://jomans.000webhostapp.com/getScore.php",
                "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                try {
                    JSONArray scoreArr = new JSONArray(result);

                    for (int i = 0; i < scoreArr.length(); i++) {
                        JSONObject assObj = scoreArr.getJSONObject(i);

                        String subjectName = assObj.getString("SubjectName");
                        int score = assObj.getInt("Score");

                        listScore.add(new ScoreView(subjectName, score));
                    }


//                    Toast.makeText(getApplicationContext(),
//                            listScore.get(0).getSubjectName() + " " +
//                                    listScore.get(0).getScore(),
//                            Toast.LENGTH_SHORT).show();

                    adapter = new ScoreAdapter(this, listScore);
                    recyclerView.setAdapter(adapter);

//                    try {
//                        adapter = new ScoreAdapter(this, listScore);
//                        recyclerView.setAdapter(adapter);
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score);

        listScore = new ArrayList<>();

        recyclerView = findViewById(R.id.score_view_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeImg = findViewById(R.id.score_home);
        homeImg.setOnClickListener(view -> finish());

        User user = (User) getIntent().getSerializableExtra("EXTRA_USER");
        Student student = (Student) getIntent().getSerializableExtra("EXTRA_STUDENT");

        String[] nameWords = user.getFullName().split(" ");

        TextView studentName = findViewById(R.id.score_header_student_name);
        studentName.setText(nameWords[0]);

        Class class_;

        String[] field = new String[1];
        field[0] = "ClassID";

        String[] data = new String[1];
        data[0] = student.getClassID();
        PutData putData = new PutData("https://jomans.000webhostapp.com/getClass.php",
                "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();

                List<String> classData = Arrays.asList(result.split(","));

                if (classData.size() == 2) {
                    class_ = new Class(student.getClassID(),
                            classData.get(0),
                            classData.get(1));

                    TextView className = findViewById(R.id.score_class_name);
                    className.setText(class_.getClassName());

                    studentName.setOnClickListener(v -> {
                        Intent intent = new Intent(this, StudentProfileActivity.class);
                        intent.putExtra("EXTRA_USER", user);
                        intent.putExtra("EXTRA_STUDENT", student);
                        intent.putExtra("EXTRA_CLASS", class_);
                        startActivity(intent);
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        }

        loadScore(student);

    }
}