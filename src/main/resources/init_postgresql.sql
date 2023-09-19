DROP table IF EXISTS Course_Student;
DROP table IF EXISTS Student;
DROP table IF EXISTS Course;
DROP table IF EXISTS Teacher;

Create table IF NOT EXISTS Student
(
    id     int generated by default as identity primary key,
    name   VARCHAR(100) not null UNIQUE,
    age    int          not null check (age > 0),
    gender VARCHAR      not null
    );


Create table IF NOT EXISTS Teacher
(
    id   int generated by default as identity primary key,
    name VARCHAR not null UNIQUE
);

Create table IF NOT EXISTS Course
(
    id         int generated by default as identity primary key,
    name       VARCHAR not null UNIQUE,
    teacher_id int REFERENCES Teacher(id) on delete set null
    );


Insert Into student (name, age, gender)
values ('Egor Filippov', 35, 'MALE'),
       ('Ilia Rybalko', 34, 'MALE'),
       ('Darya Kasatkina', 30, 'FEMALE');
Insert Into Teacher (name)
values ('Java Teacher'),
       ('Algorithm Teacher'),
       ('Testing Teacher')
;
Insert Into Course (name, teacher_id)
values ('Java' , 1),
       ('Algorithm', 1),
       ('Testing', 2),
       ('Statistic',  2)
;
Create table IF NOT EXISTS Course_Student
(
    course_id  int REFERENCES Course (id) on DELETE CASCADE,
    student_id int REFERENCES Student (id) on DELETE CASCADE,
    PRIMARY KEY (course_id, student_id)
    );

Insert Into Course_Student
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 3)
;
select *
from Course_Student;