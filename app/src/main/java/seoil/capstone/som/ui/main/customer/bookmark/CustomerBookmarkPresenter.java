package seoil.capstone.som.ui.main.customer.bookmark;

public class CustomerBookmarkPresenter implements CustomerBookmarkContract.Presenter{

    private CustomerBookmarkContract.View mView;
    private CustomerBookmarkInteractor mInteractor;

    @Override
    public void setView(CustomerBookmarkContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new CustomerBookmarkInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }
}
