package jovisimons.dekeet.AuthService.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jovisimons.dekeet.common.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FBUserService {

    public void CreateUser(User user) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setUid(user.getUid())
                .setEmail(user.getEmail())
                .setEmailVerified(false)
                .setPassword(user.getPassword())
                .setDisplayName(user.getName())
                .setPhotoUrl("http://www.example.com/12345678/photo.png")
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
        System.out.println("Successfully created new user: " + userRecord.getUid());
    }

    public void UpdateUser(User updatedUser) throws FirebaseAuthException {
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(updatedUser.getUid())
                .setEmail(updatedUser.getEmail())
                .setDisplayName(updatedUser.getName());

        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);

        SetRole(updatedUser);

        System.out.println("Successfully updated user: " + userRecord.getUid());
    }

    public void DeleteUser(String uid) throws FirebaseAuthException {
        FirebaseAuth.getInstance().deleteUser(uid);
        System.out.println("Successfully deleted user.");
    }

    public void SetRole(User user) throws FirebaseAuthException {
        Map<String, Object> claims = new HashMap<>();
        claims.put(user.getRole(), true);
        FirebaseAuth.getInstance().setCustomUserClaims(user.getUid(), claims);
    }
}
