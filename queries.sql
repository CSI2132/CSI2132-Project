-- List of dentists in each branch
SELECT dentist.first_name, dentist.last_name, dentist.speciality, branch.branch_id, branch.city, branch.branch_address
FROM Dentist
LEFT JOIN Branch ON Dentist.branch_id = Branch.branch_id;

-- Add new Patients
INSERT INTO Patient(username, patient_password, patient_address, first_name, last_name, gender, insurance, SSN, email_address, date_of_birth) 
VALUES ('JohnDoe', 'doeJohnPswd', '123 Example Street', 'John', 'Doe', 'MALE', 'Allstate Insurance Inc.',12456789, john.doe@hotmail.com, '1999-01-31');

-- Check upcoming appointment with the dentist
SELECT a.appointment_date, a.start_time, a.end_time, a.assigned_room, a.appointment_type, dentist.first_name, dentist.last_name, hygienist.first_name, hygienist.last_name
FROM Appointment AS a
    LEFT JOIN Dentist ON appointment.dentist_user_id = Dentist.dentist_user_id
    LEFT JOIN Hygienist ON appointment.hygienist_user_id = Hygienist.hygienist_user_id
WHERE appointment_status = 'UPCOMING' AND appointment.start_time > CURRENT_TIME;

-- Set a new appointment
INSERT INTO Appointment(
        patient_user_id, dentist_user_id, hygienist_user_id
        , appointment_date, start_time, end_time, appointment_type
        , appointment_status, assigned_room
) VALUES (
    1,
    1,
    1,
    '2022-05-01',
    '10:00:00',
    '11:00:00',
    'SCALING',
    'ACTIVE',
    'Main room'
);

-- Add a new employee
-- todo: create employee table
INSERT INTO Employee(username, employee_password, first_name, last_name, employee_address, employee_role, SSN, salary, branch_id)
VALUES ('bobdole123', '123456789', 'Bob', 'Dole', '456 Street Example', '?', '123456789', 100000, 01);


-- Check the types of procedures available
SELECT procedure_type_name, procedure_type_description 
FROM ProcedureType;






























