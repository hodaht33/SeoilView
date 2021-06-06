package seoil.capstone.som.ui.main.customer.info;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;

public class CustomerInfoFragment extends Fragment implements CustomerInfoContract.View, View.OnClickListener{

    private CustomerInfoPresenter mPresenter;
    private Button mBtnLogout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CustomerInfoPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_info, container, false);

        initView(view);

        initListener();

        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void initView(View view) {

        mBtnLogout = view.findViewById(R.id.btnCInfoLogout);
    }

    private void initListener() {

        mBtnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnCInfoLogout) {

            Intent intent = new Intent(getActivity(), LoginActivity.class);

            SharedPreferences.Editor e = getContext().getSharedPreferences("keepLogin", Context.MODE_PRIVATE).edit();
            e.putBoolean("keepLoginState", false).commit();

            startActivity(intent);

            getActivity().finish();
        }
    }
}
