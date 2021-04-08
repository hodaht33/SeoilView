package seoil.capstone.som.ui.main.manager.event;

import seoil.capstone.som.base.BaseContract;

public interface ManagerEventContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<ManagerEventContract.View> {

    }
}
