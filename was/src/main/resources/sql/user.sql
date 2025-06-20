CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20),
  email VARCHAR(100),
  password VARCHAR(100),
  role VARCHAR(40),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, email, password, role) VALUES
('test', 'johndoe@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_USER'),
('JBoss', 'bwlim@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER'),
('Raon', 'bwlim@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER'),
('limpaul', 'paul@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER'),
('Dragon', 'Dragon@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER'),
('paul', 'paul@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_OWNER'),
('admin', 'admin@example.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'ROLE_ADMIN');



