package seoil.capstone.som.ui.event.detail;

import java.util.HashMap;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

// 이벤트 상세 정보 MVP 인터페이스
public interface DetailEventContract {

    interface Interactor extends BaseContract.Interactor {

        void getEventByCode(int eventCode, OnFinishApiListener<EventData.EventCodeRes> onFinishApiListener);
        void updateEvent(EventData.UpdateReq req, OnFinishApiListener<EventData.StatusRes> onFinishApiListener);
        void deleteEvent(int eventCode, OnFinishApiListener<EventData.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setEvent(HashMap<String, String> eventHashMap);
        void setDeleted();
        void finishDetailEvent();
    }

    interface Presenter extends BaseContract.Presenter<DetailEventContract.View> {

    }
}
