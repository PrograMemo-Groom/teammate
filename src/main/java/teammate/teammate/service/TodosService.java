package teammate.teammate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import teammate.teammate.domain.Todos;
import teammate.teammate.repository.TodosRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TodosService {
    private final TodosRepository todosRepository;

    public Map<String, List<Todos>> getTodos(String teamCode, int year, int month, int day) {
        return todosRepository.getTodos(teamCode, year, month, day);
    }

    public Todos updateTodo(int todoId, Todos updateTodo) {
        return todosRepository.updateTodo(todoId, updateTodo);
    }

    public void addTodo(Todos addTodo) {
        todosRepository.addTodo(addTodo);
    }

    public boolean deleteTodo(int todoId) {
        return todosRepository.deleteTodo(todoId);
    }
}
