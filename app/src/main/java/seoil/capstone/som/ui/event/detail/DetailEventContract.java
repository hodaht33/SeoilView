package seoil.capstone.som.ui.event.detail;

import java.util.HashMap;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public interface DetailEventContract {

    interface Interactor extends BaseContract.Interactor {

        void getEventByCode(int eventCode, OnFinishApiListener<EventData.eventCodeRes> onFinishApiListener);

        void updateEvent(EventData.UpdateReq req, OnFinishApiListener<EventData.StatusRes> onFinishApiListener);

        void deleteEvent(int eventCode, OnFinishApiListener<EventData.StatusRes> onFinishApiListener);
    }

    interface Presenter extends BaseContract.Presenter<DetailEventContract.View> {

    }

    interface View extends BaseContract.View {

        void setEvent(HashMap<String, String> eventHashMap);

        void setDeleted();

        void initDetailEvent();
    }
}
