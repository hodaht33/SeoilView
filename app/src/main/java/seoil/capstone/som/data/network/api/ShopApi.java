package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.ShopDTO;
import seoil.capstone.som.data.network.model.retrofit.Shop;

// 매장 api
public class ShopApi {

    // 매장 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATE = 3;

    private Shop mShopInfo;

    public ShopApi(Retrofit retrofit) {

        mShopInfo = retrofit.create(Shop.class);
    }

    // 매장 정보 요청
    public void getShopInformation(String shopId, OnFinishApiListener onFinishApiListener) {

        Call<ShopDTO.GetRes> call = mShopInfo.getShopInformation(shopId);
        call.enqueue(new Callback<ShopDTO.GetRes>() {
            @Override
            public void onResponse(Call<ShopDTO.GetRes> call, Response<ShopDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 키워드로 검색되는 매장 정보 요청
    public void getShopInfoWithKeyword(String keyword, int page, OnFinishApiListener onFinishApiListener) {

        Call<ShopDTO.GetRes> call = mShopInfo.getShopInfoWithKeyword(keyword, page);
        call.enqueue(new Callback<ShopDTO.GetRes>() {
            @Override
            public void onResponse(Call<ShopDTO.GetRes> call, Response<ShopDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 카테고리로 검색되는 매장 정보 요청
    public void getShopInfoWithCategory(String category, int page, OnFinishApiListener onFinishApiListener) {

        Call<ShopDTO.GetRes> call = mShopInfo.getShopInfoWithCategory(category, page);
        call.enqueue(new Callback<ShopDTO.GetRes>() {
            @Override
            public void onResponse(Call<ShopDTO.GetRes> call, Response<ShopDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 매장 정보 추가 요청
    public void insertShopInfo(ShopDTO.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<ShopDTO.StatusRes> call = mShopInfo.insertShopInfo(req);
        call.enqueue(new Callback<ShopDTO.StatusRes>() {
            @Override
            public void onResponse(Call<ShopDTO.StatusRes> call, Response<ShopDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 매장 정보 수정 요청
    public void updateShopInfo(ShopDTO.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        Call<ShopDTO.StatusRes> call = mShopInfo.updateShopInfo(req);
        call.enqueue(new Callback<ShopDTO.StatusRes>() {
            @Override
            public void onResponse(Call<ShopDTO.StatusRes> call, Response<ShopDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 가게 정보 삭제 요청
    public void deleteShopInfo(String shopId, String shopCode, OnFinishApiListener onFinishApiListener) {

        Call<ShopDTO.StatusRes> call = mShopInfo.deleteShopInfo(shopId, shopCode);
        call.enqueue(new Callback<ShopDTO.StatusRes>() {
            @Override
            public void onResponse(Call<ShopDTO.StatusRes> call, Response<ShopDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ShopDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
