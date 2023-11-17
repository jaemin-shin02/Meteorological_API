package ma.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortWeatherDto {
    private String fcstDate;
    private String fcstTime;

    private String TMP;
    private String UUU;
    private String VVV;
    private String VEC;
    private String WSD;
    private String SKY;
    private String PTY;
    private String POP;
    private String WAV;
    private String PCP;
    private String REH;
    private String SNO;

    public ShortWeatherDto(String fcstDate, String fcstTime) {
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
    }
}
