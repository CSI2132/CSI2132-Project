import { useEffect, useState } from "react";

function Patient(props) {
  const [medicalHistory, setMedicalHistory] = useState([]);
  useEffect(() => {
    fetch(`http://localhost:8080/appointment/getPatientRecord/${props.userId}`).then(async (result) => {
      if (result.ok) {
        setMedicalHistory(await result.json());
      }
    });
  });

  const [upcomingAppointments, setUpcomingAppointments] = useState([]);
  useEffect(() => {
    fetch(`http://localhost:8080/appointment/getAppointmentByPatientId${props.userId}`).then(async (result) => {
      if (result.ok) {
        setUpcomingAppointments(await result.json());
      }
    });
  });

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
  const labels = [

  ];

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
  for (const record of records) {
    result.push(
      <>
        <summary>
          {`Treatment at ${record.treatment_date}`}
        </summary>
        <div>
          {() => {
            const result = [];
            for (let i = 0; i < keys.length; i++) {
              result.push(
                <div>
                  <b>{names[i]}</b>: {record[keys[i]]}
                </div>
              )
            }
            return result;
          }}
        </div>
      </>
    );
  }
}

function getUpcomingAppointments(appointments) {
  const result = [];
  for (const appointment of appointments) {
    result.push(
      <tr>
        <td>
          {appointment["aa.appointment_date"]}
        </td>
        <td>
          {appointment["aa.start_time"]}
        </td>
        <td>
          {appointment["aa.end_time"]}
        </td>
        <td>
          {appointment["aa.assigned_room"]}
        </td>
        <td>
          {appointment["aa.appointment_type"]}
        </td>
        <td>
          {appointment["dentist.first_name"] + appointment["dentist.last_name"]}
        </td>
        <td>
          {appointment["hygenist.first_name"] + appointment["hygenist.last_name"]}
        </td>
      </tr>
    );
  }

  return (
    <table>
      <thead>
        <tr>
            <th>Appointment Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Room</th>
            <th>Type</th>
            <th>Dentist</th>
            <th>Hygenist</th>
        </tr>
      </thead>
      <tbody>
        {result}
      </tbody>
    </table>
  );
}

export default Patient;