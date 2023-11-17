package ma.weather.service;

import lombok.RequiredArgsConstructor;
import ma.weather.domain.MidWeather;
import ma.weather.repository.MidWeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MidWeatherService {
    private final MidWeatherRepository midWeatherRepository;

    @Transactional
    public Long save(MidWeather midWeather){
        MidWeather save = midWeatherRepository.save(midWeather);
        return save.getId();
    }

    public MidWeather findOne(Long id){
        return midWeatherRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일치하는 날씨 정보가 없습니다.")
        );
    }

    public List<MidWeather> findAll(){
        return midWeatherRepository.findAll();
    }

    public MidWeather findByRegId(String regId){
        return midWeatherRepository.findByRegId(regId);
    }


}
