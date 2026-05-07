package h2o.tz.lyapanov.tz_lyapanov_aa.repository;

import h2o.tz.lyapanov.tz_lyapanov_aa.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, String> {
    Optional<Laptop> findById(String id);
}
