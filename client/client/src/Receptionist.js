import { useState } from "react";
import { Link } from "react-router-dom";

function Receptionist() {
  //-- [Extra Features] --
  const [showPassword, setShowPassword] = useState(false); //Show password function

  //-- [Input Validation] --
  const [errorMsg, setErrorMsg] = useState(false); //Boolean to see if error input (For fields that dont HAVE to be unique)
  const [confirmPassword, setConfirmPassword] = useState({
    //-- PASSWORD INPUT VALIDATION --
    confirm_password: "",
  });
  function handlePswdValidation(event) {
    setConfirmPassword({
      ...confirmPassword, //(Recognize input in field)
      [event.target.name]: event.target.value,
    });
  }
  const [doesUsernameExist, setDoesUsernameExist] = useState(false);
  const [doesEmailExist, setDoesEmailExist] = useState(false);
  const [doesSSNExist, setDoesSSNExist] = useState(false);

  //-- Form data payload for [ADD/EDIT] patient info --
  const [formDataAddEdit, setFormDataAddEdit] = useState({
    //-- Set Form FIELDS --
    user_id: "",
    username: "",
    password: "",
    patient_address: "",
    first_name: "",
    last_name: "",
    gender: "OTHER",
    insurance: "",
    ssn: "",
    email_address: "",
    date_of_birth: "",
  });
  function handleFormChangeAddEdit(event) {
    //-- Gets entire FORM DATA as payload --
    setFormDataAddEdit({
      ...formDataAddEdit, //... = spread operator
      [event.target.name]: event.target.value,
    });
  }

  //-- Form data payload for [SET] patient appt --
  const [formDataSet, setFormDataSet] = useState({
    // appointment_id: "",
    patient_user_id: "",
    dentist_user_id: "",
    hygienist_user_id: "",
    appointment_date: "",
    start_time: "",
    end_time: "",
    appointment_type: "SCALING",
    appointment_status: "ACTIVE",
    assigned_room: "",
  });
  function handleFormChangeSet(event) {
    setFormDataSet({
      ...formDataSet,
      [event.target.name]: event.target.value,
    });
  }

  //-- Dynamic Dropdown menus (AUTO populate BASED on database data) --
  //Backend endpoint to get ALL (Appointment Procedure) records
  const appointmentFetcher = async () => {
    const response = await fetch("appointment/getProcedure/");
    const data = response.json();
    return data;
  };
  const [procedureOptions, setProcedureOptions] = useState([]);
  // const [fetchingAppointmentType, setFetchingAppointmentType] = useState(false);
  const getAppointmentTypeOption = () => {
    const result = [];
    if (procedureOptions.length > 0) {
      procedureOptions.forEach((option) => {
        result.push(
          <option value={option.procedure_type_name}>
            {option.procedure_type_name}
          </option>
        );
      });
      // } else if (!fetchingAppointmentType) {
    } else {
      // // setFetchingAppointmentType(true);
      appointmentFetcher().then((data) => setProcedureOptions(data));
    }
    return result;
  };

  //-- [DETERMINE PAGE MODE]: ADD when no patientId exists,   EDIT selected patient id,   SET patient appt --
  const [receptionistOption, setReceptionistOption] = useState("addPatient");
  const isEditPatient = receptionistOption === "editPatient";
  const isSetPatientAppt = receptionistOption === "setPatientAppointment";

  //Backend endpoint to get ALL (Patient, Dentist, Hygiensts from USER table) records: userIds
  const patientFetcher = async (endpoint) => {
    const response = await fetch(endpoint);
    const data = response.json();
    return data;
  };
  const [userLists, setUserLists] = useState({});
  // const [fetchingUserList, setFetchingUserList] = useState({});
  const getUserList = (endpoint, key) => {
    const result = [];
    const userList = userLists[endpoint] || [];
    if (userList.length > 0) {
      userList.forEach((option) => {
        result.push(
          <option value={option.user_id}>
            {" "}
            {`${option.first_name} ${option.last_name}`}{" "}
          </option>
        );
      });
      // } else if (!fetchingUserList[endpoint]) {
    } else {
      // setFetchingUserList({ ...fetchingUserList, [endpoint]: true})
      patientFetcher(endpoint).then((data) => {
        setUserLists({ ...userLists, [endpoint]: data });
        if (isEditPatient) {
          setFormDataAddEdit({ ...formDataAddEdit, [key]: data[0].user_id });
        } else if (isSetPatientAppt) {
          setFormDataSet({ ...formDataSet, [key]: data[0].user_id });
        }
      });
    }
    return result;
  };

  //-- [ON SUBMIT] --
  let submitForm = async (e) => {
    e.preventDefault();
    console.log(formDataAddEdit); //DEBUGING PURPOSES
    console.log(formDataSet); //DEBUGING PURPOSES
    console.log(receptionistOption); //DEBUGING PURPOSES

    //Input validation Initalization
    var usernameExists = false;
    var emailExists = false;
    var ssnExists = false;
    var passwordError = false;

    try {
      switch (receptionistOption) {
        case "addPatient":
          // -- INPUT VALIDATION --
          //username, email, ssn has to be UNIQUE (Doesnt exist in db)
          let patientUsernameInUriAdd = formDataAddEdit.username;
          let patientUsrnmeAdd = await fetch(
            `/patient/getByUsername/${patientUsernameInUriAdd}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          usernameExists = await patientUsrnmeAdd.json();
          setDoesUsernameExist(usernameExists);

          let patientEmailInUriAdd = formDataAddEdit.email_address;
          let patientEmailAdd = await fetch(
            `/patient/getByEmail/${patientEmailInUriAdd}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          emailExists = await patientEmailAdd.json();
          setDoesEmailExist(emailExists);

          let patientSSNInUriAdd = formDataAddEdit.ssn;
          let patientSSNAdd = await fetch(
            `/patient/getBySSN/${patientSSNInUriAdd}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          ssnExists = await patientSSNAdd.json();
          setDoesSSNExist(ssnExists);

          //-- HIT backend endpoint ONLY if ensure ALL VALIDATION Pass --
          if (!usernameExists && !emailExists && !ssnExists) {
            // var formDataFormatted = [formDataAddEdit];  //Potential fix: [Uncaught SyntaxError: Unexpected end of JSON input]
            let resAdd = await fetch("/patient/addPatient/", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(formDataAddEdit),
            });
            // let resAddJson = await resAdd.json(); //[Uncaught SyntaxError: Unexpected end of JSON input]
            if (resAdd.ok) {
              alert("Success!\n ADDED a new PATIENT");
            } else {
              alert("ERROR!\n INVALID SUBMISSION");
            }
          } else {
            alert("ERROR!\n INVALID Input Values");
          }
          break;

        case "editPatient":
          //-- INPUT VALIDATION --
          //username, email, ssn has to be UNIQUE (Doesnt exist in db)
          if (formDataAddEdit.username.length !== 0) {
            let patientUsernameInUriEdit = formDataAddEdit.username;
            let patientUsrnmeEdit = await fetch(
              `/patient/getByUsername/${patientUsernameInUriEdit}`,
              {
                method: "GET",
                headers: {
                  "Content-Type": "application/json",
                },
              }
            );
            usernameExists = await patientUsrnmeEdit.json();
            setDoesUsernameExist(usernameExists);
          } else {
            setDoesUsernameExist(false);
          }

          if (formDataAddEdit.email_address.length !== 0) {
            let patientEmailInUriEdit = formDataAddEdit.email_address;
            let patientEmailEdit = await fetch(
              `/patient/getByEmail/${patientEmailInUriEdit}`,
              {
                method: "GET",
                headers: {
                  "Content-Type": "application/json",
                },
              }
            );
            emailExists = await patientEmailEdit.json();
            setDoesEmailExist(emailExists);
          } else {
            setDoesEmailExist(false);
          }

          if (formDataAddEdit.ssn.length !== 0) {
            let patientSSNInUriEdit = formDataAddEdit.ssn;
            let patientSSNEdit = await fetch(
              `/patient/getBySSN/${patientSSNInUriEdit}`,
              {
                method: "GET",
                headers: {
                  "Content-Type": "application/json",
                },
              }
            );
            ssnExists = await patientSSNEdit.json();
            setDoesSSNExist(ssnExists);
          } else {
            setDoesSSNExist(false);
          }

          //Password matching validation boolean set
          if (formDataAddEdit.password !== confirmPassword.confirm_password) {
            setErrorMsg(true);
            passwordError = true;
          } else {
            setErrorMsg(false); //Remove Error message from screen
            passwordError = false;
          }

          //-- HIT backend endpoint ONLY if ensure ALL VALIDATION Pass --
          if (!usernameExists && !emailExists && !ssnExists && !passwordError) {
            let patientUserIdInUri = parseInt(
              formDataAddEdit.user_id.toString()
            );
            let resEdit = await fetch(
              `/patient/editPatient/${patientUserIdInUri}`,
              {
                method: "PUT",
                headers: {
                  "Content-Type": "application/json",
                },
                body: JSON.stringify(formDataAddEdit),
              }
            );
            if (resEdit.ok) {
              alert("Success!\n EDITED the PATIENT recorded");
            } else {
              alert("ERROR!\n INVALID SUBMISSION");
            }
          } else {
            alert("ERROR!\n INVALID Input Validation");
          }
          break;

        case "setPatientAppointment":
          //-- INPUT VALIDATION --
          //Appointment Date, and time need values
          if (
            formDataSet.appointment_date.length === 0 ||
            formDataSet.start_time.length === 0 ||
            formDataSet.end_time.length === 0
          ) {
            setErrorMsg(true); //Keep Error message on screen until resubmit
            alert("ERROR!\n INVALID Input Validation");
          }

          //-- HIT backend endpoint ONLY if ensure ALL VALIDATION Pass --
          else {
            setErrorMsg(false); //No Error Msg

            let resSet = await fetch("/appointment/setAppointment/", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(formDataSet),
            });
            if (resSet.ok) {
              alert("Success!\n Patient APPOINTMENT SET");
            } else {
              alert("ERROR!\n INVALID SUBMISSION");
            }
          }
          break;

        default:
          console.log("Nothing Happened.");
          break;
      }
    } catch (err) {
      alert("ERROR!\n Could not connect to the database.");
      console.log(err);
    }
  };

  //-- FRONTEND UI --
  const receptionistForm = (
    <div className="container-fluid">
      <div>
        <Link to="/" className="btn btn-secondary">
          {" "}
          Logout{" "}
        </Link>
        <h2 className="text-center mt-5">
          {" "}
          {isEditPatient ? "[EDIT]" : isSetPatientAppt ? "[SET]" : "[ADD]"}{" "}
          Patient {isSetPatientAppt ? "Appointment" : "Information"}{" "}
        </h2>
        <div>
          <div className="mb-auto text-center">
            {" "}
            <i> Select Functionality: </i>
            <select
              aria-labelledby="dropdownMenuButton"
              onChange={(event) => {
                setReceptionistOption(event.target.value);
              }}
            >
              <option value="addPatient">Add Patient</option>
              <option value="editPatient">Edit Patient</option>
              <option value="setPatientAppointment">
                Set Patient Appointment
              </option>
            </select>
          </div>
          {isEditPatient && (
            <div>
              <p className="mb-auto text-center mt-3">
                {" "}
                (NOTE: If field left empty, existing information for that field
                will be retained.){" "}
              </p>
            </div>
          )}
        </div>
      </div>

      <form
        id="receptionistForm"
        className="col-8 m-auto"
        onSubmit={submitForm}
      >
        {/* ADD or EDIT Patient MODE */}
        {!isSetPatientAppt && (
          <div
            className="col mt-5"
            style={{ background: "linear-gradient(#a7acd9, #9e8fb2)" }}
          >
            {/* UserId, Username, Password */}
            <div className="form-row">
              <div className="form-group col">
                {isEditPatient && (
                  <div>
                    <label>User: &nbsp; </label>
                    <select name="user_id" onChange={handleFormChangeAddEdit}>
                      {getUserList("user/allPatientId/", "user_id")}
                    </select>
                  </div>
                )}
              </div>

              <div className="form-group col">
                <label>Username: &nbsp; </label>
                <input
                  name="username"
                  type="text"
                  onChange={handleFormChangeAddEdit}
                />
                {doesUsernameExist && (
                  <p className="error">
                    {" "}
                    //// &nbsp; USERNAME has to be UNIQUE &nbsp; \\\\{" "}
                  </p>
                )}
              </div>

              {/* Password */}
              <div className="form-col">
                <div className="form-group col">
                  <label>Password: &nbsp;</label>
                  <input
                    name="password"
                    type="password"
                    onChange={handleFormChangeAddEdit}
                  />
                  {isEditPatient &&
                    (!showPassword ? (
                      <span>
                        {" "}
                        --{" "}
                        <a
                          onClick={() => setShowPassword(!showPassword)}
                          className="text-primary"
                        >
                          {" "}
                          Show{" "}
                        </a>
                      </span>
                    ) : (
                      <em> [ {formDataAddEdit.password} ] </em>
                    ))}
                </div>
                {isEditPatient && (
                  <div className="form-group col">
                    <label>Confirm Password: &nbsp; </label>
                    <input
                      name="confirm_password"
                      type="password"
                      onChange={handlePswdValidation}
                    />
                    <p> (Leave blank to keep the same password) </p>
                    {errorMsg && (
                      <p className="error">
                        {" "}
                        //// &nbsp; PASSWORDs need to MATCH! &nbsp; \\\\{" "}
                      </p>
                    )}
                  </div>
                )}
              </div>
            </div>

            {/* First Name, Last Name, Email Address, Gender */}
            <div className="form-row">
              <div className="form-group col">
                <label>First Name: &nbsp; </label>
                <div>
                  <input
                    name="first_name"
                    type="text"
                    onChange={handleFormChangeAddEdit}
                  />
                </div>
              </div>
              <div className="form-group col">
                <label>Last Name: &nbsp; </label>
                <div>
                  <input
                    name="last_name"
                    type="text"
                    onChange={handleFormChangeAddEdit}
                  />
                </div>
              </div>
              <div className="form-group col">
                <label>Email: &nbsp; </label>
                <div>
                  <input
                    name="email_address"
                    type="text"
                    onChange={handleFormChangeAddEdit}
                  />
                  {doesEmailExist && (
                    <p className="error">
                      {" "}
                      //// &nbsp; EMAIL has to be UNIQUE &nbsp; \\\\{" "}
                    </p>
                  )}
                </div>
              </div>
              <div className="form-group col">
                <label>Gender: &nbsp; </label>
                <div>
                  <select name="gender" onChange={handleFormChangeAddEdit}>
                    <option value="OTHER">Other</option>
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                  </select>
                </div>
              </div>
            </div>

            {/* Date Of Birth, SSN, Insurance, Address */}
            <div className="form-row">
              <div className="form-group col">
                <label>Address: &nbsp; </label>
                <div>
                  <input
                    name="patient_address"
                    type="text"
                    onChange={handleFormChangeAddEdit}
                  />
                </div>
              </div>
              <div className="form-group col">
                <label>Date Of Birth: &nbsp; </label>
                <div>
                  <input
                    name="date_of_birth"
                    type="date"
                    onChange={handleFormChangeAddEdit}
                  />
                </div>
              </div>
              <div className="form-group col">
                <label>SSN: &nbsp; </label>
                <div>
                  <input
                    name="ssn"
                    type="number"
                    onChange={handleFormChangeAddEdit}
                  />
                  {doesSSNExist && (
                    <p className="error">
                      {" "}
                      //// &nbsp; SSN has to be UNIQUE &nbsp; \\\\{" "}
                    </p>
                  )}
                </div>
              </div>
              <div className="form-group col">
                <label>Insurance: &nbsp; </label>
                <div>
                  <input
                    name="insurance"
                    type="text"
                    onChange={handleFormChangeAddEdit}
                  />
                </div>
              </div>
            </div>
          </div>
        )}

        {/* SET Patient Appointment MODE  */}
        {isSetPatientAppt && (
          <div
            className="col mt-5"
            style={{ background: "linear-gradient(#3eadcf, #abe9cd)" }}
          >
            {/* UserIds: Appointment, patient, dentist, hygienist */}
            <label
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              {" "}
              <b> User IDs &nbsp; </b>{" "}
            </label>
            <div className="form-group row">
              {/* <div className="form-group col">
                                <label>Appointment: &nbsp; </label>
                                <input name="appointment_userId" type="text" readOnly />
                            </div> */}
              <div className="form-group col">
                <label>Patient: &nbsp; </label>
                <select name="patient_user_id" onChange={handleFormChangeSet}>
                  {getUserList("user/allPatientId/", "patient_user_id")}
                </select>
              </div>
              <div className="form-group col">
                <label>Dentist: &nbsp; </label>
                <select name="dentist_user_id" onChange={handleFormChangeSet}>
                  {getUserList("user/allDentistId/", "dentist_user_id")}
                </select>
              </div>
              <div className="form-group col">
                <label>Hygienist: &nbsp; </label>
                <select name="hygienist_user_id" onChange={handleFormChangeSet}>
                  {getUserList("user/allHygienistId/", "hygienist_user_id")}
                </select>
              </div>
            </div>

            {/* Appointment Details: status, assigned room, date, start time, end time, type */}
            <label
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              {" "}
              <b> Appointment Details &nbsp; </b>{" "}
            </label>
            {errorMsg && (
              <p className="error mb-auto text-center">
                {" "}
                //// &nbsp; INPUT VALUES CANT BE EMPTY! &nbsp; \\\\{" "}
              </p>
            )}
            <div className="form-group row">
              <div className="form-group col">
                <label>Status: &nbsp; </label>
                <div>
                  <select
                    name="appointment_status"
                    onChange={handleFormChangeSet}
                  >
                    <option value="ACTIVE"> ACTIVE </option>
                    <option value="CANCELLED"> CANCELLED </option>
                  </select>
                </div>
              </div>
              <div className="form-group col">
                <label>Assigned Room: &nbsp; </label>
                <input
                  name="assigned_room"
                  type="text"
                  onChange={handleFormChangeSet}
                />
              </div>
              <div className="form-group col">
                <label>Appointment Date: &nbsp; </label>
                <input
                  name="appointment_date"
                  type="date"
                  onChange={handleFormChangeSet}
                />
              </div>
              <div className="form-group col">
                <label>Start Time: &nbsp; </label>
                <input
                  name="start_time"
                  type="time"
                  onChange={handleFormChangeSet}
                />
                <label>End Time: &nbsp; </label>
                <input
                  name="end_time"
                  type="time"
                  onChange={handleFormChangeSet}
                />
              </div>
              <div className="form-group col">
                <label>Type: &nbsp; </label>
                <div>
                  <select
                    name="appointment_type"
                    onChange={handleFormChangeSet}
                  >
                    {getAppointmentTypeOption()}
                  </select>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* SUBMIT form  */}
        <div className="form-group">
          <button type="submit" className="btn btn-primary">
            {isEditPatient ? "Save" : isSetPatientAppt ? "Set" : "Add"} Details
          </button>
        </div>
      </form>
    </div>
  );
  return receptionistForm;
}
export default Receptionist;
