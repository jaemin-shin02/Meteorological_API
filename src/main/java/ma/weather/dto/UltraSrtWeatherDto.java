package ma.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UltraSrtWeatherDto {
    private String fcstDate;
    private String fcstTime;

    private String LGT;
    private String PTY;
    private String RN1;
    private String SKY;
    private String T1H;
    private String REH;
    private String UUU;
    private String VVV;
    private String VEC;
    private String WSD;

    public UltraSrtWeatherDto(String fcstDate, String fcstTime) {
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
    }
}
