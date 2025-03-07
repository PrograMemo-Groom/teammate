package teammate.teammate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    private Long id;

    @Column(length = 30, nullable = false)
    private String user_id;
    @Column(length = 30, nullable = false)
    private String nickname;
    private String introduction;

    @Column(name = "preferred_position")
    private String preferredPosition;

    @Column(name = "status_message")
    private String statusMessage;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;
}
