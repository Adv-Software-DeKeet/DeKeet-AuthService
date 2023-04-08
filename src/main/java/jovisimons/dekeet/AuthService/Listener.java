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

    @RabbitListener(queues = {"q.userRegister"})
    public void onUserRegistration(User user) {
        log.info("User Registration Event Received: {}", user);
        try {
            svc.SetRole(user);
        } catch (FirebaseAuthException e) {
            log.info(e.getMessage());
        }
    }

    @RabbitListener(queues = {"q.userUpdate"})
    public void onUserUpdate(User user) {
        log.info("User update Event Received: {}", user);
        try {
            svc.UpdateUser(user);
        } catch (FirebaseAuthException e) {
            log.info(e.getMessage());
        }
    }

    @RabbitListener(queues = {"q.userDelete"})
    public void onUserDelete(String uid) {
        log.info("User delete Event Received: {}", uid);
        try {
            svc.DeleteUser(uid);
        } catch (FirebaseAuthException e) {
            log.info(e.getMessage());
        }
    }
}
