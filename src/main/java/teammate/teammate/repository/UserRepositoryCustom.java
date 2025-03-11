package teammate.teammate.repository;

import teammate.teammate.domain.Users;

import java.util.List;

public interface UserRepositoryCustom {
    List<Users> getTeamUsersByTeamCode(String teamCode);
}
