package project;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
 private static final Map<String, String> userCredentials = new HashMap<>();

 static {
     userCredentials.put("admin", "admin");
     userCredentials.put("user1", "password1");
     userCredentials.put("user2", "password2");
 }

 public static boolean authenticate(String username, String password) {
     return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
 }
}
