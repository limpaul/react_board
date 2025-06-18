CREATE TABLE restaurant (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  name VARCHAR(255),
  address VARCHAR(255),
  explain VARCHAR(1000),
  min_order_price INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE menu (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,           -- 기본 키, 자동 증가
  restaurant_id BIGINT,                           -- 외래 키 대상 필드
  name VARCHAR(255),                              -- 메뉴 이름
  price INT,                                      -- 가격
  description TEXT,                               -- 메뉴 설명 (긴 텍스트 가능)
  image_url TEXT,                                 -- 이미지 URL
  score INT,                                      -- 평점 또는 점수
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)  -- 외래 키 연결
);

