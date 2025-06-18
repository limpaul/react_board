CREATE TABLE restaurant (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  name VARCHAR(255),
  address VARCHAR(255),
  description VARCHAR(1000),
  rating FLOAT,
  min_order_price INT,
  delivery_time_min INT,
  delivery_time_max INT,
  delivery_fee INT,
  image VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE menu (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,           -- 기본 키, 자동 증가
  restaurant_id BIGINT,                           -- 외래 키 대상 필드
  name VARCHAR(255),                              -- 메뉴 이름
  price INT,                                      -- 가격
  description TEXT,                               -- 메뉴 설명 (긴 텍스트 가능)
  image VARCHAR(500),                                 -- 이미지 URL
  score INT,                                      -- 평점 또는 점수
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)  -- 외래 키 연결
);

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '피자플래닛', '서울특별시 중구 가상로 234', '친절한 서비스와 풍부한 양으로 유명한 피자플래닛', 4.6, 30, 40, 2000, 10000,'https://example.com/images/store1.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '김밥천국 중앙점', '서울특별시 동작구 가상로 258', '줄 서서 먹는 맛집 김밥천국 중앙점', 4.3, 20, 30, 0, 10000,'https://example.com/images/store2.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (4, '홍콩반점0410', '서울특별시 노원구 가상로 17', 'SNS에서도 화제인 홍콩반점0410', 4.1, 35, 45, 1500, 10000,'https://example.com/images/store3.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '버거킹 신림점', '서울특별시 성동구 가상로 246', '가족 단위 고객에게 인기인 버거킹 신림점', 4.7, 25, 35, 0, 10000,'https://example.com/images/store4.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '맘스터치', '서울특별시 강남구 가상로 100', '정통의 맛을 고집하는 맘스터치', 4.5, 30, 40, 3000, 10000,'https://example.com/images/store5.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (4, '노랑통닭', '서울특별시 노원구 가상로 121', '친절한 서비스와 풍부한 양으로 유명한 노랑통닭', 4.2, 40, 50, 2500, 10000,'https://example.com/images/store6.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '한솥도시락', '서울특별시 영등포구 가상로 224', '가성비와 품질 모두 만족시키는 한솥도시락', 4.0, 15, 25, 0, 10000,'https://example.com/images/store7.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '이삭토스트', '서울특별시 영등포구 가상로 227', '혼밥하기 좋은 이삭토스트', 4.4, 20, 30, 1800, 10000,'https://example.com/images/store8.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (4, 'BBQ치킨', '서울특별시 관악구 가상로 66', '혼밥하기 좋은 BBQ치킨', 4.1, 35, 50, 2000, 10000,'https://example.com/images/store9.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '죠스떡볶이', '서울특별시 성동구 가상로 42', '가족 단위 고객에게 인기인 죠스떡볶이', 4.3, 20, 30, 1000, 10000,'https://example.com/images/store10.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '명동칼국수', '서울특별시 강남구 가상로 14', 'SNS에서도 화제인 명동칼국수', 4.6, 30, 40, 0, 10000,'https://example.com/images/store11.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (4, '신전떡볶이', '서울특별시 영등포구 가상로 107', '줄 서서 먹는 맛집 신전떡볶이', 4.2, 25, 35, 1500, 10000,'https://example.com/images/store12.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '설빙', '서울특별시 노원구 가상로 148', '혼밥하기 좋은 설빙', 4.5, 15, 25, 2000, 10000,'https://example.com/images/store13.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '청년다방', '서울특별시 강남구 가상로 5', '언제 먹어도 질리지 않는 청년다방', 4.0, 30, 45, 1000, 10000,'https://example.com/images/store14.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (4, '두찜', '서울특별시 종로구 가상로 261', '가성비와 품질 모두 만족시키는 두찜', 4.3, 35, 50, 2500, 10000,'https://example.com/images/store15.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '교촌치킨', '서울특별시 성동구 가상로 253', '깔끔한 포장과 정성이 담긴 교촌치킨', 4.6, 40, 55, 3000, 10000,'https://example.com/images/store16.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '맥도날드', '서울특별시 서초구 가상로 226', '정통의 맛을 고집하는 맥도날드', 4.4, 20, 30, 0, 10000,'https://example.com/images/store17.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (4, '본죽', '서울특별시 서초구 가상로 97', 'SNS에서도 화제인 본죽', 4.1, 25, 35, 1000, 10000,'https://example.com/images/store18.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (2, '홍대돈부리', '서울특별시 은평구 가상로 108', '언제 먹어도 질리지 않는 홍대돈부리', 4.3, 30, 40, 2000, 10000,'https://example.com/images/store19.jpg');

