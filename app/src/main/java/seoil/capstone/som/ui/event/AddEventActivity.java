package seoil.capstone.som.ui.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import seoil.capstone.som.R;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextTitle;
    private EditText mEditTextContents;
    private Button mBtnStartDate;
    private Button mBtnEndDate;
    private Button mBtnFinish;
    private CheckBox mCheckPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mEditTextTitle=(EditText)findViewById(R.id.editTextAddEventTitle);
        mEditTextContents=(EditText)findViewById(R.id.editTextAddEventContents);
        mBtnStartDate=(Button)findViewById(R.id.btnAddEventStartDate);
        mBtnEndDate=(Button)findViewById(R.id.btnAddEventEndDate);
        mBtnFinish=(Button)findViewById(R.id.btnAddEventFinish);
        mCheckPush=(CheckBox)findViewById(R.id.checkAddEventPush);

        mBtnStartDate.setOnClickListener(this);
        mBtnEndDate.setOnClickListener(this);
        mBtnFinish.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "완료", Toast.LENGTH_SHORT).show();
    }
}