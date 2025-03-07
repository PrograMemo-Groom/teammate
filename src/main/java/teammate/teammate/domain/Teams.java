package teammate.teammate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment 설정
    private Long id;

    @Column(unique = true, nullable = false, name = "user_id")
    private String userId;

    @Column(unique = true, nullable = false, name = "team_code")
    private String teamCode;

    @Column(nullable = false)
    private Enum<Role> role;

    @Column(nullable = true, name = "team_description")
    private String description;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean is_active;
}
