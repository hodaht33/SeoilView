package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuDTO {

    public static class Req {

        @SerializedName("menuName")
        @Expose
        private String menuName;
        @SerializedName("menuPrice")
        @Expose
        private int menuPrice;
        @SerializedName("menuIngredients")
        @Expose
        private String menuIngredients;

        public Req(String menuName, int menuPrice, String menuIngredients) {
            this.menuName = menuName;
            this.menuPrice = menuPrice;
            this.menuIngredients = menuIngredients;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public int getMenuPrice() {
            return menuPrice;
        }

        public void setMenuPrice(int menuPrice) {
            this.menuPrice = menuPrice;
        }

        public String getMenuIngredients() {
            return menuIngredients;
        }

        public void setMenuIngredients(String menuIngredients) {
            this.menuIngredients = menuIngredients;
        }
    }

    public static class Res {

        public class Result {

            @SerializedName("menuName")
            @Expose
            private String menuName;
            @SerializedName("menuPrice")
            @Expose
            private int menuPrice;
            @SerializedName("menuIngredients")
            @Expose
            private String menuIngredients;

            public String getMenuName() {
                return menuName;
            }

            public void setMenuName(String menuName) {
                this.menuName = menuName;
            }

            public int getMenuPrice() {
                return menuPrice;
            }

            public void setMenuPrice(int menuPrice) {
                this.menuPrice = menuPrice;
            }

            public String getMenuIngredients() {
                return menuIngredients;
            }

            public void setMenuIngredients(String menuIngredients) {
                this.menuIngredients = menuIngredients;
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
