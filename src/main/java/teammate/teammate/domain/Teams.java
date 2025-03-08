package teammate.teammate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teams")
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Users user;

    @Column(name = "team_code", unique = true, nullable = false)
    private String teamCode;

    @Column(nullable = false)
    private Enum<Role> role;

    @Column(name = "team_description", nullable = true)
    private String description;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date updateTime;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean is_active;

    @OneToMany(mappedBy = "teams", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todos> todosList;
}
