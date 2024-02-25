SELECT * FROM student where age > 15 and age < 17;

select name from student;

select * from student where name like '%r%';

select * from student where age > id;

select * from student order by age;

ALTER TABLE student add constraint age_check CHECK (age>16);

ALTER TABLE student add constraint unique_name UNIQUE (name);

ALTER TABLE student add constraint unique_name_color UNIQUE (name,color);

ALTER TABLE student ALTER COLUMN name SET NOT NULL;

ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;
