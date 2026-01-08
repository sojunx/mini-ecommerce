package dev.sojunx.ecommerce.authorize.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/test")
    ResponseEntity<?> test() {

        var res = new HashMap<>();
        res.put("status", HttpStatus.OK);
        res.put("message", "Success");
        res.put("data", "test");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<?> getUsers() {

        var res = new HashMap<>();
        res.put("status", HttpStatus.OK);
        res.put("message", "Success");
        res.put("data", "test2");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
