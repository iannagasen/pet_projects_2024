
CREATE TABLE IF NOT EXISTS client (
  id            INTEGER PRIMARY KEY AUTO_INCREMENT,
  client_name   VARCHAR(255) NOT NULL,
  secret        VARCHAR(1025) NOT NULL
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS redirect_urls (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  url         VARCHAR(1025) NOT NULL,
  client_id   INTEGER,
  FOREIGN KEY (client_id) REFERENCES client(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS client_scopes (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  scope       VARCHAR(255) NOT NULL,
  client_id   INTEGER,
  FOREIGN KEY (client_id) REFERENCES client(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS grant_types (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  type        VARCHAR(255) NOT NULL,
  client_id   INTEGER,
  FOREIGN KEY (client_id) REFERENCES client(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS authentication_methods (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  method      VARCHAR(255) NOT NULL,
  client_id   INTEGER,
  FOREIGN KEY (client_id) REFERENCES client(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS token_settings (
  id                INTEGER PRIMARY KEY AUTO_INCREMENT,
  access_token_ttl  INTEGER,
  type              VARCHAR(255),
  client_id   INTEGER,
  FOREIGN KEY (client_id) REFERENCES client(id)
) ENGINE=InnoDB;

-- INSERT client

INSERT INTO client
VALUES (1, 'client', 'secret');

INSERT INTO redirect_urls
VALUES (1, 'http://my-redirect_url.com', 1);

INSERT INTO client_scopes
VALUES (1, 'openid', 1);

INSERT INTO grant_types
VALUES (1, 'authorization_code', 1);

INSERT INTO grant_types
VALUES (2, 'client_credentials', 1);

INSERT INTO authentication_methods
VALUES (1, 'client_secret_basic', 1);

INSERT INTO token_settings
VALUES (1, 5, 'reference', 1);
