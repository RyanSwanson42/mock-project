DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS orders_items_function;

CREATE TABLE orders (
	table_num INT AUTO_INCREMENT(1000, 1) PRIMARY KEY,
	total_price INT
);

CREATE TABLE menu (
	item_id INT PRIMARY KEY,
	item_name VARCHAR(20) NOT NULL,
	item_price DOUBLE NOT NULL,
	item_desc VARCHAR(250) NOT NULL
);

CREATE TABLE orders_menu_junction (
	table_num INT,
	item_id INT,
	foreign key (table_num) references orders(table_num),
	foreign key (item_id) references menu(item_id),
	constraint table_item primary key (table_num, item_id)
);


INSERT INTO menu (item_id, item_name, item_price, item_desc) VALUES (1, 'Steak', 15.00, 'A delicious strip steak.');
INSERT INTO menu (item_id, item_name, item_price, item_desc) VALUES (2, 'Salad', 6.00, 'A nutritious Caesar salad.');
INSERT INTO menu (item_id, item_name, item_price, item_desc) VALUES (3, 'Lobster', 20.00, 'A juicy boiled lobster.');
INSERT INTO menu (item_id, item_name, item_price, item_desc) VALUES (4, 'Burger', 5.00, 'A burger with cheese on a seseme bun.');
INSERT INTO menu (item_id, item_name, item_price, item_desc) VALUES (5, 'Icecream', 4.00, 'A frozen dessert.');

