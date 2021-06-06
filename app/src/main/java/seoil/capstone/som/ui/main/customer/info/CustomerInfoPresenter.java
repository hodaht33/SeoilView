package seoil.capstone.som.ui.main.customer.info;

public class CustomerInfoPresenter implements CustomerInfoContract.Presenter{

    private CustomerInfoContract.View view;
    private CustomerInfoInteractor mInteractor;
    @Override
    public void setView(CustomerInfoContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
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
