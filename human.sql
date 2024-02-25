CREATE TABLE human(
id serial PRIMARY KEY,
name VARCHAR(50),
age INTEGER,
license BOOLEAN
car_id INTEGER REFERENCES car(id)
);

CREATE TABLE human(
id serial PRIMARY KEY,
model VARCHAR(50),
brand VARCHAR(50),
cost INTEGER

);