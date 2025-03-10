package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Todos;
import teammate.teammate.domain.Users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MainRepository {
    private final EntityManager em;

    // 팀 코드를 이용해 유저 프로필 정보 불러오기
    public List<Users> getTeamUsersByTeamCode(String teamCode){
//        String sql = "SELECT u FROM Users u JOIN u.teams t WHERE t.teamCode = :teamCode";
        String sql = "SELECT u FROM Users u JOIN Teams t ON u.userId = t.userId WHERE t.teamCode = :teamCode";

        List<Users> resultList = em.createQuery(sql, Users.class)
                .setParameter("teamCode", teamCode)
                .getResultList();

        return resultList;
    }

    public List<CalendarEvents> getCalendar(String teamCode, int year, int month) {
        String sql = "SELECT c FROM CalendarEvents c WHERE c.teamCode = :teamCode " +
                "AND YEAR(c.startDateAt) = :year " +
                "AND MONTH(c.startDateAt) = :month " +
                "ORDER BY c.startDateAt";

        List<CalendarEvents> resultList = em.createQuery(sql, CalendarEvents.class)
                .setParameter("teamCode", teamCode)
                .setParameter("year", year)
                .setParameter("month", month)
                .getResultList();

        return resultList;
    }

    public List<CalendarEvents> getEvent(String teamCode, int year, int month, int day) {
        String sql = "SELECT c FROM CalendarEvents c WHERE c.teamCode = :teamCode " +
                "AND YEAR(c.startDateAt) = :year " +
                "AND MONTH(c.startDateAt) = :month " +
                "AND DAY(c.startDateAt) = :day " +
                "ORDER BY c.startDateAt";

        List<CalendarEvents> resultList = em.createQuery(sql, CalendarEvents.class)
                .setParameter("teamCode", teamCode)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("day", day)
                .getResultList();

        return resultList;
    }

    public Map<String, List<Todos>> getTodos(String teamCode, int year, int month, int day) {
        String sql = "SELECT t FROM Todos t WHERE t.teamCode = :teamCode " +
                "AND YEAR(t.createTime) = :year " +
                "AND MONTH(t.createTime) = :month " +
                "AND DAY(t.createTime) = :day " +
                "ORDER BY t.createTime";

        List<Todos> resultList = em.createQuery(sql, Todos.class)
                .setParameter("teamCode", teamCode)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("day", day)
                .getResultList();

        // userId를 기준으로 그룹화
        return resultList.stream().collect(Collectors.groupingBy(t -> t.getUsers().getUserId()));
    }
}
