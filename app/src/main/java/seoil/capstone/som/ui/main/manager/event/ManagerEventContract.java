package seoil.capstone.som.ui.main.manager.event;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public interface ManagerEventContract {

    interface View extends BaseContract.View {

        //어댑터에 데이터 갱싱
        void setAdapter(ArrayList<ManagerEventAdapter.Item> eventName, ArrayList<Integer> eventCode, ArrayList<String> eventDate);

        //상세이벤트 액티비티 이동
        void startDetailedEvent(int eventCode);

        //이벤트 추가 완료시 갱신
        void endInsert();
    }

    interface Presenter extends BaseContract.Presenter<ManagerEventContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        //이벤트 조회
        void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener);

        //이벤트 삽입
        void insertEvent(EventData.InsertReq req, OnFinishApiListener<EventData.StatusRes> onFinishApiListener);
    }
}
