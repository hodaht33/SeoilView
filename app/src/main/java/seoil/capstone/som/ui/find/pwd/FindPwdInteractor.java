package seoil.capstone.som.ui.find.pwd;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

// 비밀번호 찾기(변경) 모델
public class FindPwdInteractor implements FindPwdContract.Interactor {

    // 핸드폰 번호 요청
    @Override
    public void getPhoneNumber(String userId, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().getUserPhoneNumber(userId, onFinishApiListener);
    }

    // 인증번호 문자 전송 요청
    @Override
    public void sendSms(Auth.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    // 인증번호 확인 요청
    @Override
    public void sendAuthCode(Auth.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthCode(req, onFinishApiListener);
    }

    // 비밀번호 변경 요청
    @Override
    public void changePassword(String userId, UserData.ChangePasswordReq req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().updatePassword(userId, req, onFinishApiListener);
    }
}
