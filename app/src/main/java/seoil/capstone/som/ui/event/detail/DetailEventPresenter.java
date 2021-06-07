package seoil.capstone.som.ui.event.detail;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.EventApi;
import seoil.capstone.som.data.network.model.EventData;

// 이벤트 상세 정보 프레젠터
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

    // 이벤트 요청
    public void getEvent(int eventCode) {

        OnFinishApiListener<EventData.EventCodeRes> onFinishApiListener = new OnFinishApiListener<EventData.EventCodeRes>() {
            @Override
            public void onSuccess(EventData.EventCodeRes eventCodeRes) {

                Log.d("eventCode", String.valueOf(eventCodeRes.getStatus()));

                if (eventCodeRes.getStatus() == EventApi.SUCCESS) {

                    List<EventData.EventCodeRes.Result> list = eventCodeRes.getResults();

                    HashMap<String, String> data = new HashMap<>();

                    for (EventData.EventCodeRes.Result result : list) {

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

    // 이벤트 수정 요청
    public void updateEvent(int eventCode, String eventName, String eventContents, String startDate, String endDate) {

        OnFinishApiListener<EventData.StatusRes> onFinishApiListener = new OnFinishApiListener<EventData.StatusRes>() {
            @Override
            public void onSuccess(EventData.StatusRes statusRes) {

                Log.d("eventstatus", String.valueOf(statusRes.getStatus()));

                if (statusRes.getStatus() == EventApi.SUCCESS) {

                    view.finishDetailEvent();
                }

            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("eventstatus", String.valueOf(t.getMessage()));
            }
        };

        mInteractor.updateEvent(new EventData.UpdateReq(eventCode, eventName, eventContents, startDate, endDate), onFinishApiListener);
    }

    // 이벤트 삭제 요청
    public void deleteEvent(int eventCode) {

        OnFinishApiListener<EventData.StatusRes> onFinishApiListener = new OnFinishApiListener<EventData.StatusRes>() {
            @Override
            public void onSuccess(EventData.StatusRes statusRes) {

                if (statusRes.getStatus() == EventApi.SUCCESS) {

                    view.setDeleted();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.deleteEvent(eventCode, onFinishApiListener);
    }

    // 날짜 포맷에 맞게 수정
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

    // 문자열이 널이 아닌지 검사
    public Boolean isTextSet(String str) {

        return str != null && !str.equals("");
    }
}
