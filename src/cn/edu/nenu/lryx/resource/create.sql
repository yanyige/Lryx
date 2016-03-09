Hibernate: 
    drop table if exists User
Hibernate: 
    create table User (
        id integer not null auto_increment,
        hellWorld varchar(255),
        p varchar(255),
        u varchar(255),
        primary key (id)
    )
insert into User(helloWorld,p,u) values('helloWorld','pppppY','12345');