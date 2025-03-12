package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teammate.teammate.domain.CalendarEvents;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CalendarRepository {
    private final EntityManager em;

    // 일정 1개 조회
    @Transactional(readOnly = true)
    public CalendarEvents getCalendarById(int id) {
        String sql = "SELECT c FROM CalendarEvents c WHERE c.id = :id";

        CalendarEvents calendarEvents = em.createQuery(sql, CalendarEvents.class)
                .setParameter("id", id)
                .getSingleResult();

        return calendarEvents;

    }

    @Transactional
    public CalendarEvents updateCalendar(int id, CalendarEvents updateCalendar) {
        String sql = "SELECT c FROM CalendarEvents c WHERE c.id = :id";

        CalendarEvents existingCalendar = em.createQuery(sql, CalendarEvents.class)
                .setParameter("id", id)
                .getSingleResult();

        // 업데이트 로직
        existingCalendar.setTitle(updateCalendar.getTitle());
        existingCalendar.setCategory(updateCalendar.getCategory());
        existingCalendar.setDescription(updateCalendar.getDescription());
        existingCalendar.setStartDateAt(updateCalendar.getStartDateAt());

        return existingCalendar;
    }

    @Transactional
    public boolean deleteCalendar(int id) {
        String sql = "DELETE FROM calendar_events WHERE id = :id";

        int rowsAffected = em.createNativeQuery(sql)
                .setParameter("id", id)
                .executeUpdate();

        return rowsAffected > 0;
    }

    @Transactional
    public boolean addCalendar(CalendarEvents calendar) {
        try {
            em.persist(calendar); // INSERT 수행

            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<CalendarEvents> getCalendar(String teamCode, int year, int month) {
        String sql = "SELECT c FROM CalendarEvents c WHERE c.teamCode = :teamCode " + "AND YEAR(c.startDateAt) = :year " + "AND MONTH(c.startDateAt) = :month " + "ORDER BY c.startDateAt";

        List<CalendarEvents> resultList = em.createQuery(sql, CalendarEvents.class).setParameter("teamCode", teamCode).setParameter("year", year).setParameter("month", month).getResultList();

        return resultList;
    }

    @Transactional(readOnly = true)
    public List<CalendarEvents> getEvent(String teamCode, int year, int month, int day) {
        String sql = "SELECT c FROM CalendarEvents c WHERE c.teamCode = :teamCode " + "AND YEAR(c.startDateAt) = :year " + "AND MONTH(c.startDateAt) = :month " + "AND DAY(c.startDateAt) = :day " + "ORDER BY c.startDateAt";

        List<CalendarEvents> resultList = em.createQuery(sql, CalendarEvents.class).setParameter("teamCode", teamCode).setParameter("year", year).setParameter("month", month).setParameter("day", day).getResultList();

        return resultList;
    }
}
