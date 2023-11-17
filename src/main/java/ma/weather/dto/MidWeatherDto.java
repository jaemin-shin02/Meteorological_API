package ma.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MidWeatherDto {
    private String regId;

    private int day;
    private int taMin;
    private int taMinLow;
    private int taMinHigh;
    private int taMax;
    private int taMaxLow;
    private int taMaxHigh;
}
