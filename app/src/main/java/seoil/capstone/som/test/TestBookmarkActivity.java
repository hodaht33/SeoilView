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
import seoil.capstone.som.data.network.api.BookmarkApi;
import seoil.capstone.som.data.network.model.BookmarkInfo;

public class TestBookmarkActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editUserId;
    private EditText editShopCode;
    private EditText editShopId;
    private Button btnSend;
    private Button btnGetShop;
    private Button btnGetUser;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bookmark);

        editUserId = findViewById(R.id.editBookmarkUserId);
        editShopCode = findViewById(R.id.editBookmarkShopCode);
        editShopId = findViewById(R.id.editBookmarkShopId);
        btnSend = findViewById(R.id.btnBookmarkSend);
        btnGetShop = findViewById(R.id.btnBookmarkGetShop);
        btnGetUser = findViewById(R.id.btnBookmarkGetUser);
        btnDelete = findViewById(R.id.btnBookmarkDelete);

        btnSend.setOnClickListener(this);
        btnGetShop.setOnClickListener(this);
        btnGetUser.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnBookmarkSend) {

            String userId = editUserId.getText().toString();
            String shopCode = editShopCode.getText().toString();
            String shopId = editShopId.getText().toString();

            if (!userId.isEmpty() && !shopCode.isEmpty() && !shopId.isEmpty()) {

                OnFinishApiListener<BookmarkInfo.StatusRes> onFinishApiListener = new OnFinishApiListener<BookmarkInfo.StatusRes>() {
                    @Override
                    public void onSuccess(BookmarkInfo.StatusRes statusRes) {

                        if (statusRes.getStatus() == BookmarkApi.SUCCESS) {

                            Log.d("test", "성공");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().addBookmark(new BookmarkInfo.InsertReq(userId, shopId), onFinishApiListener);
            }
        } else if (viewId == R.id.btnBookmarkGetShop) {

            String userId = editUserId.getText().toString();

            if (!userId.isEmpty()) {

                OnFinishApiListener<BookmarkInfo.ShopInfoRes> onFinishApiListener = new OnFinishApiListener<BookmarkInfo.ShopInfoRes>() {
                    @Override
                    public void onSuccess(BookmarkInfo.ShopInfoRes statusRes) {

                        if (statusRes.getStatus() == BookmarkApi.SUCCESS) {

                            List<BookmarkInfo.ShopInfoRes.Result> list = statusRes.getResults();
                            for (BookmarkInfo.ShopInfoRes.Result res : list) {

                                Log.d("test", res.getShopId());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().getBookmarkShopInfo(userId, onFinishApiListener);
            }
        } else if (viewId == R.id.btnBookmarkGetUser) {

            String shopId = editShopId.getText().toString();

            if (!shopId.isEmpty()) {

                OnFinishApiListener<BookmarkInfo.UserInfoRes> onFinishApiListener = new OnFinishApiListener<BookmarkInfo.UserInfoRes>() {
                    @Override
                    public void onSuccess(BookmarkInfo.UserInfoRes statusRes) {

                        if (statusRes.getStatus() == BookmarkApi.SUCCESS) {

                            List<BookmarkInfo.UserInfoRes.Result> list = statusRes.getResults();
                            for (BookmarkInfo.UserInfoRes.Result res : list) {

                                Log.d("test", res.getUserId());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().getBookmarkUserInfo(shopId, onFinishApiListener);
            }
        } else if (viewId == R.id.btnBookmarkDelete) {

            String userId = editUserId.getText().toString();
            String shopCode = editShopCode.getText().toString();
            String shopId = editShopId.getText().toString();

            if (!userId.isEmpty() && !shopCode.isEmpty() && !shopId.isEmpty()) {

                OnFinishApiListener<BookmarkInfo.StatusRes> onFinishApiListener = new OnFinishApiListener<BookmarkInfo.StatusRes>() {
                    @Override
                    public void onSuccess(BookmarkInfo.StatusRes statusRes) {

                        if (statusRes.getStatus() == BookmarkApi.SUCCESS) {

                            Log.d("test", "성공");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                        Log.d("test", t.toString());
                    }
                };

                AppApiHelper.getInstance().deleteBookmark(userId, shopCode, shopId, onFinishApiListener);
            }
        }
    }
}