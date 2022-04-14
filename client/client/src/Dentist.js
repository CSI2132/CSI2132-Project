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
      console.log(response);
      if (response.ok) {
        let data = await response.json();
        console.log(data);
        setAppointments(data);
      }
    }

    temp();
  }, []);

  console.log(appointments);
  async function getPatientRecords(patient_id) {
    if (patientRecord[patient_id]) {
      setPatientRecord({
        ...patientRecord,
        [patient_id]: null,
      });
    } else {
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
        
        setPatientRecord({
          ...patientRecord,
          [patient_id]: data,
        });
      }
    }
  }

  function showPatientRecords(patient_id) {
    

    if (patientRecord[patient_id]) {
      const allRecordDivs = [];
      for (const record of patientRecord[patient_id]) {
        const result = [];
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
          "patient_charge",
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
          "Patient Charge",
        ];

        for (let i = 0; i < keys.length; i++) {
          result.push(
            <div>
              <b>{names[i]}</b>: {record[keys[i]]}
            </div>
          );
        }

        allRecordDivs.push(
          <div className="card" style={{width: "50%"}}>
            <div className="card-body">
              <h5 className="card-title"></h5>
              <h6 className="card-subtitle mb-2 text-muted"></h6>
              {result}
            </div>
          </div>
        );
      }

      return allRecordDivs;
    }
  }

  return (
    <div className="container-fluid">
      <div className="row mt-3 mh-50">
        {appointments.map((val) => {
          return (
            <div className="col-4  ml-3 mr-3 border">
              <div>{`Appointment at ${val.appointment_date} with ${val.patient_first_name} ${val.patient_last_name}`}</div>
              <button
                value={val["patient_user_id"]}
                onClick={() => getPatientRecords(val.patient_user_id)}
              >
                {patientRecord[val.patient_user_id] ? "Hide Records" : "Show Records"}
              </button>
              {showPatientRecords(val["patient_user_id"])}
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Dentist;
