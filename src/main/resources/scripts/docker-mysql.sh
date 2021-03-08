# docker mysql 설치 (localhost:10000 포워딩)

# 초기 id, password : root / password
# version : mysql 5.7

docker run -d -p 10000:3306 -e MYSQL_ROOT_PASSWORD=password --name mysql mysql:5.7

# lower_case_table_names 의 설정을 바꿔야 하는 경우

/etc/mysql/conf.d/docker.cnf 를 열어 lower_case_table_names=1 를 추가한다.
vim을 쓰기 위해서는 apt update && apt-get install vim 이 필요.