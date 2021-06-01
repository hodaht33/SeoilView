package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Path;
import retrofit2.http.Query;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StatisticsData;
import seoil.capstone.som.data.network.model.retrofit.Statistics;

public class StatisticsApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATE = 3;

    private Statistics mStatisticsData;

    public StatisticsApi(Retrofit retrofit) {

        mStatisticsData = retrofit.create(Statistics.class);
    }

    public void getAgeGroupStatistics(String shopId, String startDate, String endDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsData.GetAgeGroupRes> call = mStatisticsData.getAgeGroupStatistics(shopId, startDate, endDate);
        call.enqueue(new Callback<StatisticsData.GetAgeGroupRes>() {
            @Override
            public void onResponse(Call<StatisticsData.GetAgeGroupRes> call, Response<StatisticsData.GetAgeGroupRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsData.GetAgeGroupRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getGenderStatistics(String shopId, String startDate, String endDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsData.GetGenderRes> call = mStatisticsData.getGenderStatistics(shopId, startDate, endDate);
        call.enqueue(new Callback<StatisticsData.GetGenderRes>() {
            @Override
            public void onResponse(Call<StatisticsData.GetGenderRes> call, Response<StatisticsData.GetGenderRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsData.GetGenderRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getDailySales(String shopId, String starDate, String endDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsData.GetDayRes> call = mStatisticsData.getDailySales(shopId, starDate, endDate);
        call.enqueue(new Callback<StatisticsData.GetDayRes>() {
            @Override
            public void onResponse(Call<StatisticsData.GetDayRes> call, Response<StatisticsData.GetDayRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsData.GetDayRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });

    }

    public void getWeeklySales(String shopId, String month, String startDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsData.GetWeekRes> call = mStatisticsData.getWeeklySales(shopId, month, startDate);
        call.enqueue(new Callback<StatisticsData.GetWeekRes>() {
            @Override
            public void onResponse(Call<StatisticsData.GetWeekRes> call, Response<StatisticsData.GetWeekRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsData.GetWeekRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getMonthlySales(String shopId, String year, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsData.GetMonthRes> call = mStatisticsData.getMonthlySales(shopId, year);
        call.enqueue(new Callback<StatisticsData.GetMonthRes>() {
            @Override
            public void onResponse(Call<StatisticsData.GetMonthRes> call, Response<StatisticsData.GetMonthRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsData.GetMonthRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertStatisticsData(StatisticsData.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsData.StatusRes> call = mStatisticsData.insertStatisticsData(req);
        call.enqueue(new Callback<StatisticsData.StatusRes>() {
            @Override
            public void onResponse(Call<StatisticsData.StatusRes> call, Response<StatisticsData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
