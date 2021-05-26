package seoil.capstone.som.ui.event.detail;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public interface DetailEventContract {

    interface Interactor extends BaseContract.Interactor {

        void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener);

        void updateEvent(EventData.UpdateReq req, OnFinishApiListener<EventData.UpdateReq> onFinishApiListener);

        void deleteEvent(int eventCode, OnFinishApiListener<EventData.StatusRes> onFinishApiListener);
    }

    interface Presenter extends BaseContract.Presenter<DetailEventContract.View> {

    }

    interface View extends BaseContract.View {

    }
}
