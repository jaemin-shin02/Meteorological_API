package ma.weather.service;

import lombok.RequiredArgsConstructor;
import ma.weather.domain.UltraSrtWeather;
import ma.weather.repository.UltraSrtWeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UltraSrtWeatherService {
    private final UltraSrtWeatherRepository ultraSrtWeatherRepository;

    @Transactional
    public Long save(UltraSrtWeather ultraSrtWeather){
        UltraSrtWeather save = ultraSrtWeatherRepository.save(ultraSrtWeather);
        return save.getId();
    }

    @Transactional
    public UltraSrtWeather findOne(Long id){
        return ultraSrtWeatherRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 날씨 정보가 없습니다.")
        );
    }

    public List<UltraSrtWeather> findAll(){
        return ultraSrtWeatherRepository.findAll();
    }

    public List<UltraSrtWeather> findByBaseDateAndTime(String baseDate, String baseTime){
        return ultraSrtWeatherRepository.findByBaseDateAndBaseTime(baseDate, baseTime);
    }
}
