package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LecturerActivity extends AppCompatActivity {

    Button classBtn, scoreBtn;;
    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);

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

        Lecturer lecturer;

        String[] field = new String[1];
        field[0] = "UserID";

        String[] data = new String[1];
        data[0] = user.getUserID();
        PutData putData = new PutData("https://jomans.000webhostapp.com/getLecturer.php",
                "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
//                Toast.makeText(getApplicationContext(), "Put Completed", Toast.LENGTH_SHORT).show();
                String result = putData.getResult();

                lecturer = new Lecturer(result);

                classBtn.setOnClickListener(v -> {
                    Intent intent = new Intent(this, LecturerClassActivity.class);
                    intent.putExtra("EXTRA_USER", user);
                    intent.putExtra("EXTRA_LECTURER", lecturer);
                    startActivity(intent);
                });

                scoreBtn.setOnClickListener(v -> {
                    Intent intent = new Intent(this, LecturerScoreActivity.class);
                    startActivity(intent);
                });

            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Failed to fetch lecturer's data", Toast.LENGTH_SHORT).show();
        }
    }
}