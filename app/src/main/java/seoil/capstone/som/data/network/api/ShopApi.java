package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.ShopData;
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

    public void getShopInformation(String shopId, OnFinishApiListener onFinishApiListener) {

        Call<ShopData.GetRes> call = mShopInfo.getShopInformation(shopId);
        call.enqueue(new Callback<ShopData.GetRes>() {
            @Override
            public void onResponse(Call<ShopData.GetRes> call, Response<ShopData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getShopInfoWithKeyword(String keyword, int page, OnFinishApiListener onFinishApiListener) {

        Call<ShopData.GetRes> call = mShopInfo.getShopInfoWithKeyword(keyword, page);
        call.enqueue(new Callback<ShopData.GetRes>() {
            @Override
            public void onResponse(Call<ShopData.GetRes> call, Response<ShopData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getShopInfoWithCategory(String category, int page, OnFinishApiListener onFinishApiListener) {

        Call<ShopData.GetRes> call = mShopInfo.getShopInfoWithCategory(category, page);
        call.enqueue(new Callback<ShopData.GetRes>() {
            @Override
            public void onResponse(Call<ShopData.GetRes> call, Response<ShopData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertShopInfo(ShopData.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<ShopData.StatusRes> call = mShopInfo.insertShopInfo(req);
        call.enqueue(new Callback<ShopData.StatusRes>() {
            @Override
            public void onResponse(Call<ShopData.StatusRes> call, Response<ShopData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateShopInfo(ShopData.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        Call<ShopData.StatusRes> call = mShopInfo.updateShopInfo(req);
        call.enqueue(new Callback<ShopData.StatusRes>() {
            @Override
            public void onResponse(Call<ShopData.StatusRes> call, Response<ShopData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteShopInfo(String shopId, String shopCode, OnFinishApiListener onFinishApiListener) {

        Call<ShopData.StatusRes> call = mShopInfo.deleteShopInfo(shopId, shopCode);
        call.enqueue(new Callback<ShopData.StatusRes>() {
            @Override
            public void onResponse(Call<ShopData.StatusRes> call, Response<ShopData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
