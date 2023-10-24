-- Ambiente de Lab com senha descriptografada do admin: 12345@Letmein
INSERT INTO usuarios (login,senha,is_account_locked,credentials_expiration,is_enabled,account_expiration, role)
VALUES ('admin','$2a$12$I1LmXUXK9PkDyd0Hv9Tqr.LD3wx9HK3MwgLP6g7jbkT2KIx2OM1Ju', FALSE, null, TRUE, null, 2);