package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// 인증번호 인증 DTO
public class Auth {

    // 인증번호 확인 요청 DTO
    public static class Req {

        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("authCode")
        @Expose
        private String authCode;

        public Req(String phoneNumber, String authCode) {
            this.phoneNumber = phoneNumber;
            this.authCode = authCode;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }
    }

    // 서버 응답 DTO
    public static class StatusRes {

        @SerializedName("status")
        @Expose
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}