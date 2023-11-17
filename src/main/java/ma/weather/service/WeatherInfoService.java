package ma.weather.service;

import lombok.RequiredArgsConstructor;
import ma.weather.domain.WeatherInfo;
import ma.weather.repository.WeatherInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeatherInfoService {
    private final WeatherInfoRepository weatherInfoRepository;

    @Transactional
    public Long save(WeatherInfo weatherInfo){
        WeatherInfo save = weatherInfoRepository.save(weatherInfo);
        return save.getId();
    }

    @Transactional
    public WeatherInfo findOne(Long id){
        return weatherInfoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 상세 정보가 없습니다.")
        );
    }

    public List<WeatherInfo> findBySrtWeatherId(Long id){
        return weatherInfoRepository.findByShortWeatherId(id);
    }

    public List<WeatherInfo> findByUltraSrtWeatherId(Long id){
        return weatherInfoRepository.findByUltraSrtWeatherId(id);
    }

}
