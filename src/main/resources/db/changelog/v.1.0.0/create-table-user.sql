CREATE TABLE users
(
    id              BIGSERIAL PRIMARY KEY NOT NULL,
    first_name      VARCHAR(50)           NOT NULL,
    last_name       VARCHAR(50)           NOT NULL,
    organization_id BIGINT
);