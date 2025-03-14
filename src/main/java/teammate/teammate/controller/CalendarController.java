package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.service.CalendarService;

import java.util.List;

@RestController
@RequestMapping("/main/calendar")
@Slf4j
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * @param teamCode
     * @param year
     * @param month
     * @return 캘린더의 해당 연월에 해당하는 모든 일정 데이터 조회
     */
    @GetMapping("/{teamCode}/{year}/{month}")
    public ResponseEntity<ApiResponse<List<CalendarEvents>>> getTeamCalendar3(@PathVariable String teamCode,
                                                                              @PathVariable int year, @PathVariable int month) {
        ApiResponse<List<CalendarEvents>> calendarEvents = calendarService.getCalendar(teamCode, year, month);

        return ResponseEntity.status(calendarEvents.getStatus()).body(calendarEvents);
    }

    /**
     * @param teamCode
     * @param year
     * @param month
     * @param day
     * @return 캘린더의 해당 연월일에 해당하는 일정 데이터 조회
     */
    @GetMapping("/{teamCode}/{year}/{month}/{day}")
    public ResponseEntity<ApiResponse<List<CalendarEvents>>> getEvent2(@PathVariable String teamCode,
                                                         @PathVariable int year, @PathVariable int month, @PathVariable int day) {
        ApiResponse<List<CalendarEvents>> event = calendarService.getEvent(teamCode, year, month, day);

        return ResponseEntity.status(event.getStatus()).body(event);
    }

    /**
     * @param id
     * @return id로 일정 모달 데이터 1개를 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CalendarEvents>> getCalendarById(@PathVariable int id) {
        ApiResponse<CalendarEvents> calendar = calendarService.getCalendarById(id);

        return ResponseEntity.status(calendar.getStatus()).body(calendar);
    }


    /**
     * @param id
     * @param updateCalendar
     * @return id에 일정 모달 데이터를 업데이트
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<CalendarEvents>> updateCalendar(@PathVariable int id, @RequestBody CalendarEvents updateCalendar) {
        ApiResponse<CalendarEvents> updatedCalendar = calendarService.updateCalendar(id, updateCalendar);

        return ResponseEntity.status(updatedCalendar.getStatus()).body(updatedCalendar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCalendar(@PathVariable int id) {
        boolean isDeleted = calendarService.deleteCalendar(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse(200, "일정 삭제 성공"));
        } else {
            log.warn("Todo not found. Failed to delete todoId = {}", id); // 실패 로그
            return ResponseEntity.status(404).body(new ApiResponse(200, String.format("실패 : %d번에 해당하는 일정 또는 회의를 찾지 못했습니다.", id)));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCalendar(@RequestBody CalendarEvents calendar) {
        boolean isCreated = calendarService.addCalendar(calendar);

        if(isCreated) {
            return ResponseEntity.status(200).body(new ApiResponse(200, "일정 추가 성공"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(404, "일정 생성 실패"));
        }
    }
}
