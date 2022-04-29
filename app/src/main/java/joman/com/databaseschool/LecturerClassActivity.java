package joman.com.databaseschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class LecturerClassActivity extends AppCompatActivity {

    ImageView homeImg;
    RecyclerView recyclerView;

    ArrayList<AssignmentView> listAssignment;

    AssignmentAdapter adapter;

    private void loadAssignments(Class class_) {

        if (class_ == null) {
            Toast.makeText(getApplicationContext(), "Class is null", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] field = new String[1];
        field[0] = "ClassID";

        String[] data = new String[1];
        data[0] = class_.getClassID();

        PutData putData = new PutData("https://jomans.000webhostapp.com/getAssignment.php",
                "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();
                try {
                    JSONArray assArr = new JSONArray(result);

                    for (int i = 0; i < assArr.length(); i++) {
                        JSONObject assObj = assArr.getJSONObject(i);

                        String subjectName = assObj.getString("SubjectName");
                        String assignment = assObj.getString("Assignment");
                        String deadline = assObj.getString("Deadline");

                        listAssignment.add(new AssignmentView(subjectName, assignment, deadline));
                    }


//                    Toast.makeText(getApplicationContext(),
//                            listAssignment.get(0).getSubject() + " " +
//                                    listAssignment.get(0).getAsg() + " " +
//                                    listAssignment.get(0).getDeadline()
//                            , Toast.LENGTH_LONG).show();

                    adapter = new AssignmentAdapter(this, listAssignment);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                AssignmentView assignmentView = new AssignmentView("tes", "tes", "2022-01-20");
//                listAssignment.add(new AssignmentView("tes", "tes", "2022-01-20"));
//
//                adapter = new AssignmentAdapter(this, listAssignment);
//                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_class);

        listAssignment = new ArrayList<>();

        recyclerView = findViewById(R.id.assignment_view_lecturer);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeImg = findViewById(R.id.class_home);
        homeImg.setOnClickListener(view -> finish());

        User user = (User) getIntent().getSerializableExtra("EXTRA_USER");
        Lecturer lecturer = (Lecturer) getIntent().getSerializableExtra("EXTRA_LECTURER");

        String[] nameWords = user.getFullName().split(" ");

        TextView lecturerName = findViewById(R.id.class_header_lecturert_name);
        lecturerName.setText(nameWords[0]);

        Class class_ = null;

        String[] field = new String[1];
        field[0] = "NIP";

        String[] data = new String[1];
        data[0] = lecturer.getNip();
        PutData putData = new PutData("https://jomans.000webhostapp.com/getLecturerClass.php",
                "POST", field, data);

        if (putData.startPut()) {
            if (putData.onComplete()) {
                String result = putData.getResult();

//                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                List<String> classData = Arrays.asList(result.split(","));

                if (classData.size() == 2) {
                    class_ = new Class(classData.get(0),
                            lecturer.getNip(),
                            classData.get(1));

                    TextView className = findViewById(R.id.class_name);
                    className.setText(class_.getClassName());

                    //TODO lecturer profile

                }
                else {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        }

        loadAssignments(class_);

    }
}