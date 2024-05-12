package ma.FITTRACK.MyFit.services;

import ma.FITTRACK.MyFit.models.CaloriesTracking;
import ma.FITTRACK.MyFit.repository.CaloriesTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaloriesTrackingService {

    @Autowired
    private CaloriesTrackingRepository caloriesTrackingRepository;

    public List<CaloriesTracking> getAllTrackings() {
        return caloriesTrackingRepository.findAll();
    }

    public CaloriesTracking getTrackingById(Long id) {
        Optional<CaloriesTracking> tracking = caloriesTrackingRepository.findById(id);
        if(tracking.isPresent()) {
            return tracking.get();
        } else {
            throw new RuntimeException("Tracking not found for id: " + id);
        }
    }

    public CaloriesTracking createTracking(CaloriesTracking tracking) {
        return caloriesTrackingRepository.save(tracking);
    }

    public CaloriesTracking updateTracking(Long id, CaloriesTracking trackingDetails) {
        Optional<CaloriesTracking> tracking = caloriesTrackingRepository.findById(id);
        if(tracking.isPresent()) {
            CaloriesTracking existingTracking = tracking.get();
            existingTracking.setCalories(trackingDetails.getCalories());
            existingTracking.setDate(trackingDetails.getDate());
            return caloriesTrackingRepository.save(existingTracking);
        } else {
            throw new RuntimeException("Tracking not found for id: " + id);
        }
    }

    public void deleteTracking(Long id) {
        Optional<CaloriesTracking> tracking = caloriesTrackingRepository.findById(id);
        if(tracking.isPresent()) {
            caloriesTrackingRepository.delete(tracking.get());
        } else {
            throw new RuntimeException("Tracking not found for id: " + id);
        }
    }
}