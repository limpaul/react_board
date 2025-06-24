CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20),
  email VARCHAR(100),
  password VARCHAR(100),
  address VARCHAR(255),
  role VARCHAR(40),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, email, password, role, address) VALUES
('test', 'johndoe@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_USER', '서울특별시 강남구 테헤란로 123'),
('JBoss', 'bwlim@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER', '서울특별시 마포구 독막로 45'),
('Raon', 'bwlim@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER', '서울특별시 송파구 가락로 88'),
('limpaul', 'paul@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER', '서울특별시 노원구 상계로 31'),
('Dragon', 'Dragon@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER', '서울특별시 종로구 종로3길 9'),
('paul', 'paul@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER', '서울특별시 중구 퇴계로 100'),
('admin', 'admin@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_ADMIN', '서울특별시 강서구 공항대로 15');


