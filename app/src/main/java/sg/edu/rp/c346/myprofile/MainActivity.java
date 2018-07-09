package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvGPA;
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        tvGPA = findViewById(R.id.textViewGPA);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);

        // This is a new line

    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        Float fgpa = Float.parseFloat(etGPA.getText().toString());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Name", strName);
        prefEdit.putFloat("GPA", fgpa);
        prefEdit.putInt("gender", rgGender.getCheckedRadioButtonId());

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String name = prefs.getString("Name", "invalid name!");
        Float gpa = prefs.getFloat("GPA", 0);
        Integer gender = prefs.getInt("gender", 0);

        etName.setText(name);
        etGPA.setText(gpa.toString());
        rgGender.check(gender);

        Toast toast = Toast.makeText(getApplicationContext(), name + " " + gpa, Toast.LENGTH_LONG);
        toast.show();

    }
}
