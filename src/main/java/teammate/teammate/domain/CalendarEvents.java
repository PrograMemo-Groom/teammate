package teammate.teammate.domain;

import jakarta.persistence.*;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDateTime;

@Table(name = "calendar_events")
public class CalendarEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_code", nullable = false)
    private String teamCode;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private Enum<Category> category;

    @Column(name = "start_date_at")
    private LocalDateTime startTime;
}
