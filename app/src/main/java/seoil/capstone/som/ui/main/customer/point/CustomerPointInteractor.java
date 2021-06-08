package seoil.capstone.som.ui.main.customer.point;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.PointDTO;

public class CustomerPointInteractor implements CustomerPointContract.Interactor{
    
    //DB 잔여 포인트 조회
    @Override
    public synchronized void getCurrentPoint(String id, OnFinishApiListener<PointDTO.GetCurrentRes> onFinishApiListener) {

        AppApiHelper.getInstance().getCurrentPoint(id, onFinishApiListener);
    }

    //DB 적립 포인트 조회
    @Override
    public synchronized void getSavePoint(String id, OnFinishApiListener<PointDTO.GetSaveRes> onFinishApiListener) {

        AppApiHelper.getInstance().getSavePoint(id, onFinishApiListener);
    }

    //DB 사용 포인트 조회
    @Override
    public synchronized void getUsingPoint(String id, OnFinishApiListener<PointDTO.GetUsingRes> onFinishApiListener) {

        AppApiHelper.getInstance().getUsingPoint(id, onFinishApiListener);
    }
}
