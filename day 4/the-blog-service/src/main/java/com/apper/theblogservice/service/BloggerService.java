package com.apper.theblogservice.service;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.EmailIsRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {
    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }
    // Blogger represents actual table in the database
    // JPA persists class into database
    public Blogger createBlogger(String email, String name, String password) throws EmailIsRegisteredException {
//        if (bloggerRepository.findByEmail(email)) {
//            throw new EmailIsRegisteredException("Email already registered.");
//        }

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        // how to persist object to database: create repository
        return bloggerRepository.save(blogger);
    }

    public Blogger getBlogger(String id) {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);

        return bloggerResult.get();
    }

    public List<Blogger> getAllBloggers() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

    public Blog createBlog(String title, String body, String bloggerId) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setBloggerId(bloggerId);

        return blogRepository.save(blog);
    }

    public Blog updateBlog(String blogId, String title, String body) throws BlogNotFoundException {
        Optional<Blog> currentBlog = blogRepository.findById(blogId);
        currentBlog.ifPresent(blog -> {
            blog.setTitle(title);
            blog.setBody(body);
            blog.setLastUpdate(LocalDateTime.now());
            blogRepository.save(blog);
        });

        return currentBlog.get();
    }

    public Blog getBlog(String id) {
        Optional<Blog> blogResult = blogRepository.findById(id);

        return blogResult.get();
    }

    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    public List<Blog> getAllBlogsByBloggerId(String bloggerId) {
        return blogRepository.findAllByBloggerId(bloggerId);
    }
}
