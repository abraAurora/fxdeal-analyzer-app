CREATE TABLE fx_deals (
    deal_id SERIAL PRIMARY KEY,
    deal_unique_id VARCHAR(255) NOT NULL,
    from_currency_iso_code CHAR(3) NOT NULL,
    to_currency_iso_code CHAR(3) NOT NULL,
    deal_timestamp TIMESTAMPTZ NOT NULL,
    deal_amount NUMERIC(18, 4) NOT NULL,
    imported_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE UNIQUE INDEX unique_deal_id ON fx_deals (deal_unique_id);
