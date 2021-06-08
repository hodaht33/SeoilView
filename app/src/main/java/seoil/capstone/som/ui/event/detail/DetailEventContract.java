package seoil.capstone.som.ui.event.detail;

import java.util.HashMap;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventDataDTO;

// 이벤트 상세 정보 MVP 인터페이스
public interface DetailEventContract {

    interface Interactor extends BaseContract.Interactor {

        void getEventByCode(int eventCode, OnFinishApiListener<EventDataDTO.EventCodeRes> onFinishApiListener);
        void updateEvent(EventDataDTO.UpdateReq req, OnFinishApiListener<EventDataDTO.StatusRes> onFinishApiListener);
        void deleteEvent(int eventCode, OnFinishApiListener<EventDataDTO.StatusRes> onFinishApiListener);
    }

    interface View extends BaseContract.View {

        void setEvent(HashMap<String, String> eventHashMap);
        void setDeleted();
        void finishDetailEvent();
    }

    interface Presenter extends BaseContract.Presenter<DetailEventContract.View> {

    }
}
