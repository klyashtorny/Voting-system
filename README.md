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
curl -s http://localhost:8080/topjava-graduation/rest/admin/users --user admin1@gmail.com:admin1
Get
curl -s http://localhost:8080/topjava-graduation/rest/admin/users/100001 --user admin1@gmail.com:admin1
Post
curl -s -X POST -d '{"name":"New User","email":"user10@yandex.ru","password":"1234567"}' -H 'Content-Type:application/json;charset=UTF-8' curl -s http://localhost:8080/topjava-graduation/rest/admin/users --user admin1@gmail.com:admin1
Delete
curl -s -X DELETE http://localhost:8080/topjava-graduation/rest/admin/users/100002 --user admin1@gmail.com:admin1

VOTE
GetAll(current)
curl -s http://localhost:8080/topjava-graduation/rest/restaurants --user user1@yandex.ru:password1

GetAllByDate(history)
curl -s http://localhost:8080/topjava-graduation/rest/restaurants/history?dateTime=2018-01-07 --user user1@yandex.ru:password1
curl -s http://localhost:8080/topjava-graduation/rest/restaurants/history?dateTime=2018-01-06 --user user1@yandex.ru:password1

GetByRestaurants
curl -s http://localhost:8080/topjava-graduation/rest/restaurants/100008/votes --user admin1@gmail.com:admin1
curl -s http://localhost:8080/topjava-graduation/rest/restaurants/100009/votes --user admin2@gmail.com:admin2

Post
curl -s -X POST -d -H http://localhost:8080/topjava-graduation/rest/restaurants/100009/vote --user user3@yandex.ru:password3
curl -s -X POST -d -H http://localhost:8080/topjava-graduation/rest/restaurants/100009/vote --user user4@yandex.ru:password4
curl -s -X POST -d -H http://localhost:8080/topjava-graduation/rest/restaurants/100009/vote --user user5@yandex.ru:password5
curl -s -X POST -d -H http://localhost:8080/topjava-graduation/rest/restaurants/100009/vote --user user6@yandex.ru:password6

RESTAURANT

GetAll
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants --user user1@yandex.ru:password1

GetAllByAdmin
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/admin/100000/all --user admin1@gmail.com:admin1
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/admin/100001/all --user admin2@gmail.com:admin2

Create
curl -s -X POST -d '{"name": "New Restaurant","address":"New Address"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava-graduation/rest/profile/restaurants --user admin2@gmail.com:admin2

Update
curl -s -X PUT -d '{"name": "Update Restaurant","address":"Update Address"}' -H 'Content-Type: application/json'  http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008 --user admin1@gmail.com:admin1

Delete

curl -s -X DELETE http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008 --user admin1@gmail.com:admin1

MENU

Get

curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/100009/menu/100014 --user admin2@gmail.com:admin2

GetAllByRestaurant
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008/menus --user admin1@gmail.com:admin1

getActualWithDishes
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008/menu --user user4@yandex.ru:password4

Create
curl -s -X POST -d '{"registered": "2018-01-19","name":"New Menu"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008/menu --user admin1@gmail.com:admin1

Update
curl -s -X PUT -d '{"registered": "2018-01-19","name":"Update Menu"}' -H 'Content-Type: application/json'  http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008/menu/100013 --user admin1@gmail.com:admin1

Delete
curl -s -X DELETE http://localhost:8080/topjava-graduation/rest/profile/restaurants/100008/menu/100013 --user admin1@gmail.com:admin1

DISH

Get
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/menu/100014/dish/100018 --user admin2@gmail.com:admin2

GetAllByMenu
curl -s http://localhost:8080/topjava-graduation/rest/profile/restaurants/menu/100012/dishes --user admin1@gmail.com:admin1

Create
curl -s -X POST -d '{"name": "New Dish","price":"150"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava-graduation/rest/profile/restaurants/menu/100012/dish --user admin1@gmail.com:admin1

Update
curl -s -X PUT -d '{"name": "Update Dish","price":"180"}' -H 'Content-Type: application/json'  http://localhost:8080/topjava-graduation/rest/profile/restaurants/menu/100014/dish/100018 --user admin1@gmail.com:admin1

Delete
curl -s -X DELETE http://localhost:8080/topjava-graduation/rest/profile/restaurants/menu/100012/dish/100017 --user admin1@gmail.com:admin1
