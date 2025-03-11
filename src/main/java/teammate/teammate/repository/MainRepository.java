package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Todos;
import teammate.teammate.domain.Users;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MainRepository {
    private final EntityManager em;

    // 팀 코드를 이용해 유저 프로필 정보 불러오기
    @Transactional(readOnly = true)
    public List<Users> getTeamUsersByTeamCode(String teamCode) {
//        String sql = "SELECT u FROM Users u JOIN u.teams t WHERE t.teamCode = :teamCode";
        String sql = "SELECT u FROM Users u JOIN Teams t ON u.userId = t.userId WHERE t.teamCode = :teamCode";

        List<Users> resultList = em.createQuery(sql, Users.class).setParameter("teamCode", teamCode).getResultList();

        return resultList;
    }




}
