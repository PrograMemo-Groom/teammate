package teammate.teammate.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import teammate.teammate.domain.Teams;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.UserRepository;
import teammate.teammate.repository.TeamRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private static final String UPLOAD_DIR = "uploads/";
    private final UserRepository userRepository;  // 변경된 Repository
    private final BCryptPasswordEncoder passwordEncoder;
    private final TeamRepository teamRepository;

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


    @Transactional
    public Users registerUser(Users user, String teamCode) {
        if (userRepository.existsByUserId(user.getUserId())) {
            throw new RuntimeException("이미 사용 중인 아이디입니다.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("이미 등록된 이메일입니다.");
        }

        // 팀 코드 확인
        Teams team = teamRepository.findByTeamCode(teamCode)
                .orElseGet(() -> {
                    // 팀 코드가 없으면 새 팀 생성
                    Teams newTeam = new Teams();
                    newTeam.setTeamCode(teamCode);
                    newTeam.setUserId(user.getUserId());  // 팀 생성자 저장
                    newTeam.setRole("팀장");  // 팀을 생성한 사람은 팀장
                    newTeam.setCreateTime(new Date());
                    newTeam.setUpdateTime(new Date());
                    newTeam.setIs_active(true);
                    return teamRepository.save(newTeam);
                });

        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTeam(team);

        // 유저 저장
        return userRepository.save(user);
    }
}