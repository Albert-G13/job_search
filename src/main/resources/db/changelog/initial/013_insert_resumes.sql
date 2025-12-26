INSERT INTO resumes (name, salary, is_active, created_date, update_time, applicant_id, category_id)
VALUES ('Middle developer', 95000, TRUE, '2025-12-17 08:35:00', '2025-12-17 08:50:00', select id from USERS_TABLE where USERS_TABLE.EMAIL = 'kostya.bro@mail.ru', select id from CATEGORIES where CATEGORIES.NAME = 'IT'),
       ('Middle developer', 100000, TRUE, '2025-12-18 20:35:00', '2025-12-18 21:50:00', select id from USERS_TABLE where USERS_TABLE.EMAIL = 'kostya.bro@mail.ru', select id from CATEGORIES where CATEGORIES.NAME = 'Software Development');
