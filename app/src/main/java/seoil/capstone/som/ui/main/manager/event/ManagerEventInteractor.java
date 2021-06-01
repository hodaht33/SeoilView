package seoil.capstone.som.ui.main.manager.event;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public class ManagerEventInteractor implements ManagerEventContract.Interactor{
    
    //DB에 점주의 아이디로 이벤트 정보 조회
    @Override
    public void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getEvent(shopId, onFinishApiListener);
    }

    @Override
    public void insertEvent(EventData.InsertReq req, OnFinishApiListener<EventData.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertEvent(req, onFinishApiListener);
    }
}
