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

    private ManagerEventContract.View view;             //Presenter에 설정할 뷰
    private ManagerEventInteractor mInteractor;         //DB 연결 기능을 함

    //ManagerEventFragment의 뷰를 Presenter에 설정
    @Override
    public void setView(ManagerEventContract.View view) {
        this.view = view;
    }

    //view 해제
    @Override
    public void releaseView() {
        this.view = null;
    }

    //Interactor 생성
    @Override
    public void createInteractor() {
        mInteractor = new ManagerEventInteractor();
    }

    //Interactor 해제
    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    //이벤트 조회 정보를 Interactor에 넘겨준다, Interactor에서 받은 데이터를 view에 전달
    public synchronized void getEvent(String shopId) {

        OnFinishApiListener<EventData.GetRes> onFinishApiListener = new OnFinishApiListener<EventData.GetRes>() {
            @Override
            public void onSuccess(EventData.GetRes getRes) {

                if (getRes.getStatus() == EventApi.SUCCESS) {

                    List<EventData.GetRes.Result> list = getRes.getResults();

                    ArrayList<ManagerEventAdapter.Item> mainEventName = new ArrayList<>();
                    ArrayList<Integer> mainEventCode = new ArrayList<>();
                    ArrayList<String> mainEventDate = new ArrayList<>();

                    if (list == null) {

                        mainEventName.add(new ManagerEventAdapter.Item(ManagerEventAdapter.HEADER, "진행중 이벤트", null));
                        mainEventName.add(new ManagerEventAdapter.Item(ManagerEventAdapter.HEADER, "시작전 이벤트", null));
                        mainEventName.add(new ManagerEventAdapter.Item(ManagerEventAdapter.HEADER, "종료된 이벤트", null));
                    } else {


                        ManagerEventAdapter.Item doEventName;
                        ArrayList<Integer> doEventCode = new ArrayList<>();
                        ArrayList<String> doEventDate = new ArrayList<>();

                        doEventName = new ManagerEventAdapter.Item(ManagerEventAdapter.HEADER,"진행중 이벤트", null);
                        doEventCode.add(-1);
                        doEventDate.add("");
                        doEventName.invisibleChildren = new ArrayList<>();

                        ManagerEventAdapter.Item beforeEventName;
                        ArrayList<Integer> beforeEventCode = new ArrayList<>();
                        ArrayList<String> beforeEventDate = new ArrayList<>();

                        beforeEventName = new ManagerEventAdapter.Item(ManagerEventAdapter.HEADER,"시작전 이벤트", null);
                        beforeEventCode.add(-1);
                        beforeEventDate.add("");
                        beforeEventName.invisibleChildren = new ArrayList<>();

                        ManagerEventAdapter.Item endEventName;
                        ArrayList<Integer> endEventCode = new ArrayList<>();
                        ArrayList<String> endEventDate = new ArrayList<>();
                        
                        endEventName = new ManagerEventAdapter.Item(ManagerEventAdapter.HEADER,"종료된 이벤트", null);
                        endEventCode.add(-1);
                        endEventDate.add("");
                        endEventName.invisibleChildren = new ArrayList<>();
                        
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

                            String dateResultStart = result.getStartDate();
                            int yearResultStart = Integer.parseInt(dateResultStart.substring(0, 4));
                            int monthResultStart = Integer.parseInt(dateResultStart.substring(5, 7));
                            int dayResultStart = Integer.parseInt(dateResultStart.substring(8, 10));

                            String temp = result.getStartDate() + "~" + result.getEndDate();

                            if (yearResultStart > year
                                    || monthResultStart > month
                                    || (month == monthResultStart && dayResultStart > day)) {   //시작전 이벤트

                                beforeEventName.invisibleChildren.add(new ManagerEventAdapter.Item(ManagerEventAdapter.CHILD, result.getEventName(),
                                        temp));
                                beforeEventDate.add(temp);
                                beforeEventCode.add(result.getEventCode());
                            } else if (yearResult > year
                                    || monthResult > month
                                    || (month == monthResult && dayResult >= day)) {            //진행중 이벤트

                                doEventName.invisibleChildren.add(new ManagerEventAdapter.Item(ManagerEventAdapter.CHILD, result.getEventName(),
                                                                    temp));
                                doEventDate.add(temp);
                                doEventCode.add(result.getEventCode());
                            } else {                                                            //종료된 이벤트

                                endEventName.invisibleChildren.add(new ManagerEventAdapter.Item(ManagerEventAdapter.CHILD, result.getEventName(),
                                                                        temp));
                                endEventDate.add(temp);
                                endEventCode.add(result.getEventCode());
                            }
                        }

                        mainEventName.add(doEventName);
                        mainEventName.add(beforeEventName);
                        mainEventName.add(endEventName);

                        mainEventCode.addAll(doEventCode);
                        mainEventCode.addAll(beforeEventCode);
                        mainEventCode.addAll(endEventCode);

                        mainEventDate.addAll(doEventDate);
                        mainEventDate.addAll(beforeEventDate);
                        mainEventDate.addAll(endEventDate);
                    }

                    view.setAdapter(mainEventName, mainEventCode, mainEventDate);
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Log.d("MEvent", String.valueOf(t.getMessage()));
            }
        };
        mInteractor.getEvent(shopId, onFinishApiListener);
    }

    public void setEventCode(int eventCode) {

        view.startDetailedEvent(eventCode);
    }
}
