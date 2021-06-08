package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.StatisticsDTO;
import seoil.capstone.som.data.network.model.retrofit.Statistics;

// 통계 api
public class StatisticsApi {

    // 통계 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATE = 3;

    private Statistics mStatisticsData;

    public StatisticsApi(Retrofit retrofit) {

        mStatisticsData = retrofit.create(Statistics.class);
    }

    // 나이대 통계 요청
    public void getAgeGroupStatistics(String shopId, String startDate, String endDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsDTO.GetAgeGroupRes> call = mStatisticsData.getAgeGroupStatistics(shopId, startDate, endDate);
        call.enqueue(new Callback<StatisticsDTO.GetAgeGroupRes>() {
            @Override
            public void onResponse(Call<StatisticsDTO.GetAgeGroupRes> call, Response<StatisticsDTO.GetAgeGroupRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsDTO.GetAgeGroupRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 성별 통계 요청
    public void getGenderStatistics(String shopId, String startDate, String endDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsDTO.GetGenderRes> call = mStatisticsData.getGenderStatistics(shopId, startDate, endDate);
        call.enqueue(new Callback<StatisticsDTO.GetGenderRes>() {
            @Override
            public void onResponse(Call<StatisticsDTO.GetGenderRes> call, Response<StatisticsDTO.GetGenderRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsDTO.GetGenderRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 일별 매출 통계 요청
    public void getDailySales(String shopId, String starDate, String endDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsDTO.GetDayRes> call = mStatisticsData.getDailySales(shopId, starDate, endDate);
        call.enqueue(new Callback<StatisticsDTO.GetDayRes>() {
            @Override
            public void onResponse(Call<StatisticsDTO.GetDayRes> call, Response<StatisticsDTO.GetDayRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsDTO.GetDayRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });

    }

    // 주별 매출 통계 요청
    public void getWeeklySales(String shopId, String month, String startDate, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsDTO.GetWeekRes> call = mStatisticsData.getWeeklySales(shopId, month, startDate);
        call.enqueue(new Callback<StatisticsDTO.GetWeekRes>() {
            @Override
            public void onResponse(Call<StatisticsDTO.GetWeekRes> call, Response<StatisticsDTO.GetWeekRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsDTO.GetWeekRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 월별 매출 통계 요청
    public void getMonthlySales(String shopId, String year, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsDTO.GetMonthRes> call = mStatisticsData.getMonthlySales(shopId, year);
        call.enqueue(new Callback<StatisticsDTO.GetMonthRes>() {
            @Override
            public void onResponse(Call<StatisticsDTO.GetMonthRes> call, Response<StatisticsDTO.GetMonthRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsDTO.GetMonthRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 통계(방문 정보) 추가 요청
    public void insertStatisticsData(StatisticsDTO.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<StatisticsDTO.StatusRes> call = mStatisticsData.insertStatisticsData(req);
        call.enqueue(new Callback<StatisticsDTO.StatusRes>() {
            @Override
            public void onResponse(Call<StatisticsDTO.StatusRes> call, Response<StatisticsDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatisticsDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
