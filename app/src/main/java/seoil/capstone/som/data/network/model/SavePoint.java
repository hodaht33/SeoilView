package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SavePoint {

    // 요청 //

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
        @SerializedName("savePointAmount")
        @Expose
        private int savePointAmount;

        public InsertReq(String id, String shopCode, String shopName, int savePointAmount) {
            this.id = id;
            this.shopCode = shopCode;
            this.shopName = shopName;
            this.savePointAmount = savePointAmount;
        }

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

        public int getSavePointAmount() {
            return savePointAmount;
        }

        public void setSavePointAmount(int savePointAmount) {
            this.savePointAmount = savePointAmount;
        }
    }

    // 응답 //

    public static class GetRes {

        public class Result {

            @SerializedName("savePointDate")
            @Expose
            private String savePointDate;
            @SerializedName("shopCode")
            @Expose
            private String shopCode;
            @SerializedName("shopName")
            @Expose
            private String shopName;
            @SerializedName("savePointAmount")
            @Expose
            private int savePointAmount;

            public String getSavePointDate() {
                return savePointDate;
            }

            public void setSavePointDate(String savePointDate) {
                this.savePointDate = savePointDate;
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

            public int getSavePointAmount() {
                return savePointAmount;
            }

            public void setSavePointAmount(int savePointAmount) {
                this.savePointAmount = savePointAmount;
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
