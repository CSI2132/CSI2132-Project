import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

function Receptionist() {

    //-- Form data payload for [EDIT/ADD] patient info --
    const [formDataAddEdit, setFormDataAddEdit] = useState({ //-- Set Form FIELDS --
        user_id: "",
        username: "",
        password: "",
        patient_address: "",
        first_name: "",
        last_name: "",
        gender: "",
        insurance: "",
        ssn: "",
        email_address: "",
        date_of_birth: "",
    });

    const [showPassword, setShowPassword] = useState(false); //Show password function
    function handleFormChangeAddEdit(event) {  //-- Gets entire FORM DATA as payload --
        setFormDataAddEdit({
            ...formDataAddEdit, //... = spread operator 
            [event.target.name]: event.target.value
        })
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
        appointment_type: "procedure_type_name_enum",
        appointment_status: "CHECK (appointment_status IN ('ACTIVE', 'CANCELLED'))",
        assigned_room: ""
    });
    function handleFormChangeSet(event) {
        setFormDataSet({
            ...formDataSet,
            [event.target.name]: event.target.value
        })
    }


    //-- DETERMINING DROPDOWN SELECTED: ADD when no patientId exists,   EDIT selected patient id,   set patient appt --
    const [receptionistOption, setReceptionistOption] = useState("addPatient");
    //-- Helper vars --
    const isEditPatient = receptionistOption === "editPatient";
    const isSetPatientAppt = receptionistOption === "setPatientAppointment";

    let submitForm = async (e) => {
        e.preventDefault();
        console.log(formDataAddEdit); //DEBUGING PURPOSES
        console.log(formDataSet); //DEBUGING PURPOSES
        console.log(receptionistOption); //DEBUGING PURPOSES

        try {
            switch (receptionistOption) {
                case "addPatient":
                    console.log("Im in AddPatient"); //DEBUGING PURPOSES

                    //-- Hit Backend Enpoint --
                    // var formDataFormatted = [formDataAddEdit];  //Potentiall fix: [Uncaught SyntaxError: Unexpected end of JSON input] 
                    let resAdd = await fetch("/patient/addPatient/", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(formDataAddEdit),
                    });
                    // let resAddJson = await resAdd.json();
                    // if (resAdd.ok) {
                    //     console.log(resAddJson);
                    // } else {
                    //     console.log("error bro");
                    // }

                    break;

                case "editPatient":
                    let patientUserIdInUri = parseInt(formDataAddEdit.user_id.toString());
                    let resEdit = await fetch(`/patient/editPatient/${patientUserIdInUri}`, {
                        method: "PUT",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(formDataAddEdit),
                    });
                    // let resEditJson = await resEdit.json();
                    // if (resEdit.ok) {
                    //     console.log(resEditJson);
                    // } else {
                    //     console.log("error bro");
                    // }
                    break;

                case "setPatientAppointment":
                    //-- Hit Backend Enpoint --
                    let resSet = await fetch("/appointment/add/", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(formDataSet),
                    });
                    // let resSetJson = await resSet.json();
                    // if (resSet.ok) {
                    //     console.log(resSetJson);
                    // } else {
                    //     console.log("error Setting");
                    // }
                    break;

                default:
                    console.log("Nothing Happened.")
                    break;
            }
        } catch (err) {
            console.log(err);
        }
    };

    //-- FRONTEND UI --
    const receptionistForm = (
        <div className="container-fluid" >
            <div>
                <h2 className="text-center"> {isEditPatient ? '[EDIT]' : (isSetPatientAppt ? '[SET]' : '[ADD]')} Patient {isSetPatientAppt ? 'Appointment' : 'Information'} </h2>
                <div>
                    <div className="mb-auto text-center">  <i> Select Functionality: </i>
                        <select id="receptionistFunctions" aria-labelledby="dropdownMenuButton" onChange={(event) => { setReceptionistOption(event.target.value) }}>
                            <option value="addPatient">Add Patient</option>
                            <option value="editPatient">Edit Patient</option>
                            <option value="setPatientAppointment">Set Patient Appointment</option>
                        </select>
                    </div>
                </div>
            </div>

            <form
                id="receptionistForm"
                className="col-8 m-auto"
                onSubmit={submitForm}
            >
                {/* ADD or EDIT Patient MODE */}
                {!isSetPatientAppt &&
                    <div className="col mt-5" style={{ background: "linear-gradient(#a7acd9, #9e8fb2)" }}>
                        {/* UserId, Username, Password (TODO: USERID Dropdown iterating existing db) */}
                        <div className="form-row">
                            <div className="form-group col">
                                {isEditPatient &&
                                    <div>
                                        <label>UserId: &nbsp; </label>
                                        {/* <input name="userId" type="text" readOnly ref={register} className={`form-control ${errors.userId ? 'is-invalid' : ''}`} /> */}
                                        <input name="user_id" type="text" onChange={handleFormChangeAddEdit} />
                                        {/* <div className="invalid-feedback">{errors.userId?.message}</div> */}
                                    </div>
                                }
                            </div>

                            <div className="form-group col">
                                <label>Username: &nbsp; </label>
                                {/* <input name="Username" type="text" ref={register} className={`form-control ${errors.firstName ? 'is-invalid' : ''}`} /> */}
                                <input name="username" type="text" onChange={handleFormChangeAddEdit} />
                                {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                            </div>

                            {/* Password */}
                            <div className="form-col">
                                <div className="form-group col">
                                    <label>
                                        Password: &nbsp;
                                    </label>
                                    {/* <input name="password" type="password" ref={register} className={`form-control ${errors.password ? 'is-invalid' : ''}`} /> */}
                                    <input name="password" type="password" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.password?.message}</div> */}
                                    {isEditPatient &&
                                        (!showPassword
                                            ? <span> -- <a onClick={() => setShowPassword(!showPassword)} className="text-primary"> Show </a></span>
                                            : <em> [ {formDataAddEdit.password} ] </em>
                                        )
                                    }
                                </div>
                                {isEditPatient &&
                                    <div className="form-group col">
                                        <label>Confirm Password: &nbsp; </label>
                                        {/* <input name="confirmPassword" type="password" ref={register} className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`} /> */}
                                        <input name="confirm_password" type="password" onChange={handleFormChangeAddEdit} />
                                        {/* <div className="invalid-feedback">{errors.confirmPassword?.message}</div> */}
                                        <p> (Leave blank to keep the same password) </p>
                                    </div>
                                }
                            </div>
                        </div>

                        {/* First Name, Last Name, Email Address, Gender */}
                        <div className="form-row">
                            <div className="form-group col">
                                <label>First Name: &nbsp; </label>
                                <div>
                                    {/* <input name="firstName" type="text" ref={register} className={`form-control ${errors.firstName ? 'is-invalid' : ''}`} /> */}
                                    <input name="first_name" type="text" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>Last Name: &nbsp; </label>
                                <div>
                                    {/* <input name="lastName" type="text" ref={register} className={`form-control ${errors.lastName ? 'is-invalid' : ''}`} /> */}
                                    <input name="last_name" type="text" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.lastName?.message}</div> */}
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>Email: &nbsp; </label>
                                <div>
                                    {/* <input name="email" type="text" ref={register} className={`form-control ${errors.email ? 'is-invalid' : ''}`} /> */}
                                    <input name="email_address" type="text" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.email?.message}</div> */}
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>Gender: &nbsp; </label>
                                {/* <select name="gender" ref={register} className={`form-control ${errors.gender ? 'is-invalid' : ''}`}> */}
                                <div>
                                    <select name="gender" onChange={handleFormChangeAddEdit}>
                                        <option value=""></option>
                                        <option value="MALE">Male</option>
                                        <option value="FEMALE">Female</option>
                                        <option value="OTHER">Other</option>
                                    </select>
                                    {/* <div className="invalid-feedback">{errors.gender?.message}</div> */}
                                </div>
                            </div>
                        </div>

                        {/* Date Of Birth, SSN, Insurance, Address */}
                        <div className="form-row">
                            <div className="form-group col">
                                <label>Address: &nbsp; </label>
                                <div>
                                    {/* <input name="address" type="text" ref={register} className={`form-control ${errors.address ? 'is-invalid' : ''}`} /> */}
                                    <input name="patient_address" type="text" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.address?.message}</div> */}
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>Date Of Birth: &nbsp; </label>
                                <div>
                                    {/* <input name="dob" type="text" ref={register} className={`form-control ${errors.dob ? 'is-invalid' : ''}`} /> */}
                                    <input name="date_of_birth" type="date" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.dob?.message}</div> */}
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>SSN: &nbsp; </label>
                                <div>
                                    {/* <input name="ssn" type="text" ref={register} className={`form-control ${errors.ssn ? 'is-invalid' : ''}`} /> */}
                                    <input name="ssn" type="number" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.ssn?.message}</div> */}
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>Insurance: &nbsp; </label>
                                <div>
                                    {/* <input name="insurance" type="text" ref={register} className={`form-control ${errors.insurance ? 'is-invalid' : ''}`} /> */}
                                    <input name="insurance" type="text" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.insurance?.message}</div> */}
                                </div>
                            </div>
                        </div>
                    </div>
                }


                {/* SET Patient Appointment MODE  */}
                {isSetPatientAppt &&
                    <div className="col mt-5" style={{ background: "linear-gradient(#3eadcf, #abe9cd)" }}>

                        {/* UserIds: Appointment, patient, dentist, hygienist */}
                        <label style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}> <b> User IDs &nbsp; </b> </label>
                        <div className="form-group row">
                            {/* <div className="form-group col">
                                <label>Appointment: &nbsp; </label>
                                <input name="appointment_userId" type="text" readOnly />
                            </div> */}
                            <div className="form-group col">
                                <label>Patient: &nbsp; </label>
                                <input name="patient_user_id" type="text" onChange={handleFormChangeSet} />
                            </div>
                            <div className="form-group col">
                                <label>Dentist: &nbsp; </label>
                                <input name="dentist_user_id" type="text" onChange={handleFormChangeSet} />
                            </div>
                            <div className="form-group col">
                                <label>Hygienist: &nbsp; </label>
                                <input name="hygienist_user_id" type="text" onChange={handleFormChangeSet} />
                            </div>
                        </div>

                        {/* Appointment Details: status, assigned room, date, start time, end time, type */}
                        <label style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}> <b> Appointment Details &nbsp; </b> </label>
                        <div className="form-group row">
                            <div className="form-group col">
                                <label>Status: &nbsp; </label>
                                <div>
                                    <select name="appointment_status" onChange={handleFormChangeSet}>
                                        <option value=""></option>
                                        <option value="ACTIVE"> ACTIVE </option>
                                        <option value="CANCELLED"> CANCELLED </option>
                                    </select>
                                </div>
                            </div>
                            <div className="form-group col">
                                <label>Assigned Room: &nbsp; </label>
                                <input name="assigned_room" type="text" onChange={handleFormChangeSet} />
                            </div>
                            <div className="form-group col">
                                <label>Appointment Date: &nbsp; </label>
                                <input name="appointment_date" type="date" onChange={handleFormChangeSet} />
                            </div>
                            <div className="form-group col">
                                <label>Start Time: &nbsp; </label>
                                <input name="start_time" type="time" onChange={handleFormChangeSet} />
                                <label>End Time: &nbsp; </label>
                                <input name="end_time" type="time" onChange={handleFormChangeSet} />
                            </div>
                            <div className="form-group col">
                                <label>Type: &nbsp; </label>
                                <div>
                                    <select name="appointment_type" onChange={handleFormChangeSet}>
                                        <option value=""></option>
                                        <option value="SCALING"> Scaling </option>
                                        <option value="FLUORIDE"> Fluoride </option>
                                        <option value="REMOVAL">Removal</option>
                                        <option value="FILLINGS">Fillings</option>
                                        <option value="ROOT_CANAL">Root Canal</option>
                                        <option value="WHITENING">Whitening</option>
                                        <option value="ENDODONTIC">Endodontic</option>
                                        <option value="ORTHODONTIC">Orthodontic</option>
                                        <option value="PERIODONTIC">Periodontic</option>
                                        <option value="ENDODONTIC_AND_PERIODONTIC">Endodontic And Periodontic</option>
                                        <option value="Other">Other</option>
                                    </select>
                                </div>
                            </div>
                        </div>


                    </div>
                }



                {/* SUBMIT form  */}
                <div className="form-group">
                    <button type="submit" className="btn btn-primary" >
                        {isEditPatient ? 'Save' : (isSetPatientAppt ? 'Set' : 'Add')} Details
                    </button>
                    <Link to={isEditPatient ? '.' : (isSetPatientAppt ? '..' : '...')} className="btn btn-link"> {isSetPatientAppt ? 'Back' : 'Cancel'} </Link>
                </div>
            </form>
        </div>
    );
    return receptionistForm;
}
export default Receptionist;