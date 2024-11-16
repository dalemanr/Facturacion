INSERT INTO clients (name, lastname, email, create_at) VALUES ('David', 'Aleman', 'gg@gmail.com', '2024-08-28');
INSERT INTO clients (name, lastname, email, create_at) VALUES ('Andres', 'Guzman', 'gg@gmail.com', '2024-08-29');
INSERT INTO clients (name, lastname, email, create_at) VALUES ('Daniel', 'Roman', 'gg@gmail.com', '2024-08-29');
INSERT INTO clients (name, lastname, email, create_at) VALUES ('Camilo', 'Rojas', 'gg@gmail.com', '2024-08-29');

INSERT INTO products(name, price, create_at) VALUES ("Camara panasonic", 300, NOW());
INSERT INTO products(name, price, create_at) VALUES ("Sanitario", 120, NOW());
INSERT INTO products(name, price, create_at) VALUES ("Figurita Anime", 50, NOW());
INSERT INTO products(name, price, create_at) VALUES ("Notebook", 500, NOW());
INSERT INTO products(name, price, create_at) VALUES ("Teclado Mecanico", 70, NOW());

INSERT INTO facturas (description, observacion, client_id, create_at) VALUES ('Factura equipos de oficina', null, 1,NOW());
INSERT INTO facturas_items (cantidad, factura_id, product_id) VALUES (1,1,1);
INSERT INTO facturas_items (cantidad, factura_id, product_id) VALUES (2,1,4);
INSERT INTO facturas_items (cantidad, factura_id, product_id) VALUES (1,1,3);
INSERT INTO facturas_items (cantidad, factura_id, product_id) VALUES (1,1,5);

INSERT INTO facturas (description, observacion, client_id, create_at) VALUES ('Factura Sanitario', 'Nota importante', 1,NOW());
INSERT INTO facturas_items (cantidad, factura_id, product_id) VALUES (3,2,1);