-- Enums --
CREATE TYPE seniority_enum AS ENUM ('INTERN', 'ENTRY-LEVEL', 'MID-LEVEL', 'SENIOR', 'LEAD');
CREATE TYPE procedure_type_name_enum AS ENUM ('SCALING', 'FLUORIDE', 'REMOVAL', 'FILLINGS', 'ROOT_CANAL', 'WHITENING', 'ENDODONTIC', 'ORTHODONTIC', 'PERIODONTIC', 'ENDODONTIC_AND_PERIODONTIC', 'OTHER');

-- Tables --
CREATE TABLE Patient (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    patient_password TEXT NOT NULL,
    patient_address TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    gender TEXT NOT NULL CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
    insurance TEXT NOT NULL,
    SSN TEXT UNIQUE NOT NULL CONSTRAINT valid_SSN CHECK (SSN ~ '^(\d{9})$'),
    email_address TEXT UNIQUE NOT NULL CONSTRAINT valid_email CHECK (email_address ~ '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    date_of_birth DATE NOT NULL
);

CREATE TABLE Branch (
    branch_id SERIAL PRIMARY KEY,
    city TEXT NOT NULL,
    branch_address TEXT NOT NULL
);

CREATE TABLE BranchManager (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    branch_manager_password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    branch_manager_address TEXT NOT NULL,
    branch_manager_role TEXT NOT NULL,
    SSN TEXT UNIQUE NOT NULL CONSTRAINT valid_SSN CHECK (SSN ~ '^(\d{9})$'),
    salary NUMERIC(16, 2) NOT NULL CONSTRAINT valid_salary CHECK (salary >= 0),
    branch_id SERIAL NOT NULL REFERENCES Branch(branch_id)
);

CREATE TABLE Receptionist (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    receptionist_password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    receptionist_address TEXT NOT NULL,
    receptionist_role TEXT NOT NULL, -- [TODO: Remove? What is its purpose? Seen in other Employee Types as well]
    SSN TEXT UNIQUE NOT NULL CONSTRAINT valid_SSN CHECK (SSN ~ '^(\d{9})$'),
    salary NUMERIC(16, 2) NOT NULL CONSTRAINT valid_salary CHECK (salary >= 0),
    branch_id SERIAL NOT NULL REFERENCES Branch(branch_id)
);

CREATE TABLE Dentist (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    dentist_password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    dentist_address TEXT NOT NULL,
    dentist_role TEXT NOT NULL,
    SSN TEXT UNIQUE NOT NULL CONSTRAINT valid_SSN CHECK (SSN ~ '^(\d{9})$'),
    salary NUMERIC(16, 2) NOT NULL CONSTRAINT valid_salary CHECK (salary >= 0),
    branch_id SERIAL NOT NULL REFERENCES Branch(branch_id),
    speciality procedure_type_name_enum NOT NULL
);

CREATE TABLE Hygienist (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    hygienist_password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    hygienist_address TEXT NOT NULL,
    hygienist_role TEXT NOT NULL,
    SSN TEXT UNIQUE NOT NULL CONSTRAINT valid_SSN CHECK (SSN ~ '^(\d{9})$'),
    salary NUMERIC(16, 2) NOT NULL CONSTRAINT valid_salary CHECK (salary >= 0),
    branch_id SERIAL NOT NULL REFERENCES Branch(branch_id),
    seniority seniority_enum NOT NULL
);

CREATE TABLE Appointment (
    appointment_id SERIAL PRIMARY KEY,
    patient_user_id INTEGER NOT NULL,
    dentist_user_id INTEGER NOT NULL,
    hygienist_user_id INTEGER NOT NULL,
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL, -- todo: Time stamp with or without timezone? Depends on frontend logic?]
    end_time TIME NOT NULL,
    appointment_type procedure_type_name_enum NOT NULL,
    appointment_status TEXT NOT NULL CHECK (appointment_status IN ('COMPLETE', 'ONGOING', 'CANCELLED')),
    assigned_room TEXT NOT NULL
);

