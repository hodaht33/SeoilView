package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopInfo {

    public static class InsertReq {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("shopAddress")
        @Expose
        private String shopAddress;
        @SerializedName("shopCategory")
        @Expose
        private String shopCategory;

        public InsertReq(String id, String shopName, String shopAddress, String shopCategory) {
            this.id = id;
            this.shopName = shopName;
            this.shopAddress = shopAddress;
            this.shopCategory = shopCategory;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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
