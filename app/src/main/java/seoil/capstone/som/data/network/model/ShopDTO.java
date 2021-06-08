package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 매장 DTO
public class ShopDTO {

    // 매장 정보 요청 DTO
    public static class InsertReq {

        @SerializedName("shopCode")
        @Expose
        private String shopCode;
        @SerializedName("shopId")
        @Expose
        private String shopId;
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

        public InsertReq(String shopCode, String shopId, String shopName, String shopPostCode, String shopAddress, String shopCategory) {
            this.shopCode = shopCode;
            this.shopId = shopId;
            this.shopName = shopName;
            this.shopPostCode = shopPostCode;
            this.shopAddress = shopAddress;
            this.shopCategory = shopCategory;
        }

        public String getShopCode() {
            return shopCode;
        }

        public void setShopCode(String shopCode) {
            this.shopCode = shopCode;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
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

    // 매장 정보 수정 요청 DTO
    public static class UpdateReq {

        @SerializedName("shopCode")
        @Expose
        private String shopCode;
        @SerializedName("shopId")
        @Expose
        private String shopId;
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

        public UpdateReq(String shopCode, String shopId, String shopName, String shopPostCode, String shopAddress, String shopCategory) {
            this.shopCode = shopCode;
            this.shopId = shopId;
            this.shopName = shopName;
            this.shopPostCode = shopPostCode;
            this.shopAddress = shopAddress;
            this.shopCategory = shopCategory;
        }

        public String getShopCode() {
            return shopCode;
        }

        public void setShopCode(String shopCode) {
            this.shopCode = shopCode;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
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

    // 매장 정보 응답 DTO
    public static class GetRes {

        public class Result {

            @SerializedName("shopName")
            @Expose
            private String shopName;
            @SerializedName("shopAddress")
            @Expose
            private String shopAddress;
            @SerializedName("shopCategory")
            @Expose
            private String shopCategory;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
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
