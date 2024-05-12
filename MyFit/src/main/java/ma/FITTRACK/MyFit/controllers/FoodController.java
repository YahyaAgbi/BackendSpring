package ma.FITTRACK.MyFit.controllers;

import ma.FITTRACK.MyFit.models.Food;
import ma.FITTRACK.MyFit.models.User;
import ma.FITTRACK.MyFit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Food addFood(@RequestBody Food food, @RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);

        return userService.saveFood(food);
    }

    @GetMapping("/get")
    public List<Food> getFoods(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return userService.getFoodsByUser(user);
    }
}

