package seoil.capstone.som.ui.main.manager.event;

import java.util.ArrayList;

import seoil.capstone.som.base.BaseContract;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.EventData;

public interface ManagerEventContract {

    interface View extends BaseContract.View {

        void setAdapter(ArrayList<String> eventName, ArrayList<Integer> eventCode,
                             ArrayList<String> eventStartDate, ArrayList<String> eventEndDate, Boolean isInProgress);

    }

    interface Presenter extends BaseContract.Presenter<ManagerEventContract.View> {

    }

    interface Interactor extends BaseContract.Interactor {

        void getEvent(String shopId, OnFinishApiListener<EventData.GetRes> onFinishApiListener);
    }
}
