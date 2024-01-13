insert into users (created_at, updated_at, id, email, password, user_role)
select '2023-12-22 20:34:35.291000', null, '3a36ee2e-52dc-40d3-b06b-3cc5e425e1e4', 'admin@adm.com',
        '$2a$10$i6TpXJ/ypJE8xB5nEX6Cu.Cif34wQwI1EYJ3uYVtsvTKq61r7zPbe', 'ADMIN'
where not exists(select * from users where email like 'admin@adm.com');