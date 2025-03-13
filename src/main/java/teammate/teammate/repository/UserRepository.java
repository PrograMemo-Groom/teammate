package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teammate.teammate.domain.Users;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserId(String userId);
//    private final EntityManager em;
//
//    public Users getUserByUsername(int user_id){
//        String query = "select u from Users u";
//
//        List<Users> resultList = em.createQuery(query, Users.class).getResultList();
//
//        return resultList.get(0);
//    }

    Optional<Users> findByEmail(String email);
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}