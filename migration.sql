USE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS adCategory;
DROP TABLE IF EXISTS categories;

# Ensure the name is UNIQUE for no duplicates.
CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    userrole VARCHAR(240) NOT NULL DEFAULT 'user',
    username VARCHAR(240) NOT NULL UNIQUE,
    email VARCHAR(240) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ads (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    randId VARCHAR(240) NOT NULL,
    userid INT UNSIGNED NOT NULL,
    title VARCHAR(240) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userid) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE categories (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    catTitle VARCHAR(240) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE adCategory (
    adID INT UNSIGNED NOT NULL,
    catID INT UNSIGNED NOT NULL,
    FOREIGN KEY (adID) REFERENCES ads(id)
        ON DELETE CASCADE,
    FOREIGN KEY (catID) REFERENCES categories(id)
        ON DELETE CASCADE
);

select * from users;

select * from adCategory;

DELETE FROM ads;

DELETE from users;

SELECT * from ads;

INSERT INTO categories (catTitle) VALUES ("jobs");

insert into users (userrole, username, email, password) VALUES ("user", "arianag", "grande@email.com", "codeup");

UPDATE users SET userrole = 'admin' WHERE username = 'Thomas';

