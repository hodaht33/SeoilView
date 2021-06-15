package seoil.capstone.som.ui.main.manager.info;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import seoil.capstone.som.GlobalApplication;
import seoil.capstone.som.R;
import seoil.capstone.som.ui.login.LoginActivity;

// 점주 사용자 정보 관리 프레그먼트
// TODO: 미완성 상태
public class ManagerInfoFragment extends Fragment implements ManagerInfoContract.View, View.OnClickListener {

    private ManagerInfoPresenter mPresenter;
    private Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManagerInfoPresenter();
        mPresenter.setView(this);
        mPresenter.createInteractor();
    }

    @Override
    public void onDestroy() {

        mPresenter.releaseInteractor();
        mPresenter.releaseView();
        mPresenter = null;

        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_info, container, false);

        btnLogout = view.findViewById(R.id.btnMLogout);

        btnLogout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        int viewId = v.getId();

        if (viewId == R.id.btnMLogout) {
            // 로그아웃

            // 로그인 지속 기능 비활성화
            SharedPreferences.Editor e = getContext().getSharedPreferences("keepLogin", Context.MODE_PRIVATE).edit();
            e.putBoolean("keepLoginState", false).commit();

            // 사용자 전역 정보 삭제
            ((GlobalApplication) getActivity().getApplicationContext()).logout();

            // 로그인 창으로 이동
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

            getActivity().finish();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog(String msg) {

    }
}