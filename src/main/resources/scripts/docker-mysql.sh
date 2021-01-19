# docker mysql 설치 (localhost:10000 포워딩)

# 초기 id, password : root / password
# version : mysql 5.7

docker run -d -p 10000:3306 -e MYSQL_ROOT_PASSWORD=password --name mysql mysql:5.7