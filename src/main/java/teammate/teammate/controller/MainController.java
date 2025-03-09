package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.MainRepository;
import teammate.teammate.repository.UserRepository;
import teammate.teammate.service.MainService;

import java.util.List;

@RestController
@RequestMapping("/main")
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/getTeamUsers/{teamCode}")
    public ResponseEntity<List<Users>> getTeamUsers(@PathVariable String teamCode) {
        List<Users> users = mainService.getTeamUsersByTeamCode(teamCode);

        log.info("users {}", users.toString());

        return ResponseEntity.ok(users);
    }
}
