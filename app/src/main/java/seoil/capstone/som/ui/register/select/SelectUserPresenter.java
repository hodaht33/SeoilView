package seoil.capstone.som.ui.register.select;

import android.content.Context;
import android.content.res.Resources;

public class SelectUserPresenter implements SelectUserContract.Presenter {

    private SelectUserContract.View view;
    private Context context;
    private Resources res;

    @Override
    public void setView(SelectUserContract.View view) {
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
