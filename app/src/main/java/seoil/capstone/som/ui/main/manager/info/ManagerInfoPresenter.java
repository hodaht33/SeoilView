package seoil.capstone.som.ui.main.manager.info;

// TODO: 미완성 상태
public class ManagerInfoPresenter implements ManagerInfoContract.Presenter {

    private ManagerInfoContract.View mView;
    private ManagerInfoInteractor mInteractor;

    @Override
    public void setView(ManagerInfoContract.View view) {

        mView = view;
    }

    @Override
    public void releaseView() {

        mView = null;
    }

    @Override
    public void createInteractor() {

        mInteractor = new ManagerInfoInteractor();
    }

    @Override
    public void releaseInteractor() {

        mInteractor = null;
    }
}
