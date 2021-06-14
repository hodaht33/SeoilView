package seoil.capstone.som.ui.register.manager;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.UserDTO;

// 점주 회원가입 모델
public class ManagerRegisterInteractor implements ManagerRegisterContract.Interactor {

    // 회원가입 수행
    @Override
    public void register(UserDTO.Manager manager, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().managerRegister(manager, onFinishApiListener);
    }

    // 인증번호 문자 전송
    @Override
    public void sendSms(AuthDTO.Req req, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    // 인증번호 전송
    @Override
    public void sendAuthCode(AuthDTO.Req req, OnFinishApiListener<AuthDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthCode(req, onFinishApiListener);
    }
}
