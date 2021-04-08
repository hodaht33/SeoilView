package seoil.capstone.som.ui.find;

import android.content.Context;
import android.content.res.Resources;

public class FindPresenter implements FindContract.Preseneter {

    private FindContract.View view;
    private Context context;
    private Resources res;

    @Override
    public void setView(FindContract.View view) {
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
