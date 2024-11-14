CREATE TABLE IF NOT EXISTS `study`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `publishing_year` INT NULL,
  `isbn` VARCHAR(45) NULL,
  `publisher` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `isbn_UNIQUE` (`isbn` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `study`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `subscribed` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE);

insert into book (name, author, publishing_year, isbn, publisher)
values
('The Lord of the Rings', 'J.R.R. Tolkien', 1954, '0395026468', 'Allen & Unwin'),
('To Kill a Mockingbird', 'Harper Lee', 1960, '0446310759', 'HarperPerennial'),
('The Fault in Our Stars', 'John Green', 2012, '0316038746', 'Dutton'),
('The Book Thief', 'Markus Zusak', 2005, '0375831004', 'Knopf'),
('The Shack', 'William P. Young', 2007, '0316067860', 'Windblown Media'),
('The Kite Runner', 'Khaled Hosseini', 2003, '0385506982', 'Riverhead Books'),
('The Nightingale', 'Kristin Hannah', 2015, '0385387035', 'St. Martin''s Press');

insert into client (name, surname, phone, subscribed)
values
('John', 'Doe', '123-456-7890', 1),
('Jane', 'Smith', '987-654-3210', 0),
('Michael', 'Johnson', '555-123-4567', 1),
('Emily', 'Brown', '555-987-6543', 1),
('David', 'Wilson', '555-111-2222', 0),
('Olivia', 'Miller', '555-333-4444', 1),
('William', 'Davis', '555-555-5555', 1)


