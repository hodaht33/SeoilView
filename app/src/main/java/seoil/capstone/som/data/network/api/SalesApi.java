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

    public void getIncomeSales(String shopId, String salesDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        Call<SalesData.GetRes> call = mSalesInfo.getIncomeSales(shopId, salesDate);
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

    public void getSpendingSales(String shopId, String salesDate, OnFinishApiListener<SalesData.GetRes> onFinishApiListener) {

        Call<SalesData.GetRes> call = mSalesInfo.getSpendingSales(shopId, salesDate);
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

    public void getSalesStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<SalesData.GetStatisticsRes> onFinishApiListener) {

        Call<SalesData.GetStatisticsRes> call = mSalesInfo.getStatisticsSales(shopId, startDate, endDate);
        call.enqueue(new Callback<SalesData.GetStatisticsRes>() {
            @Override
            public void onResponse(Call<SalesData.GetStatisticsRes> call, Response<SalesData.GetStatisticsRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesData.GetStatisticsRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertSalesWithDate(SalesData.Req req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        Call<SalesData.StatusRes> call = mSalesInfo.insertSalesWithDate(req);
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

    public void insertSales(SalesData.Req req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        Call<SalesData.StatusRes> call = mSalesInfo.insertSales(req);
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

    public void updateSpendingSales(SalesData.Req req, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        Call<SalesData.StatusRes> call = mSalesInfo.updateSpendingSales(req);
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

    public void deleteSpendingSales(String shopId, String salesDate, OnFinishApiListener<SalesData.StatusRes> onFinishApiListener) {

        Call<SalesData.StatusRes> call = mSalesInfo.deleteSpendingSales(shopId, salesDate);
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
