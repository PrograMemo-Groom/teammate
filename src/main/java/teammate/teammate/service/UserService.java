package teammate.teammate.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teammate.teammate.domain.UserLinks;
import teammate.teammate.domain.UserSkills;
import teammate.teammate.domain.Users;
import teammate.teammate.repository.UserLinksRepository;
import teammate.teammate.repository.UserRepository;
import teammate.teammate.repository.UserSkillsRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private static final String UPLOAD_DIR = "uploads/";
    private final UserRepository userRepository;  // 변경된 Repository
    private final UserSkillsRepository userSkillsRepository;
    private final UserLinksRepository userLinksRepository;

    // 프로필 조회
    public Users getUserProfile(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 프로필을 찾을 수 없습니다. userId: " + userId));
    }

    // 프로필 수정
    public Users updateUserProfile(
            String userId,
            String nickname,
            String introduction,
            String preferredPosition,
            String statusMessage,
            List<String> skills,
            List<String> urls,
            MultipartFile profileImage) throws IOException {

        Users user = getUserProfile(userId);

        // 기본 정보 업데이트
        if (nickname != null) user.setNickname(nickname);
        if (introduction != null) user.setIntroduction(introduction);
        if (preferredPosition != null) user.setPreferredPosition(preferredPosition);
        if (statusMessage != null) user.setStatusMessage(statusMessage);

        // 프로필 이미지 업데이트
        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = userId + "_" + profileImage.getOriginalFilename();
            Path filePath = Path.of(UPLOAD_DIR + fileName);
            Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            user.setProfileImg(Files.readAllBytes(filePath));
        }

        // 기술 스택 업데이트
        if (skills != null) {
            List<UserSkills> existingSkills = userSkillsRepository.findByUsers(user);
            List<String> existingSkillNames = existingSkills.stream().map(UserSkills::getSkill).collect(Collectors.toList());

            for (String skill : skills) {
                if (!existingSkillNames.contains(skill)) {
                    userSkillsRepository.save(new UserSkills(0, user, skill));
                }
            }

            // 기존 기술 스택 중에서 요청된 목록에 없는 것은 삭제
            for (UserSkills skill : existingSkills) {
                if (!skills.contains(skill.getSkill())) {
                    userSkillsRepository.delete(skill);
                }
            }
        }

        // URL 업데이트
        if (urls != null) {
            List<UserLinks> existingLinks = userLinksRepository.findByUsers(user);
            List<String> existingUrlList = existingLinks.stream().map(UserLinks::getUrl).collect(Collectors.toList());

            for (String url : urls) {
                if (!existingUrlList.contains(url)) {
                    userLinksRepository.save(new UserLinks(0, user, url));
                }
            }

            // 기존 URL 중에서 요청된 목록에 없는 것은 삭제
            for (UserLinks link : existingLinks) {
                if (!urls.contains(link.getUrl())) {
                    userLinksRepository.delete(link);
                }
            }
        }

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

        //업로드할 디렉토리 경로 설정
        Path uploadDir = Path.of("uploads/");

        //디렉토리가 존재하지 않으면 생성
        if (!Files.exists(uploadDir)) {
            log.warn("'uploads/' 디렉토리가 없어서 생성합니다.");
            Files.createDirectories(uploadDir);
        }

        //저장할 파일 경로 설정
        String fileName = userId + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);

        //파일 저장
        log.info("저장 경로: {}", filePath);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        //데이터베이스에 이미지 저장 (BLOB)
        user.setProfileImg(Files.readAllBytes(filePath));
        userRepository.save(user);

        return "uploads/" + fileName;
    }

    public List<Users> getTeamUsersByTeamCode(String teamCode){

        return userRepository.getTeamUsersByTeamCode(teamCode);
    }


}