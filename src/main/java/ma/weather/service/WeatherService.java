package ma.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ma.weather.domain.*;
import ma.weather.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherService {
    private final ShortWeatherService shortWeatherService;
    private final UltraSrtWeatherService ultraSrtWeatherService;
    private final WeatherInfoService weatherInfoService;
    private final MidWeatherService midWeatherService;
    private final MidWeatherInfoService midWeatherInfoService;

    private final String apiKey = "hykiVYxb2EfbSrwsSiqH8AE1fKGaSdSJG4YpiY1dWY2wi/lJ9l5bOCry0weCcs21XuJ8KlI8k1Nqdm4B9040/Q==";
    private final String midApiKey = "hykiVYxb2EfbSrwsSiqH8AE1fKGaSdSJG4YpiY1dWY2wi/lJ9l5bOCry0weCcs21XuJ8KlI8k1Nqdm4B9040/Q==";
    private final String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0";

    @Transactional
    public WeatherResponse getShortTermForecast(String baseDate, String baseTime, String nx, String ny) {
        String url = String.format("%s/getVilageFcst?serviceKey=%s&base_date=%s&base_time=%s&nx=%s&ny=%s&dataType=JSON&numOfRows=289",
                apiUrl, apiKey, baseDate, baseTime, nx, ny);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("result = " + result);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherResponse weatherResponse = objectMapper.readValue(result, WeatherResponse.class);
            WeatherResponse.Items items = weatherResponse.getResponse().getBody().getItems();

            for(int i=0; i<items.getItem().size(); i+=12){
                WeatherResponse.Item item = items.getItem().get(i);
                ShortWeather shortWeather = ShortWeather.create(item.getBaseDate(), item.getBaseTime(), item.getFcstDate(), item.getFcstTime());
                shortWeatherService.save(shortWeather);
                for(int j=0; j<12; j++){
                    if(i+j >= items.getItem().size()) break;
                    item = items.getItem().get(i+j);
                    if(item.getCategory().contains("TMX")){
                        i++;
                        j--;
                        continue;
                    }
                    WeatherInfo weatherInfo = WeatherInfo.create(item.getCategory(), item.getFcstValue(), item.getNx(), item.getNy());
                    weatherInfo.setShortWeather(shortWeather);

                    weatherInfoService.save(weatherInfo);
                }
            }
            return weatherResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public WeatherResponse getMShortTermForecast(String baseDate, String baseTime, String nx, String ny) {
        String url = String.format("%s/getUltraSrtFcst?serviceKey=%s&base_date=%s&base_time=%s&nx=%s&ny=%s&dataType=JSON&numOfRows=60",
                apiUrl, apiKey, baseDate, baseTime, nx, ny);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            WeatherResponse weatherResponse = objectMapper.readValue(result, WeatherResponse.class);
            WeatherResponse.Items items = weatherResponse.getResponse().getBody().getItems();

            for(int i=0; i<6; i++){
                WeatherResponse.Item item = items.getItem().get(i);
                UltraSrtWeather ultraSrtWeather = UltraSrtWeather.create(item.getBaseDate(), item.getBaseTime(), item.getFcstDate(), item.getFcstTime());
                ultraSrtWeatherService.save(ultraSrtWeather);
            }
            List<UltraSrtWeather> all = ultraSrtWeatherService.findAll();

            for(int i=0; i<items.getItem().size(); i+=6){
                for(int j=0; j<6; j++){
                    if(i+j >= items.getItem().size()) break;
                    WeatherResponse.Item item = items.getItem().get(i + j);
                    WeatherInfo weatherInfo = WeatherInfo.create(item.getCategory(), item.getFcstValue(), item.getNx(), item.getNy());
                    weatherInfo.setUltraSrtWeather(all.get(j));

                    weatherInfoService.save(weatherInfo);
                }
            }
            return weatherResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public MidWeatherResponse getMidTa(String regId, String tmFc) {
        String url = String.format("https://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey=%s&regId=%s&tmFc=%s&dataType=JSON"
        , midApiKey, regId, tmFc);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MidWeatherResponse midWeatherResponse = objectMapper.readValue(result, MidWeatherResponse.class);
            MidWeatherResponse.Item item = midWeatherResponse.getResponse().getBody().getItems().getItem().get(0);

            MidWeather midWeather = MidWeather.create(regId);
            Long save = midWeatherService.save(midWeather);

            List<MidWeatherInfo> midWeatherInfos = new ArrayList<>();

            for (int i = 3; i <= 10; i++) {
                midWeatherInfos.add(MidWeatherInfo.create(
                        i,
                        getTaMin(item, i),
                        getTaMinLow(item, i),
                        getTaMinHigh(item, i),
                        getTaMax(item, i),
                        getTaMaxLow(item, i),
                        getTaMaxHigh(item, i)
                ));
            }

            for (MidWeatherInfo midWeatherInfo : midWeatherInfos) {
                midWeatherInfo.setMidWeather(midWeather);
               midWeatherInfoService.save(midWeatherInfo);
            }
            return midWeatherResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getTaMin(MidWeatherResponse.Item item, int index) {
        return (int) getFieldValue(item, "" +
                "taMin" + index);
    }

    private int getTaMinLow(MidWeatherResponse.Item item, int index) {
        return (int) getFieldValue(item, "taMin" + index + "Low");
    }

    private int getTaMinHigh(MidWeatherResponse.Item item, int index) {
        return (int) getFieldValue(item, "taMin" + index + "High");
    }

    private int getTaMax(MidWeatherResponse.Item item, int index) {
        return (int) getFieldValue(item, "taMax" + index);
    }

    private int getTaMaxLow(MidWeatherResponse.Item item, int index) {
        return (int) getFieldValue(item, "taMax" + index + "Low");
    }

    private int getTaMaxHigh(MidWeatherResponse.Item item, int index) {
        return (int) getFieldValue(item, "taMax" + index + "High");
    }

    private static Object getFieldValue(MidWeatherResponse.Item item, String fieldName) {
        try {
            Field field = MidWeatherResponse.Item.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(item);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error getting field value: " + fieldName, e);
        }
    }




}
