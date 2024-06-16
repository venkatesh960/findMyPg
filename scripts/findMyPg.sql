CREATE TABLE `findmypg`.`owner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL,
  `middle_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(45) NULL,
  `email_id` VARCHAR(45) NULL,
  `mobile_num` VARCHAR(45) NULL,
  `user_name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `createdBy` VARCHAR(45) NULL,
  `createdtimestamp` TIMESTAMP(6) NULL,
  `updatetimestamp` TIMESTAMP(6) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `findmypg`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `emp_firstname` VARCHAR(45) NULL,
  `emp_lastname` VARCHAR(45) NULL,
  `emp_middlename` VARCHAR(45) NULL,
  `emp_email_id` VARCHAR(45) NULL,
  `emp_mobile_num` VARCHAR(45) NULL,
  `emp_username` VARCHAR(45) NULL,
  `owner_id` VARCHAR(45) NOT NULL,
`createdBy` VARCHAR(45) NULL,
  `createdtimestamp` TIMESTAMP(6) NULL,
  `updatetimestamp` TIMESTAMP(6) NULL,
  PRIMARY KEY (`id`));