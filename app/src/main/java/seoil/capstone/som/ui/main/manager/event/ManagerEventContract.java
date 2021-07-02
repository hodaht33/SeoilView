package seoil.capstone.som.ui.main.manager.event;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventDataDTO;

// 이벤트 MVP 인터페이스
public interface ManagerEventContract {

    interface View extends BaseContract.View {

        void setAdapter(ArrayList<ManagerEventAdapter.Item> eventName, ArrayList<Integer> eventCode, ArrayList<String> eventDate);
        void startDetailedEvent(int eventCode);
        void endInsert();
    }

    interface Presenter extends BaseContract.Presenter<ManagerEventContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getEvent(String shopId, OnFinishApiListener<EventDataDTO.GetRes> onFinishApiListener);
        void insertEvent(EventDataDTO.InsertReq req, OnFinishApiListener<EventDataDTO.StatusRes> onFinishApiListener);
    }
}
