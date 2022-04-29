package joman.com.databaseschool;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.email);
        textInputEditTextPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(v -> {
            String email = String.valueOf(textInputEditTextEmail.getText());
            String password = String.valueOf(textInputEditTextPassword.getText());

            if (email.equals("") || password.equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill the form", Toast.LENGTH_SHORT).show();
            } else {
                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler();
                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[2];
                    field[0] = "Email";
                    field[1] = "Password";
                    //Creating array for data
                    String[] data = new String[2];
                    data[0] = email;
                    data[1] = password;
                    PutData putData = new PutData("https://jomans.000webhostapp.com/login.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();

                            if(result.length() == 10) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("EXTRA_SESSION_ID", result);
                                startActivity(intent);

                                textInputEditTextEmail.setText(null);
                                textInputEditTextPassword.setText(null);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                });
            }

        });

    }
}