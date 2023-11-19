package ma.weather.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ma.weather.domain.ShortWeather;
import ma.weather.domain.WeatherInfo;

import ma.weather.dto.ShortWeatherDto;
import ma.weather.service.ShortWeatherService;
import ma.weather.service.WeatherInfoService;
import ma.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShortWeatherController {
    private final WeatherService weatherService;
    private final ShortWeatherService shortWeatherService;
    private final WeatherInfoService weatherInfoService;

    @GetMapping("/shortWeather")
    @ResponseBody
    public Result shortWeather(){

        weatherService.getShortTermForecast("20231119", "0500", "60", "127");

        List<ShortWeather> byBaseDateAndTime = shortWeatherService.findByBaseDateAndTime("20231119", "0500");

        List<ShortWeatherDto> shortWeatherDtoList = new ArrayList<>();
        for (ShortWeather shortWeather : byBaseDateAndTime) {
            ShortWeatherDto shortWeatherDto = new ShortWeatherDto(shortWeather.getFcstDate(), shortWeather.getFcstTime());
            for (WeatherInfo weatherInfo : shortWeather.getWeatherInfoList()) {
                switch (weatherInfo.getCategory()) {
                    case "TMP":
                        shortWeatherDto.setTMP(weatherInfo.getValue());
                        break;
                    case "UUU":
                        shortWeatherDto.setUUU(weatherInfo.getValue());
                        break;
                    case "VVV":
                        shortWeatherDto.setVVV(weatherInfo.getValue());
                        break;
                    case "VEC":
                        shortWeatherDto.setVEC(weatherInfo.getValue());
                        break;
                    case "WSD":
                        shortWeatherDto.setWSD(weatherInfo.getValue());
                        break;
                    case "SKY":
                        shortWeatherDto.setSKY(weatherInfo.getValue());
                        break;
                    case "PTY":
                        shortWeatherDto.setPTY(weatherInfo.getValue());
                        break;
                    case "POP":
                        shortWeatherDto.setPOP(weatherInfo.getValue());
                        break;
                    case "WAV":
                        shortWeatherDto.setWAV(weatherInfo.getValue());
                        break;
                    case "PCP":
                        shortWeatherDto.setPCP(weatherInfo.getValue());
                        break;
                    case "REH":
                        shortWeatherDto.setREH(weatherInfo.getValue());
                        break;
                    case "SNO":
                        shortWeatherDto.setSNO(weatherInfo.getValue());
                        break;
                }
            }
            shortWeatherDtoList.add(shortWeatherDto);
        }

        return new Result(shortWeatherDtoList.size(), shortWeatherDtoList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T date;
    }
}
