package seoil.capstone.som.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.model.SavePoint;
import seoil.capstone.som.data.network.model.UsingPoint;

public class TestSavePointActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userId;
    private EditText shopCode;
    private EditText shopName;
    private EditText usingAmount;
    private Button btnSend;
    private Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_save_point);

        userId = findViewById(R.id.editSaveUserId);
        shopCode = findViewById(R.id.editSaveShopCode);
        shopName = findViewById(R.id.editSaveShopName);
        usingAmount = findViewById(R.id.editSaveAmount);
        btnSend = findViewById(R.id.btnSendSave);
        btnGet = findViewById(R.id.btnGetSave);

        btnSend.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSendSave) {

            String id = userId.getText().toString();
            String code = shopCode.getText().toString();
            String name = shopName.getText().toString();
            String strAmount = usingAmount.getText().toString();

            if (!id.isEmpty() && !code.isEmpty() && !name.isEmpty() && !strAmount.isEmpty()) {

                OnFinishApiListener<SavePoint.StatusRes> onFinishApiListener = new OnFinishApiListener<SavePoint.StatusRes>() {
                    @Override
                    public void onSuccess(SavePoint.StatusRes statusRes) {

                        if (statusRes.getStatus() == PointApi.SUCCESS) {

                            showToast("성공");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                int amount = Integer.parseInt(strAmount);

                AppApiHelper.getInstance().insertSavePoint(new SavePoint.InsertReq(id, code, name, amount), onFinishApiListener);
            }

        } else if (v.getId() == R.id.btnGetSave) {

            OnFinishApiListener<SavePoint.GetRes> onFinishApiListener = new OnFinishApiListener<SavePoint.GetRes>() {
                @Override
                public void onSuccess(SavePoint.GetRes getRes) {

                    if (getRes.getStatus() == PointApi.SUCCESS) {

                        List<SavePoint.GetRes.Result> list = getRes.getResults();
                        Log.d("test", list.toString());
                        Log.d("test", String.valueOf(list.size()));
                        for (SavePoint.GetRes.Result result : list) {
                            Log.d("test", result.getSavePointDate());
                            Log.d("test", result.getShopCode());
                            Log.d("test", result.getShopName());
                            Log.d("test", String.valueOf(result.getSavePointAmount()));
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    Log.d("test", t.toString());
                }
            };

            AppApiHelper.getInstance().getSavePoint(userId.getText().toString(), onFinishApiListener);
        }
    }

    private void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}