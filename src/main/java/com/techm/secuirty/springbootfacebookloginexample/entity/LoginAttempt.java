package com.techm.security.facebookloginexample.entity;

// Step 1: Create an entity class to represent a login attempt
@Entity
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String ipAddress;
    private LocalDateTime timestamp;

    // getters and setters
}

// Step 2: Create a repository interface for this entity
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
}

// Step 3: Create an event listener
@Component
public class AuthenticationEventListener {
    @Autowired
    private LoginAttemptRepository loginAttemptRepository;

    @EventListener
    public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
        Authentication authentication = event.getAuthentication();
        String username = authentication.getName();
        String ipAddress = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();

        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setUsername(username);
        loginAttempt.setIpAddress(ipAddress);
        loginAttempt.setTimestamp(LocalDateTime.now());

        loginAttemptRepository.save(loginAttempt);
    }
}
