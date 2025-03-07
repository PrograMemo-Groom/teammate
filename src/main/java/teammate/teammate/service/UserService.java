package teammate.teammate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.UserRepository;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users getUserByUsername(int user_id) {
        Users user = userRepository.getUserByUsername(user_id);

        return user;
    }
}
