package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StockDTO;
import seoil.capstone.som.data.network.model.retrofit.Stock;

// 재고 api
public class StockApi {

    // 재고 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATE = 3;

    private Stock mStockData;

    public StockApi(Retrofit retrofit) {

        mStockData = retrofit.create(Stock.class);
    }

    // 재고 정보 요청
    public void getStock(String shopId , OnFinishApiListener<StockDTO.GetRes> onFinishApiListener) {

        Call<StockDTO.GetRes> call = mStockData.getStock(shopId);
        call.enqueue(new Callback<StockDTO.GetRes>() {
            @Override
            public void onResponse(Call<StockDTO.GetRes> call, Response<StockDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 재고 추가 요청
    public void insertStock(StockDTO.Req req, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        Call<StockDTO.StatusRes> call = mStockData.insertStock(req);
        call.enqueue(new Callback<StockDTO.StatusRes>() {
            @Override
            public void onResponse(Call<StockDTO.StatusRes> call, Response<StockDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 재고 수량 수정 요청
    public void updateStockAmount(StockDTO.Req req, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        Call<StockDTO.StatusRes> call = mStockData.updateStockAmount(req);
        call.enqueue(new Callback<StockDTO.StatusRes>() {
            @Override
            public void onResponse(Call<StockDTO.StatusRes> call, Response<StockDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 재고 수정 요청
    public void updateStock(StockDTO.UpdateAllReq req, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        Call<StockDTO.StatusRes> call = mStockData.updateStock(req);
        call.enqueue(new Callback<StockDTO.StatusRes>() {
            @Override
            public void onResponse(Call<StockDTO.StatusRes> call, Response<StockDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 재고 삭제 요청
    public void deleteStock(String shopId, String stockName, OnFinishApiListener<StockDTO.StatusRes> onFinishApiListener) {

        Call<StockDTO.StatusRes> call = mStockData.deleteStock(shopId, stockName);
        call.enqueue(new Callback<StockDTO.StatusRes>() {
            @Override
            public void onResponse(Call<StockDTO.StatusRes> call, Response<StockDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
