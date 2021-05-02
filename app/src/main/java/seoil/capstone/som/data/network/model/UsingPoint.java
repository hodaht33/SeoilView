package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsingPoint {

    // 요청 //

    public static class GetReq {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class InsertReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("shopCode")
        @Expose
        private String shopCode;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("usingPointAmount")
        @Expose
        private int usingPointAmount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public int getUsingPointAmount() {
            return usingPointAmount;
        }

        public void setUsingPointAmount(int usingPointAmount) {
            this.usingPointAmount = usingPointAmount;
        }
    }

    // 응답 //

    public static class GetRes {

        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("usingPointDate")
        @Expose
        private String usingPointDate;
        @SerializedName("shopCode")
        @Expose
        private String shopCode;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("usingPointAmount")
        @Expose
        private int usingPointAmount;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUsingPointDate() {
            return usingPointDate;
        }

        public void setUsingPointDate(String usingPointDate) {
            this.usingPointDate = usingPointDate;
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

        public int getUsingPointAmount() {
            return usingPointAmount;
        }

        public void setUsingPointAmount(int usingPointAmount) {
            this.usingPointAmount = usingPointAmount;
        }
    }

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
