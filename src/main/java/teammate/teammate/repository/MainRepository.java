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

    @Transactional(readOnly = true)
    public Map<String, List<Todos>> getTodos(String teamCode, int year, int month, int day) {
        String sql = "SELECT t FROM Todos t WHERE t.teamCode = :teamCode " + "AND YEAR(t.createTime) = :year " + "AND MONTH(t.createTime) = :month " + "AND DAY(t.createTime) = :day " + "ORDER BY t.createTime";

        List<Todos> resultList = em.createQuery(sql, Todos.class).setParameter("teamCode", teamCode).setParameter("year", year).setParameter("month", month).setParameter("day", day).getResultList();

        // userId를 기준으로 그룹화
        return resultList.stream().collect(Collectors.groupingBy(t -> t.getUserId()));
    }

    @Transactional // 데이터 변경이 일어나므로 트랜잭션 적용
    public Todos updateTodo(int todoId, Todos updateTodo) {
        String sql = "SELECT t FROM Todos t WHERE t.id = :todoId";

        Todos existingTodo = em.createQuery(sql, Todos.class).setParameter("todoId", todoId).getSingleResult();

        // Todo 업데이트 로직
        existingTodo.setTask(updateTodo.getTask());
        existingTodo.setCompleted(updateTodo.getCompleted());

        return existingTodo;
    }

    @Transactional
    public void addTodo(Todos addTodo) { // 업데이트 행수 로직으로 변경
        addTodo.setCompleted(false);  // 명시적으로 false 설정
        addTodo.setCreateTime(LocalDateTime.now());
        addTodo.setUpdateTime(LocalDateTime.now());
        em.persist(addTodo); // Insert 쿼리
//        return addTodo;
    }

    @Transactional
    public boolean deleteTodo(int todoId) {
        String sql = "DELETE FROM todos WHERE id = :todoId";

        int rowsAffected = em.createNativeQuery(sql)
                .setParameter("todoId", todoId)
                .executeUpdate();

        return rowsAffected > 0;
    }

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
    public void addCalendar(CalendarEvents calendar) {
        em.persist(calendar); // insert 자동
    }
}
