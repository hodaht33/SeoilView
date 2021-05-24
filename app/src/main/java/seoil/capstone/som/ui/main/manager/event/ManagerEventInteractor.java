package seoil.capstone.som.ui.main.manager.event;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public class ManagerEventInteractor implements ManagerEventContract.Interactor{


    @Override
    public void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener) {
        AppApiHelper.getInstance().getEvent(shopId, onFinishApiListener);
    }
}
