package application.services;

import application.jpaRepos.UserRepo;
import application.models.BlogUser;
import application.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService{

    @PersistenceContext
    private EntityManager em;

    private UserRepo userRepo;

    private PasswordEncoder encoder;


    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

        // em.find(AppUser.class, username);

        return em.createQuery(
                        "SELECT user FROM BlogUser user WHERE user.loginName  = :loginName",
                        BlogUser.class)
                .setParameter("loginName", loginName)
                .getSingleResult();
    }


    @Transactional
    public BlogUser getLoggedInUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            Object principal =  authentication.getPrincipal();
            if(principal instanceof BlogUser){
                return (BlogUser) principal;
            }
        }
        return  null;
    }

    @Transactional
    public List<BlogUser> getAllUsers() {
        try {
                /*List<BlogUser> blogUsers = em.createQuery(getQuery(), BlogUser.class)
                        .getResultList();*/

            return userRepo.findAll();}
        catch (Exception e){
            e.printStackTrace();
        }
        return null;}

    @Transactional
    public boolean registerUser(BlogUser userToRegister){

        try {
            userToRegister.setPassword(encoder.encode(userToRegister.getPassword()));


      /*    userRepo.save(blogUser);
        */
            em.persist(userToRegister);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public BlogUser[] createExampleUsers(){

        String userPw = encoder.encode("user");
        String adminPw = encoder.encode("admin");

        BlogUser user = new BlogUser( "user", userPw, Role.REG_USER);
        BlogUser admin = new BlogUser( "admin", adminPw, Role.ADMIN);

        BlogUser[] users = {user, admin};

        return users;

    }


    @Transactional
    public void changePassword(BlogUser blogUser) {
        BlogUser blogUserFromDB = (BlogUser) loadUserByUsername(blogUser.getUsername());
        blogUserFromDB.setPassword(blogUser.getPassword());
    }



}


