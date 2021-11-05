package application.services;

import application.jpaRepos.BlogRepo;
import application.jpaRepos.UserRepo;
import application.models.Blog;
import application.models.BlogUser;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogService {

    @PersistenceContext
    private EntityManager em;

    private BlogRepo blogRepo;

    public BlogService(EntityManager em, BlogRepo blogRepo) {
        this.em = em;
        this.blogRepo = blogRepo;
    }

    @Transactional
    public List<Blog> getAllBlogs() {
        try {
                /*List<Blog> blogs = em.createQuery(getQuery(), Blog.class)
                        .getResultList();*/

            return blogRepo.findAll();}
        catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Transactional
    public boolean createBlog(Blog blogToCreate){

        try {
            em.persist(blogToCreate);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