INSERT INTO restaurant (user_id, name, address, description, rating, delivery_time_min, delivery_time_max, delivery_fee, min_order_price, image)
VALUES (3, '땡초김밥', '서울특별시 성동구 가상로 14', '혼밥하기 좋은 땡초김밥', 4.0, 15, 20, 500, 10000,'https://example.com/images/store20.jpg');


















INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '트러플 머쉬룸 피자', 5770, 'https://source.unsplash.com/featured/300x200?food,7577', '2025-01-20 05:07:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '불고기 피자', 10752, 'https://source.unsplash.com/featured/300x200?food,6132', '2025-02-17 19:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '쉬림프 피자', 17808, 'https://source.unsplash.com/featured/300x200?food,8188', '2025-03-01 05:42:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '치즈 크러스트 피자', 17997, 'https://source.unsplash.com/featured/300x200?food,7739', '2025-02-27 07:57:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '베이컨 체더 피자', 5629, 'https://source.unsplash.com/featured/300x200?food,5372', '2025-02-12 01:53:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '페퍼로니 피자', 13171, 'https://source.unsplash.com/featured/300x200?food,4957', '2025-01-20 11:17:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '페퍼로니 피자', 19581, 'https://source.unsplash.com/featured/300x200?food,7802', '2025-03-06 19:18:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '고르곤졸라 피자', 11510, 'https://source.unsplash.com/featured/300x200?food,1970', '2025-03-07 19:54:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '쉬림프 피자', 16095, 'https://source.unsplash.com/featured/300x200?food,1358', '2025-01-24 03:38:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '치즈 크러스트 피자', 11501, 'https://source.unsplash.com/featured/300x200?food,4153', '2025-03-10 08:05:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '트러플 머쉬룸 피자 11', 12200, 'https://source.unsplash.com/featured/300x200?food,4395', '2025-03-12 04:38:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '치즈 크러스트 피자 12', 18255, 'https://source.unsplash.com/featured/300x200?food,3548', '2025-03-28 13:08:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '치즈 크러스트 피자 13', 18263, 'https://source.unsplash.com/featured/300x200?food,6015', '2025-01-27 12:56:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '콤비네이션 피자 14', 10988, 'https://source.unsplash.com/featured/300x200?food,5879', '2025-01-05 05:02:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '페퍼로니 피자 15', 13203, 'https://source.unsplash.com/featured/300x200?food,9929', '2025-02-16 19:02:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '하와이안 피자 16', 3569, 'https://source.unsplash.com/featured/300x200?food,1960', '2025-02-07 19:04:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '치즈 크러스트 피자 17', 7243, 'https://source.unsplash.com/featured/300x200?food,3643', '2025-04-01 09:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '콤비네이션 피자 18', 4998, 'https://source.unsplash.com/featured/300x200?food,274', '2025-02-03 22:28:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '하와이안 피자 19', 4988, 'https://source.unsplash.com/featured/300x200?food,8333', '2025-02-19 05:25:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '베이컨 체더 피자 20', 9993, 'https://source.unsplash.com/featured/300x200?food,5655', '2025-02-04 22:22:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '치즈 김밥', 7028, 'https://source.unsplash.com/featured/300x200?food,1002', '2025-01-06 22:00:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '계란 김밥', 14153, 'https://source.unsplash.com/featured/300x200?food,7678', '2025-02-08 04:48:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '참치 김밥', 6434, 'https://source.unsplash.com/featured/300x200?food,6819', '2025-01-06 10:10:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '김치 김밥', 12324, 'https://source.unsplash.com/featured/300x200?food,1761', '2025-01-18 14:02:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '매운 참치 김밥', 15621, 'https://source.unsplash.com/featured/300x200?food,1154', '2025-03-31 14:54:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '계란 김밥', 10973, 'https://source.unsplash.com/featured/300x200?food,6500', '2025-02-02 07:43:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '소고기 김밥', 19092, 'https://source.unsplash.com/featured/300x200?food,7509', '2025-03-13 22:36:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '계란 김밥', 7467, 'https://source.unsplash.com/featured/300x200?food,7502', '2025-02-14 08:00:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '김치 김밥', 6073, 'https://source.unsplash.com/featured/300x200?food,2301', '2025-02-09 05:14:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '참치 김밥', 18829, 'https://source.unsplash.com/featured/300x200?food,5722', '2025-01-16 07:42:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '계란 김밥 11', 19178, 'https://source.unsplash.com/featured/300x200?food,9304', '2025-02-04 05:10:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '계란 김밥 12', 16489, 'https://source.unsplash.com/featured/300x200?food,3955', '2025-03-24 05:27:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '소고기 김밥 13', 6236, 'https://source.unsplash.com/featured/300x200?food,9789', '2025-01-20 07:02:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '멸치 김밥 14', 11099, 'https://source.unsplash.com/featured/300x200?food,6424', '2025-01-28 13:30:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '참치 김밥 15', 5463, 'https://source.unsplash.com/featured/300x200?food,9980', '2025-02-03 10:12:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '유부 김밥 16', 4413, 'https://source.unsplash.com/featured/300x200?food,5281', '2025-03-18 00:08:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '멸치 김밥 17', 4370, 'https://source.unsplash.com/featured/300x200?food,5399', '2025-03-11 15:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '치즈 김밥 18', 11087, 'https://source.unsplash.com/featured/300x200?food,4313', '2025-03-02 22:05:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '소고기 김밥 19', 14465, 'https://source.unsplash.com/featured/300x200?food,1186', '2025-02-27 07:59:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '돈까스 김밥 20', 16986, 'https://source.unsplash.com/featured/300x200?food,2572', '2025-02-02 20:53:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '에그타르트', 18844, 'https://source.unsplash.com/featured/300x200?food,5124', '2025-01-14 05:38:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '딤섬', 13743, 'https://source.unsplash.com/featured/300x200?food,8532', '2025-01-22 08:19:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '칠리새우', 8821, 'https://source.unsplash.com/featured/300x200?food,1321', '2025-03-21 17:37:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '양장피', 6494, 'https://source.unsplash.com/featured/300x200?food,8269', '2025-01-27 21:01:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '에그타르트', 8540, 'https://source.unsplash.com/featured/300x200?food,2591', '2025-01-12 07:41:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '완탕면', 17348, 'https://source.unsplash.com/featured/300x200?food,3248', '2025-03-10 14:34:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '딤섬', 4798, 'https://source.unsplash.com/featured/300x200?food,489', '2025-03-31 05:42:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '깐풍기', 19490, 'https://source.unsplash.com/featured/300x200?food,5778', '2025-03-30 11:41:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '마라샹궈', 8834, 'https://source.unsplash.com/featured/300x200?food,7568', '2025-03-10 11:59:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '칠리새우', 15641, 'https://source.unsplash.com/featured/300x200?food,5527', '2025-01-29 19:22:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '양장피 11', 16125, 'https://source.unsplash.com/featured/300x200?food,5375', '2025-02-18 14:58:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '완탕면 12', 13649, 'https://source.unsplash.com/featured/300x200?food,6285', '2025-01-20 02:28:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '양장피 13', 4145, 'https://source.unsplash.com/featured/300x200?food,7538', '2025-03-25 00:03:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '탄탄면 14', 16535, 'https://source.unsplash.com/featured/300x200?food,4626', '2025-01-08 11:44:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '딤섬 15', 4706, 'https://source.unsplash.com/featured/300x200?food,8492', '2025-01-07 19:13:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '홍콩식 볶음밥 16', 18073, 'https://source.unsplash.com/featured/300x200?food,2055', '2025-03-21 15:50:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '마라샹궈 17', 19552, 'https://source.unsplash.com/featured/300x200?food,9814', '2025-03-03 21:59:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '딤섬 18', 17987, 'https://source.unsplash.com/featured/300x200?food,9350', '2025-03-11 08:55:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '마라샹궈 19', 18262, 'https://source.unsplash.com/featured/300x200?food,6807', '2025-04-01 21:24:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (4, '홍콩식 볶음밥 20', 13684, 'https://source.unsplash.com/featured/300x200?food,2426', '2025-03-18 19:16:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '와퍼', 9237, 'https://source.unsplash.com/featured/300x200?food,4456', '2025-02-19 06:30:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '콰트로치즈와퍼', 10092, 'https://source.unsplash.com/featured/300x200?food,9442', '2025-03-10 05:42:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '치킨버거', 9185, 'https://source.unsplash.com/featured/300x200?food,9532', '2025-01-10 10:09:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '더블 와퍼', 14135, 'https://source.unsplash.com/featured/300x200?food,8993', '2025-04-01 20:06:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '통새우버거', 10702, 'https://source.unsplash.com/featured/300x200?food,422', '2025-02-28 01:26:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '통새우버거', 6387, 'https://source.unsplash.com/featured/300x200?food,5717', '2025-03-05 07:45:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '몬스터X', 5280, 'https://source.unsplash.com/featured/300x200?food,8293', '2025-03-16 08:51:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '콰트로치즈와퍼', 5462, 'https://source.unsplash.com/featured/300x200?food,8050', '2025-01-20 00:09:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '치즈버거', 18993, 'https://source.unsplash.com/featured/300x200?food,5777', '2025-04-02 03:37:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '베이컨치즈버거', 11665, 'https://source.unsplash.com/featured/300x200?food,9164', '2025-02-05 10:17:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '더블 와퍼 11', 15758, 'https://source.unsplash.com/featured/300x200?food,4886', '2025-01-06 03:58:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '치킨버거 12', 12868, 'https://source.unsplash.com/featured/300x200?food,6077', '2025-01-13 20:57:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '불고기버거 13', 14980, 'https://source.unsplash.com/featured/300x200?food,1810', '2025-03-29 23:29:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '콰트로치즈와퍼 14', 14521, 'https://source.unsplash.com/featured/300x200?food,2768', '2025-01-24 20:12:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '베이컨치즈버거 15', 7908, 'https://source.unsplash.com/featured/300x200?food,9802', '2025-01-30 06:37:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '치즈버거 16', 8150, 'https://source.unsplash.com/featured/300x200?food,676', '2025-01-03 14:11:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '와퍼 17', 12357, 'https://source.unsplash.com/featured/300x200?food,6000', '2025-03-02 13:27:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '치즈버거 18', 11058, 'https://source.unsplash.com/featured/300x200?food,737', '2025-01-22 04:51:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '와퍼 19', 18725, 'https://source.unsplash.com/featured/300x200?food,3717', '2025-01-31 07:00:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '몬스터X 20', 17559, 'https://source.unsplash.com/featured/300x200?food,8065', '2025-02-27 11:48:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '인크레더블버거', 19671, 'https://source.unsplash.com/featured/300x200?food,7480', '2025-01-02 03:20:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '치즈볼', 14107, 'https://source.unsplash.com/featured/300x200?food,7537', '2025-01-21 06:20:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념치킨', 18687, 'https://source.unsplash.com/featured/300x200?food,5638', '2025-01-27 07:01:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '케이준강정', 11972, 'https://source.unsplash.com/featured/300x200?food,7536', '2025-02-19 18:14:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '인크레더블버거', 4764, 'https://source.unsplash.com/featured/300x200?food,9189', '2025-01-06 10:50:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념치킨', 13058, 'https://source.unsplash.com/featured/300x200?food,4201', '2025-01-03 16:21:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '포테이토버거', 10590, 'https://source.unsplash.com/featured/300x200?food,6617', '2025-01-15 17:59:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '인크레더블버거', 9521, 'https://source.unsplash.com/featured/300x200?food,4327', '2025-01-19 21:11:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '포테이토버거', 14534, 'https://source.unsplash.com/featured/300x200?food,8944', '2025-02-03 02:15:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '포테이토버거', 3839, 'https://source.unsplash.com/featured/300x200?food,8078', '2025-01-19 01:21:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '포테이토버거 11', 3920, 'https://source.unsplash.com/featured/300x200?food,3844', '2025-02-23 10:55:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '맘스통살버거 12', 6996, 'https://source.unsplash.com/featured/300x200?food,2957', '2025-03-17 23:50:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '후라이드치킨 13', 3857, 'https://source.unsplash.com/featured/300x200?food,2616', '2025-02-28 04:27:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '싸이순살 14', 13527, 'https://source.unsplash.com/featured/300x200?food,3981', '2025-03-04 21:44:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념치킨 15', 17081, 'https://source.unsplash.com/featured/300x200?food,4858', '2025-02-24 18:33:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '싸이플렉스버거 16', 17989, 'https://source.unsplash.com/featured/300x200?food,8017', '2025-02-01 02:45:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '싸이플렉스버거 17', 18637, 'https://source.unsplash.com/featured/300x200?food,9059', '2025-02-20 06:20:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념치킨 18', 12867, 'https://source.unsplash.com/featured/300x200?food,1360', '2025-03-25 10:35:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '케이준강정 19', 12334, 'https://source.unsplash.com/featured/300x200?food,63', '2025-02-15 02:56:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '싸이플렉스버거 20', 4592, 'https://source.unsplash.com/featured/300x200?food,3640', '2025-03-03 02:04:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '뿌링클 치킨', 13852, 'https://source.unsplash.com/featured/300x200?food,1176', '2025-02-21 03:37:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '마늘 치킨', 9741, 'https://source.unsplash.com/featured/300x200?food,2296', '2025-03-24 08:52:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '허니버터 치킨', 9584, 'https://source.unsplash.com/featured/300x200?food,8862', '2025-01-26 14:45:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '허니버터 치킨', 12432, 'https://source.unsplash.com/featured/300x200?food,8571', '2025-04-02 03:20:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '마늘 치킨', 14985, 'https://source.unsplash.com/featured/300x200?food,4049', '2025-01-21 08:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '매운 양념 치킨', 4745, 'https://source.unsplash.com/featured/300x200?food,5047', '2025-03-20 07:18:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '청양마요 치킨', 13185, 'https://source.unsplash.com/featured/300x200?food,788', '2025-02-08 23:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '마늘 치킨', 14318, 'https://source.unsplash.com/featured/300x200?food,3569', '2025-01-22 14:46:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념 치킨', 16094, 'https://source.unsplash.com/featured/300x200?food,6149', '2025-03-10 00:19:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '간장 치킨', 17627, 'https://source.unsplash.com/featured/300x200?food,9661', '2025-02-14 17:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '마늘 치킨 11', 13997, 'https://source.unsplash.com/featured/300x200?food,4202', '2025-01-21 16:00:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '청양마요 치킨 12', 19666, 'https://source.unsplash.com/featured/300x200?food,2338', '2025-02-25 15:52:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념 치킨 13', 10602, 'https://source.unsplash.com/featured/300x200?food,9672', '2025-01-27 20:51:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '마늘 치킨 14', 8636, 'https://source.unsplash.com/featured/300x200?food,203', '2025-01-06 11:11:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '뿌링클 치킨 15', 13488, 'https://source.unsplash.com/featured/300x200?food,1548', '2025-02-23 02:31:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '청양마요 치킨 16', 18256, 'https://source.unsplash.com/featured/300x200?food,7759', '2025-01-26 03:32:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '양념 치킨 17', 15305, 'https://source.unsplash.com/featured/300x200?food,5628', '2025-03-09 22:29:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '오리지널 치킨 18', 4269, 'https://source.unsplash.com/featured/300x200?food,5380', '2025-03-25 10:46:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '간장 치킨 19', 11038, 'https://source.unsplash.com/featured/300x200?food,8239', '2025-03-15 07:37:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (5, '청양마요 치킨 20', 10130, 'https://source.unsplash.com/featured/300x200?food,1945', '2025-01-16 00:28:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '새우튀김도시락', 4896, 'https://source.unsplash.com/featured/300x200?food,7986', '2025-02-08 14:40:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '오징어볶음', 5024, 'https://source.unsplash.com/featured/300x200?food,7447', '2025-02-06 11:04:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '카레도시락', 13539, 'https://source.unsplash.com/featured/300x200?food,1138', '2025-03-08 07:17:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '오징어볶음', 7911, 'https://source.unsplash.com/featured/300x200?food,4026', '2025-01-16 09:46:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '불고기도시락', 5062, 'https://source.unsplash.com/featured/300x200?food,983', '2025-03-11 03:58:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '함박스테이크', 15935, 'https://source.unsplash.com/featured/300x200?food,7179', '2025-03-26 07:52:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '닭강정도시락', 14461, 'https://source.unsplash.com/featured/300x200?food,170', '2025-01-09 06:32:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '치킨마요', 7073, 'https://source.unsplash.com/featured/300x200?food,5132', '2025-01-15 00:53:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '불고기도시락', 3171, 'https://source.unsplash.com/featured/300x200?food,3492', '2025-01-29 19:43:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '닭강정도시락', 17711, 'https://source.unsplash.com/featured/300x200?food,5694', '2025-02-01 04:42:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '돈까스도시락 11', 6160, 'https://source.unsplash.com/featured/300x200?food,637', '2025-03-28 17:25:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '새우튀김도시락 12', 15694, 'https://source.unsplash.com/featured/300x200?food,4792', '2025-03-09 14:52:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '돈까스도시락 13', 13645, 'https://source.unsplash.com/featured/300x200?food,4502', '2025-02-18 15:15:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '돈까스도시락 14', 5111, 'https://source.unsplash.com/featured/300x200?food,7008', '2025-02-11 06:12:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '닭강정도시락 15', 16559, 'https://source.unsplash.com/featured/300x200?food,8760', '2025-03-05 18:30:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '오징어볶음 16', 11758, 'https://source.unsplash.com/featured/300x200?food,5676', '2025-02-17 07:33:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '불고기도시락 17', 5132, 'https://source.unsplash.com/featured/300x200?food,5926', '2025-01-18 13:16:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '카레도시락 18', 12918, 'https://source.unsplash.com/featured/300x200?food,3858', '2025-03-13 03:39:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '카레도시락 19', 16644, 'https://source.unsplash.com/featured/300x200?food,8813', '2025-01-29 06:07:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (2, '불고기도시락 20', 17560, 'https://source.unsplash.com/featured/300x200?food,267', '2025-03-04 03:20:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '에그드랍 토스트', 15906, 'https://source.unsplash.com/featured/300x200?food,8652', '2025-02-21 05:39:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '치킨마요 토스트', 17735, 'https://source.unsplash.com/featured/300x200?food,5070', '2025-02-22 12:08:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '베이컨포테이토 토스트', 13128, 'https://source.unsplash.com/featured/300x200?food,2124', '2025-02-10 21:30:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '더블치즈 토스트', 9182, 'https://source.unsplash.com/featured/300x200?food,7072', '2025-03-02 19:08:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '불갈비 토스트', 13794, 'https://source.unsplash.com/featured/300x200?food,2727', '2025-02-20 23:07:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '불갈비 토스트', 13985, 'https://source.unsplash.com/featured/300x200?food,6276', '2025-01-15 06:22:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '김치햄 토스트', 3608, 'https://source.unsplash.com/featured/300x200?food,2988', '2025-03-31 20:27:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '베이컨포테이토 토스트', 15750, 'https://source.unsplash.com/featured/300x200?food,4581', '2025-03-16 02:52:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '더블치즈 토스트', 15825, 'https://source.unsplash.com/featured/300x200?food,3816', '2025-01-04 15:16:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '치킨마요 토스트', 11765, 'https://source.unsplash.com/featured/300x200?food,2559', '2025-02-03 21:00:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '햄치즈 토스트 11', 11639, 'https://source.unsplash.com/featured/300x200?food,1336', '2025-01-29 02:16:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '참치마요 토스트 12', 17890, 'https://source.unsplash.com/featured/300x200?food,2242', '2025-02-28 14:15:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '피자 토스트 13', 19111, 'https://source.unsplash.com/featured/300x200?food,1808', '2025-01-06 11:56:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '참치마요 토스트 14', 3116, 'https://source.unsplash.com/featured/300x200?food,3850', '2025-03-14 12:24:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '에그드랍 토스트 15', 4726, 'https://source.unsplash.com/featured/300x200?food,4913', '2025-03-21 23:01:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '에그드랍 토스트 16', 17538, 'https://source.unsplash.com/featured/300x200?food,2872', '2025-01-26 10:09:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '햄치즈 토스트 17', 15546, 'https://source.unsplash.com/featured/300x200?food,2144', '2025-01-03 17:59:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '에그드랍 토스트 18', 8005, 'https://source.unsplash.com/featured/300x200?food,4829', '2025-02-07 12:02:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '피자 토스트 19', 18888, 'https://source.unsplash.com/featured/300x200?food,2781', '2025-02-18 08:11:00');
INSERT INTO menu (restaurant_id, name, price, image, created_at) VALUES (3, '불갈비 토스트 20', 9536, 'https://source.unsplash.com/featured/300x200?food,3222', '2025-02-28 19:32:00');