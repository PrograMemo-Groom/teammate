package teammate.teammate.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.repository.CalendarRepository;

import java.util.List;

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

    public List<CalendarEvents> getCalendar(String teamCode, int year, int month) {
        return calendarRepository.getCalendar(teamCode, year, month);
    }

    public List<CalendarEvents> getEvent(String teamCode, int year, int month, int day) {
        return calendarRepository.getEvent(teamCode, year, month, day);
    }
}
