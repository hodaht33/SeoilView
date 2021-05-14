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
import seoil.capstone.som.data.network.model.PointData;

public class TestUsingPointActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private EditText userId;
    private EditText shopCode;
    private EditText shopName;
    private EditText usingAmount;
    private Button btnSend;
    private Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_using_point);

        recyclerView = findViewById(R.id.recyclerView);
        userId = findViewById(R.id.editUserId);
        shopCode = findViewById(R.id.editShopCode);
        shopName = findViewById(R.id.editShopName);
        usingAmount = findViewById(R.id.editUsingAmount);
        btnSend = findViewById(R.id.btnSendUsing);
        btnGet = findViewById(R.id.btnGetUsing);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter());

        btnSend.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSendUsing) {

            String id = userId.getText().toString();
            String code = shopCode.getText().toString();
            String name = shopName.getText().toString();
            String strAmount = usingAmount.getText().toString();

            if (!id.isEmpty() && !code.isEmpty() && !name.isEmpty() && !strAmount.isEmpty()) {

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

                int amount = Integer.parseInt(strAmount);

                AppApiHelper.getInstance().insertUsingPoint(new PointData.InsertUsingReq(id, name, amount), onFinishApiListener);
            }

        } else if (v.getId() == R.id.btnGetUsing) {

            OnFinishApiListener<PointData.GetUsingRes> onFinishApiListener = new OnFinishApiListener<PointData.GetUsingRes>() {
                @Override
                public void onSuccess(PointData.GetUsingRes getRes) {

                    if (getRes.getStatus() == PointApi.SUCCESS) {

                        List<PointData.GetUsingRes.Result> list = getRes.getResults();
                        Log.d("test", list.toString());
                        Log.d("test", String.valueOf(list.size()));
                        for (PointData.GetUsingRes.Result result : list) {
                            Log.d("test", result.getUsingPointDate());
                            Log.d("test", result.getShopName());
                            Log.d("test", String.valueOf(result.getUsingPointAmount()));
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    Log.d("test", t.toString());
                }
            };

            AppApiHelper.getInstance().getUsingPoint(userId.getText().toString(), onFinishApiListener);
        }
    }

    private void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}