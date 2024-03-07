DROP table `cart` CASCADE;
DROP table `item` CASCADE;

CREATE TABLE `cart` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR,
`email` VARCHAR,
`password` VARCHAR,
);

CREATE TABLE `item` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`cart_id` INT,
`name` VARCHAR,
`price` FLOAT,
`quantity` INT,
`image` VARCHAR,
FOREIGN KEY (`cart_id` REFERENCES `seller` (`id`)
);