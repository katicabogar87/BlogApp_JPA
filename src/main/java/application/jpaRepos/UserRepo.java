package application.jpaRepos;

import application.models.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<BlogUser, String> {
  }
