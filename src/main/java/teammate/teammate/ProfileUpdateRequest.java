package teammate.teammate;import lombok.Getter;import lombok.Setter;import java.util.List;@Getter@Setterpublic class ProfileUpdateRequest {	private String nickname;	private String introduction;	private String preferredPosition;	private String statusMessage;	private List<String> skills;	private List<String> urls;}