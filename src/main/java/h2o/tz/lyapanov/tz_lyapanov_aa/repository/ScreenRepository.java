package h2o.tz.lyapanov.tz_lyapanov_aa.repository;

import h2o.tz.lyapanov.tz_lyapanov_aa.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, String> {
    Optional<Screen> findById(String id);
}
