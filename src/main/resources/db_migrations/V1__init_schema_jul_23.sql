DROP TABLE IF EXISTS users;;
CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    first_name       VARCHAR(255)          NOT NULL,
    last_name        VARCHAR(255)          NOT NULL,
    birth_date       DATE                  NOT NULL,
    phone_number     VARCHAR(255)          NOT NULL,
    email            VARCHAR(255)          NOT NULL,
    created_at TIMESTAMPTZ           NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ           NOT NULL DEFAULT NOW(),
    CONSTRAINT unique_user_email UNIQUE (email)
    );