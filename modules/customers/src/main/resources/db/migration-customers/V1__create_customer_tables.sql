CREATE TABLE IF NOT EXISTS customers
(
    id          SERIAL PRIMARY KEY,
    external_id UUID UNIQUE              NOT NULL,
    first_name  TEXT                     NOT NULL,
    last_name   TEXT                     NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_customers_external_id ON customers USING btree (external_id);

CREATE TABLE IF NOT EXISTS customer_addresses
(
    id           SERIAL PRIMARY KEY,
    address_type TEXT                     NOT NULL,
    customer_id  INTEGER                  NOT NULL,
    street       TEXT                     NOT NULL,
    city         TEXT                     NOT NULL,
    postal_code  TEXT                     NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_customer_addresses_customer_id ON customer_addresses USING btree (customer_id);

CREATE TABLE IF NOT EXISTS customer_contacts
(
    id           SERIAL PRIMARY KEY,
    customer_id  INTEGER                  NOT NULL,
    contact_type TEXT                     NOT NULL,
    value        TEXT                     NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_customer_contacts_customer_id ON customer_contacts USING btree (customer_id);
