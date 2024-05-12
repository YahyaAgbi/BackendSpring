package ma.FITTRACK.MyFit.repository;

import ma.FITTRACK.MyFit.models.Food;
import ma.FITTRACK.MyFit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByUser(Optional<User> user);
}