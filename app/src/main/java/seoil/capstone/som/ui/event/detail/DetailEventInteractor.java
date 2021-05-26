package seoil.capstone.som.ui.event.detail;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public class DetailEventInteractor implements DetailEventContract.Interactor {


    @Override
    public void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener) {
        AppApiHelper.getInstance().getEvent(shopId, onFinishApiListener);
    }

    @Override
    public void updateEvent(EventData.UpdateReq req, OnFinishApiListener<EventData.UpdateReq> onFinishApiListener) {
        AppApiHelper.getInstance().updateEvent(req, onFinishApiListener);
    }

    @Override
    public void deleteEvent(int eventCode, OnFinishApiListener<EventData.StatusRes> onFinishApiListener) {
        AppApiHelper.getInstance().deleteEvent(eventCode, onFinishApiListener);
    }
}
