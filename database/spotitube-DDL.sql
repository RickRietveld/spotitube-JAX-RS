create table playlist
(
	id int(3) not null,
	name varchar(25) not null,
	owner varchar(25) not null
)
;

create table song
(
	id int(3) not null,
	title varchar(75) not null,
	performer varchar(30) not null,
	duration int(4) not null,
	album varchar(50) not null,
	offlineAvailable tinyint(1) not null
)
;

create table token
(
	token varchar(15) not null
)
;

create table track
(
	id int(3) not null,
	title varchar(75) not null,
	performer varchar(30) not null,
	duration int(4) not null,
	offlineAvailable tinyint(1) not null
)
;

create table user
(
	user varchar(255) not null
		primary key,
	password varchar(255) not null,
	token varchar(255) not null
)
;

create table video
(
	id int(3) not null,
	title varchar(75) not null,
	performer varchar(30) not null,
	duration int(4) not null,
	playcount int(5) not null,
	publicationDate date not null,
	description varchar(100) not null,
	offlineAvailable tinyint(1) not null
)
;

