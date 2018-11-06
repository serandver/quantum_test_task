CREATE  TABLE `demo`.`employee` (
  `id` INT NOT NULL ,
  `name` VARCHAR(200) NOT NULL ,
  `city` VARCHAR(45) NOT NULL ,
  `salary` INT NOT NULL ,
  `sex` VARCHAR(1) NOT NULL ,
  `position` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) );