package teammate.teammate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    private Long id;

    @Column(name = "user_id", length = 30, nullable = false)
    private String userId;

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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todos> todosList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSkills> userSkillsList;

    @Lob
    @Column(name = "profile_img")
    private byte[] profileImg;
}
