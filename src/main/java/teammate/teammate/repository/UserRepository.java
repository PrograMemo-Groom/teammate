package teammate.teammate.repository;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import teammate.teammate.domain.Users;

import java.math.BigInteger;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public Users getUserByUsername(BigInteger user_id){
        String query = "select u from Users u";

        List<Users> resultList = em.createQuery(query, Users.class).getResultList();

        return resultList.get(0);
    }

}
