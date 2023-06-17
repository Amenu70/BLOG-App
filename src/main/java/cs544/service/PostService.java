package cs544.service;

import cs544.dto.PostDto;
import cs544.exception.PostNotFoundException;
import cs544.model.Post;
import cs544.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private AuthService authService;

    public List<PostDto> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }
    public void savePost(PostDto postDto){
        Post post = mapFromDToTOPost(postDto);
        postRepository.save(post);
    }
    public void editPost(Long id, PostDto postDto){
        Post post =postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For id"+id));
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
        post.setUpdateOn(new Date());
        postRepository.save(post);

    }
    public void deletePost(Long id){
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new PostNotFoundException("For id"+id);
        }
    }
    public PostDto readSinglePost(Long id){
        Post post =postRepository.findById(id).orElseThrow(()->new PostNotFoundException("For is"+id));
        return mapFromPostToDto(post);
    }
    private PostDto mapFromPostToDto(Post post){
        PostDto postDto= new PostDto();
        postDto.setId(post.getId());
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setUsername(post.getUserName());
        return postDto;
    }
    private Post mapFromDToTOPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());
//        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setPostDate(new Date());
//        post.setUserName(loggedInUser.userName());
        post.setUpdateOn(new Date());
        return post;
    }


}
