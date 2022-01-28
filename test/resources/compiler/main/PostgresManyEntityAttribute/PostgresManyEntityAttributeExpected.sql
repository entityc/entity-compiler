--
-- COMMON FUNCTIONS
--
CREATE OR REPLACE FUNCTION update_modified_on_column()
    RETURNS TRIGGER AS $$
BEGIN
  NEW.modified_on = now();
  RETURN NEW;
END;
$$ language 'plpgsql';

--
-- TABLES
--

-- Represents a user in the system.
CREATE TABLE IF NOT EXISTS platform_user (
    user_id UUID PRIMARY KEY NOT NULL,
    username TEXT NOT NULL
);

COMMENT ON TABLE platform_user IS 'Represents a user in the system.';

CREATE TABLE IF NOT EXISTS platform_user_roles (
    user_id UUID NOT NULL,
    value INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES platform_user(user_id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS platform_user_roles_user_idx ON platform_user_roles(user_id ASC);
