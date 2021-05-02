package seoil.capstone.som.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import seoil.capstone.som.R;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.PointApi;
import seoil.capstone.som.data.network.api.SalesApi;
import seoil.capstone.som.data.network.model.SalesInfo;
import seoil.capstone.som.data.network.model.SavePoint;
import seoil.capstone.som.data.network.model.ShopInfo;

public class TestSalesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editShopCode;
    private EditText editShopId;
    private EditText editSalesAmount;
    private Button btnSend;
    private Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sales);

        editShopCode = findViewById(R.id.editSalesShopCode);
        editShopId = findViewById(R.id.editSalesShopId);
        editSalesAmount = findViewById(R.id.editSalesAmount);
        btnSend = findViewById(R.id.btnSalesSend);
        btnGet = findViewById(R.id.btnSalesGet);

        btnSend.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSalesSend) {

            String shopCode = editShopCode.getText().toString();
            String shopId = editShopId.getText().toString();
            String salesAmount = editSalesAmount.getText().toString();

            if (!shopCode.isEmpty() && !shopId.isEmpty() && !salesAmount.isEmpty()) {

                int amount = Integer.parseInt(salesAmount);

                OnFinishApiListener<SalesInfo.StatusRes> onFinishApiListener = new OnFinishApiListener<SalesInfo.StatusRes>() {
                    @Override
                    public void onSuccess(SalesInfo.StatusRes statusRes) {

                        if (statusRes.getStatus() == SalesApi.SUCCESS) {

                            Log.d("test", "success");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().insertSalesInfo(new SalesInfo.InsertReq(shopCode, shopId, amount), onFinishApiListener);
            }

        } else if (v.getId() == R.id.btnSalesGet) {

            String shopId = editShopId.getText().toString();

            if (!shopId.isEmpty()) {

                OnFinishApiListener<SalesInfo.GetRes> onFinishApiListener = new OnFinishApiListener<SalesInfo.GetRes>() {
                    @Override
                    public void onSuccess(SalesInfo.GetRes getRes) {

                        if (getRes.getStatus() == SalesApi.SUCCESS) {

                            List<SalesInfo.GetRes.Result> list = getRes.getResults();
                            Log.d("test", list.toString());
                            Log.d("test", String.valueOf(list.size()));
                            for (SalesInfo.GetRes.Result result : list) {
                                Log.d("test", result.getSalesDate());
                                Log.d("test", String.valueOf(result.getSalesAmount()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().getSalesInfo(shopId, onFinishApiListener);
            }
        }
    }
}