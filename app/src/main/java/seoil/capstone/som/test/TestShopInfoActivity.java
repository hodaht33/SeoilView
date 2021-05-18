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
import seoil.capstone.som.data.network.api.ShopApi;
import seoil.capstone.som.data.network.model.ShopData;

public class TestShopInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText shopCode;
    private EditText userId;
    private EditText shopName;
    private EditText shopAddress;
    private EditText shopCategory;
    private Button btnSend;
    private Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shop_info);

        shopCode = findViewById(R.id.editShopInfoCode);
        userId = findViewById(R.id.editShopInfoUserId);
        shopName = findViewById(R.id.editShopInfoName);
        shopAddress = findViewById(R.id.editShopInfoAddress);
        shopCategory = findViewById(R.id.editShopInfoCategory);
        btnSend = findViewById(R.id.btnShopInfoSend);
        btnGet = findViewById(R.id.btnShopInfoGet);

        btnSend.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnShopInfoSend) {

            String code = shopCode.getText().toString();
            String id = userId.getText().toString();
            String shName = shopName.getText().toString();
            String shAdr = shopAddress.getText().toString();
            String category = shopCategory.getText().toString();

            if (!code.isEmpty()
                && !id.isEmpty()
                && !shName.isEmpty()
                && !shAdr.isEmpty()
                && !category.isEmpty()) {

                OnFinishApiListener<ShopData.StatusRes> onFinishApiListener = new OnFinishApiListener<ShopData.StatusRes>() {
                    @Override
                    public void onSuccess(ShopData.StatusRes statusRes) {

                        if (statusRes.getStatus() == ShopApi.SUCCESS) {

                            Log.d("test", "success");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                //AppApiHelper.getInstance().insertShopInfo(new ShopData.InsertReq(id, shName, shAdr, category), onFinishApiListener);
            }

        } else if (v.getId() == R.id.btnShopInfoGet) {

            String id = userId.getText().toString();

            if (!id.isEmpty()) {

                OnFinishApiListener<ShopData.GetRes> onFinishApiListener = new OnFinishApiListener<ShopData.GetRes>() {
                    @Override
                    public void onSuccess(ShopData.GetRes getRes) {

                        List<ShopData.GetRes.Result> list = getRes.getResults();
                        Log.d("test", list.toString());
                        Log.d("test", String.valueOf(list.size()));
                        for (ShopData.GetRes.Result result : list) {
                            Log.d("test", result.getShopName());
                            Log.d("test", result.getShopAddress());
                            Log.d("test", result.getShopCategory());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                //AppApiHelper.getInstance().getShopInfoWithKeyword(id, onFinishApiListener);
            }

        }
    }
}