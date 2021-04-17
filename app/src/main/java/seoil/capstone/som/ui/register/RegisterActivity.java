package seoil.capstone.som.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.register.select.SelectUserFragment;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, RegisterCommunicator.Communicator {

    private RegisterContract.Presenter mPresenter;
    private int mFragmentLayoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPresenter = new RegisterPresenter();
        mFragmentLayoutId = R.id.fragmentLayoutRegit;

        mPresenter.setView(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(mFragmentLayoutId, new SelectUserFragment())
                .commit();
    }

    @Override
    protected void onDestroy() {
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    @Override
    public void changeAnotherFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(mFragmentLayoutId, fragment)
                .commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}