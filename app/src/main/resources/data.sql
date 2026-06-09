-- Inserimento skill studenti
INSERT INTO STUDENT_SKILL (name, rarity, damage, description, skill_type, preparation_required) VALUES('Appunti Incantati', 'COMMON', 12, 'Un attacco base in cui lo studente evoca frammenti di appunti scritti a mano che si materializzano come energia grezza e colpiscono il nemico.', 'ATTACK', 5);
INSERT INTO STUDENT_SKILL (name, rarity, damage, description, skill_type, preparation_required) VALUES('Sigillo del Ragionamento', 'RARE', 28,'Lo studente traccia un simbolo magico fatto di logica pura e deduzione. Questo sigillo si imprime sul bersaglio e ne destabilizza la struttura mentale.','ATTACK', 15);
INSERT INTO STUDENT_SKILL (name, rarity, damage, description, skill_type, preparation_required) VALUES ('Meditazione del Conoscere Profondo', 'EPIC', 52, 'Una tecnica avanzata in cui lo studente entra in uno stato di concentrazione totale. L energia mentale viene convertita in potere offensivo.', 'ATTACK', 30);
INSERT INTO STUDENT_SKILL (name, rarity, damage, description, skill_type, preparation_required) VALUES ('Apoteosi della Comprensione Totale', 'LEGENDARY', 95, 'Lo studente raggiunge un livello di comprensione assoluta della materia. La realtà stessa sembra piegarsi alla sua conoscenza.', 'ATTACK', 50);

-- Inserimento skill boss
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Richiamo alla Lavagna', 'COMMON', 8, 'Domanda improvvisa sotto pressione, causa danni leggeri e destabilizzazione mentale.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Annotazione Correttiva', 'COMMON', 6, 'Corregge gli errori in tempo reale trasformando ogni imprecisione in pressione costante.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Interrogazione Minore', 'COMMON', 10, 'Domanda semplice ma insidiosa che infligge pressione mentale e danno diretto.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Domanda a Incrocio Logico', 'RARE', 22, 'Collega più argomenti sotto stress, infligge danno medio e riduce la concentrazione.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Analisi Impietosa', 'RARE', 25, 'Espone le debolezze dello studente pubblicamente, riduce preparazione e infligge danno medio.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Vortice di Domande Mirate', 'RARE', 20, 'Sequenza rapida di domande precise che riduce concentrazione e infligge danni medi.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Esame di Resistenza Cognitiva', 'EPIC', 50, 'Lunga sequenza di domande complesse che consuma energie mentali e riduce concentrazione.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Risonanza del Sapere', 'EPIC', 45, 'Il boss assorbe l energia mentale dispersa dello studente e recupera HP moderati.', 'HEALING');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Esame del Sigillo Temporale', 'EPIC', 55, 'Altera la percezione del tempo, fa perdere preparazione accumulata e infligge danni significativi.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Giudizio Saggio', 'LEGENDARY', 90, 'La commissione infligge danni enormi riducendo concentrazione e preparazione simultaneamente.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Verdetto Finale', 'LEGENDARY', 100, 'La commissione giudica lo studente infliggendo danni elevati e alterando la stabilità mentale.', 'ATTACK');
INSERT INTO BOSS_SKILL (name, rarity, damage, description, skill_type) VALUES ('Privilegio del Docente', 'LEGENDARY', 80, 'Il professore converte la conoscenza accumulata in rigenerazione pura recuperando grandi quantità di HP.', 'HEALING');

-- Inserimento nemici
INSERT INTO ENEMY (name, life, max_life, difficult) VALUES ('Assistente del Corso', 60, 60, 'EASY');
INSERT INTO ENEMY (name, life, max_life, difficult) VALUES ('Professore Ordinario', 120, 120, 'MEDIUM');
INSERT INTO ENEMY (name, life, max_life, difficult) VALUES ('Commissione d Esame', 200, 200, 'HARD');

-- Inserimento item
INSERT INTO ITEM (name, type, price) VALUES ('Caffe', 'drink', 10);
INSERT INTO ITEM (name, type, price) VALUES ('Energy Drink', 'drink', 20);
INSERT INTO ITEM (name, type, price) VALUES ('Merendina', 'snack', 10);
INSERT INTO ITEM (name, type, price) VALUES ('Panino', 'snack', 25);
INSERT INTO ITEM (name, type, price) VALUES ('Appunti Fotocopiati', 'book', 8);
INSERT INTO ITEM (name, type, price) VALUES ('Dispensa del Prof', 'book', 15);
INSERT INTO ITEM (name, type, price) VALUES ('Manuale Base', 'book', 28);
INSERT INTO ITEM (name, type, price) VALUES ('Appunti del Secchione', 'book', 35);
INSERT INTO ITEM (name, type, price) VALUES ('Trattato Avanzato', 'book', 58);
INSERT INTO ITEM (name, type, price) VALUES ('Tesi di Dottorato', 'book', 75);
INSERT INTO ITEM (name, type, price) VALUES ('Codex Universitatis', 'book', 120);
INSERT INTO ITEM (name, type, price) VALUES ('Il Libro Proibito della Sessione', 'book', 160);

