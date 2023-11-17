package ma.weather.repository;

import ma.weather.domain.UltraSrtWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UltraSrtWeatherRepository extends JpaRepository<UltraSrtWeather, Long> {
    List<UltraSrtWeather> findByBaseDateAndBaseTime(String baseDate, String baseTime);

}
