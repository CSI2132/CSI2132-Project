-- List of dentists in each branch
SELECT dentist.first_name, dentist.last_name, dentist.speciality, branch.branch_id, branch.city, branch.branch_address
FROM Dentist
LEFT JOIN Branch 
    ON Dentist.branch_id = Branch.branch_id
    -- WHERE Branch.branch_id = 1;
    ORDER BY branch_id;

-- Check upcoming appointment with the dentist
SELECT aa.appointment_date, aa.start_time, aa.end_time, aa.assigned_room, aa.appointment_type, 
	dentist.first_name AS dentist_first_name, dentist.last_name AS dentist_last_name, 
	hygienist.first_name AS hygienist_first_name, hygienist.last_name AS hygienist_last_name
FROM Appointment AS aa
    LEFT JOIN Dentist ON aa.dentist_user_id = Dentist.user_id
    LEFT JOIN Hygienist ON aa.hygienist_user_id = Hygienist.user_id
WHERE appointment_status = 'ACTIVE' AND aa.appointment_date > CURRENT_DATE
-- AND aa.dentist_user_id = 1;

-- Set a new appointment
INSERT INTO Appointment(
        patient_user_id, dentist_user_id, hygienist_user_id
        , appointment_date, start_time, end_time, appointment_type
        , appointment_status, assigned_room
) VALUES (
    118,
    110,
    117,
    '2022-05-01',
    '10:00:00',
    '11:00:00',
    'SCALING',
    'ACTIVE',
    'Main room'
);

-- Add a new user / employee / patient 
-- NOTE: user_id in Dentist, Receptionist, Hygienist, and BranchManager depend on user_id in Employee
-- NOTE: user_id in Employee and Patient depend on user_id in Users

-- Inserting a Patient
INSERT INTO Users(user_id, username, password) VALUES (9001, 'bob_dole', 'abc123');
INSERT INTO Patient(user_id, username, patient_password, patient_address, first_name, last_name, gender, insurance, SSN, email_address, date_of_birth) VALUES(
    9001,
    'bob_dole', 
    'abc123', 
    '123 Example Street', 
    'John', 
    'Doe', 
    'MALE', 
    'Allstate Insurance Inc.',
    123456789, 
    'john.doe@hotmail.com', 
    '1999-01-31'
);
SELECT * FROM patient WHERE user_id = 9001;

-- Inserting a Dentist
INSERT INTO Users (user_id, username, password) VALUES (18923,'Big-Brother', 'DC647EB65E6711E155375218212B3964');
INSERT INTO Employee(user_id, employee_role) VALUES (18923, 'DENTIST');
INSERT INTO Dentist(user_id, username, dentist_password, first_name, last_name, dentist_address, dentist_role, ssn, salary, branch_id, speciality) VALUES (
    18923,
    'Big-Brother',
    'DC647EB65E6711E155375218212B3964',
    'George',
    'Orwell',
    '1984 George Orwell Street',
    'DENTIST',
    987654331,
    120000,
    1,
    'PERIODONTIC'
);

-- Inserting a Receptionist
INSERT INTO Users (user_id, username, password) VALUES (9502, 'frank_green_7', 'BB93BE0A949768AC8994F35AB53899FE');
INSERT INTO Employee (user_id, employee_role) VALUES (9502, 'RECEPTIONIST');
INSERT INTO Receptionist(user_id, username, receptionist_password, first_name, last_name, receptionist_address, ssn, salary, branch_id) VALUES (
    9502,
    'frank_green_7',
    'BB93BE0A949768AC8994F35AB53899FE',
    'Frank',
    'Green',
    '61 Avenue Drive',
    984614332,
    220000,
    1
);

-- Inserting a Hygienist
INSERT INTO Users (user_id, username, password) VALUES (5001, 'mike_stern', '$2a$12$gpEYbZcIuMhSjI6yVCNQwejoOenveJSNT4Zyt81210mu2AgUdJDi2!3');
INSERT INTO Employee (user_id, employee_role) VALUES (5001, 'HYGIENIST');
INSERT INTO Hygienist(user_id, username, hygienist_password, first_name, last_name, hygienist_address, hygienist_role, ssn, salary, branch_id, seniority) VALUES (
    5001,
    'mike_stern',
    '$2a$12$gpEYbZcIuMhSjI6yVCNQwejoOenveJSNT4Zyt81210mu2AgUdJDi2!3',
    'Mike',
    'Stern',
    '3 Lane Street',
    'SUPERVISOR',
    984614333,
    520000,
    1,
    'LEAD'
);

-- Inserting a Branch Manager
INSERT INTO Users (user_id,username, password) VALUES (50999,'ya_boi', 'def54321');
INSERT INTO Employee (user_id, employee_role) VALUES (50999, 'BRANCHMANAGER');
INSERT INTO Branchmanager(user_id, username, branch_manager_password, first_name, last_name, branch_manager_address, branch_manager_role, ssn, salary, branch_id) VALUES (
    50999,
    'ya_boi',
    'def54321',
    'Ya',
    'Boi',
    '1 Lane Street',
    'BRANCH_MANAGER',
    984614334,
    620000,
    1
);

-- Check the types of procedures available
SELECT procedure_type_name, procedure_type_description 
FROM ProcedureType;
