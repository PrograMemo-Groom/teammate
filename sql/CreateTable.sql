USE
teammate;

-- 유저 테이블
CREATE TABLE users
(
    id                 INT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id            VARCHAR(30)  NOT NULL UNIQUE COMMENT '회원가입 시 유저 ID',
    nickname           VARCHAR(30)  NOT NULL COMMENT '닉네임',
    introduction       TEXT         DEFAULT NULL COMMENT '한줄 소개',
    preferred_position VARCHAR(30)  DEFAULT NULL COMMENT '선호하는 포지션',
    status_message     VARCHAR(100) DEFAULT NULL COMMENT '상태 메시지',
    email              VARCHAR(80)  NOT NULL UNIQUE COMMENT '이메일',
    password           VARCHAR(255) NOT NULL COMMENT '비밀번호'
) DEFAULT CHARSET = utf8mb4;

-- 팀 테이블
CREATE TABLE teams
(
    id               INT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id          VARCHAR(30) NOT NULL COMMENT 'user_id',
    team_code        VARCHAR(50) NOT NULL UNIQUE COMMENT '팀 코드',
    team_name        VARCHAR(50) DEFAULT NULL COMMENT '팀 이름',
    role             ENUM('OWNER', 'MEMBER') DEFAULT 'MEMBER' COMMENT '권한',
    team_description TEXT        DEFAULT NULL COMMENT '팀 설명',
    created_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at       TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    is_active        BOOLEAN     DEFAULT TRUE COMMENT '활성화 여부',
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) DEFAULT CHARSET = utf8mb4;

-- 일정 테이블 (날짜와 시간을 분리하여 저장)
CREATE TABLE calendar_events
(
    id            INT PRIMARY KEY AUTO_INCREMENT COMMENT '이벤트 ID',
    team_code     VARCHAR(50)  NOT NULL COMMENT '팀 코드',
    title         VARCHAR(100) NOT NULL COMMENT '일정 제목',
    description   TEXT         NOT NULL COMMENT '일정 내용',
    category      ENUM('MEETING', 'SCHEDULE') NOT NULL COMMENT '카테고리 (회의/일정)',
    start_date_at DATETIME     NOT NULL COMMENT '회의 날짜 및 시간',
    FOREIGN KEY (team_code) REFERENCES teams (team_code)
) DEFAULT CHARSET = utf8mb4;

-- 할 일 테이블
CREATE TABLE todos
(
    id         INT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id    VARCHAR(30)  NOT NULL COMMENT '유저 ID',
    team_code  VARCHAR(50)  NOT NULL COMMENT '팀 코드',
    task       VARCHAR(255) NOT NULL COMMENT '할 일 내용',
    completed  BOOLEAN   DEFAULT FALSE COMMENT '완료 여부',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (team_code) REFERENCES teams (team_code)
) DEFAULT CHARSET = utf8mb4;

-- 유저가 여러 개의 기술 스택을 가질 수 있도록 하는 테이블
CREATE TABLE user_skills
(
    id      INT PRIMARY KEY AUTO_INCREMENT  COMMENT 'id',
    user_id VARCHAR(30) NOT NULL COMMENT '유저 ID',
    skill   VARCHAR(50) NOT NULL COMMENT '기술 스택',
    UNIQUE (user_id, skill), -- 중복 방지를 위해 UNIQUE 제약 조건 추가
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) DEFAULT CHARSET = utf8mb4;

-- 유저가 여러 개의 URL 링크를 가질 수 있도록 하는 테이블
CREATE TABLE user_links
(
    id      INT PRIMARY KEY AUTO_INCREMENT  COMMENT 'id',
    user_id    VARCHAR(30)  NOT NULL COMMENT '유저 ID',
    url     VARCHAR(200) NOT NULL COMMENT 'URL 링크',
    FOREIGN KEY (user_id) REFERENCES users (user_id)
) DEFAULT CHARSET = utf8mb4;
