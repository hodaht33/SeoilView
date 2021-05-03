package seoil.capstone.som.data.network.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesInfo;
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

    public void getSalesInfo(String id, OnFinishApiListener<SalesInfo.GetRes> onFinishApiListener) {

        Call<SalesInfo.GetRes> call = mSalesInfo.getSalesInfo(id);
        call.enqueue(new Callback<SalesInfo.GetRes>() {
            @Override
            public void onResponse(Call<SalesInfo.GetRes> call, Response<SalesInfo.GetRes> response) {
                Log.d("test", response.toString());
                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesInfo.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertSalesInfo(SalesInfo.InsertReq req, OnFinishApiListener<SalesInfo.StatusRes> onFinishApiListener) {

        Call<SalesInfo.StatusRes> call = mSalesInfo.insertSalesInfo(req);
        call.enqueue(new Callback<SalesInfo.StatusRes>() {
            @Override
            public void onResponse(Call<SalesInfo.StatusRes> call, Response<SalesInfo.StatusRes> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesInfo.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
