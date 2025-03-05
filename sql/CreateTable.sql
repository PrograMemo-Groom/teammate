-- 유저 테이블
CREATE TABLE users
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    user_id       INT PRIMARY KEY AUTO_INCREMENT COMMENT 'DB 저장용 유저 ID',
    user_login_id VARCHAR(30)  NOT NULL UNIQUE COMMENT '회원가입 시 유저 ID',
    email         VARCHAR(80)  NOT NULL UNIQUE COMMENT '이메일',
    password      VARCHAR(255) NOT NULL COMMENT '비밀번호'
);

-- 팀 테이블
CREATE TABLE teams
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    team_id      INT PRIMARY KEY AUTO_INCREMENT COMMENT '팀 ID',
    created_user INT NOT NULL COMMENT '팀 생성자',
    FOREIGN KEY (created_user) REFERENCES users (user_id) ON DELETE CASCADE
);

-- 팀과 유저의 관계를 나타내는 테이블
CREATE TABLE user_team
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    team_id INT NOT NULL COMMENT '팀 ID',
    user_id INT NOT NULL COMMENT '유저 ID',
    role    ENUM ('OWNER', 'MEMBER') DEFAULT 'MEMBER' COMMENT '권한',
    PRIMARY KEY (team_id, user_id),
    FOREIGN KEY (team_id) REFERENCES teams (team_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- 일정 테이블 (날짜와 시간을 분리하여 저장)
CREATE TABLE calendar_events
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    event_id    INT PRIMARY KEY AUTO_INCREMENT COMMENT '이벤트 ID',
    team_id     INT                          NOT NULL COMMENT '팀 ID',
    title       VARCHAR(100)                 NOT NULL COMMENT '일정 제목',
    description TEXT COMMENT '일정 내용',
    category    ENUM ('MEETING', 'SCHEDULE') NOT NULL COMMENT '카테고리 (회의/일정)',
    event_date  DATE                         NOT NULL COMMENT '일정 날짜',
    event_time  TIME                         NOT NULL COMMENT '일정 시간',
    FOREIGN KEY (team_id) REFERENCES teams (team_id) ON DELETE CASCADE
);

-- 할 일 테이블
CREATE TABLE todos
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    todo_id   INT PRIMARY KEY AUTO_INCREMENT COMMENT '할 일 ID',
    user_id   INT          NOT NULL COMMENT '유저 ID',
    team_id   INT          NOT NULL COMMENT '팀 ID',
    task      VARCHAR(255) NOT NULL COMMENT '할 일 내용',
    completed BOOLEAN DEFAULT FALSE COMMENT '완료 여부',
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams (team_id) ON DELETE CASCADE
);

-- 유저 프로필 정보 저장을 위한 테이블 (상태 메시지 추가)
CREATE TABLE user_profiles
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    user_id            INT PRIMARY KEY COMMENT '유저 ID',
    nickname           VARCHAR(30) NOT NULL COMMENT '닉네임',
    introduction       TEXT         DEFAULT NULL COMMENT '한줄 소개',
    preferred_position VARCHAR(30)  DEFAULT NULL COMMENT '선호하는 포지션',
    status_message     VARCHAR(100) DEFAULT NULL COMMENT '상태 메시지',
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- 유저가 여러 개의 기술 스택을 가질 수 있도록 하는 테이블
CREATE TABLE user_technologies
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    user_id    INT         NOT NULL COMMENT '유저 ID',
    technology VARCHAR(50) NOT NULL COMMENT '기술 스택',
    PRIMARY KEY (user_id, technology),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- 유저가 여러 개의 URL 링크를 가질 수 있도록 하는 테이블
CREATE TABLE user_links
(
    id         INT AUTO_INCREMENT COMMENT 'index', -- 추가된 부분
    user_id INT          NOT NULL COMMENT '유저 ID',
    url     VARCHAR(200) NOT NULL COMMENT 'URL 링크',
    PRIMARY KEY (user_id, url),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
