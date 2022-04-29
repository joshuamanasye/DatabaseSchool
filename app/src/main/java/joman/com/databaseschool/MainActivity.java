package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        TextView hello = findViewById(R.id.hello);
        continueBtn = findViewById(R.id.continue1);

        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        String userStatus;

        User user;

        if (sessionId != null) {
//        hello.setTextColor(ContextCompat.getColor(this, R.color.spiced_nectarine));

            String[] field = new String[1];
            field[0] = "UserID";
            //Creating array for data
            String[] data = new String[1];
            data[0] = sessionId;
            PutData putData = new PutData("https://jomans.000webhostapp.com/getUser.php",
                    "POST", field, data);

            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();

                    List<String> userData = Arrays.asList(result.split(","));

                    if(userData.size() == 6) {
                        user = new User(sessionId,
                                userData.get(0),
                                userData.get(1),
                                userData.get(2),
                                userData.get(3),
                                userData.get(4),
                                userData.get(5));

                        String[] nameWords = user.getFullName().split(" ");
                        String greetings = "Hello " + nameWords[0] + "!";
//                        hello.setTextSize(40);
                        hello.setText(greetings);

//                        String tesData = userData.get(3);
//                        hello.setText(tesData);


                        field = new String[1];//dapetin user status
                        field[0] = "UserID";
                        //Creating array for data
                        data = new String[1];
                        data[0] = sessionId;
                        putData = new PutData("https://jomans.000webhostapp.com/getStatus.php",
                                "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                userStatus = putData.getResult();

                                if (userStatus.equals("Lecturer")) {
                                    mainView.setBackgroundColor(ContextCompat.getColor(
                                            mainView.getContext(), R.color.reef_encounter));

                                    String btnText = "CONTINUE AS LECTURER";

                                    continueBtn.setText(btnText);

                                    continueBtn.setOnClickListener(v -> {
                                        Intent intent = new Intent(this, LecturerActivity.class);
                                        intent.putExtra("EXTRA_USER", user);
                                        startActivity(intent);
                                        finish();
                                    });
                                }
                                else if (userStatus.equals("Admin")) {
                                    mainView.setBackgroundColor(ContextCompat.getColor(
                                            mainView.getContext(), R.color.casandora_yellow));

                                    String btnText = "CONTINUE AS ADMIN";

                                    continueBtn.setText(btnText);

                                    continueBtn.setOnClickListener(v -> {
                                        Intent intent = new Intent(this, AdminActivity.class);
                                        intent.putExtra("EXTRA_USER", user);
                                        startActivity(intent);
                                        finish();
                                    });

                                }
                                else {
                                    continueBtn.setOnClickListener(v -> {
                                        Intent intent = new Intent(this, StudentActivity.class);
                                        intent.putExtra("EXTRA_USER", user);
                                        startActivity(intent);
                                        finish();
                                    });
                                }
//                                Toast.makeText(getApplicationContext(), userStatus, Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }
}