CREATE TABLE organizations (
                               id UUID PRIMARY KEY,
                               name VARCHAR(150) NOT NULL,
                               slug VARCHAR(100) NOT NULL UNIQUE,
                               status VARCHAR(30) NOT NULL,
                               created_at TIMESTAMPTZ NOT NULL,
                               updated_at TIMESTAMPTZ NOT NULL
);