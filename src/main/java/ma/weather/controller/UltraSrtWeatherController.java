package ma.weather.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ma.weather.domain.UltraSrtWeather;
import ma.weather.domain.WeatherInfo;

import ma.weather.dto.UltraSrtWeatherDto;
import ma.weather.service.UltraSrtWeatherService;
import ma.weather.service.WeatherInfoService;
import ma.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UltraSrtWeatherController {
    private final WeatherService weatherService;
    private final UltraSrtWeatherService ultraSrtWeatherService;
    private final WeatherInfoService weatherInfoService;

    @GetMapping("/ultraSrtWeather")
    @ResponseBody
    public Result ultraShortWeather(){

        weatherService.getMShortTermForecast("20231116", "0630", "60", "127");

        List<UltraSrtWeather> byBaseDateAndTime = ultraSrtWeatherService.findByBaseDateAndTime("20231116", "0630");

        List<UltraSrtWeatherDto> ultraSrtWeatherDtoList = new ArrayList<>();

        for (UltraSrtWeather ultraSrtWeather : byBaseDateAndTime) {
            UltraSrtWeatherDto ultraSrtWeatherDto = new UltraSrtWeatherDto(ultraSrtWeather.getFcstDate(), ultraSrtWeather.getFcstTime());
            for (WeatherInfo weatherInfo : ultraSrtWeather.getWeatherInfoList()) {
                switch (weatherInfo.getCategory()) {
                    case "LGT":
                        ultraSrtWeatherDto.setLGT(weatherInfo.getValue());
                        break;
                    case "PTY":
                        ultraSrtWeatherDto.setPTY(weatherInfo.getValue());
                        break;
                    case "RN1":
                        ultraSrtWeatherDto.setRN1(weatherInfo.getValue());
                        break;
                    case "SKY":
                        ultraSrtWeatherDto.setSKY(weatherInfo.getValue());
                        break;
                    case "T1H":
                        ultraSrtWeatherDto.setT1H(weatherInfo.getValue());
                        break;
                    case "REH":
                        ultraSrtWeatherDto.setREH(weatherInfo.getValue());
                        break;
                    case "UUU":
                        ultraSrtWeatherDto.setUUU(weatherInfo.getValue());
                        break;
                    case "VVV":
                        ultraSrtWeatherDto.setVVV(weatherInfo.getValue());
                        break;
                    case "VEC":
                        ultraSrtWeatherDto.setVEC(weatherInfo.getValue());
                        break;
                    case "WSD":
                        ultraSrtWeatherDto.setWSD(weatherInfo.getValue());
                        break;
                }
            }
            ultraSrtWeatherDtoList.add(ultraSrtWeatherDto);
        }
        return new Result(ultraSrtWeatherDtoList.size(), ultraSrtWeatherDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T date;
    }
}
