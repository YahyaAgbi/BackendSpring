package ma.FITTRACK.MyFit.services;

import ma.FITTRACK.MyFit.models.CaloriesTracking;
import ma.FITTRACK.MyFit.models.Food;
import ma.FITTRACK.MyFit.models.User;
import ma.FITTRACK.MyFit.repository.CaloriesTrackingRepository;
import ma.FITTRACK.MyFit.repository.FoodRepository;
import ma.FITTRACK.MyFit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CaloriesTrackingRepository caloriesTrackingRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> getFoodsByUser(Optional<User> user) {
        return foodRepository.findByUser(user);
    }

    public CaloriesTracking saveObjective(CaloriesTracking objective) {
        return caloriesTrackingRepository.save(objective);
    }

    public List<CaloriesTracking> getObjectivesByUser(Optional<User> user) {
        return caloriesTrackingRepository.findByUser(user);
    }
}