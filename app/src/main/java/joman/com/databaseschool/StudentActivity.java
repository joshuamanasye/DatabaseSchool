package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Arrays;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    Button classBtn, scoreBtn;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(view -> finish());

        classBtn = findViewById(R.id.classBtn);
        scoreBtn = findViewById(R.id.scoreBtn);

        User user = (User) getIntent().getSerializableExtra("EXTRA_USER");

        TextView hi = findViewById(R.id.hi);

        String[] nameWords = user.getFullName().split(" ");

        String greetings = "Hi " + nameWords[0] + "!";
        hi.setText(greetings);

//        Toast.makeText(getApplicationContext(), user.getUserID(), Toast.LENGTH_SHORT).show();

        Student student;

        String[] field = new String[1];
        field[0] = "UserID";

        String[] data = new String[1];
        data[0] = user.getUserID();
        PutData putData = new PutData("https://jomans.000webhostapp.com/getStudent.php",
                "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
//                Toast.makeText(getApplicationContext(), "Put Completed", Toast.LENGTH_SHORT).show();
                String result = putData.getResult();

//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                List<String> studentData = Arrays.asList(result.split(","));

                if (studentData.size() == 2) {
                    student = new Student(user.getUserID(),
                            studentData.get(0),
                            studentData.get(1));

                    classBtn.setOnClickListener(v -> {
                        Intent intent = new Intent(this, StudentClassActivity.class);
                        intent.putExtra("EXTRA_USER", user);
                        intent.putExtra("EXTRA_STUDENT", student);
                        startActivity(intent);
                    });

                    scoreBtn.setOnClickListener(v -> {
                        Intent intent = new Intent(this, StudentScoreActivity.class);
                        intent.putExtra("EXTRA_USER", user);
                        intent.putExtra("EXTRA_STUDENT", student);
                        startActivity(intent);
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Failed to fetch student's data", Toast.LENGTH_SHORT).show();
        }
    }
}