package seoil.capstone.som.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.model.PointData;

public class TestPointActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editId;
    private EditText editPoint;
    private Button btnSend;
    private Button btnUpdate;
    private Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_point);

        editId = findViewById(R.id.editMemberId);
        editPoint = findViewById(R.id.editPointAmount);
        btnSend = findViewById(R.id.btnSendCur);
        btnUpdate = findViewById(R.id.btnUpdateCur);
        btnGet = findViewById(R.id.btnGetCur);

        btnSend.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSendCur) {

            String id = editId.getText().toString();

            if (!id.isEmpty()) {

                OnFinishApiListener<PointData.StatusRes> onFinishApiListener = new OnFinishApiListener<PointData.StatusRes>() {
                    @Override
                    public void onSuccess(PointData.StatusRes statusRes) {

                        if (statusRes.getStatus() == PointApi.SUCCESS) {

                            showToast("성공");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().insertCurrentPoint(new PointData.InsertCurrentReq(id), onFinishApiListener);
            }
        } else if (v.getId() == R.id.btnGetCur) {

            String id = editId.getText().toString();

            if (!id.isEmpty()) {

                OnFinishApiListener<PointData.GetCurrentRes> onFinishApiListener = new OnFinishApiListener<PointData.GetCurrentRes>() {
                    @Override
                    public void onSuccess(PointData.GetCurrentRes getRes) {

                        if (getRes.getStatus() == PointApi.SUCCESS) {

                            showToast("성공");
                            editPoint.setText(String.valueOf(getRes.getPoint()));
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().getCurrentPoint(id, onFinishApiListener);
            }
        } else if (v.getId() == R.id.btnUpdateCur) {

            String id = editId.getText().toString();
            String strPoint = editPoint.getText().toString();

            if (!id.isEmpty() && !strPoint.isEmpty()) {

                int point = Integer.parseInt(strPoint);

                OnFinishApiListener<PointData.StatusRes> onFinishApiListener = new OnFinishApiListener<PointData.StatusRes>() {
                    @Override
                    public void onSuccess(PointData.StatusRes statusRes) {

                        if (statusRes.getStatus() == PointApi.SUCCESS) {

                            showToast("성공");
                        } else if (statusRes.getStatus() == PointApi.ERROR_NOT_ENOUGH_POINT) {

                            showToast("포인트 부족");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().updateCurrentPoint(new PointData.UpdateCurrentReq(id, point), onFinishApiListener);
            }
        }
    }

    private void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}