package teammate.teammate.repository;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.stereotype.Repository;import teammate.teammate.domain.UserLinks;import teammate.teammate.domain.Users;import java.util.List;@Repositorypublic interface UserLinksRepository extends JpaRepository<UserLinks, Integer> {	List<UserLinks> findByUsers(Users users);}