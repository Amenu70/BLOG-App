package cs544.service;

import cs544.model.Post;
import cs544.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public void savePost(Post post){
        postRepository.save(post);
    }
}
