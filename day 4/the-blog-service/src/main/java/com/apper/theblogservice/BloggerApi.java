package com.apper.theblogservice;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.EmailIsRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BloggerApi {
    private BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    // for Blogger
    @PostMapping("/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailIsRegisteredException {
        System.out.println(request);

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());
        return response;
    }

    @GetMapping("/blogger/{id}")
    public BloggerDetails getBlogger(@PathVariable String id) {
        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration((blogger.getCreatedAt()));

        return bloggerDetails;
    }

    @GetMapping("/blogger")
    @ResponseStatus(HttpStatus.OK)
    public List<BloggerDetails> getAllBloggers() {
        List<Blogger> bloggers = bloggerService.getAllBloggers();

        List<BloggerDetails> bloggerDetails = bloggers.stream().map(
                blogger -> {
                    BloggerDetails bloggerResponse = new BloggerDetails();
                    bloggerResponse.setId(blogger.getId());
                    bloggerResponse.setName(blogger.getName());
                    bloggerResponse.setEmail(blogger.getEmail());
                    bloggerResponse.setDateRegistration(blogger.getCreatedAt());
                    return bloggerResponse;
                }
        ).toList();

        return bloggerDetails;
    }

    // for Blog

    @PostMapping("/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) {
        System.out.println(request);

        Blog createdBlog = bloggerService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdated(createdBlog.getLastUpdate());

        return response;
    }

    @PutMapping("/blog/{blog_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreateBlogResponse updateBlog(@PathVariable String blog_id, @RequestBody CreateBlogRequest request) throws BlogNotFoundException {
        Blog blog = bloggerService.updateBlog(blog_id, request.getTitle(), request.getBody());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(blog.getId());
        response.setBloggerId(blog.getBloggerId());
        response.setCreatedAt(blog.getCreatedAt());
        response.setLastUpdated(blog.getLastUpdate());

        return response;
    }

    @GetMapping("/blog/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDetails getBlog(@PathVariable  String id) {
        Blog blog = bloggerService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBloggerId(blog.getBloggerId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdated(blog.getLastUpdate());

        return blogDetails;
    }

    @GetMapping("/blog")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDetails> getAllBlogs() {
        List<Blog> blogs = bloggerService.getAllBlogs();

        List<BlogDetails> blogDetails = blogs.stream().map(
                blog -> {
                    BlogDetails blogResponse = new BlogDetails();
                    blogResponse.setBloggerId(blog.getBloggerId());
                    blogResponse.setTitle(blog.getTitle());
                    blogResponse.setBody(blog.getBody());
                    blogResponse.setCreatedAt(blog.getCreatedAt());
                    blogResponse.setLastUpdated(blog.getLastUpdate());
                    return blogResponse;
                }
        ).toList();

        return blogDetails;
    }

    @GetMapping("/blog/blogger/{blogger_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<BlogDetails> getAllBlogsByBloggerId(@PathVariable String blogger_id) {
        List<Blog> blogs = bloggerService.getAllBlogsByBloggerId(blogger_id);

        List<BlogDetails> blogDetails = blogs.stream().map(
                blog -> {
                    BlogDetails blogResponse = new BlogDetails();
                    blogResponse.setBloggerId(blog.getBloggerId());
                    blogResponse.setTitle(blog.getTitle());
                    blogResponse.setBody(blog.getBody());
                    blogResponse.setCreatedAt(blog.getCreatedAt());
                    blogResponse.setLastUpdated(blog.getLastUpdate());
                    return blogResponse;
                }
        ).toList();

        return blogDetails;
    }


}
