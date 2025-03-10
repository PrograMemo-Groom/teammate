package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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

}
