
CREATE TABLE IF NOT EXISTS patient
(
    id              UUID                PRIMARY KEY,
    name            VARCHAR(255)        NOT NULL,
    surname         VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    address         VARCHAR(255)        NOT NULL,
    dob             DATE                NOT NULL,
    mobile_number    VARCHAR(255)        NOT NULL,
    gender          VARCHAR(255)          NOT NULL,
    registered_date    DATE        NOT NULL,
    created_at  TIMESTAMP           NOT NULL,
    updated_at      TIMESTAMP           NOT NULL
    );



-- INSERT INTO patient (id, address, created_at, dob, email, gender, mobile_number, name, surname, updated_at, registered_date)
-- VALUES
--     (gen_random_uuid(), '1234 Elm Street, Springfield', CURRENT_TIMESTAMP, '1985-01-15', 'john.doe@example.com', 'Male', '123-456-7890', 'John', 'Doe', CURRENT_TIMESTAMP, '2025-01-01'),
--     (gen_random_uuid(), '5678 Oak Avenue, Springfield', CURRENT_TIMESTAMP, '1990-07-22', 'jane.smith@example.com', 'Female', '987-654-3210', 'Jane', 'Smith', CURRENT_TIMESTAMP,'2025-02-12'),
--     (gen_random_uuid(), '9101 Pine Road, Springfield', CURRENT_TIMESTAMP, '1995-09-11', 'alice.jones@example.com', 'Female', '555-123-4567', 'Alice', 'Jones', CURRENT_TIMESTAMP,'2025-01-12'),
--     (gen_random_uuid(), '1122 Maple Blvd, Springfield', CURRENT_TIMESTAMP, '1980-04-30', 'bob.white@example.com', 'Male', '444-789-1011', 'Bob', 'White', CURRENT_TIMESTAMP,'2025-04-20'),
--     (gen_random_uuid(), '1314 Birch Lane, Springfield', CURRENT_TIMESTAMP, '1992-12-05', 'charlie.brown@example.com', 'Male', '333-555-7777', 'Charlie', 'Brown', CURRENT_TIMESTAMP,'2025-09-01'),
--     (gen_random_uuid(), '1516 Cedar Street, Springfield', CURRENT_TIMESTAMP, '1983-03-17', 'diana.lee@example.com', 'Female', '222-444-8888', 'Diana', 'Lee', CURRENT_TIMESTAMP,'2025-03-23'),
--     (gen_random_uuid(), '1718 Redwood Drive, Springfield', CURRENT_TIMESTAMP, '1998-10-25', 'emma.wilson@example.com', 'Female', '111-222-3333', 'Emma', 'Wilson', CURRENT_TIMESTAMP,'2025-05-13'),
--     (gen_random_uuid(), '1920 Fir Avenue, Springfield', CURRENT_TIMESTAMP, '1987-05-02', 'frank.martin@example.com', 'Male', '666-777-9999', 'Frank', 'Martin', CURRENT_TIMESTAMP,'2025-01-16'),
--     (gen_random_uuid(), '2022 Sequoia Road, Springfield', CURRENT_TIMESTAMP, '2000-08-18', 'grace.james@example.com', 'Female', '555-888-7777', 'Grace', 'James', CURRENT_TIMESTAMP,'2025-05-22'),
--     (gen_random_uuid(), '2123 Redwood Lane, Springfield', CURRENT_TIMESTAMP, '1994-11-12', 'henry.clark@example.com', 'Male', '444-555-6666', 'Henry', 'Clark', CURRENT_TIMESTAMP,'2025-05-10');
