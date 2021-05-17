package seoil.capstone.som.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.badge.BadgeUtils;

import seoil.capstone.som.R;

public class TestPhoneAuthActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextPhoneNumber;
    private EditText editTextAuthCode;
    private Button btnSend;
    private Button btnAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_phone_auth);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextAuthCode = findViewById(R.id.editTextAuthCode);
        btnSend = findViewById(R.id.btnAuthSend);
        btnAuth = findViewById(R.id.btnAuthAuth);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnAuthSend) {


        } else if (v.getId() == R.id.btnAuthAuth) {


        }
    }
}