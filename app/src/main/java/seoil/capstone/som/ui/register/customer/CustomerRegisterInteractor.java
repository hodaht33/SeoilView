package seoil.capstone.som.ui.register.customer;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.AuthDTO;
import seoil.capstone.som.data.network.model.UserDTO;

// 손님 회원가입 모델
public class CustomerRegisterInteractor implements CustomerRegisterContract.Interactor {

    // 회원가입 수행
    @Override
    public void register(UserDTO.Customer customer, OnFinishApiListener<UserDTO.StatusRes> onFinishApiListener) {

        AppApiHelper.getInstance().customerRegister(customer, onFinishApiListener);
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
