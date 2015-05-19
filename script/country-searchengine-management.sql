CREATE TABLE `semranker`.`country` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
ALTER TABLE `semranker`.`searchengine_country` 
ADD COLUMN `country` INT NULL AFTER `id`,
ADD INDEX `fk_country_secountry_idx` (`country` ASC);
ALTER TABLE `semranker`.`searchengine_country` 
ADD CONSTRAINT `fk_country_secountry`
  FOREIGN KEY (`country`)
  REFERENCES `semranker`.`country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
INSERT INTO `semranker`.`country` (`id`, `name`) VALUES ('1', 'Italy');
INSERT INTO `semranker`.`country` (`id`, `name`) VALUES ('2', 'USA');
INSERT INTO `semranker`.`country` (`id`, `name`) VALUES ('3', 'France');
INSERT INTO `semranker`.`country` (`id`, `name`) VALUES ('4', 'Germany');

UPDATE `semranker`.`searchengine_country` SET `country`='1' WHERE `id`='1';
UPDATE `semranker`.`searchengine_country` SET `country`='2' WHERE `id`='2';
UPDATE `semranker`.`searchengine_country` SET `country`='3' WHERE `id`='3';
UPDATE `semranker`.`searchengine_country` SET `country`='4' WHERE `id`='4';
UPDATE `semranker`.`searchengine_country` SET `country`='1' WHERE `id`='5';
UPDATE `semranker`.`searchengine_country` SET `country`='1' WHERE `id`='6';

ALTER TABLE `semranker`.`account_domain_history_data` 
ADD COLUMN `searchengine` INT NULL AFTER `score`,
ADD COLUMN `country` INT NULL AFTER `searchengine`,
ADD INDEX `fk_searchengine_adhd_idx` (`searchengine` ASC),
ADD INDEX `fk_country_adhd_idx` (`country` ASC);
ALTER TABLE `semranker`.`account_domain_history_data` 
ADD CONSTRAINT `fk_searchengine_adhd`
  FOREIGN KEY (`searchengine`)
  REFERENCES `semranker`.`searchengine` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_country_adhd`
  FOREIGN KEY (`country`)
  REFERENCES `semranker`.`country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `semranker`.`account_domain_competitor_history_data` 
ADD COLUMN `searchengine` INT NULL AFTER `score`,
ADD COLUMN `country` INT NULL AFTER `searchengine`,
ADD INDEX `fk_searchengine_adchd_idx` (`searchengine` ASC),
ADD INDEX `fk_country_adchd_idx` (`country` ASC);
ALTER TABLE `semranker`.`account_domain_competitor_history_data` 
ADD CONSTRAINT `fk_searchengine_adchd`
  FOREIGN KEY (`searchengine`)
  REFERENCES `semranker`.`searchengine` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_country_adchd`
  FOREIGN KEY (`country`)
  REFERENCES `semranker`.`country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  