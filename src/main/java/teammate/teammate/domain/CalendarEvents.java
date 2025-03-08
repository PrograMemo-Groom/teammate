package teammate.teammate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendar_events")
public class CalendarEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "team_code", nullable = false)
    private String teamCode;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private String category;

    @Column(name = "start_date_at")
    private LocalDateTime startTime;
}
