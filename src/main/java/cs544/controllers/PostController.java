package cs544.controllers;

import cs544.dto.PostDto;
import cs544.model.Post;
import cs544.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity publishPost(@RequestBody PostDto postDto){
        postService.savePost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> posts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(postService.readSinglePost(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity editPost(@PathVariable Long id, @RequestBody PostDto postDto){
        postService.editPost(id,postDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
