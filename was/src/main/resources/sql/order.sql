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
  delivery_person_id BIGINT,-- 배달 담당자 (nullable)
  delivery_fee INT,                  -- 배달 수수료
  total_price INT,          -- 총 주문 금액
  status VARCHAR(20) DEFAULT 'PENDING', -- 주문 상태
  address VARCHAR(255),     -- 배송지
  ordered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  unique_str varchar(50) UNIQUE,

  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id) ON DELETE CASCADE,
  FOREIGN KEY (delivery_person_id) REFERENCES delivery_person(id) ON DELETE SET NULL
);

CREATE TABLE order_items (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,        -- 어떤 주문에 속했는지
  menu_id BIGINT NOT NULL,         -- 어떤 메뉴인지
  quantity INT NOT NULL,           -- 수량
  price INT NOT NULL,              -- 단가 (주문 시점 가격)

  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (menu_id) REFERENCES menu(id) ON DELETE CASCADE
);
