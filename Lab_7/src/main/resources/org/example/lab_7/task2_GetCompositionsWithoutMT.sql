select name
from music
where lower (name) not like "%m%%" and lower (name) not like "%t%";