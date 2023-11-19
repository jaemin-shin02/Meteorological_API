package ma.weather.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.weather.domain.MidWeather;
import ma.weather.domain.MidWeatherInfo;
import ma.weather.dto.MidWeatherDto;
import ma.weather.dto.MidWeatherResponse;
import ma.weather.service.MidWeatherInfoService;
import ma.weather.service.MidWeatherService;
import ma.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MidWeatherController {
    private final WeatherService weatherService;
    private final MidWeatherService midWeatherService;
    private final MidWeatherInfoService midWeatherInfoService;

    @GetMapping("/midWeather")
    @ResponseBody
    public Result midWeather(){

        MidWeatherResponse midTa = weatherService.getMidTa("11B10101", "202311190600");
        MidWeather midWeather = midWeatherService.findByRegId("11B10101");
        List<MidWeatherInfo> byMidWeatherId = midWeatherInfoService.findByMidWeatherId(midWeather.getId());
        List<MidWeatherDto> collect = byMidWeatherId.stream().map(
                i -> new MidWeatherDto(midWeather.getRegId(), i.getDay(), i.getTaMin(), i.getTaMinLow(), i.getTaMinHigh(), i.getTaMax(), i.getTaMaxLow(), i.getTaMaxHigh())
        ).collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T date;
    }
}
