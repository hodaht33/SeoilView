package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesData;
import seoil.capstone.som.data.network.model.retrofit.Sales;

public class SalesApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;

    private Sales mSalesInfo;

    public SalesApi(Retrofit retrofit) {

        mSalesInfo = retrofit.create(Sales.class);
    }

    public void getSalesData(String id, String date, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        Call<SalesData.GetRes> call = mSalesInfo.getSalesData(id, date);
        call.enqueue(new Callback<SalesData.GetRes>() {
            @Override
            public void onResponse(Call<SalesData.GetRes> call, Response<SalesData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getSalesStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        Call<SalesData.GetRes> call = mSalesInfo.getSalesStatistics(shopId, startDate, endDate);
        call.enqueue(new Callback<SalesData.GetRes>() {
            @Override
            public void onResponse(Call<SalesData.GetRes> call, Response<SalesData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertSalesData(SalesData.InsertReq req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        Call<SalesData.StatusRes> call = mSalesInfo.insertSalesData(req);
        call.enqueue(new Callback<SalesData.StatusRes>() {
            @Override
            public void onResponse(Call<SalesData.StatusRes> call, Response<SalesData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
