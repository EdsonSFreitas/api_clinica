ALTER TABLE `pacientes` ADD `ativo` tinyint;
update pacientes set ativo = 1;
alter table pacientes modify ativo tinyint not null