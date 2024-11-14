--CREATE TABLE `study`.`music` (
--  `id` INT NOT NULL,
--  `name` VARCHAR(64) NOT NULL,
--  PRIMARY KEY (`id`));

insert into study.music (id, name) values (1, 'Bohemian Rhapsody'),
       (2, 'Stairway to Heaven'),
       (3, 'Imagine'),
       (4, 'Sweet Child O Mine'),
       (5, 'Hey Jude'),
       (6, 'Hotel California'),
       (7, 'Billie Jean'),
       (8, 'Wonderwall'),
       (9, 'Smells Like Teen Spirit'),
       (10, 'Let It Be'),
       (11, 'I Want It All'),
       (12, 'November Rain'),
       (13, 'Losing My Religion'),
       (14, 'One'),
       (15, 'With or Without You'),
       (16, 'Sweet Caroline'),
       (17, 'Yesterday'),
       (18, 'Dont Stop Believin'),
       (19, 'Crazy Train'),
       (20, 'Always')) as new_data
where not exists (select 1 from study.music);