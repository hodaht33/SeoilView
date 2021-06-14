package seoil.capstone.som.ui.main.customer.info;

// TODO: 미완성 상태
public class CustomerInfoPresenter implements CustomerInfoContract.Presenter{

    private CustomerInfoContract.View mView;
    private CustomerInfoInteractor mInteractor;

    @Override
    public void setView(CustomerInfoContract.View view) {
        this.mView = view;
    }

    @Override
    public void releaseView() {
        this.mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerInfoInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }
}
