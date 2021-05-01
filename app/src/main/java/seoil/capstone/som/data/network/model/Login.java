package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    /////////
    // 요청 //
    /////////

    public static class LoginReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("pwd")
        @Expose
        private String pwd;

        public LoginReq(String id, String pwd) {
            this.id = id;
            this.pwd = pwd;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

    /////////
    // 응답 //
    /////////

    public static class LoginRes {

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("code")
        @Expose
        private String code;

        public LoginRes(int status, String id, String code) {
            this.status = status;
            this.id = id;
            this.code = code;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class KakaoLoginRes {

        private String id;

        public KakaoLoginRes(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class NaverLoginRes {

        private String id;
        private String birthdate;
        private String gender;
        private String email;
        private String phoneNumber;

        public NaverLoginRes(String id, String birthdate, String gender, String email, String phoneNumber) {
            this.id = id;
            this.birthdate = birthdate;
            this.gender = gender;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
