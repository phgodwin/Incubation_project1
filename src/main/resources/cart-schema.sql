DROP TABLE if exists`item` CASCADE;
DROP TABLE if exists `cart`CASCADE;

	CREATE TABLE `cart` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	 );
	 
	CREATE TABLE `item`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(255),
	`price` INT,
	`quantity` INT,
	`image` VARCHAR(255)
	`cart_id` INT,
	FOREIGN KEY (`cart_id) REFERENCES `cart`(`id`)
	);