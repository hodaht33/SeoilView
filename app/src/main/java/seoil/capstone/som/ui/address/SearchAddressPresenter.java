package seoil.capstone.som.ui.address;

public class SearchAddressPresenter implements SearchAddressContract.Presenter{
    private SearchAddressContract.View view;

    @Override
    public void setView(SearchAddressContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {

    }

    @Override
    public void releaseInteractor() {

    }
}
