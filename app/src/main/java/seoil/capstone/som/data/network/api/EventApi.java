package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventDataDTO;
import seoil.capstone.som.data.network.model.retrofit.Event;

// 이벤트 api
public class EventApi {

    // 이벤트 응답 코드
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;

    private Event mEventData;

    public EventApi(Retrofit retrofit) {

        mEventData = retrofit.create(Event.class);
    }

    // 이벤트 정보 요청
    public void getEvent(String shopId, OnFinishApiListener onFinishApiListener) {

        Call<EventDataDTO.GetRes> call = mEventData.getEvent(shopId);
        call.enqueue(new Callback<EventDataDTO.GetRes>() {
            @Override
            public void onResponse(Call<EventDataDTO.GetRes> call, Response<EventDataDTO.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventDataDTO.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 이벤트 코드로 이벤트 정보 요청
    public void getEventByCode(int eventCode, OnFinishApiListener onFinishApiListener) {

        Call<EventDataDTO.EventCodeRes> call = mEventData.getEventByCode(eventCode);
        call.enqueue(new Callback<EventDataDTO.EventCodeRes>() {
            @Override
            public void onResponse(Call<EventDataDTO.EventCodeRes> call, Response<EventDataDTO.EventCodeRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventDataDTO.EventCodeRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 현재 진행중인 이벤트 정보 요청
    public void getOngoingEvent(String userId, OnFinishApiListener onFinishApiListener) {

        Call<EventDataDTO.OngoingEventRes> call = mEventData.getOngoingEvent(userId);
        call.enqueue(new Callback<EventDataDTO.OngoingEventRes>() {
            @Override
            public void onResponse(Call<EventDataDTO.OngoingEventRes> call, Response<EventDataDTO.OngoingEventRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventDataDTO.OngoingEventRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 이벤트 추가 요청
    public void insertEvent(EventDataDTO.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<EventDataDTO.StatusRes> call = mEventData.insertEvent(req);
        call.enqueue(new Callback<EventDataDTO.StatusRes>() {
            @Override
            public void onResponse(Call<EventDataDTO.StatusRes> call, Response<EventDataDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventDataDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 이벤트 수정 요청
    public void updateEvent(EventDataDTO.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        Call<EventDataDTO.StatusRes> call = mEventData.updateEvent(req);
        call.enqueue(new Callback<EventDataDTO.StatusRes>() {
            @Override
            public void onResponse(Call<EventDataDTO.StatusRes> call, Response<EventDataDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventDataDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    // 이벤트 삭제 요청
    public void deleteEvent(int eventCode, OnFinishApiListener onFinishApiListener) {

        Call<EventDataDTO.StatusRes> call = mEventData.deleteEvent(eventCode);
        call.enqueue(new Callback<EventDataDTO.StatusRes>() {
            @Override
            public void onResponse(Call<EventDataDTO.StatusRes> call, Response<EventDataDTO.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventDataDTO.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
