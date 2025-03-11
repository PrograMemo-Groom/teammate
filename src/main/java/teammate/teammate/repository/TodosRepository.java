package teammate.teammate.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import teammate.teammate.domain.Todos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TodosRepository {

    private final EntityManager em;

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
}
