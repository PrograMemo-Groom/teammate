package teammate.teammate.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import teammate.teammate.controller.ApiResponse;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.repository.CalendarRepository;

import java.util.List;

import static teammate.teammate.controller.ApiResponse.createError;
import static teammate.teammate.controller.ApiResponse.createSuccessWithNoContent;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public CalendarEvents getCalendarById(int id) {
        return calendarRepository.getCalendarById(id);
    }

    public CalendarEvents updateCalendar(int id, CalendarEvents updateCalendar) {
        return calendarRepository.updateCalendar(id, updateCalendar);
    }

    public boolean deleteCalendar(int id) {
        return calendarRepository.deleteCalendar(id);
    }

    public void addCalendar(CalendarEvents calendar) {
        calendarRepository.addCalendar(calendar);
    }

    public ApiResponse<List<CalendarEvents>> getCalendar(String teamCode, int year, int month) {
        try {
            // 필수 값 검증
            if (teamCode == null || teamCode.isBlank()) {
                throw new IllegalArgumentException("팀 코드는 필수입니다.");
            }
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("월(month)은 1~12 사이여야 합니다.");
            }

            List<CalendarEvents> calendarEvents = calendarRepository.getCalendar(teamCode, year, month);

            // 성공했으나 데이터가 [] 일 때
            if (calendarEvents == null || calendarEvents.isEmpty()) {
                String message = String.format("%d년 %d월의 캘린더 이벤트 정보가 없습니다.", year, month);
                return (ApiResponse<List<CalendarEvents>>) createSuccessWithNoContent(message);
            }

            // 성공적으로 데이터 조회
            return ApiResponse.createSuccess(calendarEvents, String.format("%d년 %d월의 캘린더 데이터를 성공적으로 조회했습니다.", year, month));

        } catch (MethodArgumentTypeMismatchException e) { // 잘못된 타입 변환
            return (ApiResponse<List<CalendarEvents>>) createError("잘못된 매개변수 타입: " + e.getMessage());
        } catch (NumberFormatException e) { // 숫자 변환 실패
            return (ApiResponse<List<CalendarEvents>>) createError("숫자 변환 오류: " + e.getMessage());
        } catch (DataAccessException e) { // DB 접근 중 오류 발생
            return (ApiResponse<List<CalendarEvents>>) createError("데이터베이스 오류 발생: " + e.getMessage());
        } catch (NullPointerException e) { // 데이터 누락
            return (ApiResponse<List<CalendarEvents>>) createError("잘못된 요청: 필수 데이터가 누락되었습니다.");
        } catch (IllegalArgumentException e) { // teamCode, year, month 값이 유효하지 않은 경우
            return (ApiResponse<List<CalendarEvents>>) createError("잘못된 요청: " + e.getMessage());
        } catch (Exception e) { // 기타 예상하지 못한 예외 처리
            return (ApiResponse<List<CalendarEvents>>) createError("서버 내부 오류: " + e.getMessage());
        }
    }

    public List<CalendarEvents> getEvent(String teamCode, int year, int month, int day) {
        return calendarRepository.getEvent(teamCode, year, month, day);
    }
}
