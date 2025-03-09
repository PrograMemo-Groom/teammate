package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Users;
import teammate.teammate.service.MainService;

import java.util.List;

@RestController
@RequestMapping("/main")
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/users/{teamCode}")
    public ResponseEntity<List<Users>> getTeamUsers(@PathVariable String teamCode) {
        List<Users> users = mainService.getTeamUsersByTeamCode(teamCode);

        log.info("users {}", users.toString());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/calendar/{teamCode}/{year}/{month}")
    public ResponseEntity<List<CalendarEvents>> getTeamCalendar(@PathVariable String teamCode,
                                                                @PathVariable int year, @PathVariable int month) {
        List<CalendarEvents> calendarEvents = mainService.getCalendar(teamCode, year, month);

        return ResponseEntity.ok(calendarEvents);
    }

    @GetMapping("/calendar/{teamCode}/{year}/{month}/{day}")
    public ResponseEntity<List<CalendarEvents>> getEvent(@PathVariable String teamCode,
                                                   @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        List<CalendarEvents> event = mainService.getEvent(teamCode, year, month, day);

        return ResponseEntity.ok(event);
    }
}
