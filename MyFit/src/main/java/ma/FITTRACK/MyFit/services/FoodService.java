package ma.FITTRACK.MyFit.services;

import ma.FITTRACK.MyFit.models.Food;
import ma.FITTRACK.MyFit.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isPresent()) {
            return food.get();
        } else {
            throw new RuntimeException("Food not found for id: " + id);
        }
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public Food updateFood(Long id, Food foodDetails) {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isPresent()) {
            Food existingFood = food.get();
            existingFood.setNameFood(foodDetails.getNameFood());
            existingFood.setCalories(foodDetails.getCalories());
            existingFood.setFat(foodDetails.getFat());
            existingFood.setProtein(foodDetails.getProtein());
            existingFood.setCarbs(foodDetails.getCarbs());
            return foodRepository.save(existingFood);
        } else {
            throw new RuntimeException("Food not found for id: " + id);
        }
    }

    public void deleteFood(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isPresent()) {
            foodRepository.delete(food.get());
        } else {
            throw new RuntimeException("Food not found for id: " + id);
        }
    }
}