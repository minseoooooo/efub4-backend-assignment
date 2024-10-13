/* data.sql */
CREATE TABLE IF NOT EXISTS account (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(60) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(16) NOT NULL,
    university VARCHAR(16),
    student_id VARCHAR(16) NOT NULL
);

INSERT INTO account (email, password, nickname, university, student_id) VALUES
    ('test@example.com', 'encodedPassword123', 'TestNickname','ewha', '2117020');

CREATE TABLE IF NOT EXISTS board (
    board_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT,
    board_name VARCHAR(16) NOT NULL,
    board_description VARCHAR(16),
    board_notice VARCHAR(16),
    FOREIGN KEY (account_id)
    REFERENCES account(account_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

INSERT INTO board (account_id, board_name, board_description, board_notice) VALUES
    (1, '게시판1', '게시판1 설명','게시판1 공지');