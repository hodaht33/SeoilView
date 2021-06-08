package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// 로그인 DTO
public class LoginDTO {

    /////////
    // 요청 //
    /////////

    // 로그인 요청 DTO
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

    // 로그인 응답 DTO
    public static class LoginRes {

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("code")
        @Expose
        private String code;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    // 카카오 간편 로그인 응답 DTO
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

    // 네이버 간편 로그인 응답 DTO
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
