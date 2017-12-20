DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;
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