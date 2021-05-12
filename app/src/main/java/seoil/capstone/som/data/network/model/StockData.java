package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockData {

    public static class Req {

        @SerializedName("shopId")
        @Expose
        private int shopId;
        @SerializedName("stockName")
        @Expose
        private String stockName;
        @SerializedName("stockAmount")
        @Expose
        private int stockAmount;

        public Req(int shopId, String stockName, int stockAmount) {
            this.shopId = shopId;
            this.stockName = stockName;
            this.stockAmount = stockAmount;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getStockName() {
            return stockName;
        }

        public void setStockName(String stockName) {
            this.stockName = stockName;
        }

        public int getStockAmount() {
            return stockAmount;
        }

        public void setStockAmount(int stockAmount) {
            this.stockAmount = stockAmount;
        }
    }


    public static class GetRes {

        public class Result {

            @SerializedName("stockName")
            @Expose
            private String stockName;
            @SerializedName("stockAmount")
            @Expose
            private int stockAmount;

            public String getStockName() {
                return stockName;
            }

            public void setStockName(String stockName) {
                this.stockName = stockName;
            }

            public int getStockAmount() {
                return stockAmount;
            }

            public void setStockAmount(int stockAmount) {
                this.stockAmount = stockAmount;
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

    public class StatusRes {

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
