package jovisimons.dekeet.AuthService.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jovisimons.dekeet.AuthService.service.FBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {

    @Autowired
    FBUserService userSvc;
    @GetMapping("/{id}")
    public ResponseEntity<String> CheckToken(@PathVariable String id) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(id);
            String uid = decodedToken.getUid();
            return new ResponseEntity<>(uid, HttpStatus.OK);
        } catch (FirebaseAuthException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
