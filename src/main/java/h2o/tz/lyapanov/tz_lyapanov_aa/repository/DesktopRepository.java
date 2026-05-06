package h2o.tz.lyapanov.tz_lyapanov_aa.repository;

import h2o.tz.lyapanov.tz_lyapanov_aa.model.Desktop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DesktopRepository extends JpaRepository<Desktop, String> {

    Optional<Desktop> findById(String id);

}
