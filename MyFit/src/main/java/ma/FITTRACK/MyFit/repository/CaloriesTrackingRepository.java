package ma.FITTRACK.MyFit.repository;

import ma.FITTRACK.MyFit.models.CaloriesTracking;
import ma.FITTRACK.MyFit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CaloriesTrackingRepository extends JpaRepository<CaloriesTracking, Long> {
    List<CaloriesTracking> findByDateBetween(Date startDate, Date endDate);

    CaloriesTracking getTrackingById(Long id);

    List<CaloriesTracking> findByUser(Optional<User> user);
}