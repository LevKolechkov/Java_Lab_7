CREATE TABLE `study`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
  `author` VARCHAR(64) NOT NULL,
  `publishingYear` INT NOT NULL,
  `isbn` VARCHAR(45) NOT NULL,
  `publisher` VARCHAR(64),
  PRIMARY KEY (`id`));