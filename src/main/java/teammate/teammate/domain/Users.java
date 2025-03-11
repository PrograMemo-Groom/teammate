package teammate.teammate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    private int id;

    @Column(name = "user_id", length = 30, nullable = false, unique = true)
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

    @Lob // BLOB 타입을 사용하기 위해 @Lob 어노테이션 추가
    @Column(name = "profile_img", columnDefinition = "BLOB")
    private byte[] profileImg; // BLOB 데이터로 이미지를 저장

    // 사용자 기술 스택 (1:N 관계 설정)
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSkills> userSkills;

    // 사용자 URL (1:N 관계 설정)
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLinks> userLinks;
}