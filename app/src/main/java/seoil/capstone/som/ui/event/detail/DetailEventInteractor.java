package seoil.capstone.som.ui.event.detail;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

// 이벤트 상세 정보 모델
public class DetailEventInteractor implements DetailEventContract.Interactor {

    // 이벤트 코드로 검색된 이벤트 정보 요청
    @Override
    public void getEventByCode(int eventCode, OnFinishApiListener<EventData.EventCodeRes> onFinishApiListener) {
        AppApiHelper.getInstance().getEventByCode(eventCode, onFinishApiListener);
    }

    // 이벤트 수정 요청
    @Override
    public void updateEvent(EventData.UpdateReq req, OnFinishApiListener<EventData.StatusRes> onFinishApiListener) {
        AppApiHelper.getInstance().updateEvent(req, onFinishApiListener);
    }

    // 이벤트 삭제 요청
    @Override
    public void deleteEvent(int eventCode, OnFinishApiListener<EventData.StatusRes> onFinishApiListener) {
        AppApiHelper.getInstance().deleteEvent(eventCode, onFinishApiListener);
    }
}
