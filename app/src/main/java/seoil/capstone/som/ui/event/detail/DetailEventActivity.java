package seoil.capstone.som.ui.event.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import seoil.capstone.som.R;

public class DetailEventActivity extends AppCompatActivity implements DetailEventContract.View{

    private DetailEventPresenter mPresenter;
    private int mEventCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        mPresenter = new DetailEventPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();

        mEventCode = getIntent().getIntExtra("eventCode", -1);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}