package ma.FITTRACK.MyFit.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "calories_tracking")
public class CaloriesTracking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "calories")
    private int calories;

    @Column(name = "date")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(Optional<User> user) {
    }
}

