package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teammate.teammate.domain.Todos;
import teammate.teammate.service.TodosService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main/todos")
@Slf4j
@RequiredArgsConstructor
public class TodosController {
    private final TodosService todosService;

    /**
     * @param teamCode
     * @param year
     * @param month
     * @param day
     * @return UserId로 그룹핑된 Todos 데이터 조회
     */
    @GetMapping("/{teamCode}/{year}/{month}/{day}")
    public ResponseEntity<ApiResponse<Map<String, List<Todos>>>> getTodos(@PathVariable String teamCode, @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        ApiResponse<Map<String, List<Todos>>> groupedTodos = todosService.getTodos(teamCode, year, month, day);

        return ResponseEntity.status(groupedTodos.getStatus()).body(groupedTodos);
    }


    /**
     * @param todoId
     * @param updateTodo
     * @return 해당 todoId에 대해서 입력받은 todoData 값으로 업데이트
     */
    @PostMapping("/{todoId}")
    public ResponseEntity<ApiResponse> updateTodo(@PathVariable int todoId, @RequestBody Todos updateTodo) {
        todosService.updateTodo(todoId, updateTodo);

        return ResponseEntity.ok(new ApiResponse(200, "업데이트 성공"));
    }

    /**
     * @param addTodo
     * @return RequestBody로 받은 team_code, user_id에 대해 Todo 추가
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addTodo(@RequestBody Todos addTodo) {
        todosService.addTodo(addTodo);

        return ResponseEntity.ok(new ApiResponse(200, "Todo 추가 성공"));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId) {
        boolean isDeleted = todosService.deleteTodo(todoId);

        if (isDeleted) {
            log.info("Todo deleted successfully. Deleted todoId = {}", todoId); // 성공 로그
            return ResponseEntity.ok("Todo deleted successfully. Deleted todoId = " + todoId);
        } else {
            log.warn("Todo not found. Failed to delete todoId = {}", todoId); // 실패 로그
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found.");
        }
    }
}
