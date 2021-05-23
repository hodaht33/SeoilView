package seoil.capstone.som.ui.main.customer.bookmark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;

public class CustomerBookmarkFragment extends Fragment implements View.OnClickListener, CustomerBookmarkContract.View{

    private CustomerBookmarkPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerBookmarkPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_bookmark, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LoginActivity.class);

        SharedPreferences.Editor e = getContext().getSharedPreferences("keepLogin", Context.MODE_PRIVATE).edit();
        e.putBoolean("keepLoginState", false).commit();

        startActivity(intent);

        getActivity().finish();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
