package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teammate.teammate.domain.Users;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Users> getTeamUsersByTeamCode(String teamCode) {
        String sql = "SELECT u FROM Users u JOIN Teams t ON u.userId = t.userId WHERE t.teamCode = :teamCode";

        List<Users> resultList = em.createQuery(sql, Users.class).setParameter("teamCode", teamCode).getResultList();

        return resultList;
    }
}
