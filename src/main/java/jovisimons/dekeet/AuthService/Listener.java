package jovisimons.dekeet.AuthService;

import com.google.firebase.auth.FirebaseAuthException;
import jovisimons.dekeet.AuthService.service.FBUserService;
import jovisimons.dekeet.common.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Listener {
    @Autowired
    FBUserService svc;

    @RabbitListener(queues = {"q.auth"})
    public void onUserRegistration(User user) {
        log.info("User Registration Event Received: {}", user);
        try {
            svc.SetRole(user);
        } catch (FirebaseAuthException e) {
            log.info(e.getMessage());
        }
    }
}
