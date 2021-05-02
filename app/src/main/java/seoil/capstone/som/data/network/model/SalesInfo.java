package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalesInfo {

    public static class InsertReq {

        @SerializedName("shopCode")
        @Expose
        private String shopCode;
        @SerializedName("shopId")
        @Expose
        private String shopId;
        @SerializedName("salesAmount")
        @Expose
        private int salesAmount;

        public InsertReq(String shopCode, String shopId, int salesAmount) {
            this.shopCode = shopCode;
            this.shopId = shopId;
            this.salesAmount = salesAmount;
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

        public int getSalesAmount() {
            return salesAmount;
        }

        public void setSalesAmount(int salesAmount) {
            this.salesAmount = salesAmount;
        }
    }

    public static class GetRes {

        public class Result {

            @SerializedName("salesDate")
            @Expose
            private String salesDate;
            @SerializedName("salesAmount")
            @Expose
            private int salesAmount;

            public String getSalesDate() {
                return salesDate;
            }

            public void setSalesDate(String salesDate) {
                this.salesDate = salesDate;
            }

            public int getSalesAmount() {
                return salesAmount;
            }

            public void setSalesAmount(int salesAmount) {
                this.salesAmount = salesAmount;
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
