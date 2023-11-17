package ma.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MidWeatherResponse {

    @JsonProperty("response")
    private Response response;

    @Data
    public static class Response {
        private Header header;
        private Body body;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }
    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;

        // Getter, Setter, toString 등 필요한 메서드 추가
    }

    @Data
    public static class Body {
        @JsonProperty("dataType")
        private String dataType;

        @JsonProperty("items")
        private Items items;

        private int pageNo;
        private int numOfRows;
        private int totalCount;
    }

    @Data
    public static class Items {
        @JsonProperty("item")
        private List<Item> item;

        // getter methods
    }

    @Data
    public static class Item {
        private String regId;

        private int taMin3;

        private int taMin3Low;

        private int taMin3High;

        private int taMax3;

        private int taMax3Low;

        private int taMax3High;

        private int taMin4;

        private int taMin4Low;

        private int taMin4High;

        private int taMax4;

        private int taMax4Low;

        private int taMax4High;

        private int taMin5;

        private int taMin5Low;

        private int taMin5High;

        private int taMax5;

        private int taMax5Low;

        private int taMax5High;

        private int taMin6;

        private int taMin6Low;

        private int taMin6High;

        private int taMax6;

        private int taMax6Low;

        private int taMax6High;

        private int taMin7;

        private int taMin7Low;

        private int taMin7High;

        private int taMax7;

        private int taMax7Low;

        private int taMax7High;

        private int taMin8;

        private int taMin8Low;

        private int taMin8High;

        private int taMax8;

        private int taMax8Low;

        private int taMax8High;

        private int taMin9;

        private int taMin9Low;

        private int taMin9High;

        private int taMax9;

        private int taMax9Low;

        private int taMax9High;

        private int taMin10;

        private int taMin10Low;

        private int taMin10High;

        private int taMax10;

        private int taMax10Low;

        private int taMax10High;
    }

}
