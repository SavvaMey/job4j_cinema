CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    fio text NOT NULL,
    phone text unique NOT NULL
);
CREATE TABLE halls (
    id serial PRIMARY KEY,
    row int NOT NULL,
    col int NOT NULL,
    status bool NOT NULL DEFAULT false,
    accaunt_id int references accounts(id)
);

INSERT INTO halls(row, col) VALUES (1, 1);
INSERT INTO halls(row, col) VALUES (1, 2);
INSERT INTO halls(row, col) VALUES (1, 3);

INSERT INTO halls(row, col) VALUES (2, 1);
INSERT INTO halls(row, col) VALUES (2, 2);
INSERT INTO halls(row, col) VALUES (2, 3);

INSERT INTO halls(row, col) VALUES (3, 1);
INSERT INTO halls(row, col) VALUES (3, 2);
INSERT INTO halls(row, col) VALUES (3, 3);