-- Inserimento drink
INSERT INTO DRINK (id, increase_concentration) SELECT id, 15 FROM ITEM WHERE name = 'Caffe';
INSERT INTO DRINK (id, increase_concentration) SELECT id, 30 FROM ITEM WHERE name = 'Energy Drink';

-- Inserimento snack
INSERT INTO SNACK (id, increase_life) SELECT id, 20 FROM ITEM WHERE name = 'Merendina';
INSERT INTO SNACK (id, increase_life) SELECT id, 40 FROM ITEM WHERE name = 'Panino';

-- Inserimento libri rarità
INSERT INTO BOOK (id, rarity) SELECT id, 'COMMON' FROM ITEM WHERE name = 'Appunti Fotocopiati';
INSERT INTO BOOK (id, rarity) SELECT id, 'COMMON' FROM ITEM WHERE name = 'Dispensa del Prof';
INSERT INTO BOOK (id, rarity) SELECT id, 'RARE' FROM ITEM WHERE name = 'Manuale Base';
INSERT INTO BOOK (id, rarity) SELECT id, 'RARE' FROM ITEM WHERE name = 'Appunti del Secchione';
INSERT INTO BOOK (id, rarity) SELECT id, 'EPIC' FROM ITEM WHERE name = 'Trattato Avanzato';
INSERT INTO BOOK (id, rarity) SELECT id, 'EPIC' FROM ITEM WHERE name = 'Tesi di Dottorato';
INSERT INTO BOOK (id, rarity) SELECT id, 'LEGENDARY' FROM ITEM WHERE name = 'Codex Universitatis';
INSERT INTO BOOK (id, rarity) SELECT id, 'LEGENDARY' FROM ITEM WHERE name = 'Il Libro Proibito della Sessione';

-- Inserimento stanze
INSERT INTO ROOM (name, description) VALUES ('Atrio', 'Atrio del palazzo universitario');
INSERT INTO ROOM (name, description) VALUES ('Area Ristoro', 'Area con distributori automatici');
INSERT INTO ROOM (name, description) VALUES ('Biblioteca', 'Biblioteca universitaria');
INSERT INTO ROOM (name, description) VALUES ('Aula 1', 'Aula didattica');
INSERT INTO ROOM (name, description) VALUES ('Aula 2', 'Aula didattica');
INSERT INTO ROOM (name, description) VALUES ('Sala Professori', 'Sala riservata ai professori');

-- Inserimento lootable room
INSERT INTO LOOTABLE_ROOM (id, looted) SELECT id, false FROM ROOM WHERE name = 'Aula 1';
INSERT INTO LOOTABLE_ROOM (id, looted) SELECT id, false FROM ROOM WHERE name = 'Aula 2';

-- Inserimento shop room
INSERT INTO SHOP_ROOM (id, dispenser_name) SELECT id, 'SnackDrink Dispenser' FROM ROOM WHERE name = 'Area Ristoro';
INSERT INTO SHOP_ROOM (id, dispenser_name) SELECT id, 'Book Dispenser' FROM ROOM WHERE name = 'Biblioteca';

-- Inserimento shop items
INSERT INTO SHOP_ITEM (shop_room_id, item_id, quantity) SELECT sr.id, i.id, 5 FROM SHOP_ROOM sr, ITEM i WHERE sr.dispenser_name = 'SnackDrink Dispenser' AND i.name IN ('Caffe', 'Energy Drink', 'Merendina', 'Panino');
INSERT INTO SHOP_ITEM (shop_room_id, item_id, quantity) SELECT sr.id, i.id, 3 FROM SHOP_ROOM sr, ITEM i WHERE sr.dispenser_name = 'Book Dispenser' AND i.name IN ('Appunti Fotocopiati', 'Dispensa del Prof', 'Manuale Base', 'Appunti del Secchione', 'Trattato Avanzato', 'Tesi di Dottorato', 'Codex Universitatis', 'Il Libro Proibito della Sessione');

-- Collegamenti tra stanze
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Atrio' AND r2.name = 'Area Ristoro';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Atrio' AND r2.name = 'Biblioteca';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Atrio' AND r2.name = 'Aula 1';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Atrio' AND r2.name = 'Aula 2';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Atrio' AND r2.name = 'Sala Professori';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Area Ristoro' AND r2.name = 'Atrio';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Area Ristoro' AND r2.name = 'Biblioteca';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Area Ristoro' AND r2.name = 'Sala Professori';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Biblioteca' AND r2.name = 'Atrio';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Biblioteca' AND r2.name = 'Area Ristoro';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Biblioteca' AND r2.name = 'Sala Professori';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Aula 1' AND r2.name = 'Atrio';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Aula 2' AND r2.name = 'Atrio';
INSERT INTO ROOM_EXIT (room_id, exit_id) SELECT r1.id, r2.id FROM ROOM r1, ROOM r2 WHERE r1.name = 'Sala Professori' AND r2.name = 'Atrio';