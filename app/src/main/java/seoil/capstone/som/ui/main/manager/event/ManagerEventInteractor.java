package seoil.capstone.som.ui.main.manager.event;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventDataDTO;

// 이벤트 모델
public class ManagerEventInteractor implements ManagerEventContract.Interactor{
    
    //DB에 점주의 아이디로 이벤트 정보 조회
    @Override
    public void getEvent(String shopId, OnFinishApiListener<EventDataDTO.GetRes> onFinishApiListener) {

        AppApiHelper.getInstance().getEvent(shopId, onFinishApiListener);
    }

    //DB에 이벤트 정보 삽입
    @Override
    public void insertEvent(EventDataDTO.InsertReq req, OnFinishApiListener<EventDataDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().insertEvent(req, onFinishApiListener);
    }
}
