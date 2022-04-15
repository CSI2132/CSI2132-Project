import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Dentist(props) {
  const [appointments, setAppointments] = useState([]);
  const [patientRecord, setPatientRecord] = useState({});
  const [creatingRecords, setCreatingRecords] = useState({});
  const [recordForms, setRecordForms] = useState({});

  useEffect(() => {
    async function temp() {
      //Since cookie should be in browser, will automatically be sent, verified in backend, return appointments, patient records
      const response = await fetch(
        "/appointment/getAppointmentByDentistOrHygienistId/" +
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

  async function submitRecord(form, patient_id, appointment_id) {
    const query = {
      record: {
        progress_notes: form.progress_notes,
        patient_user_id: patient_id,
      },
      treatment: {
        appointment_type: form.appointment_type,
        treatment_type: form.treatment_type,
        medication: form.medication,
        symptoms: form.symptoms,
        tooth: form.tooth,
        comments: form.comments,
        treatment_date: form.treatment_date,
        treatment_description: form.treatment_description,
        appointment_procedure_description:
          form.appointment_procedure_description,
        tooth_involved: form.tooth_involved,
        procedure_amount: form.procedure_amount,
        patient_charge: form.patient_charge,
        insurance_charge: form.insurance_charge,
        total_charge: form.total_charge,
        appointment_id: appointment_id,
      },
    };

    const response = await fetch("/dentist/addTreatmentInfo", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        accept: "application/json",
      },
      body: JSON.stringify(query),
    });

    if (response.ok) {
      setRecordForms({ ...recordForms, [patient_id]: {} });
      setCreatingRecords({ ...creatingRecords, [patient_id]: false });
    } else {
      alert("Error submitting record");
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
          <div className="card" style={{ width: "50%" }}>
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

  function showRecordCreation(patient_id, appointment_id) {
    if (creatingRecords[patient_id]) {
      const getForm = () => recordForms[patient_id] || {};
      const result = [];

      const info = [
        {
          key: "appointment_type",
          name: "Appointment Type",
          type: "appointment_type",
        },
        {
          key: "treatment_type",
          name: "Treatment Type",
          type: "text",
        },
        {
          key: "medication",
          name: "Medication",
          type: "text",
        },
        {
          key: "symptoms",
          name: "Symptoms",
          type: "text",
        },
        {
          key: "tooth",
          name: "Tooth",
          type: "text",
        },
        {
          key: "comments",
          name: "Comments",
          type: "text",
        },
        {
          key: "progress_notes",
          name: "Progress Notes",
          type: "text",
        },
        {
          key: "treatment_date",
          name: "Treatment Date",
          type: "date",
        },
        {
          key: "treatment_description",
          name: "Treatment Description",
          type: "text",
        },
        {
          key: "appointment_procedure_description",
          name: "Appointment Procedure Description",
          type: "text",
        },
        {
          key: "tooth_involved",
          name: "Tooth Involved",
          type: "text",
        },
        {
          key: "procedure_amount",
          name: "Procedure Amount",
          type: "number",
        },
        {
          key: "patient_charge",
          name: "Patient Charge",
          type: "number",
        },
        {
          key: "insurance_charge",
          name: "Insurance Charge",
          type: "number",
        },
        {
          key: "total_charge",
          name: "Total Charge",
          type: "number",
        },
      ];

      for (const item of info) {
        let selector = null;
        if (
          item.type === "text" ||
          item.type === "number" ||
          item.type === "date"
        ) {
          selector = (
            <input
              type={item.type}
              id={`input-${item.key}`}
              value={getForm()[item.key]}
              onChange={(e) =>
                setRecordForms({
                  ...recordForms,
                  [patient_id]: { ...getForm(), [item.key]: e.target.value },
                })
              }
            />
          );
        } else if (item.type === "appointment_type") {
          selector = (
            <select
              id={`input-${item.key}`}
              value={getForm()[item.key]}
              onChange={(e) =>
                setRecordForms({
                  ...recordForms,
                  [patient_id]: { ...getForm(), [item.key]: e.target.value },
                })
              }
            >
              <option defaultValue value="SCALING">
                Scaling
              </option>
              <option value="FLUORIDE">Fluoride</option>
              <option value="REMOVAL">Removal</option>
              <option value="FILLINGS">Fillings</option>
              <option value="ROOT_CANAL">Root Canal</option>
              <option value="WHITENING">Whitening</option>
              <option value="ENDODONTIC">Endodontic</option>
              <option value="ORTHODONTIC">Orthodontic</option>
              <option value="PERIODONTIC">Periodontic</option>
              <option value="ENDODONTIC_AND_PERIODONTIC">
                Endodontic and Periodontic
              </option>
              <option value="OTHER">Other</option>
            </select>
          );

          if (!getForm()[item.key]) {
            setRecordForms({
              ...recordForms,
              [patient_id]: { ...getForm(), [item.key]: "SCALING" },
            });
          }
        }

        result.push(
          <div>
            <label for={`input-${item.key}`}>
              <b>{item.name}</b>:
            </label>
            <br />
            {selector}
          </div>
        );
      }

      return (
        <div className="card">
          <div className="card-body">
            <h5 className="card-title">New Treatment Record</h5>

            {result}

            <button
              onClick={() =>
                submitRecord(getForm(), patient_id, appointment_id)
              }
              disabled={shouldDisableRecordSubmit(getForm(), info)}
            >
              Submit
            </button>
          </div>
        </div>
      );
    }
  }

  return (
    <div className="container-fluid">
      <Link to="/" className="btn btn-secondary">
        {" "}
        Logout{" "}
      </Link>
      <h1 className="text-center mt-5 mb-5">Patient Records</h1>
      <div className="row mt-3 mh-50">
        {appointments.map((val) => {
          return (
            <div className="col-4  ml-3 mr-3 border">
              <div>{`Appointment at ${val.appointment_date} with ${val.patient_first_name} ${val.patient_last_name}`}</div>
              <button
                value={val["patient_user_id"]}
                onClick={() => getPatientRecords(val.patient_user_id)}
              >
                {patientRecord[val.patient_user_id]
                  ? "Hide Records"
                  : "Show Records"}
              </button>
              {showPatientRecords(val.patient_user_id)}
              {!patientRecord[val.patient_user_id] && (
                <>
                  <button
                    value={val["patient_user_id"]}
                    onClick={() =>
                      setCreatingRecords({
                        ...creatingRecords,
                        [val.patient_user_id]: true,
                      })
                    }
                  >
                    {"Create Treatment Record"}
                  </button>
                  {showRecordCreation(val.patient_user_id, val.appointment_id)}
                </>
              )}
            </div>
          );
        })}
      </div>
    </div>
  );
}

function shouldDisableRecordSubmit(form, info) {
  for (const item of info) {
    if (!form[item.key]) {
      return true;
    }
  }

  return false;
}

export default Dentist;
