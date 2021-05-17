package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.ShopInfo;
import seoil.capstone.som.data.network.model.retrofit.Shop;

public class ShopApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATE = 3;

    private Shop mShopInfo;

    public ShopApi(Retrofit retrofit) {

        mShopInfo = retrofit.create(Shop.class);
    }

    public void getShopInfo(String id, OnFinishApiListener<ShopInfo.GetRes> onFinishApiListener) {

        Call<ShopInfo.GetRes> call = mShopInfo.getShopInfo(id);
        call.enqueue(new Callback<ShopInfo.GetRes>() {
            @Override
            public void onResponse(Call<ShopInfo.GetRes> call, Response<ShopInfo.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopInfo.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertShopInfo(ShopInfo.InsertReq req, OnFinishApiListener<ShopInfo.StatusRes> onFinishApiListener) {

        Call<ShopInfo.StatusRes> call = mShopInfo.insertShopInfo(req);
        call.enqueue(new Callback<ShopInfo.StatusRes>() {
            @Override
            public void onResponse(Call<ShopInfo.StatusRes> call, Response<ShopInfo.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopInfo.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
