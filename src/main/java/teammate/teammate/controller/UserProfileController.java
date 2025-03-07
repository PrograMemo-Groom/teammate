package teammate.teammate.controller;import lombok.RequiredArgsConstructor;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.*;import teammate.teammate.domain.UserProfile;import teammate.teammate.service.UserProfileService;import java.util.HashMap;import java.util.Map;@RestController@RequestMapping("/profile")@RequiredArgsConstructorpublic class UserProfileController {	private final UserProfileService userProfileService;	// 프로필 조회 api	@GetMapping("/{userId}")	public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId) {		UserProfile profile = userProfileService.getUserProfile(userId);		return ResponseEntity.ok(profile);	}	// 프로필 수정 api	@PutMapping("/{userId}")	public ResponseEntity<UserProfile> updateUserProfile(			@PathVariable Long userId,			@RequestBody UserProfile profile) {		UserProfile updated = userProfileService.updateUserProfile(userId, profile);		//응답 메시지 추가		Map<String, Object> response = new HashMap<>();		response.put("message", "프로필이 성공적으로 수정되었습니다.");		response.put("updatedProfile", updated);		return ResponseEntity.ok(updated);	}	// 프로필에 마우스 호버시, 특정 사용자의 상태 메시지 조회 api	@GetMapping("/status/{userId}")	public ResponseEntity<String> getUserStatusMessage(@PathVariable Long userId) {		String statusMessage = userProfileService.getStatusMessage(userId);		return ResponseEntity.ok(statusMessage);	}}