import { computeStyles } from "@popperjs/core";
import { useEffect, useState } from "react";

function Dentist(props) {
  const [appointments, setAppointments] = useState([]);
  const [patientRecord, setPatientRecord] = useState({});

  useEffect(() => {
      async function temp() {
    //Since cookie should be in browser, will automatically be sent, verified in backend, return appointments, patient records
    const response = await fetch(
      "/appointment/getAppointmentByDentistId/" +
        localStorage.getItem("userId"),
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          accept: "application/json",
        },
      }
    );
    if (response.ok) {
        let data = await response.json();
        console.log(data);
      setAppointments(data);
    }
  }}, []);
//     const response = await fetch(
//     "/appointment/getAppointmentByDentistId/" +
//       localStorage.getItem("userId"),
//     {
//       method: "GET",
//       headers: {
//         "Content-Type": "application/json",
//         accept: "application/json",
//       },
//     }
//   );
//   if (response.ok) {
//       let data = await response.json();
//     setAppointments(data);
//   }
  console.log(appointments);
  async function getPatientRecords(patient_id) {
    const responseRecords = await fetch(
      "/appointment/getPatientRecord/" + patient_id,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          accept: "application/json",
        },
      }
    );
    if (responseRecords.ok) {
      let data = await responseRecords.json();
      const tempObj = data.reduce((acc, record) => {
        acc[record.user_id] = record;
        return acc;
      }, {});

      setPatientRecord(tempObj);
    }
  }

  function showPatientRecords(patient_id) {
    if (patientRecord[patient_id]) {
        return (
            <div>
                <p>{patientRecord[patient_id]}</p>
            </div>
        )
    }
  }

  return (
    <div className="container-fluid">
      <div className="row mt-3">
        {appointments.map((val) => {
          return (
            <div className="col-3">
              <div>Appointment details here</div>
              <button
                onClick={() =>
                  getPatientRecords(val["patient.user_id"])
                }
              >
                Show Records
              </button>
                {  showPatientRecords(val["patient.user_id"])}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Dentist;
