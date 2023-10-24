ALTER TABLE `pacientes` ADD `ativo` tinyint NOT NULL;
update pacientes set ativo = 1;