CREATE TABLE Invoice (
    invoice_id SERIAL PRIMARY KEY,
    date_of_issue DATE NOT NULL,
    contact_email_address TEXT NOT NULL REFERENCES Patient(email_address),
    patient_charge NUMERIC NOT NULL CONSTRAINT valid_patient_charge CHECK (patient_charge >= 0),
    insurance_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_insurance_charge CHECK (insurance_charge >= 0),
    total_fee_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_total_fee_charge CHECK (total_fee_charge >= 0),
    discount REAL NOT NULL CONSTRAINT valid_discount CHECK (discount BETWEEN 0.0 AND 1.0),
    penalty_absent BOOLEAN NOT NULL, -- [TODO: Frontend logic to add $14 to patient's account. Fee code: 94303]
    patient_amount NUMERIC(16, 2) NOT NULL CONSTRAINT valid_patient_amount CHECK (patient_amount >= 0),
    insurance_amount NUMERIC(16, 2) NOT NULL CONSTRAINT valid_insurance_amount CHECK (insurance_amount >= 0),
    total_amount NUMERIC(16, 2) NOT NULL CONSTRAINT valid_total_amount CHECK (total_amount >= 0),
    payment_type TEXT NOT NULL CHECK (payment_type IN ('CASH', 'VISA', 'MASTERCARD', 'PAYPAL', 'OTHER')),
    patient_user_id SERIAL NOT NULL REFERENCES Patient(user_id),
    receptionist_user_id SERIAL NOT NULL REFERENCES Receptionist(user_id),
    -- paying_employee_user_id SERIAL NOT NULL REFERENCES Employee(user_id), -- [TODO: References ALL employee types. Possible solution: Created NEW relation table for it]
    appointment_id SERIAL NOT NULL REFERENCES Appointment(appointment_id)
);

CREATE TABLE Treatment (
    treatment_id SERIAL PRIMARY KEY,
    appointment_type procedure_type_name_enum NOT NULL,
    treatment_type TEXT NOT NULL,
    medication TEXT NOT NULL,
    symptoms TEXT NOT NULL,
    tooth TEXT NOT NULL,
    comments TEXT,
    appointment_id SERIAL NOT NULL REFERENCES Appointment(appointment_id),
    treatment_date DATE NOT NULL,
    treatment_description TEXT,
    tooth_involved TEXT NOT NULL,
    procedure_amount NUMERIC(16, 2) NOT NULL CONSTRAINT valid_procedure_amount CHECK (procedure_amount >= 0),
    patient_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_patient_charge CHECK (patient_charge >= 0),
    insurance_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_insurance_charge CHECK (insurance_charge >= 0),
    total_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_total_charge CHECK (total_charge >= 0.0)
);

-- todo: double check foreign keys and primary keys
CREATE TABLE Record (
    progress_notes TEXT,
    patient_user_id SERIAL NOT NULL REFERENCES Patient(user_id),
    treatment_id SERIAL NOT NULL REFERENCES Treatment(treatment_id),
    PRIMARY KEY (progress_notes, patient_user_id)
);

CREATE TABLE ProcedureType (
    procedure_id SERIAL PRIMARY KEY,
    procedure_type_name procedure_type_name_enum NOT NULL,
    procedure_type_description TEXT
);

CREATE TABLE ResponsiblePerson (
    user_id SERIAL,
    username TEXT NOT NULL,
    responsible_person_password TEXT NOT NULL,
    patient_user_id SERIAL NOT NULL REFERENCES Patient(user_id),
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    PRIMARY KEY (patient_user_id, first_name, last_name, user_id)
);

CREATE TABLE AppointmentProcedure (
    appointment_id SERIAL NOT NULL REFERENCES Appointment(appointment_id),
    procedure_id SERIAL NOT NULL REFERENCES ProcedureType(procedure_id),
    appointment_procedure_date DATE NOT NULL,
    appointment_procedure_description TEXT NOT NULL,
    tooth_involved TEXT NOT NULL,
    procedure_amount NUMERIC(16, 2) NOT NULL CONSTRAINT valid_procedure_amount CHECK (procedure_amount >= 0),
    patient_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_patient_charge CHECK (patient_charge >= 0),
    insurance_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_insurance_charge CHECK (insurance_charge >= 0),
    total_charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_total_charge CHECK (total_charge >= 0)
);

CREATE TABLE Review (
    patient_user_id SERIAL NOT NULL REFERENCES Patient(user_id),
    branch_id SERIAL REFERENCES Branch(branch_id),
    employee_professionalism_score INTEGER NOT NULL CONSTRAINT valid_employee_professionalism_score CHECK(employee_professionalism_score between 1 and 5),
    cleanliness_score SMALLINT NOT NULL CONSTRAINT valid_cleanliness_score CHECK(cleanliness_score between 1 and 5),
    rating_score INTEGER NOT NULL CONSTRAINT valid_rating_score CHECK (rating_score BETWEEN 1 AND 5),
    review_datetime TIMESTAMP NOT NULL,
    additional_comments TEXT,
    PRIMARY KEY (patient_user_id, branch_id, employee_professionalism_score, cleanliness_score, rating_score, review_datetime, additional_comments)
);

CREATE TABLE InsuranceClaim (
    treatment_type procedure_type_name_enum NOT NULL,
    invoice_id SERIAL NOT NULL REFERENCES Invoice(invoice_id),
    PRIMARY KEY(treatment_type, invoice_id)
);

CREATE TABLE FeeCharge (
    fee_code SMALLINT NOT NULL UNIQUE,
    charge NUMERIC(16, 2) NOT NULL CONSTRAINT valid_charge CHECK (charge >= 0),
    invoice_id SERIAL NOT NULL REFERENCES Invoice(invoice_id),
    procedure_id SERIAL NOT NULL REFERENCES ProcedureType(procedure_id),
    PRIMARY KEY (fee_code, charge, invoice_id)
);

CREATE TABLE ReceptionistAlters (
    patient_user_id SERIAL NOT NULL REFERENCES Patient(user_id),
    receptionist_user_id SERIAL NOT NULL REFERENCES Receptionist(user_id)
);

CREATE TABLE ReceptionistBooks (
    receptionist_user_id SERIAL NOT NULL REFERENCES Receptionist(user_id),
    appointment_id SERIAL NOT NULL REFERENCES Appointment(appointment_id)
);

CREATE TABLE EmployeeAccesses (
    user_id SERIAL PRIMARY KEY REFERENCES Receptionist(user_id),
    progress_notes TEXT NOT NULL,
    patient_user_id SERIAL NOT NULL,
    FOREIGN KEY (progress_notes, patient_user_id) REFERENCES Record(progress_notes, patient_user_id)
);

CREATE TABLE PhoneNumber (
    user_id SERIAL REFERENCES Patient(user_id),
    phone_number TEXT CONSTRAINT valid_phone_number CHECK (phone_number ~ '^(\d{3}-\d{3}-\d{4})$'),
    PRIMARY KEY(user_id, phone_number)
);
CREATE TABLE Languages (
    user_id SERIAL REFERENCES Receptionist(user_id),
    languages TEXT,
    PRIMARY KEY(user_id, languages)
);