package ma.weather.repository;

import ma.weather.domain.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
    List<WeatherInfo> findByShortWeatherId(Long id);
    List<WeatherInfo> findByUltraSrtWeatherId(Long id);
}
