CREATE TABLE `study`.`music` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


insert into study.music (name) values ( 'Bohemian Rhapsody'),
       ( 'Stairway to Heaven'),
       ( 'Imagine'),
       ( 'Sweet Child O Mine'),
       ( 'Hey Jude'),
       ( 'Hotel California'),
       ('Billie Jean'),
       ( 'Wonderwall'),
       ('Smells Like Teen Spirit'),
       ( 'Let It Be'),
       ( 'I Want It All'),
       ( 'November Rain'),
       ( 'Losing My Religion'),
       ( 'One'),
       ( 'With or Without You'),
       ( 'Sweet Caroline'),
       ( 'Yesterday'),
       ( 'Dont Stop Believin'),
       ( 'Crazy Train'),
       ( 'Always')) as new_data
where not exists (select 1 from study.music);