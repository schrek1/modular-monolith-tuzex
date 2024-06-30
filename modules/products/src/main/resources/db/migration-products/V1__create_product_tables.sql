CREATE TABLE IF NOT EXISTS products
(
    id          SERIAL PRIMARY KEY,
    external_id UUID UNIQUE              NOT NULL,
    name        TEXT                     NOT NULL,
    description TEXT                     NOT NULL,
    price       DECIMAL(10, 2)           NOT NULL,
    category    TEXT                     NOT NULL,
    tags        TEXT[]                   NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_products_external_id ON products USING btree (external_id);

CREATE TABLE IF NOT EXISTS product_supplies
(
    id           SERIAL PRIMARY KEY,
    product_id   INTEGER                  NOT NULL,
    supplies     INTEGER                  NOT NULL,
    reservations INTEGER                  NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_product_supplies_product_id ON product_supplies USING btree (product_id);
