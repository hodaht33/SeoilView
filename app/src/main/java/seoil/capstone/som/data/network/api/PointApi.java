package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.PointDTO;
import seoil.capstone.som.data.network.model.retrofit.Point;

// 포인트 api
public class PointApi {

    // 포인트 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;
    public static final int ERROR_NOT_ENOUGH_POINT = 4;

    private Point mPointData;

    public PointApi(Retrofit retrofit) {

        mPointData = retrofit.create(Point.class);
    }

    // 잔여 포인트 요청
    public void getCurrentPoint(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.GetCurrentRes> call = mPointData.getCurrentPoint(id);
        call.enqueue(new Callback<PointDTO.GetCurrentRes>() {
            @Override
            public void onResponse(Call<PointDTO.GetCurrentRes> call, Response<PointDTO.GetCurrentRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.GetCurrentRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 잔여 포인트 튜플 생성 요청
    public void insertCurrentPointTuple(PointDTO.InsertCurrentReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.StatusRes> call = mPointData.insertPointTuple(req);
        call.enqueue(new Callback<PointDTO.StatusRes>() {
            @Override
            public void onResponse(Call<PointDTO.StatusRes> call, Response<PointDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 잔여 포인트 수정 요청
    public void updateCurrentPoint(PointDTO.UpdateCurrentReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.StatusRes> call = mPointData.updatePoint(req);
        call.enqueue(new Callback<PointDTO.StatusRes>() {
            @Override
            public void onResponse(Call<PointDTO.StatusRes> call, Response<PointDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 잔여 포인트 튜플 삭제 요청
    public void deleteCurrentTuple(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.StatusRes> call = mPointData.deletePointTuple(id);
        call.enqueue(new Callback<PointDTO.StatusRes>() {
            @Override
            public void onResponse(Call<PointDTO.StatusRes> call, Response<PointDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 포인트 사용 내역 요청
    public void getUsingPointData(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.GetUsingRes> call = mPointData.getUsingPoint(id);
        call.enqueue(new Callback<PointDTO.GetUsingRes>() {
            @Override
            public void onResponse(Call<PointDTO.GetUsingRes> call, Response<PointDTO.GetUsingRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.GetUsingRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 포인트 사용 내역 추가
    public void insertUsingPointData(PointDTO.InsertUsingReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.StatusRes> call = mPointData.insertUsingPointTuple(req);
        call.enqueue(new Callback<PointDTO.StatusRes>() {
            @Override
            public void onResponse(Call<PointDTO.StatusRes> call, Response<PointDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 포인트 적립 내역 요청
    public void getSavePointData(String id, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.GetSaveRes> call = mPointData.getSavePoint(id);
        call.enqueue(new Callback<PointDTO.GetSaveRes>() {
            @Override
            public void onResponse(Call<PointDTO.GetSaveRes> call, Response<PointDTO.GetSaveRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.GetSaveRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 포인트 적립 내역 추가
    public void insertSavePointData(PointDTO.InsertSaveReq req, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.StatusRes> call = mPointData.insertSavePointTuple(req);
        call.enqueue(new Callback<PointDTO.StatusRes>() {
            @Override
            public void onResponse(Call<PointDTO.StatusRes> call, Response<PointDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 포인트 사용 내역과 적립 내역 한번에 요청
    public void getUsingAndSavePointData(String userId, OnFinishApiListener onFinishApiListener) {

        Call<PointDTO.GetUsingAndSaveRes> call = mPointData.getUsingAndSavePoint(userId);
        call.enqueue(new Callback<PointDTO.GetUsingAndSaveRes>() {
            @Override
            public void onResponse(Call<PointDTO.GetUsingAndSaveRes> call, Response<PointDTO.GetUsingAndSaveRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PointDTO.GetUsingAndSaveRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
