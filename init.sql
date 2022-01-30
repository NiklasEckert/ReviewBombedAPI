CREATE SCHEMA review_bombed;

SET search_path TO review_bombed;

CREATE TABLE review_bombed.games (
    id serial PRIMARY KEY,
    title TEXT NOT NULL,
    publishing_date DATE NOT NULL,
    description TEXT
);

CREATE TABLE review_bombed.publisher (
    id serial PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE review_bombed.games_publisher (
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE,
    publisher_id INT REFERENCES publisher (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT games_publisher_pkey PRIMARY KEY (game_id, publisher_id)
);

CREATE TABLE review_bombed.developer (
    id serial PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE review_bombed.games_developer (
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE,
    developer_id INT REFERENCES developer (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT games_developer_pkey PRIMARY KEY (game_id, developer_id)
);

CREATE TABLE review_bombed.rb_user (
    id serial PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL
);

CREATE TABLE review_bombed.rating (
    id serial PRIMARY KEY,
    rate SMALLINT NOT NULL,
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE review_bombed.review (
    id serial PRIMARY KEY,
    rate SMALLINT NOT NULL,
    review_text TEXT NOT NULL,
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE review_bombed.diary_entry (
    id serial PRIMARY KEY,
    rate SMALLINT NOT NULL,
    date DATE NOT NULL,
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE review_bombed.list (
    id serial PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT,
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE review_bombed.list_entry (
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    list_id INT REFERENCES list (id) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT list_entry_pkey PRIMARY KEY (user_id, list_id, game_id)
);

INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed', '2007-11-14', 'The first installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed II', '2009-11-17', 'The second installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed: Brotherhood', '2010-11-16', 'The third installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed: Revelations', '2011-11-15', 'The fourth installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed III', '2012-10-30', 'The fifth installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed IV: Black Flag', '2013-10-29', 'The sixth installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Rogue', '2014-11-11', 'The seventh installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Unity', '2014-11-11', 'The eighth installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Syndicate', '2015-10-23', 'The ninth installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Origins', '2017-10-27', 'The tenth installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Odyssey', '2018-10-05', 'The eleventh installment in the Assassin''s Creed franchise.');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Valhalla', '2020-11-10', 'The twelfth installment in the Assassin''s Creed franchise.');

INSERT INTO publisher VALUES (DEFAULT, 'Ubisoft');

INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Montreal');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Québec');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Annecy');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Bucharest');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Kiev');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Shanghai');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Singapore');
INSERT INTO developer VALUES (DEFAULT, 'Ubisoft Sofia');

INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed'                AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed II'             AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed: Brotherhood'   AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed: Revelations'   AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed IV: Black Flag' AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed Rogue'          AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed Unity'          AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed Syndicate'      AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed Origins'        AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed Odyssey'        AND name = 'Ubisoft';
INSERT INTO games_publisher SELECT g.id, p.id FROM games g, publisher p WHERE title = 'Assassin''s Creed Valhalla'       AND name = 'Ubisoft';

INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed'                AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed II'             AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed: Brotherhood'   AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed: Revelations'   AND name = 'Ubisoft Montreal';

INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Québec';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Annecy';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Bucharest';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Kiev';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Shanghai';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed III'            AND name = 'Ubisoft Singapore';

INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed IV: Black Flag' AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed Rogue'          AND name = 'Ubisoft Sofia';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed Unity'          AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed Syndicate'      AND name = 'Ubisoft Québec';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed Origins'        AND name = 'Ubisoft Montreal';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed Odyssey'        AND name = 'Ubisoft Québec';
INSERT INTO games_developer SELECT g.id, d.id FROM games g, developer d WHERE title = 'Assassin''s Creed Valhalla'       AND name = 'Ubisoft Montreal';

INSERT INTO rb_user VALUES (DEFAULT, 'LEGION', 'niklas.eckert.1807@gmail.com', 'password1234');

INSERT INTO rating (rate, user_id, game_id) SELECT 5, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed';
INSERT INTO rating (rate, user_id, game_id) SELECT 8, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed II';
INSERT INTO rating (rate, user_id, game_id) SELECT 9, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed: Brotherhood';
INSERT INTO rating (rate, user_id, game_id) SELECT 7, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed: Revelations';
INSERT INTO rating (rate, user_id, game_id) SELECT 6, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed III';
INSERT INTO rating (rate, user_id, game_id) SELECT 9, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed IV: Black Flag';
INSERT INTO rating (rate, user_id, game_id) SELECT 7, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Rogue';
INSERT INTO rating (rate, user_id, game_id) SELECT 7, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Unity';
INSERT INTO rating (rate, user_id, game_id) SELECT 5, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Syndicate';
INSERT INTO rating (rate, user_id, game_id) SELECT 8, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Origins';
INSERT INTO rating (rate, user_id, game_id) SELECT 7, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Odyssey';
INSERT INTO rating (rate, user_id, game_id) SELECT 6, u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Valhalla';

INSERT INTO review (rate, review_text, user_id, game_id) SELECT 9, 'Second best in the series.', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed: Brotherhood';
INSERT INTO review (rate, review_text, user_id, game_id) SELECT 9, 'Best in the series.', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed IV: Black Flag';

INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 5, '2010-07-20', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 8, '2010-09-21', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed II';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 9, '2010-12-01', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed: Brotherhood';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 7, '2011-12-15', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed: Revelations';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 6, '2012-12-10', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed III';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 9, '2013-12-24', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed IV: Black Flag';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 7, '2015-04-14', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Rogue';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 7, '2014-11-29', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Unity';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 5, '2015-12-06', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Syndicate';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 8, '2018-01-10', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Origins';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 7, '2019-06-07', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Odyssey';
INSERT INTO diary_entry (rate, date, user_id, game_id) SELECT 6, '2021-05-26', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed Valhalla';

INSERT INTO list (name, description, user_id) SELECT 'Ezio Trilogy', 'All titles with Ezio.', u.id FROM rb_user u WHERE name = 'LEGION';
INSERT INTO list (name, description, user_id) SELECT 'AC Female', 'All AC titles with female protagonists', u.id FROM rb_user u WHERE name = 'LEGION';

INSERT INTO list_entry (user_id, list_id, game_id) SELECT u.id, l.id, g.id FROM rb_user u, list l, games g WHERE u.name = 'LEGION' AND l.name = 'Ezio Trilogy' AND g.title = 'Assassin''s Creed II';
INSERT INTO list_entry (user_id, list_id, game_id) SELECT u.id, l.id, g.id FROM rb_user u, list l, games g WHERE u.name = 'LEGION' AND l.name = 'Ezio Trilogy' AND g.title = 'Assassin''s Creed: Brotherhood';
INSERT INTO list_entry (user_id, list_id, game_id) SELECT u.id, l.id, g.id FROM rb_user u, list l, games g WHERE u.name = 'LEGION' AND l.name = 'Ezio Trilogy' AND g.title = 'Assassin''s Creed: Revelations';

INSERT INTO list_entry (user_id, list_id, game_id) SELECT u.id, l.id, g.id FROM rb_user u, list l, games g WHERE u.name = 'LEGION' AND l.name = 'AC Female' AND g.title = 'Assassin''s Creed Syndicate';
INSERT INTO list_entry (user_id, list_id, game_id) SELECT u.id, l.id, g.id FROM rb_user u, list l, games g WHERE u.name = 'LEGION' AND l.name = 'AC Female' AND g.title = 'Assassin''s Creed Odyssey';
INSERT INTO list_entry (user_id, list_id, game_id) SELECT u.id, l.id, g.id FROM rb_user u, list l, games g WHERE u.name = 'LEGION' AND l.name = 'AC Female' AND g.title = 'Assassin''s Creed Valhalla';