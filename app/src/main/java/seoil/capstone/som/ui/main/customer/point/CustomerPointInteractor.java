package seoil.capstone.som.ui.main.customer.point;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.PointData;

public class CustomerPointInteractor implements CustomerPointContract.Interactor{


    @Override
    public synchronized void getCurrentPoint(String id, OnFinishApiListener<PointData.GetCurrentRes> onFinishApiListener) {

        AppApiHelper.getInstance().getCurrentPoint(id, onFinishApiListener);
    }

    @Override
    public synchronized void getSavePoint(String id, OnFinishApiListener<PointData.GetSaveRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSavePoint(id, onFinishApiListener);
    }

    @Override
    public synchronized void getUsingPoint(String id, OnFinishApiListener<PointData.GetUsingRes> onFinishApiListener) {

        AppApiHelper.getInstance().getUsingPoint(id, onFinishApiListener);
    }
}
