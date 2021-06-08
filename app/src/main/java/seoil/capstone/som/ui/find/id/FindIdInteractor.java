package seoil.capstone.som.ui.find.id;

import seoil.capstone.som.data.network.AppApiHelper;
import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.model.AuthDTO;

// 아이디 찾기 모델
public class FindIdInteractor implements FindIdContract.Interactor {

    // 인증번호 문자 전송 요청
    @Override
    public void sendSms(AuthDTO.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendSms(req, onFinishApiListener);
    }

    // 인증번호 확인 요청
    @Override
    public void sendAuthCode(AuthDTO.Req req, OnFinishApiListener onFinishApiListener) {

        AppApiHelper.getInstance().sendAuthForFindId(req, onFinishApiListener);
    }
}
