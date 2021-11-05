package application.controllers;


import application.models.*;
import application.returnModels.ResponseObject;
import application.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * /blogs
     *  * method: GET
     *  * feladat: visszaadja az összes blogot az adatbázisból
     *
     */


    @GetMapping(value = {"/blogs"})
    public ResponseObject<List<Blog>> getAllBlogs(){
        ResponseObject<List<Blog>> toReturn = new ResponseObject<>();

        toReturn.setObject(blogService.getAllBlogs());
        toReturn.setSuccess(true);
        toReturn.setStatusCode(HttpStatus.OK);

        return toReturn;
    }

    /** * /blogs
     *  * method: POST
     *  * @RequestBody Blog blog
     *  * feladat: új blogot rögzít az adatbázisba
     *  * megjegyzés: a felhasználónak be kell jelentkeznie új blog készítéséhez*/

    @PreAuthorize("hasAuthority('CREATE_BLOG')")
    @PostMapping("/blogs")
    public String registerUser(@RequestBody Blog blog){

        if(blogService.createBlog(blog)){
            return "OK";
        }
        return "not OK";
    }
}