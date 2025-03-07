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

    private String user_id;
    private String nickname;
    private String introduction;

    @Column(name = "preferred_position")
    private String preferredPosition;

    @Column(name = "status_message")
    private String statusMessage;
    private String email;
    private String password;
}
