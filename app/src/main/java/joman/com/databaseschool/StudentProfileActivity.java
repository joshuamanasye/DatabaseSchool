package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentProfileActivity extends AppCompatActivity {

    TextView fullName, gender, dob, phone, address, nisn, className;
    ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        home = findViewById(R.id.home_profile);
        home.setOnClickListener(view -> finish());

        User user = (User) getIntent().getSerializableExtra("EXTRA_USER");
        Student student = (Student) getIntent().getSerializableExtra("EXTRA_STUDENT");
        Class class_ = (Class) getIntent().getSerializableExtra("EXTRA_CLASS");

//        Toast.makeText(getApplicationContext(), class_.getClassName(), Toast.LENGTH_SHORT).show();

        fullName = findViewById(R.id.student_full_name);
        gender = findViewById(R.id.gender);
        dob = findViewById(R.id.dob);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        nisn = findViewById(R.id.nisn);
        className = findViewById(R.id.class_student);

        fullName.setText(user.getFullName());
        gender.setText(user.getGender());
        dob.setText(user.getDOB().toString());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());
        nisn.setText(student.getNisn());
        className.setText(class_.getClassName());

    }
}