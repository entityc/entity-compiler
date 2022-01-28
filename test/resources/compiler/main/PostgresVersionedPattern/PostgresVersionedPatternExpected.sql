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

CREATE TABLE IF NOT EXISTS thing (
    modified_on TIMESTAMP WITH TIME ZONE,
    name TEXT NOT NULL,
    thing_id UUID PRIMARY KEY NOT NULL,
    UNIQUE (name)
);

CREATE TRIGGER thing_updatemodifiedon
BEFORE INSERT OR UPDATE ON thing
FOR EACH ROW EXECUTE PROCEDURE update_modified_on_column();

CREATE TABLE IF NOT EXISTS thing_version (
    notes TEXT NOT NULL,
    thing_id UUID NOT NULL,
    thing_version_id UUID PRIMARY KEY NOT NULL,
    FOREIGN KEY (thing_id) REFERENCES thing(thing_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS release (
    created_on TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    description TEXT,
    prod_enabled BOOLEAN NOT NULL,
    release_date TIMESTAMP WITH TIME ZONE NOT NULL,
    release_id UUID PRIMARY KEY NOT NULL,
    version INT NOT NULL
);

CREATE TABLE IF NOT EXISTS release_thing (
    release_id UUID NOT NULL,
    release_version_id UUID PRIMARY KEY NOT NULL,
    thing_id UUID NOT NULL,
    thing_version_id UUID NOT NULL,
    FOREIGN KEY (release_id) REFERENCES release(release_id) ON DELETE CASCADE,
    FOREIGN KEY (thing_id) REFERENCES thing(thing_id) ON DELETE CASCADE,
    FOREIGN KEY (thing_version_id) REFERENCES thing_version(thing_version_id) ON DELETE CASCADE,
    UNIQUE (release_id, thing_id),
    UNIQUE (release_id, thing_version_id)
);

CREATE INDEX IF NOT EXISTS thing_version_thing_idx ON thing_version(thing_id ASC);
CREATE INDEX IF NOT EXISTS release_thing_release_idx ON release_thing(release_id ASC);
