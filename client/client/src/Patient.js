import { useEffect, useState } from "react";

function Patient(props) {
  const [medicalHistory, setMedicalHistory] = useState([]);
  const [upcomingAppointments, setUpcomingAppointments] = useState([]);
  
  useEffect(() => {
    const userId = localStorage.getItem("userId");

    fetch(`http://localhost:8080/appointment/getPatientRecord/${userId}`).then(async (result) => {
      if (result.ok) {
        setMedicalHistory(await result.json());
      }
    });

    fetch(`http://localhost:8080/appointment/getAppointmentByPatientId/${userId}`).then(async (result) => {
      if (result.ok) {
        setUpcomingAppointments(await result.json());
      }
    });
  }, []);

  return (
    <div>
      <div>
        <h2>
          Upcoming Appointments
        </h2>

        {getUpcomingAppointments(upcomingAppointments)}
      </div>

      <div>
        <h2>
          Medical History
        </h2>

        {getMedicalHistory(medicalHistory)}
      </div>
    </div>
  )
}

function getMedicalHistory(records) {

  const keys = [
    "appointment_type",
    "treatment_type",
    "medication",
    "symptoms",
    "tooth",
    "comments",
    "progress_notes",
    "treatment_date",
    "treatment_description",
    "appointment_procedure_description",
    "tooth_involved",
    "patient_charge"
  ];

  const names = [
    "Appointment Type",
    "Treatment Type",
    "Medication",
    "Symptoms",
    "Tooth",
    "Comments",
    "Progress Notes",
    "Treatment Date",
    "Treatment Description",
    "Appointment Procedure Description",
    "Tooth Involved",
    "Patient Charge"
  ];

  // use <summary>

  const result = [];
  console.log(records);
  for (const record of records) {
    result.push(
      <details className="border">
        <summary>
        
          {`Treatment at ${record.treatment_date}`}
        </summary>
        <div className="border">
          {(() => {
            const result = [];
            for (let i = 0; i < keys.length; i++) {
              result.push(
                <div>
                  <b>{names[i]}</b>: {record[keys[i]]}
                </div>
              )
            }
            return result;
          })()}
        </div>
      </details>
    );
  }

  return result;
}

function getUpcomingAppointments(appointments) {
  const keys = [
    "appointment_type",
    "assigned_room",
    "start_time",
    "end_time",
    "hygienist_first_name",
    "hygienist_last_name",
  ];

  const names = [
    "Appointment Type",
    "Assigned Room",
    "Start Time",
    "End Time",
    "Hygienist First Name",
    "Hygienist Last Name",
  ];

  // use <summary>
  
  const result = [];
  for (const appointment of appointments) {
    result.push(
      <details className="border">
        <summary>
          {`Appointment at ${appointment.appointment_date} with ${appointment.dentist_first_name} ${appointment.dentist_last_name}`}
        </summary>
        <div className="border">
          {(() => {
            const result1 = [];
            for (let i = 0; i < keys.length; i++) {
              result1.push(
                <div>
                  <b>{names[i]}</b>: {appointment[keys[i]]}
                </div>
              )
            }
            return result1;
          })()}
        </div>
      </details>
    );
  }

  return result;
}

export default Patient;