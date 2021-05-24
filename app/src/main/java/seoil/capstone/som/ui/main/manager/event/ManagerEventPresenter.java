package seoil.capstone.som.ui.main.manager.event;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.EventApi;
import seoil.capstone.som.data.network.model.EventData;

public class ManagerEventPresenter implements ManagerEventContract.Presenter {

    private ManagerEventContract.View view;
    private ManagerEventInteractor mInteractor;

    @Override
    public void setView(ManagerEventContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new ManagerEventInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    public synchronized void getEvent(String shopId) {

        OnFinishApiListener<EventData.GetRes> onFinishApiListener = new OnFinishApiListener<EventData.GetRes>() {
            @Override
            public void onSuccess(EventData.GetRes getRes) {

                Log.d("MEvent", String.valueOf(getRes.getStatus()));

                if (getRes.getStatus() == EventApi.SUCCESS) {

                    List<EventData.GetRes.Result> list = getRes.getResults();

                    ArrayList<Integer> eventCodeInProgress = new ArrayList<>();
                    ArrayList<Integer> eventCodeEnd = new ArrayList<>();
                    ArrayList<String> eventNameInProgress = new ArrayList<>();
                    ArrayList<String> eventEndDateInProgress = new ArrayList<>();

                    ArrayList<String> eventNameEnd = new ArrayList<>();
                    ArrayList<String> eventEndDateEnd = new ArrayList<>();
                    ArrayList<String> eventStartDateInProgress = new ArrayList<>();
                    ArrayList<String> eventStartDateEnd = new ArrayList<>();

                    if (list == null) {

                        eventNameInProgress.add("진행중인 이벤트가 없습니다.");
                        eventEndDateInProgress.add("");
                        eventCodeInProgress.add(null);
                        eventStartDateInProgress.add("");

                        eventNameEnd.add("종료된 이벤트가 없습니다.");
                        eventEndDateEnd.add("");
                        eventCodeEnd.add(null);
                        eventStartDateEnd.add("");
                    } else {

                        int year, month, day;
                        Date date = new Date();
                        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String nowDate = simpleDateFormat.format(date);
                        
                        year = Integer.parseInt(nowDate.substring(0, 4));
                        month = Integer.parseInt(nowDate.substring(5, 7));
                        day = Integer.parseInt(nowDate.substring(8));

                        for (EventData.GetRes.Result result : list) {

                            String dateResult = result.getEndDate();
                            int yearResult = Integer.parseInt(dateResult.substring(0, 4));
                            int monthResult = Integer.parseInt(dateResult.substring(5, 7));
                            int dayResult = Integer.parseInt(dateResult.substring(8, 10));



                            if (year <= yearResult && month <= monthResult && day <= dayResult) {

                                eventNameInProgress.add(result.getEventName());
                                eventCodeInProgress.add(result.getEventCode());
                                eventEndDateInProgress.add(result.getEndDate());
                                eventStartDateInProgress.add(result.getStartDate());
                            } else {

                                eventNameEnd.add(result.getEventName());
                                eventCodeEnd.add(result.getEventCode());
                                eventEndDateEnd.add(result.getEndDate());
                                eventStartDateEnd.add(result.getStartDate());
                            }
                        }

                        if (eventCodeEnd == null) {

                            eventNameEnd.add("종료된 이벤트가 없습니다.");
                            eventEndDateEnd.add(null);
                            eventCodeEnd.add(null);
                            eventStartDateEnd.add(null);
                        }
                        if (eventCodeInProgress == null) {

                            eventNameInProgress.add("진행중인 이벤트가 없습니다.");
                            eventEndDateInProgress.add(null);
                            eventCodeInProgress.add(null);
                            eventStartDateInProgress.add(null);
                        }
                    }

                    view.setAdapter(eventNameInProgress, eventCodeInProgress, eventStartDateInProgress, eventEndDateInProgress, true);
                    view.setAdapter(eventNameEnd, eventCodeEnd, eventStartDateEnd, eventEndDateEnd, false);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("MEvent", String.valueOf(t.getMessage()));
            }
        };
        mInteractor.getEvent(shopId, onFinishApiListener);
    }
}
