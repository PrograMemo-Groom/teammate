package teammate.teammate.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.MainRepository;

import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class MainService {
    private final MainRepository mainRepository;

    public List<Users> getTeamUsersByTeamCode(String teamCode){

        return mainRepository.getTeamUsersByTeamCode(teamCode);
    }

    public List<CalendarEvents> getCalendar(String teamCode, int year, int month) {
        return mainRepository.getCalendar(teamCode, year, month);
    }

    public List<CalendarEvents> getEvent(String teamCode, int year, int month, int day) {
        return mainRepository.getEvent(teamCode, year, month, day);
    }
}
