package teammate.teammate.dto;import lombok.AllArgsConstructor;import lombok.Getter;import lombok.NoArgsConstructor;import lombok.Setter;import java.util.List;@Getter@Setter@NoArgsConstructor@AllArgsConstructorpublic class UserProfileResponse {	private String userId;	private String nickname;	private String introduction;	private String preferredPosition;	private String statusMessage;	private String profileImage;	private List<String> skills;	private List<String> urls;}