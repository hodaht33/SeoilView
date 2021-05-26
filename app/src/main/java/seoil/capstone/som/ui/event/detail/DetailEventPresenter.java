package seoil.capstone.som.ui.event.detail;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public class DetailEventPresenter implements DetailEventContract.Presenter {

    private DetailEventContract.View view;
    private DetailEventInteractor mInteractor;

    @Override
    public void setView(DetailEventContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        view = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new DetailEventInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }

    public void getEvent(String shopId) {

        OnFinishApiListener<EventData.GetRes> onFinishApiListener = new OnFinishApiListener<EventData.GetRes>() {
            @Override
            public void onSuccess(EventData.GetRes getRes) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.getEvent(shopId, onFinishApiListener);
    }

    public void updateEvent(int eventCode, String eventName, String eventContents, String startDate, String endDate) {

        OnFinishApiListener<EventData.UpdateReq> onFinishApiListener = new OnFinishApiListener<EventData.UpdateReq>() {
            @Override
            public void onSuccess(EventData.UpdateReq updateReq) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.updateEvent(new EventData.UpdateReq(eventCode, eventName, eventContents, startDate, endDate), onFinishApiListener);
    }

    public void deleteEvent(int eventCode) {

        OnFinishApiListener<EventData.StatusRes> onFinishApiListener = new OnFinishApiListener<EventData.StatusRes>() {
            @Override
            public void onSuccess(EventData.StatusRes statusRes) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        };

        mInteractor.deleteEvent(eventCode, onFinishApiListener);
    }
}
