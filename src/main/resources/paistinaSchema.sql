DROP table `item` CASCADE;
DROP table `cart` CASCADE;

CREATE TABLE `cart` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(255),
`email` VARCHAR(255),
`password` VARCHAR(255)
);

CREATE TABLE `item` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR,
`price` INT,
`quantity` INT,
`image` VARCHAR(255),
`cart_id` INT,
FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
);