package ma.weather.service;

import lombok.RequiredArgsConstructor;
import ma.weather.domain.ShortWeather;
import ma.weather.repository.ShortWeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShortWeatherService {
    private final ShortWeatherRepository shortWeatherRepository;

    @Transactional
    public Long save(ShortWeather shortWeather){
        ShortWeather save = shortWeatherRepository.save(shortWeather);
        return save.getId();
    }

    public ShortWeather findOne(Long id){
        return shortWeatherRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 날씨 정보가 없습니다.")
        );
    }

    public List<ShortWeather> findAll(){
        return shortWeatherRepository.findAll();
    }

    public List<ShortWeather> findByBaseDateAndTime(String baseDate, String baseTime){
        return shortWeatherRepository.findByBaseDateAndBaseTime(baseDate, baseTime);
    }
}
