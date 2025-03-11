package teammate.teammate.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import teammate.teammate.domain.CalendarEvents;
import teammate.teammate.domain.Todos;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.MainRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MainService {
    private final MainRepository mainRepository;

    public List<Users> getTeamUsersByTeamCode(String teamCode){

        return mainRepository.getTeamUsersByTeamCode(teamCode);
    }






}
