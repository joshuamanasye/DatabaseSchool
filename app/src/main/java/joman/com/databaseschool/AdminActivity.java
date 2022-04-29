package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class AdminActivity extends AppCompatActivity {

    ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(view -> finish());
    }
}