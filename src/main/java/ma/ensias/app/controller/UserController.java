package ma.ensias.app.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.ensias.app.service.UserService;
import ma.ensias.app.entity.*;




@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login/user")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody Map<String,Object> userMap){
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(email, password);
        Map<String,String> map = new HashMap<>();
        map.put("message","Login Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String, Object> userMap){

        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String phonenumber=(String) userMap.get("phonenumber");
        String adress=(String) userMap.get("adress");
        Date date= null;
 
        User user = userService.registerUser(firstName, lastName,email,password, phonenumber, adress, date);
        Map<String,String> map = new HashMap<>();
        map.put("message","registred succesfully");
        return new ResponseEntity<>(map, HttpStatus.OK);

    }
    @RequestMapping("/")
    public String hello(){
        return "helllo madafaaaaakkkk";
    }
    @GetMapping("/{name}")
    public String heello(@PathVariable String name){
        return " hellooo madafak "+ name;
    }

}

