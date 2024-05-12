package ma.FITTRACK.MyFit.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_logs")
public class User_Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "action")
    private String action;


    public User_Log() {
    }

    public User_Log(User user, LocalDateTime timestamp, String action) {
        this.user = user;
        this.timestamp = timestamp;
        this.action = action;
    }


    public Long getId() {
        return id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}