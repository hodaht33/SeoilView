package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.CurrentPoint;
import seoil.capstone.som.data.network.model.retrofit.Point;

public class PointRestApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_DATA = 2;
    public static final int ERROR_NOT_ENOUGH = 3;

    private Point mPointData;

    public PointRestApi(Retrofit retrofit) {

        mPointData = retrofit.create(Point.class);
    }

    public void getPoint(String id, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.GetRes> call = mPointData.getCurrentPoint(id);
        call.enqueue(new Callback<CurrentPoint.GetRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.GetRes> call, Response<CurrentPoint.GetRes> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void createTuple(CurrentPoint.PostReq req, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.StatusRes> call = mPointData.createPointTuple(req);
        call.enqueue(new Callback<CurrentPoint.StatusRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.StatusRes> call, Response<CurrentPoint.StatusRes> response) {

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updatePoint(CurrentPoint.PutReq req, OnFinishApiListener onFinishApiListener) {

        Call<CurrentPoint.StatusRes> call = mPointData.updatePoint(req);
        call.enqueue(new Callback<CurrentPoint.StatusRes>() {
            @Override
            public void onResponse(Call<CurrentPoint.StatusRes> call, Response<CurrentPoint.StatusRes> response) {

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

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CurrentPoint.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
