package ma.weather.repository;

import ma.weather.domain.ShortWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortWeatherRepository extends JpaRepository<ShortWeather, Long> {
    List<ShortWeather> findByBaseDateAndBaseTime(String baseDate, String baseTime);
}
