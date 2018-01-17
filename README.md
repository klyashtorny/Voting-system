Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user votes again the same day:
- If it is before 11:00 we asume that he changed his mind.
- If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides new menu each day.

CURL:

USERS
GetAll
curl -s http://localhost:8080/topjava-graduation/rest/admin/users --user admin@gmail.com:admin

Get
curl -s http://localhost:8080/topjava-graduation/rest/admin/users/100001 --user admin@gmail.com:admin

Post
curl -s -X POST -d '{"name":"New User","email":"user10@yandex.ru","password":"1234567"}' -H 'Content-Type:application
/json;charset=UTF-8' curl -s http://localhost:8080/topjava-graduation/rest/admin/users --user admin@gmail.com:admin
Delete
curl -s -X DELETE http://localhost:8080/topjava-graduation/rest/admin/users/100002 --user admin@gmail.com:admin