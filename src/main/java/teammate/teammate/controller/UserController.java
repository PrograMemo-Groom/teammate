package teammate.teammate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teammate.teammate.dto.ProfileUpdateRequest;
import teammate.teammate.dto.UserProfileResponse;
import teammate.teammate.domain.Users;
import teammate.teammate.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 프로필 조회 API
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.getUserProfileResponse(userId));
    }

    // 프로필 수정 API
    @PutMapping(value="/profile/{userId}", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, Object>> updateUserProfile(
            @PathVariable("userId") String userId,
            @RequestPart("user") String userJson,  // JSON을 String으로 받음
            @RequestPart(value = "profileImg", required = false) MultipartFile profileImg
    ) throws IOException {

        // JSON을 객체로 변환 (Jackson 사용)
        ObjectMapper objectMapper = new ObjectMapper();
        ProfileUpdateRequest request = objectMapper.readValue(userJson, ProfileUpdateRequest.class);

        UserProfileResponse updatedProfile = userService.updateUserProfile(
                userId,
                request.getNickname(),
                request.getIntroduction(),
                request.getPreferredPosition(),
                request.getStatusMessage(),
                request.getSkills(),
                request.getUrls(),
                profileImg
        );

        return ResponseEntity.ok(Map.of(
                "message", "프로필이 성공적으로 수정되었습니다.",
                "updatedProfile", updatedProfile
        ));
    }

    // 상태 메시지 조회 API
    @GetMapping("/status/{userId}")
    public ResponseEntity<String> getUserStatusMessage(@PathVariable("userId") String userId) {
        String statusMessage = userService.getStatusMessage(userId);
        return ResponseEntity.ok(userService.getStatusMessage(userId));
    }

    // 이미지 업로드 API
    @PostMapping("/profile/{userId}/uploadImage")
    public ResponseEntity<Map<String, String>> uploadProfileImage(
            @PathVariable("userId") String userId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String imageUrl = userService.uploadProfileImage(userId, file);
        log.info("업로드 성공: {}", imageUrl);
        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }

    /**
     * @param teamCode
     * @return 해당 팀코드에 해당하는 팀의 유저들 정보 조회
     */
    @GetMapping("/{teamCode}")
    public ResponseEntity<List<Users>> getTeamUsers(@PathVariable String teamCode) {
        List<Users> users = userService.getTeamUsersByTeamCode(teamCode);

        log.info("users {}", users.toString());

        return ResponseEntity.ok(users);
    }

}
