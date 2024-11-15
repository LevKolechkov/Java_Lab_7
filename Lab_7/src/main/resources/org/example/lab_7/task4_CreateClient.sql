CREATE TABLE IF NOT EXISTS `study`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `subscribed` TINYINT NOT NULL,
  PRIMARY KEY (`id`));