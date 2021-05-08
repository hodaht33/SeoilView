package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    /////////
    // 요청 //
    /////////

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

    /////////
    // 응답 //
    /////////

    public static class RegisterRes {

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
