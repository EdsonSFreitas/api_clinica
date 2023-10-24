-- Adicione a coluna como NULL
ALTER TABLE medicos ADD ativo tinyint NOT NULL;

-- Atualize os valores existentes para a coluna
UPDATE medicos SET ativo = 1;

-- Altere a coluna para NOT NULL
-- ALTER TABLE medicos SET ativo tinyint NOT NULL;