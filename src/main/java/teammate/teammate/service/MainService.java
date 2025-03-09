package teammate.teammate.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.MainRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MainService {
    private final MainRepository mainRepository;

    public List<Users> getTeamUsersByTeamCode(String teamCode){
        List<Users> users = mainRepository.getTeamUsersByTeamCode(teamCode);

        return users;
    }
}
