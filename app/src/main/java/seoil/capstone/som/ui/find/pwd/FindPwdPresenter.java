package seoil.capstone.som.ui.find.pwd;

import android.util.Log;

import seoil.capstone.som.data.network.OnFinishApiListener;
import seoil.capstone.som.data.network.api.UserApi;
import seoil.capstone.som.data.network.model.Auth;
import seoil.capstone.som.data.network.model.UserData;

public class FindPwdPresenter implements FindPwdContract.Presenter {

    private FindPwdContract.View mView;
    private FindPwdInteractor mInteractor;
    private String mUserId;
    private String mUserPhoneNumber;

    @Override
    public void setView(FindPwdContract.View view) {
        mView = view;
    }

    @Override
    public void releaseView() {
        mView = null;
    }

    @Override
    public void createInteractor() {
        mInteractor = new FindPwdInteractor();
    }

    @Override
    public void releaseInteractor() {
        mInteractor = null;
    }


    @Override
    public void sendSms(String userId) {

        // 아이디를 받아온 뒤 sendSmsReq를 호출하여 sms전송
        if (userId.isEmpty()) {

            mView.showDialog("아이디를 입력해주세요.");
        } else {

            OnFinishApiListener<UserData.GetUserInfoRes> onFinishApiListener = new OnFinishApiListener<UserData.GetUserInfoRes>() {
                @Override
                public void onSuccess(UserData.GetUserInfoRes getUserInfoRes) {


                    if (getUserInfoRes.getStatus() == UserApi.SUCCESS) {

                        mUserPhoneNumber = getUserInfoRes.getResults().get(0).getPhoneNumber();
                        sendSmsReq(mUserPhoneNumber);
                    } else if (getUserInfoRes.getStatus() == UserApi.EROR_NONE_DATA) {

                        mView.showDialog("유효하지 않은 아이디입니다.");
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    mView.showDialog("문자 전송에 문제가 발생했습니다.");
                    Log.d("find_pwd_presenter", t.toString());
                }
            };

            mUserId = userId;
            mInteractor.getPhoneNumber(userId, onFinishApiListener);
        }
    }

    private void sendSmsReq(String phoneNumber) {

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

                mView.showDialog("문자 전송에 문제가 발생했습니다.");
                Log.d("find_pwd_presenter", "no2");
                Log.d("find_pwd_presenter", t.toString());
            }
        };

        mInteractor.sendSms(new Auth.Req(phoneNumber, null), onFinishApiListener);
    }

    @Override
    public void sendAuthCode(String authCode) {

        if (authCode.isEmpty()) {

            mView.showDialog("인증번호 6자리를 입력해주세요.");
        } else if (authCode.length() != 6) {

            mView.showDialog("인증번호 6자리를 입력해주세요.");
        } else if (authCode.matches("^\\D*$")) {

            mView.showDialog("숫자만 입력해주세요.");
        } else {

            OnFinishApiListener<Auth.StatusRes> onFinishApiListener = new OnFinishApiListener<Auth.StatusRes>() {
                @Override
                public void onSuccess(Auth.StatusRes statusRes) {

                    if (statusRes.getStatus() == UserApi.SUCCESS) {

                        mView.changeToPasswordView();
                    } else if (statusRes.getStatus() == UserApi.ERROR_INVALID_AUTH) {

                        mView.showDialog("유효하지 않은 인증번호 입니다.");
                    } else {

                        mView.showDialog("인증 문제가 발생했습니다.");
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    mView.showDialog("인증 문제가 발생했습니다.");
                    Log.d("find_pwd_presenter", t.toString());
                }
            };

            mInteractor.sendAuthCode(new Auth.Req(mUserPhoneNumber, authCode), onFinishApiListener);
        }
    }

    @Override
    public void changePassword(String password, String passwordCheck) {

        if (password.isEmpty()
                || passwordCheck.isEmpty()) {

            mView.showDialog("변경할 비밀번호를 입력해주세요.");
        } else if (!password.equals(passwordCheck)) {

            mView.showDialog("비밀번호가 일치하지 않습니다.");
        } else if (password.length() < 10) {

            mView.showDialog("10자 이상 써주세요.");
        }// else if (password.matches())
        else {

            OnFinishApiListener<UserData.StatusRes> onFinishApiListener = new OnFinishApiListener<UserData.StatusRes>() {
                @Override
                public void onSuccess(UserData.StatusRes statusRes) {

                    if (statusRes.getStatus() == UserApi.SUCCESS) {

                        mView.changeToResultView();
                    } else {

                        mView.showDialog("비밀번호 변경에 실패했습니다.");
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                    mView.showDialog("비밀번호 변경에 실패했습니다.");
                    Log.d("find_pwd_presenter", t.toString());
                }
            };

            mInteractor.changePassword(mUserId, new UserData.ChangePasswordReq(password), onFinishApiListener);
        }
    }

    @Override
    public void releaseAnother() {

        mUserId = null;
        mUserPhoneNumber = null;
    }
}
