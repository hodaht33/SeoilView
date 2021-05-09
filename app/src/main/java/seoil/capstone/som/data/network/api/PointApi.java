package seoil.capstone.som.data.network.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.CurrentPoint;
import seoil.capstone.som.data.network.model.SavePoint;
import seoil.capstone.som.data.network.model.UsingPoint;
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

    public void getPoint(String id, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.GetRes> call = mPointData.getCurrentPoint(id);
        call.enqueue(new Callback<CurrentPoint.GetRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.GetRes> call, Response<CurrentPoint.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertPointTuple(CurrentPoint.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.StatusRes> call = mPointData.insertPointTuple(req);
        call.enqueue(new Callback<CurrentPoint.StatusRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.StatusRes> call, Response<CurrentPoint.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updatePoint(CurrentPoint.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.StatusRes> call = mPointData.updatePoint(req);
        call.enqueue(new Callback<CurrentPoint.StatusRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.StatusRes> call, Response<CurrentPoint.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteTuple(String id, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.StatusRes> call = mPointData.deletePointTuple(id);
        call.enqueue(new Callback<CurrentPoint.StatusRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.StatusRes> call, Response<CurrentPoint.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getUsingPoint(String id, OnFinishApiListener onFinishApiListener) {

        Call<UsingPoint.GetRes> call = mPointData.getUsingPoint(id);
        call.enqueue(new Callback<UsingPoint.GetRes>() {
            @Override
            public void onResponse(Call<UsingPoint.GetRes> call, Response<UsingPoint.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UsingPoint.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertUsingPoint(UsingPoint.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<UsingPoint.StatusRes> call = mPointData.insertUsingPointTuple(req);
        call.enqueue(new Callback<UsingPoint.StatusRes>() {
            @Override
            public void onResponse(Call<UsingPoint.StatusRes> call, Response<UsingPoint.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UsingPoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getSavePoint(String id, OnFinishApiListener onFinishApiListener) {

        Call<SavePoint.GetRes> call = mPointData.getSavePoint(id);
        call.enqueue(new Callback<SavePoint.GetRes>() {
            @Override
            public void onResponse(Call<SavePoint.GetRes> call, Response<SavePoint.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SavePoint.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertSavePoint(SavePoint.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<SavePoint.StatusRes> call = mPointData.insertSavePointTuple(req);
        call.enqueue(new Callback<SavePoint.StatusRes>() {
            @Override
            public void onResponse(Call<SavePoint.StatusRes> call, Response<SavePoint.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SavePoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
