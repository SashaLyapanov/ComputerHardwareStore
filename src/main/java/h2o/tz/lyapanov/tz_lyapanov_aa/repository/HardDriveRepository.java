package h2o.tz.lyapanov.tz_lyapanov_aa.repository;

import h2o.tz.lyapanov.tz_lyapanov_aa.model.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HardDriveRepository extends JpaRepository<HardDrive, String> {
    Optional<HardDrive> findById(String id);
}
