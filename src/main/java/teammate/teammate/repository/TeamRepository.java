package teammate.teammate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teammate.teammate.domain.Teams;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Teams, Integer> {
    Optional<Teams> findByTeamCode(String teamCode);
}