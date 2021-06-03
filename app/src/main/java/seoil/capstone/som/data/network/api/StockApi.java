package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StockData;
import seoil.capstone.som.data.network.model.retrofit.Stock;

public class StockApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATE = 3;

    private Stock mStockData;

    public StockApi(Retrofit retrofit) {

        mStockData = retrofit.create(Stock.class);
    }

    public void getStock(String shopId , OnFinishApiListener<StockData.GetRes> onFinishApiListener) {

        Call<StockData.GetRes> call = mStockData.getStock(shopId);
        call.enqueue(new Callback<StockData.GetRes>() {
            @Override
            public void onResponse(Call<StockData.GetRes> call, Response<StockData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertStock(StockData.Req req, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        Call<StockData.StatusRes> call = mStockData.insertStock(req);
        call.enqueue(new Callback<StockData.StatusRes>() {
            @Override
            public void onResponse(Call<StockData.StatusRes> call, Response<StockData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateStockAmount(StockData.Req req, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        Call<StockData.StatusRes> call = mStockData.updateStockAmount(req);
        call.enqueue(new Callback<StockData.StatusRes>() {
            @Override
            public void onResponse(Call<StockData.StatusRes> call, Response<StockData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateStock(StockData.UpdateAllReq req, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        Call<StockData.StatusRes> call = mStockData.updateStock(req);
        call.enqueue(new Callback<StockData.StatusRes>() {
            @Override
            public void onResponse(Call<StockData.StatusRes> call, Response<StockData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteStock(String shopId, String stockName, OnFinishApiListener<StockData.StatusRes> onFinishApiListener) {

        Call<StockData.StatusRes> call = mStockData.deleteStock(shopId, stockName);
        call.enqueue(new Callback<StockData.StatusRes>() {
            @Override
            public void onResponse(Call<StockData.StatusRes> call, Response<StockData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StockData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
