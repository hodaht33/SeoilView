package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    public static class SomRestLoginApi {

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("code")
        @Expose
        private int code;

        public SomRestLoginApi(int status, String id, int code) {
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

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class KakaoLoginApi {

        private String id;

        public KakaoLoginApi(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class NaverLoginApi {

        private String id;
        private String birthdate;
        private String gender;
        private String email;
        private String phoneNumber;

        public NaverLoginApi(String id, String birthdate, String gender, String email, String phoneNumber) {
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
