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
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  restaurant_id BIGINT,
  name VARCHAR(255),
  price INT,
  description TEXT,
  image_url TEXT,
  score INT,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
);
