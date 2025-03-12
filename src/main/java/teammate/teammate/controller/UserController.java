package teammate.teammate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teammate.teammate.ProfileUpdateRequest;
import teammate.teammate.UserProfileResponse;
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
        UserProfileResponse userProfile = userService.getUserProfileResponse(userId);
        return ResponseEntity.ok(userProfile);
    }

    // 프로필 수정 API
    @PutMapping(value="/profile/{userId}", consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, Object>> updateUserProfile(
            @PathVariable("userId") String userId,
            @RequestPart("user") String userJson,  // JSON을 String으로 받음
            @RequestPart(value = "profileImg", required = false) MultipartFile profileImg
    ) { try {
        // JSON을 객체로 변환 (Jackson 사용)
        ObjectMapper objectMapper = new ObjectMapper();
        ProfileUpdateRequest request = objectMapper.readValue(userJson, ProfileUpdateRequest.class);

        // 닉네임 없으면 예외 처리
        if (request.getNickname() == null || request.getNickname().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "닉네임을 입력해주세요."));
        }

        // 이미지가 정상적으로 들어오는지 확인
        if (profileImg != null && !profileImg.isEmpty()) {
            log.info("프로필 이미지 업로드 요청됨: 파일명 = {}", profileImg.getOriginalFilename());
        } else {
            log.warn("프로필 이미지가 포함되지 않음");
        }

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

        // 응답 메시지 추가
        Map<String, Object> response = Map.of(
                "message", "프로필이 성공적으로 수정되었습니다.",
                "updatedProfile", updatedProfile
        );

        return ResponseEntity.ok(response);
    } catch (IOException e) {
        log.error("JSON 변환 오류", e);
        return ResponseEntity.badRequest().body(Map.of("error", "잘못된 JSON 형식입니다."));
    } catch (Exception e) {
        log.error("프로필 수정 중 오류 발생", e);
        return ResponseEntity.status(500).body(Map.of("error", "서버 오류가 발생했습니다."));
    }
    }

    // 상태 메시지 조회 API
    @GetMapping("/status/{userId}")
    public ResponseEntity<String> getUserStatusMessage(@PathVariable("userId") String userId) {
        String statusMessage = userService.getStatusMessage(userId);
        return ResponseEntity.ok(statusMessage);
    }

    // 이미지 업로드 API
    @PostMapping("/profile/{userId}/uploadImage")
    public ResponseEntity<Map<String, String>> uploadProfileImage(
            @PathVariable("userId") String userId,
            @RequestParam("file") MultipartFile file) {
        try {
            log.info("업로드 요청됨 - userId: {}, 파일명: {}", userId, file.getOriginalFilename());

            if (file.isEmpty()) {
                log.warn("업로드된 파일이 없음");
                return ResponseEntity.badRequest().body(Map.of("error", "파일이 없습니다."));
            }

            String imageUrl = userService.uploadProfileImage(userId, file);
            log.info("업로드 성공: {}", imageUrl);
            return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
        } catch (IOException e) {
            log.error("이미지 업로드 실패", e);
            return ResponseEntity.badRequest().body(Map.of("error", "이미지 업로드 실패"));
        }
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
