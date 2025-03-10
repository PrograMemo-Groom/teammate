package teammate.teammate.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.UserRepository;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private static final String UPLOAD_DIR = "uploads/";
    private final UserRepository userRepository;  // 변경된 Repository

    // 프로필 조회
    public Users getUserProfile(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 프로필을 찾을 수 없습니다. userId: " + userId));
    }

    // 프로필 수정
    public Users updateUserProfile(String userId, Users updatedUser) {
        Users user = getUserProfile(userId);

        user.setNickname(updatedUser.getNickname());
        user.setIntroduction(updatedUser.getIntroduction());
        user.setPreferredPosition(updatedUser.getPreferredPosition());
        user.setStatusMessage(updatedUser.getStatusMessage());

        return userRepository.save(user);
    }

    // 상태 메시지 조회
    public String getStatusMessage(String userId) {
        return userRepository.findByUserId(userId)
                .map(Users::getStatusMessage)
                .orElse("상태 메시지가 없습니다.");
    }

    // 이미지 업로드
    public String uploadProfileImage(String userId, MultipartFile file) throws IOException {
        Users user = getUserProfile(userId);

        // 파일 저장 경로 설정
        String fileName = userId + "_" + file.getOriginalFilename();
        Path filePath = Path.of(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 데이터베이스에 이미지 저장 (BLOB)
        user.setProfileImg(Files.readAllBytes(filePath));
        userRepository.save(user);

        return "uploads/" + fileName;
    }


}
