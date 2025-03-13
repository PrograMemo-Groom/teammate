package teammate.teammate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import teammate.teammate.controller.ApiResponse;
import teammate.teammate.domain.Todos;
import teammate.teammate.repository.TodosRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TodosService {
    private final TodosRepository todosRepository;

    public ApiResponse<Map<String, List<Todos>>> getTodos(String teamCode, int year, int month, int day) {
        Map<String, List<Todos>> todos = todosRepository.getTodos(teamCode, year, month, day);

        if (todos.isEmpty()) {
            return new ApiResponse<>(200, "조회된 데이터가 없습니다", null);
        }

        String message = String.format("%d년 %d월 %d일의 할일 리스트를 성공적으로 조회했습니다.", year, month, day);
        return new ApiResponse<>(200, message, todos);
    }

    public void updateTodo(int todoId, Todos updateTodo) {
        todosRepository.updateTodo(todoId, updateTodo);
    }

    public Boolean addTodo(Todos addTodo) {
        todosRepository.addTodo(addTodo);
    }

    public boolean deleteTodo(int todoId) {
        return todosRepository.deleteTodo(todoId);
    }
}
