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
public interface UserRepository extends JpaRepository<Users, Integer>, UserRepositoryCustom{
    Optional<Users> findByUserId(String userId);

    List<Users> getTeamUsersByTeamCode(String teamCode);
}
