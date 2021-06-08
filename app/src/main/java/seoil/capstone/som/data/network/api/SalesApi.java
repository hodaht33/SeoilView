package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.SalesDTO;
import seoil.capstone.som.data.network.model.retrofit.Sales;

// 매출 api
public class SalesApi {

    // 매출 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;

    private Sales mSalesInfo;

    public SalesApi(Retrofit retrofit) {

        mSalesInfo = retrofit.create(Sales.class);
    }

    // 수입 요청
    public void getIncomeSales(String shopId, String salesDate, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener) {

        Call<SalesDTO.GetRes> call = mSalesInfo.getIncomeSales(shopId, salesDate);
        call.enqueue(new Callback<SalesDTO.GetRes>() {
            @Override
            public void onResponse(Call<SalesDTO.GetRes> call, Response<SalesDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 지출 요청
    public void getSpendingSales(String shopId, String salesDate, OnFinishApiListener<SalesDTO.GetRes> onFinishApiListener) {

        Call<SalesDTO.GetRes> call = mSalesInfo.getSpendingSales(shopId, salesDate);
        call.enqueue(new Callback<SalesDTO.GetRes>() {
            @Override
            public void onResponse(Call<SalesDTO.GetRes> call, Response<SalesDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 매출 통계 요청
    public void getSalesStatistics(String shopId, String startDate, String endDate, OnFinishApiListener<SalesDTO.GetStatisticsRes> onFinishApiListener) {

        Call<SalesDTO.GetStatisticsRes> call = mSalesInfo.getStatisticsSales(shopId, startDate, endDate);
        call.enqueue(new Callback<SalesDTO.GetStatisticsRes>() {
            @Override
            public void onResponse(Call<SalesDTO.GetStatisticsRes> call, Response<SalesDTO.GetStatisticsRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.GetStatisticsRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 날짜와 함께 매출 추가 요청
    public void insertSalesWithDate(SalesDTO.Req req, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        Call<SalesDTO.StatusRes> call = mSalesInfo.insertSalesWithDate(req);
        call.enqueue(new Callback<SalesDTO.StatusRes>() {
            @Override
            public void onResponse(Call<SalesDTO.StatusRes> call, Response<SalesDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 매출 추가 요청(금일 날짜로 추가)
    public void insertSales(SalesDTO.Req req, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        Call<SalesDTO.StatusRes> call = mSalesInfo.insertSales(req);
        call.enqueue(new Callback<SalesDTO.StatusRes>() {
            @Override
            public void onResponse(Call<SalesDTO.StatusRes> call, Response<SalesDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 지출 수정 요청
    public void updateSpendingSales(SalesDTO.Req req, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        Call<SalesDTO.StatusRes> call = mSalesInfo.updateSpendingSales(req);
        call.enqueue(new Callback<SalesDTO.StatusRes>() {
            @Override
            public void onResponse(Call<SalesDTO.StatusRes> call, Response<SalesDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 지출 삭제 요청
    public void deleteSpendingSales(String shopId, int salesCode, String salesDate, OnFinishApiListener<SalesDTO.StatusRes> onFinishApiListener) {

        Call<SalesDTO.StatusRes> call = mSalesInfo.deleteSpendingSales(shopId, salesCode, salesDate);
        call.enqueue(new Callback<SalesDTO.StatusRes>() {
            @Override
            public void onResponse(Call<SalesDTO.StatusRes> call, Response<SalesDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SalesDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
