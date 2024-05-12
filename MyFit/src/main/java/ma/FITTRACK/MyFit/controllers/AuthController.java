package ma.FITTRACK.MyFit.controllers;

import jakarta.validation.Valid;
import ma.FITTRACK.MyFit.models.CaloriesTracking;
import ma.FITTRACK.MyFit.models.Food;
import ma.FITTRACK.MyFit.models.User;
import ma.FITTRACK.MyFit.payload.request.LoginRequest;
import ma.FITTRACK.MyFit.payload.request.SignupRequest;
import ma.FITTRACK.MyFit.payload.response.MessageResponse;
import ma.FITTRACK.MyFit.payload.response.UserInfoResponse;
import ma.FITTRACK.MyFit.repository.CaloriesTrackingRepository;
import ma.FITTRACK.MyFit.repository.UserRepository;
import ma.FITTRACK.MyFit.security.jwt.JwtUtils;
import ma.FITTRACK.MyFit.services.FoodService;
import ma.FITTRACK.MyFit.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private FoodService foodService;

    @GetMapping("/foods")
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }



    @Autowired
    private CaloriesTrackingRepository caloriesTrackingService;

    @GetMapping ("/trackings")
    public ResponseEntity<List<CaloriesTracking>> getAllTrackings() {
        return ResponseEntity.ok(caloriesTrackingService.findAll());
    }

    @GetMapping("/trackings/{id}")
    public ResponseEntity<CaloriesTracking> getTrackingById(@PathVariable Long id) {
        return ResponseEntity.ok(caloriesTrackingService.getTrackingById(id));
    }

@GetMapping("/food/{id}")
public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
    return ResponseEntity.ok(foodService.getFoodById(id));
}

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(
                        userDetails.getUsername(),
                        userDetails.getEmail()));
    }


@PostMapping("/signup")
public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getHeight(),
            signUpRequest.getWeight(),
            signUpRequest.getAge());

    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
}



    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
 
