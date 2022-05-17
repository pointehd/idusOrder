# idus
아이디어스 백엔드 과제 

## 사용 기술
- java 11 
- spring boot 2.6.7
- spring-security
- swagger-ui 2.9.2
- jpa
- querydsl
- jwt
- lombok

## database create query 
```
create table user (
	seq bigint AUTO_INCREMENT primary key,
	user_name varchar(20) not null unique,
	nickname varchar(30) not null, 
	password varchar(255) not null,
	phone varchar(100) not null,
	email varchar(100) not null,
	gender varchar(6),
	create_datetime timestamp,
	update_datetime timestamp 
)

create table order_info (
	seq bigint AUTO_INCREMENT primary key,
	user_seq bigint,
	order_num varchar(12) not null unique,
	product_name varchar(100) not null,
	create_datetime timestamp,
	FOREIGN KEY (user_seq) REFERENCES user(seq)
)

create table expired_token (
	seq bigint AUTO_INCREMENT primary key,
	token varchar(255) not null unique ,
	create_datetime timestamp
)
```

## swagger-ui url 
http://localhost:8080/swagger-ui.html

## TODO Check list
- ~회원가입~
- ~회원 로그인~
> jwt를 이용하여 구현하였으나 refresh Token 으로 보완이 필요해보입니다.
- ~회원 로그아웃~
> jwt를 mysql에 저장하여 만료 처리하였지만 추후 radis를 이용하여 만료시간과 함께 저장 필요.   
> 현재 방식대로라면 스케줄링을 이용하여 만료 토큰이 너무 많아지지 않게 할수 있을 것같습니다.
- ~단일 회원상세 정보 조회~
- ~단일 회원 주문 목록 조회~
- ~여러 회원 목록 조회~
  - ~페이지 네이션으로 조회~
  - ~이름, 이메일로 검색~
  - ~각회원의 마지막 주문 정보~
- ~swagger-ui~
