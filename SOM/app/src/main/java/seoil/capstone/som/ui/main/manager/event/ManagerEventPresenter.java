package seoil.capstone.som.ui.main.manager.event;

import android.content.Context;
import android.content.res.Resources;

public class ManagerEventPresenter implements ManagerEventContract.Presenter {

    private ManagerEventContract.View view;
    private Context context;
    private Resources res;

    @Override
    public void setView(ManagerEventContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void releaseContext() {
        this.context = null;
    }

    @Override
    public void setResources(Resources res) {
        this.res = res;
    }

    @Override
    public void releaseResources() {
        this.res = null;
    }
}
