package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teammate.teammate.domain.Users;
import teammate.teammate.service.UserService;

import java.math.BigInteger;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/data")
    public String data(){
        return "연결 테스트";
    }

    @GetMapping("/getuser")
    public String getUser() {
        Users users = userService.getUserByUsername(BigInteger.valueOf(1));

        return users.getUser_login_id();
    }
}
