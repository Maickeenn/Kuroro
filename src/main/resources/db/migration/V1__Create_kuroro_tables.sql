CREATE TABLE kuroro
(
    internal_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    id            VARCHAR(255) NOT NULL UNIQUE,
    name          VARCHAR(255) NOT NULL,
    hit_points    INT,
    attack        INT,
    defense       INT,
    magic_attack  INT,
    magic_defense INT,
    speed         INT,
    lore          VARCHAR(1000),
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE move
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    power       INT,
    description VARCHAR(255) NOT NULL,
    category    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE type
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    type_name  VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE bonus_stats
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    hit_points    INT,
    attack        INT,
    defense       INT,
    magic_attack  INT,
    magic_defense INT,
    speed         INT,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    nickname   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE kuroro_type
(
    kuroro_id  BIGINT NOT NULL,
    type_id    BIGINT NOT NULL,
    PRIMARY KEY (kuroro_id, type_id),
    FOREIGN KEY (kuroro_id) REFERENCES kuroro (internal_id),
    FOREIGN KEY (type_id) REFERENCES type (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE move_type
(
    move_id BIGINT NOT NULL,
    type_id BIGINT NOT NULL,
    PRIMARY KEY (move_id, type_id),
    FOREIGN KEY (move_id) REFERENCES move (id),
    FOREIGN KEY (type_id) REFERENCES type (id)
);

CREATE TABLE team
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id    BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE team_kuroro
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    team_id    BIGINT NOT NULL,
    kuroro_id  BIGINT NOT NULL,
    bonus_stats_id BIGINT NOT NULL,
    FOREIGN KEY (team_id) REFERENCES team (id),
    FOREIGN KEY (kuroro_id) REFERENCES kuroro (internal_id),
    FOREIGN KEY (bonus_stats_id) REFERENCES bonus_stats (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE move_kuroro
(
    team_kuroro_id BIGINT NOT NULL,
    move_id        BIGINT NOT NULL,
    PRIMARY KEY (team_kuroro_id, move_id),
    FOREIGN KEY (team_kuroro_id) REFERENCES team_kuroro (id),
    FOREIGN KEY (move_id) REFERENCES move (id)
);
