CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    surname TEXT,
    age INT,
    email TEXT UNIQUE,
    password TEXT,
    phone_number VARCHAR(55) UNIQUE,
    avatar TEXT,
    account_type TEXT
);
CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name TEXT,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES categories (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS contact_types (
    id SERIAL PRIMARY KEY,
    type TEXT
);
CREATE TABLE IF NOT EXISTS vacancies (
    id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT,
    salary REAL,
    exp_from INT,
    exp_to INT,
    is_active BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    category_id INT,
    author_id INT,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
    );
CREATE TABLE IF NOT EXISTS resumes (
    id SERIAL PRIMARY KEY,
    name TEXT,
    salary REAL,
    is_active BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMP,
    updated_date TIMESTAMP,
    applicant_id INT,
    category_id INT,
    FOREIGN KEY (applicant_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS responded_applicants (
    id SERIAL PRIMARY KEY,
    resume_id INT,
    vacancy_id INT,
    confirmation BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (resume_id) REFERENCES resumes (id) ON DELETE CASCADE,
    FOREIGN KEY (vacancy_id) REFERENCES vacancies (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS messages (
    id SERIAL PRIMARY KEY,
    responded_applicants_id INT,
    content TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (responded_applicants_id) REFERENCES responded_applicants (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS contacts_info (
    id SERIAL PRIMARY KEY,
    contact_value TEXT,
    type_id INT,
    resume_id INT,
    FOREIGN KEY (type_id) REFERENCES contact_types (id) ON DELETE CASCADE,
    FOREIGN KEY (resume_id) REFERENCES resumes (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS education_info (
    id SERIAL PRIMARY KEY,
    institution TEXT,
    program TEXT,
    start_date DATE,
    end_date DATE,
    degree TEXT,
    resume_id INT,
    FOREIGN KEY (resume_id) REFERENCES resumes (id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS work_experience_info (
    id SERIAL PRIMARY KEY,
    years INT,
    company_name TEXT,
    position TEXT,
    responsibilities TEXT,
    resume_id INT,
    FOREIGN KEY (resume_id) REFERENCES resumes (id) ON DELETE CASCADE
);

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES ('Konstantin', 'Brownski', 30, 'kostya.bro@mail.ru', 'kostya190', '+996555334466', null, 'applicant'),
       ('Mikhail', 'Drozdov', 49, 'misha1980@mail.ru', 'mih80a', '+996506999999', null, 'employer');
INSERT INTO categories (name, parent_id)
VALUES ('IT', null),
       ('Software Development', 1),
       ('Web Development', 2);
INSERT INTO resumes (name, salary, is_active, created_date, updated_date, applicant_id, category_id)
VALUES ('Middle developer', 95000, TRUE, '2025-12-17 08:35:00', '2025-12-17 08:50:00', 1, 1),
       ('Middle developer', 100000, TRUE, '2025-12-18 20:35:00', '2025-12-18 21:50:00', 1, 2);
INSERT INTO vacancies (name, description, salary, exp_from, exp_to, is_active, created_date, updated_date, category_id, author_id)
VALUES ('Java Developer', 'Java Developer with 3 years experience', 100000, 3, 5, TRUE, '2025-12-18 12:00:00', '2025-12-18 13:00:00', 1, 2),
       ('Python Developer', 'Python Developer with 2 years experience', 80000, 2, 4, TRUE, '2025-12-18 11:00:00', '2025-12-18 15:30:00', 2,2);
INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, TRUE),
       (2, 2, FALSE);
INSERT INTO contact_types (type)
VALUES ('Phone'),
       ('Email'),
       ('Telegram');
INSERT INTO contacts_info (contact_value, type_id, resume_id)
VALUES ('+996555334466', 1, 1),
       ('kostya.bro@mail.ru', 2, 1),
       ('@kostyabro', 3, 1);
INSERT INTO education_info (institution, program, start_date, end_date, degree, resume_id)
VALUES ('IT University', 'Software Development', '2018-09-01', '2022-06-30', 'Bachelor', 1),
       ('IT University', 'Web Development', '2022-07-01', '2025-06-30', 'Master', 2);
INSERT INTO work_experience_info (years, company_name, position, responsibilities, resume_id)
VALUES (3, 'IT Company', 'Java Developer', 'Development of Java applications', 1),
       (2, 'IT Company', 'Python Developer', 'Development of Python applications', 2);
INSERT INTO messages (responded_applicants_id, content, timestamp)
VALUES (1, 'Здравствуйте! Готов обсудить вакансию.', '2025-12-18 12:30:00'),
       (2, 'Добрый день, отправил резюме.', '2025-12-18 13:30:00');
