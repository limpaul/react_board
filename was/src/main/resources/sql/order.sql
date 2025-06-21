-- 배달 기사
CREATE TABLE delivery_person (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(20),
  status VARCHAR(20) DEFAULT 'AVAILABLE', -- AVAILABLE, DELIVERING, OFFLINE 등
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 주문 테이블
CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,           -- 주문한 사용자
  restaurant_id BIGINT NOT NULL,     -- 주문한 식당
  delivery_person_id BIGINT,         -- 배달 담당자 (nullable)
  delivery_fee INT,                  -- 배달 수수료
  total_price INT NOT NULL,          -- 총 주문 금액
  status VARCHAR(20) DEFAULT 'PENDING', -- 주문 상태
  address VARCHAR(255) NOT NULL,     -- 배송지
  ordered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id) ON DELETE CASCADE,
  FOREIGN KEY (delivery_person_id) REFERENCES delivery_person(id) ON DELETE SET NULL
);
