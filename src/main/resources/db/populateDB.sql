DELETE FROM vote;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@yandex.ru', 'password1'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('User3', 'user3@yandex.ru', 'password3'),
  ('User4', 'user4@yandex.ru', 'password4'),
  ('User5', 'user5@yandex.ru', 'password5'),
  ('User6', 'user6@yandex.ru', 'password6'),
  ('User7', 'user7@yandex.ru', 'password7'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004),
  ('ROLE_USER', 100005),
  ('ROLE_USER', 100006),
  ('ROLE_ADMIN', 100007);

INSERT INTO restaurant (user_id, name, address) VALUES
  ('100001', 'Astoria', 'Набережная фонтанки'),
  ('100001', 'Gold Fish', 'Зеленая 30'),
  ('100000', 'Steakhouse', 'Дворцовая площадь'),
  ('100000', 'VasilyOstrovsky', 'Васильевский остров');

INSERT INTO menu (restaurant_id, registered, name) VALUES
  ('100008', DEFAULT, 'Launch menu Astoria'),
  ('100008','2018-01-05', 'Launch menu Astoria'),
  ('100009', DEFAULT , 'Launch menu Gold Fish');

INSERT INTO dish (menu_id, name, price) VALUES
  ('100012', 'Fresh', 100),
  ('100012', 'Soup', 300),
  ('100012', 'Main of the day', 500),
  ('100014', 'Starter', 100),
  ('100014', 'Fish', 400),
  ('100014', 'Salad', 300);

INSERT INTO vote ("RESTAURANT_ID", "USER_ID", "VOTETIME") VALUES
  ('100008', 100000, now()),
  ('100008', 100001, now()),
  ('100008', 100002, now()),
  ('100009', 100003, '2018-01-06 09:00:00'),
  ('100010', 100004, '2018-01-06 08:40:00'),
  ('100011', 100005, '2018-01-07 10:40:00'),
  ('100011', 100006, '2018-01-07 10:55:00');