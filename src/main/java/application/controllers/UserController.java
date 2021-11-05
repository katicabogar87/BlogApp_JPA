package application.controllers;

import application.models.*;
import application.returnModels.ResponseObject;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/testRequestParam") // localhost:8080/testRequestParam?id=1&name=kiscica
    public ResponseObject<String> getRequestParam(
            @RequestParam(defaultValue = "1") Integer id,
            @RequestParam(defaultValue = "userName") String name
    ) {
        ResponseObject<String> toReturn = new ResponseObject<>();
        if (id != null) {
            if (name != null) {
                toReturn.setObject(name+id);
                toReturn.setSuccess(true);
                toReturn.setStatusCode(HttpStatus.OK);
            }
        } else {
            toReturn.setSuccess(false);
        }

        return toReturn;
    }

    @GetMapping(value = {"/", "/home"})
    public ResponseObject<String> getHome(){
        ResponseObject<String> toReturn = new ResponseObject<>();

        toReturn.setObject("Hello!");
        toReturn.setSuccess(true);
        toReturn.setStatusCode(HttpStatus.OK);
        return toReturn;
    }

    /**
     *  /users
     *  * method: GET
     *  * feladat: visszaadja az összes felhasználót az adatbázisból*/


    @PreAuthorize("hasAuthority('MANAGE_USERS')")
    @GetMapping(value = {"/users"})
    public ResponseObject<List<BlogUser>> getAllUsers(){
        ResponseObject<List<BlogUser>> toReturn = new ResponseObject<>();

        toReturn.setObject(userService.getAllUsers());
        toReturn.setSuccess(true);
        toReturn.setStatusCode(HttpStatus.OK);

        return toReturn;
    }


    /**
     * /users/{id}
     *  * method: GET
     *  * @PathVariable Long id
     *  * feladat: visszaadja az adott id-jű felhasználót adatbázisból
     *  * megjegyzés: ha nincs szám típusú id-ja a felhasználóidnak, akkor ebben az endpoint-ban lecserélhető
     *  a Long id arra az adattípusra, amilyen PRIMARY KEY-t használsz*/

    @GetMapping(value = {"/users/", "/users/{loginName}"})
    public ResponseObject<BlogUser> getOneUser(@PathVariable("loginName") String loginName){

        ResponseObject<BlogUser> toReturn = new ResponseObject<>();
        if (loginName != null){

            toReturn.setObject((BlogUser) userService.loadUserByUsername(loginName));
            toReturn.setSuccess(true);
            toReturn.setStatusCode(HttpStatus.OK);
            return toReturn;
        }
        return null;
    }

    /**
     * * /user
     *  * method: GET
     *  * feladat: visszaadja a bejelentkezett felhasználót*/
    @GetMapping(value = {"/user"})
    public ResponseObject<BlogUser> getLoggedInUser(){
        ResponseObject<BlogUser> toReturn = new ResponseObject<>();

        toReturn.setObject(userService.getLoggedInUser());
        toReturn.setSuccess(true);
        toReturn.setStatusCode(HttpStatus.OK);
        return toReturn;

    }

    /**
     * /register
     *  * method: POST
     *  * @RequestBody User user
     *  * feladat: új felhasználót rögzít az adatbázisba
     *  * megjegyzés: a User típus nem kötelező - a saját felhasználói típusodat használd*/

    @PostMapping("/register")
    public String registerUser(@RequestBody BlogUser user){

        if(userService.registerUser(user)){
            return "OK";
        }
        return "not OK";
    }

    @GetMapping("/registerExample")
    public String registerExample(){
        int counter = 0;
        for (BlogUser user:userService.createExampleUsers()) {
            boolean success = userService.registerUser(user);
            if (!success){counter++;}
        }
        if (counter == 0){
            return "OK";
        }
        return "not OK";
    }
}
