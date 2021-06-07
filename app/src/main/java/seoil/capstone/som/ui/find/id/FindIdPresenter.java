package seoil.capstone.som.ui.find.id;

import java.util.ArrayList;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

// 아이디 찾기 프레젠터
public class FindIdPresenter implements FindIdContract.Presenter{

    private FindIdContract.View mView;
    private FindIdContract.Interactor mInteractor;
    private String mUserPhoneNumber;    // 인증 보내고 다른 번호로 변경하여 인증하는 것을 방지

    @Override
    public void setView(FindIdContract.View view) {

        mView = view;
    }

    @Override
    public void releaseView() {

        mView = null;
    }

    @Override
    public void createInteractor() {

        mInteractor = new FindIdInteractor();
    }

    @Override
    public void releaseInteractor() {

        mInteractor = null;
    }

    // 핸드폰 번호 유효성 검사 및 인증번호 문자 전송 요청
    @Override
    public void sendSms(String phoneNumber) {

        if (phoneNumber.isEmpty()) {

            mView.showDialog("핸드폰 번호를 입력해주세요.");
        } else if (phoneNumber.length() != 11) {

            mView.showDialog("'-'없이 숫자만 11자리로 입력해주세요.");
        } else if (!phoneNumber.matches("^\\d*$")) {

            mView.showDialog("숫자만 입력해주세요.");
        } else {

            OnFinishApiListener<Auth.StatusRes> onFinishApiListener = new OnFinishApiListener<Auth.StatusRes>() {
                @Override
                public void onSuccess(Auth.StatusRes statusRes) {

                    if (statusRes.getStatus() == UserApi.SUCCESS) {

                        mView.showDialog("문자 전송 완료\n인증번호를 입력해주세요.");
                        mView.visibleAuthView();
                    } else {

                        mView.showDialog("문자 전송 문제가 발생했습니다.");
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    mView.showDialog("문자 전송 문제가 발생했습니다.");
                }
            };

            mUserPhoneNumber = phoneNumber;
            mInteractor.sendSms(new Auth.Req(phoneNumber, null), onFinishApiListener);
        }
    }

    // 인증번호 확인 요청
    @Override
    public void sendAuthCode(String authCode) {

        OnFinishApiListener<UserData.FindIdRes> onFinishApiListener = new OnFinishApiListener<UserData.FindIdRes>() {
            @Override
            public void onSuccess(UserData.FindIdRes res) {

                if (res.getStatus() == UserApi.SUCCESS) {

                    if  (res.getResults().isEmpty()) {

                        mView.visibleResultView(null);

                        return;
                    }

                    ArrayList<String> results = new ArrayList<String>();

                    for (UserData.FindIdRes.Result result : res.getResults()) {

                        results.add(result.getUserId());
                    }

                    mView.visibleResultView(results);
                } else if (res.getStatus() == UserApi.ERROR_INVALID_AUTH) {

                    mView.showDialog("유효하지 않은 인증번호 입니다.");
                } else {

                    mView.showDialog("인증 문제가 발생했습니다.");
                }
            }

            @Override
            public void onFailure(Throwable t) {

                mView.showDialog("인증 문제가 발생했습니다.");
            }
        };

        mInteractor.sendAuthCode(new Auth.Req(mUserPhoneNumber, authCode), onFinishApiListener);
    }
}
