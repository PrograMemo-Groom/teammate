package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Todos;
import teammate.teammate.domain.Users;
import teammate.teammate.service.MainService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /**
     *
     * @param teamCode
     * @return 해당 팀코드에 해당하는 팀의 유저들 정보 조회
     */
    @GetMapping("/users/{teamCode}")
    public ResponseEntity<List<Users>> getTeamUsers(@PathVariable String teamCode) {
        List<Users> users = mainService.getTeamUsersByTeamCode(teamCode);

        log.info("users {}", users.toString());

        return ResponseEntity.ok(users);
    }


    /**
     *
     * @param teamCode
     * @param year
     * @param month
     * @return 캘린더의 해당 연월에 해당하는 모든 일정 데이터 조회
     */
    @GetMapping("/calendar/{teamCode}/{year}/{month}")
    public ResponseEntity<List<CalendarEvents>> getTeamCalendar(@PathVariable String teamCode,
                                                                @PathVariable int year, @PathVariable int month) {
        List<CalendarEvents> calendarEvents = mainService.getCalendar(teamCode, year, month);

        return ResponseEntity.ok(calendarEvents);
    }


    /**
     * @param teamCode
     * @param year
     * @param month
     * @param day
     * @return 캘린더의 해당 연월일에 해당하는 일정 데이터 조회
     */
    @GetMapping("/calendar/{teamCode}/{year}/{month}/{day}")
    public ResponseEntity<List<CalendarEvents>> getEvent(@PathVariable String teamCode,
                                                   @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        List<CalendarEvents> event = mainService.getEvent(teamCode, year, month, day);

        return ResponseEntity.ok(event);
    }

    /**
     *
     * @param id
     * @return id로 일정 상세 정보 1개를 조회
     */
    @GetMapping("/calendar/{id}")
    public ResponseEntity<CalendarEvents> getCalendarById(@PathVariable int id) {
        CalendarEvents calendar = mainService.getCalendarById(id);

        return ResponseEntity.ok(calendar);
    }


    /**
     *
     * @param id
     * @param updateCalendar
     * @return id에 해당하는 회의 일정 데이터를 업데이트
     */
    @PostMapping("/calendar/{id}")
    public ResponseEntity<CalendarEvents> updateCalendar(@PathVariable int id, @RequestBody CalendarEvents updateCalendar) {
        CalendarEvents updatedCalendar = mainService.updateCalendar(id, updateCalendar);

        return ResponseEntity.ok(updatedCalendar);
    }



    /**
     * @param teamCode
     * @param year
     * @param month
     * @param day
     * @return UserId로 그룹핑된 Todos 데이터 조회
     */
    @GetMapping("/todos/{teamCode}/{year}/{month}/{day}")
    public ResponseEntity<Map<String, List<Todos>>> getTodos(@PathVariable String teamCode,
                                                             @PathVariable int year,
                                                             @PathVariable int month,
                                                             @PathVariable int day) {
        Map<String, List<Todos>> groupedTodos = mainService.getTodos(teamCode, year, month, day);
        return ResponseEntity.ok(groupedTodos);
    }


    /**
     *
     * @param todoId
     * @param updateTodo
     * @return 해당 todoId에 대해서 입력받은 todoData 값으로 업데이트
     */
    @PostMapping("/todos/{todoId}")
    public ResponseEntity<Todos> updateTodo(@PathVariable int todoId, @RequestBody Todos updateTodo) {
        Todos updatedTodo = mainService.updateTodo(todoId, updateTodo);
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     *
     * @param addTodo
     * @return RequestBody로 받은 team_code, user_id에 대해 Todo 추가
     */
    @PostMapping("/todos")
    public ResponseEntity<Todos> addTodo(@RequestBody Todos addTodo) {
        Todos addedTodo = mainService.addTodo(addTodo);

        return ResponseEntity.ok(addedTodo);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId) {
        boolean isDeleted = mainService.deleteTodo(todoId);

        if (isDeleted) {
            log.info("Todo deleted successfully. Deleted todoId = {}", todoId); // 성공 로그
            return ResponseEntity.ok("Todo deleted successfully. Deleted todoId = " + todoId);
        } else {
            log.warn("Todo not found. Failed to delete todoId = {}", todoId); // 실패 로그
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo not found.");
        }
    }
}
