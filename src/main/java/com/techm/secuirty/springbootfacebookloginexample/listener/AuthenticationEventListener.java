import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import com.techm.security.facebookloginexample.entity.LoginAttempt;
import com.techm.security.facebookloginexample.repository.LoginAttemptRepository;

import java.time.LocalDateTime;

@Component
public class AuthenticationEventListener {

    private final LoginAttemptRepository loginAttemptRepository;

    @Autowired
    public AuthenticationEventListener(LoginAttemptRepository loginAttemptRepository) {
        this.loginAttemptRepository = loginAttemptRepository;
    }

    @EventListener
    public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        String ipAddress = ((WebAuthenticationDetails) event.getAuthentication().getDetails()).getRemoteAddress();

        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setUsername(username);
        loginAttempt.setIpAddress(ipAddress);
        loginAttempt.setTimestamp(LocalDateTime.now());

        loginAttemptRepository.save(loginAttempt);
    }
}