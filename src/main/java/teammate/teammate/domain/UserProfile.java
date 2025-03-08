package teammate.teammate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profiles")
public class UserProfile {
	@Id
	private Long userId;

	@OneToOne
	@MapsId // Users 테이블의 user_id 그대로 사용
	@JoinColumn(name = "user_id")
	private Users user;

	@Column(name="profile_img")
	private String profileImg;

	@OneToMany(mappedBy="userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<UserSkill> skills;

	@OneToMany(mappedBy = "userProfile", cascade= CascadeType.ALL, orphanRemoval = true)
	private List<UserLink> links;

}