import org.springframework.data.jpa.repository.JpaRepository;
import com.techm.security.facebookloginexample.entity.LoginAttempt;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    List<LoginAttempt> findByUsername(String username);

}