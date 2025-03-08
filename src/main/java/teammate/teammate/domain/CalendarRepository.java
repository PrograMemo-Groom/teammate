package teammate.teammate.domain;

import jakarta.persistence.*;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDateTime;

@Table(name = "calendar_events")
public class CalendarRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_code")
    private String teamCode;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Enum<Category> category;

    @Column(name = "start_date_at")
    private LocalDateTime startTime;
}
