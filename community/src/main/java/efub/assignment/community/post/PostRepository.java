package efub.assignment.community.post;

import efub.assignment.community.account.domain.Account;
import efub.assignment.community.post.domain.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    Boolean existsByAccountEmail(String email);

}
