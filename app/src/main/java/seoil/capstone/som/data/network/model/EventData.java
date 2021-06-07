package seoil.capstone.som.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// 이벤트 DTO
public class EventData {

    // 추가 요청 DTO
    public static class InsertReq {

        @SerializedName("shopId")
        @Expose
        private String shopId;
        @SerializedName("eventName")
        @Expose
        private String eventName;
        @SerializedName("eventContents")
        @Expose
        private String eventContents;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("isSendPush")
        @Expose
        private Boolean isSendPush;

        public InsertReq(String shopId, String eventName, String eventContents, String startDate, String endDate, Boolean isSendPush) {
            this.shopId = shopId;
            this.eventName = eventName;
            this.eventContents = eventContents;
            this.startDate = startDate;
            this.endDate = endDate;
            this.isSendPush = isSendPush;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getEventContents() {
            return eventContents;
        }

        public void setEventContents(String eventContents) {
            this.eventContents = eventContents;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Boolean getSendPush() {
            return isSendPush;
        }

        public void setSendPush(Boolean sendPush) {
            isSendPush = sendPush;
        }
    }

    // 수정 요청 DTO
    public static class UpdateReq {

        @SerializedName("eventCode")
        @Expose
        private int eventCode;
        @SerializedName("eventName")
        @Expose
        private String eventName;
        @SerializedName("eventContents")
        @Expose
        private String eventContents;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;

        public UpdateReq(int eventCode, String eventName, String eventContents, String startDate, String endDate) {
            this.eventCode = eventCode;
            this.eventName = eventName;
            this.eventContents = eventContents;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public int getEventCode() {
            return eventCode;
        }

        public void setEventCode(int eventCode) {
            this.eventCode = eventCode;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getEventContents() {
            return eventContents;
        }

        public void setEventContents(String eventContents) {
            this.eventContents = eventContents;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }

    // 검색 응답 DTO
    public static class GetRes {

        public class Result {

            @SerializedName("eventCode")
            @Expose
            private int eventCode;
            @SerializedName("eventName")
            @Expose
            private String eventName;
            @SerializedName("eventContents")
            @Expose
            private String eventContents;
            @SerializedName("startDate")
            @Expose
            private String startDate;
            @SerializedName("endDate")
            @Expose
            private String endDate;

            public int getEventCode() {
                return eventCode;
            }

            public void setEventCode(int eventCode) {
                this.eventCode = eventCode;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public String getEventContents() {
                return eventContents;
            }

            public void setEventContents(String eventContents) {
                this.eventContents = eventContents;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
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

    // 이벤트 코드로 검색 응답 DTO
    public static class EventCodeRes {

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
            @SerializedName("eventName")
            @Expose
            private String eventName;
            @SerializedName("eventContents")
            @Expose
            private String eventContents;
            @SerializedName("eventStartDate")
            @Expose
            private String eventStartDate;
            @SerializedName("eventEndDate")
            @Expose
            private String eventEndDate;

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

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public String getEventContents() {
                return eventContents;
            }

            public void setEventContents(String eventContents) {
                this.eventContents = eventContents;
            }

            public String getEventStartDate() {
                return eventStartDate;
            }

            public void setEventStartDate(String eventStartDate) {
                this.eventStartDate = eventStartDate;
            }

            public String getEventEndDate() {
                return eventEndDate;
            }

            public void setEventEndDate(String eventEndDate) {
                this.eventEndDate = eventEndDate;
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

    // 진행중인 이벤트 정보 응답 DTO
    public static class OngoingEventRes {

        public class Result {

            @SerializedName("shopName")
            @Expose
            private String shopName;
            @SerializedName("eventCode")
            @Expose
            private int eventCode;
            @SerializedName("eventName")
            @Expose
            private String eventName;
            @SerializedName("eventContents")
            @Expose
            private String eventContents;
            @SerializedName("startDate")
            @Expose
            private String startDate;
            @SerializedName("endDate")
            @Expose
            private String endDate;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getEventCode() {
                return eventCode;
            }

            public void setEventCode(int eventCode) {
                this.eventCode = eventCode;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public String getEventContents() {
                return eventContents;
            }

            public void setEventContents(String eventContents) {
                this.eventContents = eventContents;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
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
