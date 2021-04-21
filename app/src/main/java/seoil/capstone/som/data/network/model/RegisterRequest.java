package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

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
        @SerializedName("corporateNumber")
        @Expose
        private String corporateNumber;
        @SerializedName("storeName")
        @Expose
        private String storeName;
        @SerializedName("storeAddr")
        @Expose
        private String storeAddr;
        @SerializedName("storeCategory")
        @Expose
        private String storeCategory;

        public Manager(String id, String pwd, String birthdate, String gender, String email, String phoneNumber, boolean marketingAgreement, String corporateNumber, String storeName, String storeAddr, String storeCategory) {
            this.id = id;
            this.pwd = pwd;
            this.birthdate = birthdate;
            this.gender = gender;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.marketingAgreement = marketingAgreement;
            this.corporateNumber = corporateNumber;
            this.storeName = storeName;
            this.storeAddr = storeAddr;
            this.storeCategory = storeCategory;
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

        public String getCorporateNumber() {
            return corporateNumber;
        }

        public void setCorporateNumber(String corporateNumber) {
            this.corporateNumber = corporateNumber;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreAddr() {
            return storeAddr;
        }

        public void setStoreAddr(String storeAddr) {
            this.storeAddr = storeAddr;
        }

        public String getStoreCategory() {
            return storeCategory;
        }

        public void setStoreCategory(String storeCategory) {
            this.storeCategory = storeCategory;
        }
    }
}
