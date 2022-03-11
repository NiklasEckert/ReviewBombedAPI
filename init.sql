CREATE SCHEMA review_bombed;

SET search_path TO review_bombed;

CREATE TABLE review_bombed.games (
    id serial PRIMARY KEY,
    title TEXT NOT NULL,
    publishing_date DATE NOT NULL,
    description TEXT,
    cover_url TEXT NOT NULL,
    preview_image_url TEXT NOT NULL
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
    password TEXT NOT NULL,
    image_url TEXT
);

CREATE TABLE review_bombed.rb_role (
    id serial PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE review_bombed.user_roles (
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role_id INT REFERENCES rb_role (id) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE review_bombed.rating (
    id serial PRIMARY KEY,
    rate SMALLINT NOT NULL,
    user_id INT REFERENCES rb_user (id) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE review_bombed.review (
    id serial PRIMARY KEY,
    title TEXT NOT NULL,
    review_date date NOT NULL,
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

CREATE TABLE review_bombed.list_games (
    list_id INT REFERENCES list (id) ON UPDATE CASCADE ON DELETE CASCADE,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT list_entry_pkey PRIMARY KEY (list_id, game_id)
);

CREATE TABLE review_bombed.screenshot (
    id serial PRIMARY KEY,
    game_id INT REFERENCES games (id) ON UPDATE CASCADE ON DELETE CASCADE,
    screenshot_url TEXT NOT NULL

);

INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed', '2007-11-14', 'The first installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/6/6a/Accover.jpg/revision/latest/scale-to-width-down/699?cb=20210519104609', 'https://i.pinimg.com/originals/d9/6c/5f/d96c5ffb1e75ab3d8022aae7dddb29bb.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed II', '2009-11-17', 'The second installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/0/09/AC2coverHighRes.jpg/revision/latest/scale-to-width-down/700?cb=20120706023159', 'https://staticctf.akamaized.net/J3yJr34U2pZ2Ieem48Dwy9uqj5PNUQTn/3scVp8xK8WCvad1zmHtJxf/53cab66f5c29340786a21b8b508e8500/-ACII-_Screenshots_-_2.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed: Brotherhood', '2010-11-16', 'The third installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/2/2a/Assassins_Creed_brotherhood_cover.jpg/revision/latest/scale-to-width-down/700?cb=20210519124648', 'https://www.pcgameshardware.de/screenshots/medium/2010/11/Assassins_Creed_Brotherhood_Test_08.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed: Revelations', '2011-11-15', 'The fourth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/5/51/ACR_Boxart.jpg/revision/latest/scale-to-width-down/700?cb=20110703195808', 'http://www.digicpictures.com//images/upload/ACR_1310x560_05.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed III', '2012-10-30', 'The fifth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/e/ec/Assassin%27s_Creed_III_Cover.jpg/revision/latest/scale-to-width-down/700?cb=20120812182759', 'https://www.dsogaming.com/wp-content/uploads/2016/11/Assassins-Creed-3-feature.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed IV: Black Flag', '2013-10-29', 'The sixth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/6/6d/Assassin%27s_Creed_IV_Black_Flag.jpg/revision/latest/scale-to-width-down/699?cb=20130904164224', 'https://dailygame.at/wp-content/uploads/2019/09/assa-blackflag-765x470.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Rogue', '2014-11-11', 'The seventh installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/e/e5/Assassin%27s_Creed_Rogue_-_Cover_Art.jpeg/revision/latest/scale-to-width-down/700?cb=20140809155555', 'https://www.pcgames.de/screenshots/original/2014/08/AC_Rogue__4_-pc-games_b2teaser_169.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Unity', '2014-11-11', 'The eighth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/0/0b/Assassin%27s_Creed_Unity_Cover.jpg/revision/latest/scale-to-width-down/699?cb=20140610082722', 'https://i.pinimg.com/originals/66/05/e6/6605e6fc95cf6ff042c53ca5f0408320.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Syndicate', '2015-10-23', 'The ninth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/c/c2/ACS_Box_art_icon.jpg/revision/latest/scale-to-width-down/700?cb=20150512162954', 'https://games-mag.de/wp-content/uploads/2015/10/assassins-creed-syndicate-cinematic-trailer-new-female-assassin-and-demo-e3-2015-social.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Origins', '2017-10-27', 'The tenth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/2/2f/ACOrigins_cover.jpg/revision/latest/scale-to-width-down/700?cb=20200127122455', 'https://c4.wallpaperflare.com/wallpaper/173/586/516/assassin-s-creed-assassin-s-creed-origins-bayek-of-siwa-wallpaper-preview.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Odyssey', '2018-10-05', 'The eleventh installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/f/f5/Assassin%27s_Creed_Odyssey.jpg/revision/latest/scale-to-width-down/700?cb=20210519141716', 'https://i.ytimg.com/vi/hCHvx-I8A0w/maxresdefault.jpg');
INSERT INTO games VALUES (DEFAULT, 'Assassin''s Creed Valhalla', '2020-11-10', 'The twelfth installment in the Assassin''s Creed franchise.', 'https://static.wikia.nocookie.net/assassinscreed/images/6/65/AC_Valhalla_cover.jpg/revision/latest/scale-to-width-down/666?cb=20200430195147', 'http://digicpictures.com///images/upload/Raven_cover_00003321.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 1, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_screenshot005.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 1, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_screenshot001.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 1, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_screenshot009.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 1, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_screenshot004.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 1, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_screenshot020.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 2, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac2_image_53.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 2, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac2_image23.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 2, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac2_image22.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 2, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac2_s_014.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 2, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac2_image27.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 3, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acb_sp_s_01_rome_pantheonandhorses.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 3, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acb_sp_s_05_guncounterkill.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 3, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acb_sp_s_26_parachute.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 3, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acb_dlc4_sp_02_deliziadibelriguardo.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 3, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acb_sp_s_27_putting_the_borgia_tower_on_fire.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 4, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acr_sp_sc_04_hookblade_hanging.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 4, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acr_sp_sc_02_hookblade_zipline.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 4, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acr_sp_sc_17_ezio_combatinatlasvillage.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 4, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acr_sp_sc_15_ezio_settingthebomb.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 4, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acr_sp_sc_11_burningship.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 5, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366964075_120815_10am_ac3_s_sp_sony_benedictarnold_55_gamescom.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 5, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366964058_120604_04pm_ac3_sc_sp_28_sd_boston_musket.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 5, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366964064_120604_07pm_ac3_sc_sp_31_sd_naval_warfare.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 5, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366964056_120604_04pm_ac3_sc_sp_21_frontier_treerunning.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 5, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366964158_ac3_dlc_sp_15_frontier_ratonwolfleader2_online.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 6, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366234716_ac4bf_sc_sp_04_boardingassassination.jpg.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 6, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1372241824_acivbf_screenshotsp_e3_caribbeansea_leapoffaith_130610_4h15pmpt.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 6, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1369386341_acivbf_screenshot_havana_rushtoassassinate.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 6, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366234715_ac4bf_sc_sp_01_iiconicpose_edward.jpg.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 6, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/1366234717_ac4bf_sc_sp_07_junglefreerunning.jpg.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 7, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_rogue_screenshot_005.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 7, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_rogue_screenshot_004.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 7, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_rogue_screenshot_014.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 7, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_rogue_screenshot_003.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 7, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_rogue_screenshot_001.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 8, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_unity_screenshots_009.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 8, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_unity_screenshots_007.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 8, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_unity_screenshots_015.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 8, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_unity_screenshots_002.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 8, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/assassins_creed_unity_screenshots_010.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 9, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acs_screen_hat_gangleader_wm_20150512_1830cet.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 9, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acs_screen_combat-cane_wm_e3_150615_4pmpt_1434307896.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 9, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acs_screen_carriagegunfight_wm_e3_150615_4pmpt_1434307892.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 9, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acs_screen_bracer_wm_20150512_1830cet.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 9, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acs_screen_environment-bigben_wm_e3_150615_4pmpt_1434307898.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 10, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac_media_screen-pyramidslide_ncsa.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 10, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac_media_screen-sphinxday_ncsa.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 10, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac_media_screen-rangedcombat_ncsa.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 10, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac_media_screen-bayekshield_ncsa.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 10, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/ac_media_screen-bayekdesert_ncsa.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 11, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acodyssey_fallengodstatue.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 11, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acodyssey_battlefieldshieldbreak.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 11, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acodyssey_cleaving.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 11, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acodyssey_jumpattack.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 11, 'https://www.assassinscreed.de/sites/www.assassinscreed.de/files/acodyssey_stealthassassination.jpg');

INSERT INTO screenshot VALUES (DEFAULT, 12, 'https://images.cgames.de/images/gamestar/287/assassins-creed-valhalla_6099042.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 12, 'https://images.cgames.de/images/gamestar/287/assassins-creed-valhalla_6099043.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 12, 'https://images.cgames.de/images/gamestar/279/assassins-creed-valhalla_6099044.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 12, 'https://images.cgames.de/images/gamestar/279/assassins-creed-valhalla-screenshots_6115884.jpg');
INSERT INTO screenshot VALUES (DEFAULT, 12, 'https://images.cgames.de/images/gamestar/279/assassins-creed-valhalla-screenshots_6115880.jpg');

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

INSERT INTO rb_user VALUES (DEFAULT, 'LEGION', 'niklas.eckert.1807@gmail.com', 'password1234', 'https://nextcloud.niklas-eckert.de/index.php/s/yHS7oEHn6BMyL29/preview');

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

INSERT INTO review (rate, title, review_date, review_text, user_id, game_id) SELECT 4, 'Title1', '2022-03-09', 'Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series. Second best in the series.', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed: Brotherhood';
INSERT INTO review (rate, title, review_date, review_text, user_id, game_id) SELECT 5, 'Title2', '2022-03-10', 'Best in the series.', u.id, g.id FROM rb_user u, games g WHERE name = 'LEGION' AND title = 'Assassin''s Creed IV: Black Flag';

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
INSERT INTO list (name, description, user_id) SELECT 'AC Female', 'All AC titles with female protagonists.', u.id FROM rb_user u WHERE name = 'LEGION';

INSERT INTO list_games (list_id, game_id) SELECT l.id, g.id FROM list l, games g WHERE l.name = 'Ezio Trilogy' AND g.title = 'Assassin''s Creed II';
INSERT INTO list_games (list_id, game_id) SELECT l.id, g.id FROM list l, games g WHERE l.name = 'Ezio Trilogy' AND g.title = 'Assassin''s Creed: Brotherhood';
INSERT INTO list_games (list_id, game_id) SELECT l.id, g.id FROM list l, games g WHERE l.name = 'Ezio Trilogy' AND g.title = 'Assassin''s Creed: Revelations';

INSERT INTO list_games (list_id, game_id) SELECT l.id, g.id FROM list l, games g WHERE l.name = 'AC Female' AND g.title = 'Assassin''s Creed Syndicate';
INSERT INTO list_games (list_id, game_id) SELECT l.id, g.id FROM list l, games g WHERE l.name = 'AC Female' AND g.title = 'Assassin''s Creed Odyssey';
INSERT INTO list_games (list_id, game_id) SELECT l.id, g.id FROM list l, games g WHERE l.name = 'AC Female' AND g.title = 'Assassin''s Creed Valhalla';