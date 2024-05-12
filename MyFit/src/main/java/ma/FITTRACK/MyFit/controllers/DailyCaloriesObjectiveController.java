package ma.FITTRACK.MyFit.controllers;
import ma.FITTRACK.MyFit.models.CaloriesTracking;
import ma.FITTRACK.MyFit.models.User;
import ma.FITTRACK.MyFit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/objective")
public class DailyCaloriesObjectiveController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public CaloriesTracking addObjective(@RequestBody CaloriesTracking objective, @RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        objective.setUser(user);
        return userService.saveObjective(objective);
    }

    @GetMapping("/get")
    public List<CaloriesTracking> getObjectives(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return userService.getObjectivesByUser(user);
    }
}
