package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.PointData;
import seoil.capstone.som.data.network.model.retrofit.Point;

public class PointApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;
    public static final int ERROR_NOT_ENOUGH_POINT = 4;

    private Point mPointData;

    public PointApi(Retrofit retrofit) {

        mPointData = retrofit.create(Point.class);
    }

    public void getCurrentPoint(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointData.GetCurrentRes> call = mPointData.getCurrentPoint(id);
        call.enqueue(new Callback<PointData.GetCurrentRes>() {
            @Override
            public void onResponse(Call<PointData.GetCurrentRes> call, Response<PointData.GetCurrentRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.GetCurrentRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertCurrentPointTuple(PointData.InsertCurrentReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointData.StatusRes> call = mPointData.insertPointTuple(req);
        call.enqueue(new Callback<PointData.StatusRes>() {
            @Override
            public void onResponse(Call<PointData.StatusRes> call, Response<PointData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateCurrentPoint(PointData.UpdateCurrentReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointData.StatusRes> call = mPointData.updatePoint(req);
        call.enqueue(new Callback<PointData.StatusRes>() {
            @Override
            public void onResponse(Call<PointData.StatusRes> call, Response<PointData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteCurrentTuple(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointData.StatusRes> call = mPointData.deletePointTuple(id);
        call.enqueue(new Callback<PointData.StatusRes>() {
            @Override
            public void onResponse(Call<PointData.StatusRes> call, Response<PointData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getUsingPointData(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointData.GetUsingRes> call = mPointData.getUsingPoint(id);
        call.enqueue(new Callback<PointData.GetUsingRes>() {
            @Override
            public void onResponse(Call<PointData.GetUsingRes> call, Response<PointData.GetUsingRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.GetUsingRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertUsingPointData(PointData.InsertUsingReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointData.StatusRes> call = mPointData.insertUsingPointTuple(req);
        call.enqueue(new Callback<PointData.StatusRes>() {
            @Override
            public void onResponse(Call<PointData.StatusRes> call, Response<PointData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getSavePointData(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointData.GetSaveRes> call = mPointData.getSavePoint(id);
        call.enqueue(new Callback<PointData.GetSaveRes>() {
            @Override
            public void onResponse(Call<PointData.GetSaveRes> call, Response<PointData.GetSaveRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.GetSaveRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertSavePointData(PointData.InsertSaveReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointData.StatusRes> call = mPointData.insertSavePointTuple(req);
        call.enqueue(new Callback<PointData.StatusRes>() {
            @Override
            public void onResponse(Call<PointData.StatusRes> call, Response<PointData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
