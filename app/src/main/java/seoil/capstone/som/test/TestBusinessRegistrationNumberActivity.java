package seoil.capstone.som.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.Check;

public class TestBusinessRegistrationNumberActivity extends AppCompatActivity {

    private EditText editRegNum;
    private Button btnSend;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_business_registration_number);

        editRegNum = findViewById(R.id.editRegNum);
        btnSend = findViewById(R.id.btnRegNumSend);
        textResult = findViewById(R.id.textRegNumResult);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OnFinishApiListener<Check.StatusRes> onFinishApiListener = new OnFinishApiListener<Check.StatusRes>() {
                    @Override
                    public void onSuccess(Check.StatusRes statusRes) {

                        if (statusRes.getStatus() == UserApi.SUCCESS) {

                            textResult.setText("성공");
                        } else {

                            textResult.setText("실패");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().checkRegistrationNumber(editRegNum.getText().toString(), onFinishApiListener);
            }
        });
    }
}