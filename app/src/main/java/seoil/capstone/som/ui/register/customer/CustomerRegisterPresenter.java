package seoil.capstone.som.ui.register.customer;

public class CustomerRegisterPresenter implements CustomerRegisterContract.Presenter {

    private CustomerRegisterContract.View mView;
    private CustomerRegisterContract.Interactor mInteractor;

    @Override
    public void setView(CustomerRegisterContract.View view) {
        this.mView = view;
    }

    @Override
    public void releaseView() {
        this.mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerRegisterInteractor();
    }

    @Override
    public void releaseInteractor() {
        this.mInteractor = null;
    }


}
