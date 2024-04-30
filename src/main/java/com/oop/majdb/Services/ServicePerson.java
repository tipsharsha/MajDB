package com.oop.majdb.Services;
import com.oop.majdb.Entities.Person;
import com.oop.majdb.Entities.Post;
import com.oop.majdb.Repos.PersonRepo;
import com.oop.majdb.Repos.PostRepo;
import com.oop.majdb.Response.LoginUser;
import com.oop.majdb.Response.PersonGet;
import com.oop.majdb.Response.PostRes;
import com.oop.majdb.Response.SignupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Service
@RequestMapping("")
public class ServicePerson {

    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PostRepo postRepo;

    public ResponseEntity<Object> login(LoginUser user) {
        // Process login logic here
        if(personRepo.findByEmail(user.getEmail())==null) {
            return new ResponseEntity<>(Map.of("Error","User does not exist"), HttpStatus.NOT_FOUND);
        }
        if(personRepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) == null) {
            return new ResponseEntity<>(Map.of("Error","Username/Password Incorrect"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
    }

    public ResponseEntity<Object>  signup(SignupUser user) {
        if(personRepo.findByEmail(user.getEmail())!=null) {
            return new ResponseEntity<>(Map.of("Error","Forbidden, Account already exists"), HttpStatus.NOT_FOUND);
        }
        personRepo.save(new Person(user.getUsername(), user.getEmail(), user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
    }


    public ResponseEntity<Object> getUser(int userID) {
        Person person = personRepo.findByUserID(userID);
        if(person == null) {
            return new ResponseEntity<>(Map.of("Error","User does not exist"), HttpStatus.NOT_FOUND);
        }
        PersonGet personGet = new PersonGet(person.getUserID(), person.getName(), person.getEmail());
        return new ResponseEntity<>(personGet, HttpStatus.OK);
    }



    public List<PostRes> getPosts()
    {
        List<Post> posts = postRepo.findAll();
        for(int i  =0; i < posts.size(); i++) {
            for(int j = i+1; j < posts.size(); j++) {
                if(posts.get(i).createdAt.isBefore(posts.get(j).createdAt)) {
                    Post temp = posts.get(i);
                    posts.set(i, posts.get(j));
                    posts.set(j, temp);
                }
            }
        }

        List<PostRes> postsRes = new ArrayList<>();
        for (Post post: posts) {
            postsRes.add(new PostRes(post.getPostID(), post.getPostBody(),post.getDate(), post.getComments()));
        }
        return postsRes;


    }


    public @ResponseBody List<PersonGet> getUsers()
    {
        //Remove posts from all people
        List<Person> people = (List<Person>) personRepo.findAll();
        List<PersonGet> peopleGet = new ArrayList<>();
        for(Person person: people) {
            peopleGet.add(new PersonGet(person.getUserID(), person.getName(), person.getEmail()));
        }
        return peopleGet;
    }

}

