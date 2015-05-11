ALTER TABLE `semranker`.`searchengine_country` 
ADD COLUMN `accept_language` VARCHAR(100) NULL AFTER `searchengine`,
ADD COLUMN `host` VARCHAR(45) NULL AFTER `accept_language`;

UPDATE `semranker`.`searchengine_country` SET `accept_language`='it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3', `host`='www.google.it' WHERE `id`='1';
UPDATE `semranker`.`searchengine_country` SET `accept_language`='it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3', `host`='it.search.yahoo.com' WHERE `id`='5';
UPDATE `semranker`.`searchengine_country` SET `accept_language`='it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3', `host`='www.bing.com' WHERE `id`='6';
UPDATE `semranker`.`searchengine_country` SET `accept_language`='en-US;q=0.5,en;q=0.3', `host`='www.google.com' WHERE `id`='2';
UPDATE `semranker`.`searchengine_country` SET `accept_language`='fr-FR,fr;q=0.8,en-US;q=0.5,en;q=0.3', `host`='www.google.fr' WHERE `id`='3';
UPDATE `semranker`.`searchengine_country` SET `accept_language`='de-DE,de;q=0.8,en-US;q=0.5,en;q=0.3', `host`='www.google.de' WHERE `id`='4';
