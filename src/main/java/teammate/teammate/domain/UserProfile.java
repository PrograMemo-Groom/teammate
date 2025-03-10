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
//@Table(name = "user_profiles")
public class UserProfile {
	@Id
	private Long userId;

	@OneToOne
	@MapsId // Users 테이블의 user_id 그대로 사용
	@JoinColumn(name = "user_id")
	private Users user;

	@Column(name="profile_img")
	private String profileImg;

	public String getNickname() {
		return user.getNickname();
	}

	public String getIntroduction() {
		return user.getIntroduction();
	}

	public String getPreferredPosition() {
		return user.getPreferredPosition();
	}

	public String getStatusMessage() {
		return user.getStatusMessage();
	}
}