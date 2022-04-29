package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LecturerScoreActivity extends AppCompatActivity {

    TextInputEditText textInputNISN, textInputSubjectID, textInputScore;
    ImageView homeImg;

    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_score);

        homeImg = findViewById(R.id.lecturer_score_home);
        homeImg.setOnClickListener(view -> finish());

        textInputNISN = findViewById(R.id.nisn);
        textInputSubjectID = findViewById(R.id.subject_id);
        textInputScore = findViewById(R.id.score);

        submitBtn = findViewById(R.id.submit_button);
        submitBtn.setOnClickListener(view -> {
            String nisn = String.valueOf(textInputNISN.getText());
            String subjectId = String.valueOf(textInputSubjectID.getText());
            String scoreStr = String.valueOf(textInputScore.getText());

            if (nisn.equals("") || subjectId.equals("") || scoreStr.equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill the form", Toast.LENGTH_SHORT).show();
            }
            else {
                int score = Integer.parseInt(scoreStr);

                if (score < 0 || score > 100) {
                    Toast.makeText(getApplicationContext(), "Please input correct score", Toast.LENGTH_SHORT).show();
                }
                else {
                    Handler handler = new Handler();
                    handler.post(() -> {
                        String[] field = new String[3];
                        field[0] = "NISN";
                        field[1] = "SubjectID";
                        field[2] = "Score";
                        //Creating array for data
                        String[] data = new String[3];
                        data[0] = nisn;
                        data[1] = subjectId;
                        data[2] = String.valueOf(score);
                        PutData putData = new PutData("https://jomans.000webhostapp.com/inputScore.php",
                                "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();

                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                if (result.equals("Success")) {
                                    textInputNISN.setText(null);
                                    textInputSubjectID.setText(null);
                                    textInputScore.setText(null);
                                }
                            }
                        }
                    });
                }
            }
        });

    }
}