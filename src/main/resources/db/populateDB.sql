DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO restaurant (user_id, name, address) VALUES
  ('100001', 'Astoria', 'Набережная фонтанки'),
  ('100001', 'Gold Fish', 'Зеленая 30'),
  ('100000', 'Steakhouse', 'Дворцовая площадь'),
  ('100000', 'VasilyOstrovsky', 'Васильевский остров');

INSERT INTO menu (restaurant_id, registered, name) VALUES
  ('100002', DEFAULT, 'Launch menu Astoria'),
  ('100002','2018-01-05', 'Launch menu Astoria'),
  ('100003','2018-01-06', 'Launch menu Gold Fish');

INSERT INTO dish (menu_id, name, price) VALUES
  ('100006', 'Fresh', 100),
  ('100006', 'Soup', 300),
  ('100006', 'Main of the day', 500),
  ('100007', 'Starter', 100),
  ('100007', 'Fish', 400),
  ('100007', 'Salad', 300);