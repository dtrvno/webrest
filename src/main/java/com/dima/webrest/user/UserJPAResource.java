package com.dima.webrest.user;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional <User>user =userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id-"+id);
        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkToUsers.withRel("all-users"));
        return resource;

    }
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser=userRepository.save(user);
       URI location=ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);

    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException("id-"+id);
        }
        return userOptional.get().getPosts();
    }
    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPost(@PathVariable int id,
                                             @RequestBody Post post) {
        Optional<User> userOptional=userRepository.findById(id);
        if (!userOptional.isPresent())
             throw new UserNotFoundException("id-"+id);
        User user=userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI location=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
