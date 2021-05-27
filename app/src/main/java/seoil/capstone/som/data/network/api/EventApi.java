package seoil.capstone.som.data.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;
import seoil.capstone.som.data.network.model.retrofit.Event;
import seoil.capstone.som.data.network.model.retrofit.Point;

public class EventApi {

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int ERROR_UNDEFINED_VALUE = 2;
    public static final int ERROR_NONE_DATA = 3;

    private Event mEventData;

    public EventApi(Retrofit retrofit) {

        mEventData = retrofit.create(Event.class);
    }

    public void getEvent(String shopId, OnFinishApiListener onFinishApiListener) {

        Call<EventData.GetRes> call = mEventData.getEvent(shopId);
        call.enqueue(new Callback<EventData.GetRes>() {
            @Override
            public void onResponse(Call<EventData.GetRes> call, Response<EventData.GetRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventData.GetRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void getEventByCode(int eventCode, OnFinishApiListener onFinishApiListener) {

        Call<EventData.eventCodeRes> call = mEventData.getEventByCode(eventCode);
        call.enqueue(new Callback<EventData.eventCodeRes>() {
            @Override
            public void onResponse(Call<EventData.eventCodeRes> call, Response<EventData.eventCodeRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventData.eventCodeRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void insertEvent(EventData.InsertReq req, OnFinishApiListener onFinishApiListener) {

        Call<EventData.StatusRes> call = mEventData.insertEvent(req);
        call.enqueue(new Callback<EventData.StatusRes>() {
            @Override
            public void onResponse(Call<EventData.StatusRes> call, Response<EventData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void updateEvent(EventData.UpdateReq req, OnFinishApiListener onFinishApiListener) {

        Call<EventData.StatusRes> call = mEventData.updateEvent(req);
        call.enqueue(new Callback<EventData.StatusRes>() {
            @Override
            public void onResponse(Call<EventData.StatusRes> call, Response<EventData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }

    public void deleteEvent(int eventCode, OnFinishApiListener onFinishApiListener) {

        Call<EventData.StatusRes> call = mEventData.deleteEvent(eventCode);
        call.enqueue(new Callback<EventData.StatusRes>() {
            @Override
            public void onResponse(Call<EventData.StatusRes> call, Response<EventData.StatusRes> response) {

                if (AppApiHelper.getInstance().check404Error(response, onFinishApiListener)) {

                    return;
                }

                onFinishApiListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventData.StatusRes> call, Throwable t) {

                onFinishApiListener.onFailure(t);
            }
        });
    }
}
