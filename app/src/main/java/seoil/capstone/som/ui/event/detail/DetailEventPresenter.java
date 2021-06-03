package seoil.capstone.som.ui.event.detail;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.EventApi;
import seoil.capstone.som.data.network.model.EventData;

public class DetailEventPresenter implements DetailEventContract.Presenter {

    private DetailEventContract.View view;
    private DetailEventInteractor mInteractor;

    @Override
    public void setView(DetailEventContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        view = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new DetailEventInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    public void getEvent(int eventCode) {

        OnFinishApiListener<EventData.eventCodeRes> onFinishApiListener = new OnFinishApiListener<EventData.eventCodeRes>() {
            @Override
            public void onSuccess(EventData.eventCodeRes eventCodeRes) {

                Log.d("eventCode", String.valueOf(eventCodeRes.getStatus()));

                if (eventCodeRes.getStatus() == EventApi.SUCCESS) {

                    List<EventData.eventCodeRes.Result> list = eventCodeRes.getResults();

                    HashMap<String, String> data = new HashMap<>();

                    for (EventData.eventCodeRes.Result result : list) {

                        data.put("shopName", result.getShopName());
                        data.put("shopAddress", result.getShopAddress());
                        data.put("shopCategory", result.getShopCategory());
                        data.put("eventName", result.getEventName());
                        data.put("eventContent", result.getEventContents());
                        data.put("startDate", result.getEventStartDate());
                        data.put("endDate", result.getEventEndDate());
                    }

                    view.setEvent(data);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("eventCode", t.getMessage());
            }
        };

        mInteractor.getEventByCode(eventCode, onFinishApiListener);
    }

    public void updateEvent(int eventCode, String eventName, String eventContents, String startDate, String endDate) {

        OnFinishApiListener<EventData.StatusRes> onFinishApiListener = new OnFinishApiListener<EventData.StatusRes>() {
            @Override
            public void onSuccess(EventData.StatusRes statusRes) {

                Log.d("eventstatus", String.valueOf(statusRes.getStatus()));
                view.initDetailEvent();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.updateEvent(new EventData.UpdateReq(eventCode, eventName, eventContents, startDate, endDate), onFinishApiListener);
    }

    public void deleteEvent(int eventCode) {

        OnFinishApiListener<EventData.StatusRes> onFinishApiListener = new OnFinishApiListener<EventData.StatusRes>() {
            @Override
            public void onSuccess(EventData.StatusRes statusRes) {

                view.setDeleted();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.deleteEvent(eventCode, onFinishApiListener);
    }

    public String getDateQuery(int year, int month, int day) {

        String dateQuery;
        if (day < 10) {

            if (month < 10) {

                dateQuery = "" + year + "-0" + month + "-0" + day;
            } else {

                dateQuery = "" + year + "-" + month + "-0" + day;
            }

        } else {

            if (month < 10) {

                dateQuery = "" + year + "-0" + month + "-" + day;
            } else {

                dateQuery = "" + year + "-" + month + "-" + day;
            }
        }

        return dateQuery;
    }

    public Boolean isTextSet(String str) {

        return str != null && !str.equals("");
    }
}
