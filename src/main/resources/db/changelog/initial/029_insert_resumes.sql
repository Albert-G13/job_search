INSERT INTO resumes (name, salary, is_active, applicant_id, category_id, created_date, update_time)
VALUES
    ('Middle Java Developer', 160000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT') , now(), now()),
    ('Junior Frontend Developer', 80000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'Software Development'), now(), now()),
    ('Senior Python Developer', 220000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Junior QA Engineer', 60000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Product Designer', 110000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'Software Development'), now(), now()),
    ('IT Recruiter', 75000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'Web Development'), now(), now()),
    ('DevOps Specialist', 190000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Business Analyst', 95000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'Web Development'), now(), now()),
    ('Backend Developer (Node.js)', 130000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'kostya.bro@mail.ru'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Data Scientist', 180000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Android Developer', 150000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Content Manager', 55000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'Software Development'), now(), now()),
    ('Technical Support', 45000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now()),
    ('Marketing Specialist', 85000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'Web Development'), now(), now()),
    ('Security Engineer', 170000, true, (select ID from USERS_TABLE where USERS_TABLE.EMAIL = 'qwerty@qwerty.qwerty'), (select id from CATEGORIES where CATEGORIES.NAME = 'IT'), now(), now());