package com.oop.majdb.Controllers;

import com.oop.majdb.Entities.Person;
import com.oop.majdb.Entities.Post;
import com.oop.majdb.Repos.PersonRepo;
import com.oop.majdb.Repos.PostRepo;
import com.oop.majdb.Response.LoginUser;
import com.oop.majdb.Response.PersonGet;
import com.oop.majdb.Response.PostRes;
import com.oop.majdb.Response.SignupUser;
import com.oop.majdb.Services.ServicePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Controller
@RequestMapping("")
public class ControlPerson {

    private final ServicePerson servicePerson;

    public ControlPerson(ServicePerson servicePerson) {
        this.servicePerson = servicePerson;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginUser user) {
        return servicePerson.login(user);
    }
    @PostMapping("/signup")
    public ResponseEntity<Object>  signup(@RequestBody SignupUser user) {
        return servicePerson.signup(user);
    }


    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<Object> getUser(@RequestParam int userID) {
        return servicePerson.getUser(userID);
    }


    @GetMapping("/")
    @ResponseBody
    public List<PostRes> getPosts()
    {
        return servicePerson.getPosts();
    }


    @GetMapping("/users")
    public @ResponseBody List<PersonGet> getUsers()
    {
        return servicePerson.getUsers();
    }

}
