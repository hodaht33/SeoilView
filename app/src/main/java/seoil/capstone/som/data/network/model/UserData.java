package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 사용자 DTO
public class UserData {

    /////////
    // 요청 //
    /////////

    // 손님 회원가입 요청 DTO
    public static class Customer {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("pwd")
        @Expose
        private String pwd;
        @SerializedName("birthdate")
        @Expose
        private String birthdate;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("marketingAgreement")
        @Expose
        private boolean marketingAgreement;

        public Customer(String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement) {
            this.id = id;
            this.pwd = pwd;
            this.birthdate = birthdate;
            this.gender = gender;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.marketingAgreement = marketingAgreement;
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

        public boolean getMarketingAgreement() {
            return marketingAgreement;
        }

        public void setMarketingAgreement(boolean marketingAgreement) {
            this.marketingAgreement = marketingAgreement;
        }
    }

    // 점주 회원가입 요청 DTO
    public static class Manager {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("pwd")
        @Expose
        private String pwd;
        @SerializedName("birthdate")
        @Expose
        private String birthdate;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("marketingAgreement")
        @Expose
        private boolean marketingAgreement;
        @SerializedName("shopCode")
        @Expose
        private String shopCode;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("shopPostCode")
        @Expose
        private String shopPostCode;
        @SerializedName("shopAddress")
        @Expose
        private String shopAddress;
        @SerializedName("shopCategory")
        @Expose
        private String shopCategory;

        public Manager(String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement, String shopCode, String shopName, String shopPostCode, String shopAddress, String shopCategory) {
            this.id = id;
            this.pwd = pwd;
            this.birthdate = birthdate;
            this.gender = gender;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.marketingAgreement = marketingAgreement;
            this.shopCode = shopCode;
            this.shopName = shopName;
            this.shopPostCode = shopPostCode;
            this.shopAddress = shopAddress;
            this.shopCategory = shopCategory;
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

        public boolean isMarketingAgreement() {
            return marketingAgreement;
        }

        public void setMarketingAgreement(boolean marketingAgreement) {
            this.marketingAgreement = marketingAgreement;
        }

        public String getShopCode() {
            return shopCode;
        }

        public void setShopCode(String shopCode) {
            this.shopCode = shopCode;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopPostCode() {
            return shopPostCode;
        }

        public void setShopPostCode(String shopPostCode) {
            this.shopPostCode = shopPostCode;
        }

        public String getShopAddress() {
            return shopAddress;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopCategory() {
            return shopCategory;
        }

        public void setShopCategory(String shopCategory) {
            this.shopCategory = shopCategory;
        }
    }

    // 비밀번호 수정 요청 DTO
    public static class ChangePasswordReq {

        @SerializedName("password")
        @Expose
        private String password;

        public ChangePasswordReq(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /////////
    // 응답 //
    /////////

    // 사용자 정보 응답 DTO
    public static class GetUserInfoRes {

        public class Result {

            @SerializedName("userId")
            @Expose
            private String userId;
            @SerializedName("birthdate")
            @Expose
            private String birthdate;
            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("phoneNumber")
            @Expose
            private String phoneNumber;
            @SerializedName("marketingAgreement")
            @Expose
            private boolean marketingAgreement;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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

            public boolean isMarketingAgreement() {
                return marketingAgreement;
            }

            public void setMarketingAgreement(boolean marketingAgreement) {
                this.marketingAgreement = marketingAgreement;
            }
        }

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("results")
        @Expose
        private List<Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }
    }

    // 아이디 찾기 응답 DTO
    public static class FindIdRes {

        public class Result {

            @SerializedName("userId")
            @Expose
            private String userId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("results")
        @Expose
        private List<Result> results;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
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
