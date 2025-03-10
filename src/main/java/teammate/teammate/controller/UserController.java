package teammate.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teammate.teammate.domain.Users;
import teammate.teammate.service.UserService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/api")
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 프로필 조회 API
    @GetMapping("/profile/{userId}")
    public ResponseEntity<Users> getUserProfile(@PathVariable("userId") String userId) {
        Users user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }

    // 프로필 수정 API
    @PutMapping("/profile/{userId}")
    public ResponseEntity<Users> updateUserProfile(
            @PathVariable("userId") String userId,
            @RequestBody Users updatedUser) {
        Users updated = userService.updateUserProfile(userId, updatedUser);

        // 응답 메시지 추가
        Map<String, Object> response = new HashMap<>();
        response.put("message", "프로필이 성공적으로 수정되었습니다.");
        response.put("updatedProfile", updated);

        return ResponseEntity.ok(updated);
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
            String imageUrl = userService.uploadProfileImage(userId, file);
            return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "이미지 업로드 실패"));
        }
    }

//
//    @GetMapping("/data")
//    public String data(){
//        return "연결 테스트";
//    }
//
//    @GetMapping("/getuser")
//    public String getUser() {
//        Users users = userService.getUserByUsername(1);
//
//        return users.getUserId();
//    }


}
