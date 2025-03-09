package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Users;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainRepository {
    private final EntityManager em;

    // 팀 코드를 이용해 유저 프로필 정보 불러오기
    public List<Users> getTeamUsersByTeamCode(String teamCode){
        String sql = "SELECT u FROM Users u JOIN u.teams t WHERE t.teamCode = :teamCode";

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

}
