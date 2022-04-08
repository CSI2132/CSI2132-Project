import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
// https://jasonwatmore.com/post/2020/10/14/react-hook-form-combined-add-edit-create-update-form-example ???

function Receptionist() {

    //-- Attributes within Form --
    // const [patient, setPatient] = useState({});
    // useEffect(() => {
    //     if (!isAddMode) {
    //         //-- Get Patient Details and Set form attribute field values --
    //         userService.getById(id).then(patient => {
    //             const fields = [
    //                 'user_id',                
    //                 'username',
    //                 'patient_password',
    //                 'patient_address',
    //                 'first_name',
    //                 'last_name',
    //                 'gender',
    //                 'insurance',
    //                 'SSN',
    //                 'email_address',
    //                 'date_of_birth'
    //             ];
    //             fields.forEach(field => setValue(field, patient[field]));
    //             setPatient(patient);
    //         });
    //     }
    // }, []);

    //-- Form data payload for [EDIT/ADD] patient info --
    const [formDataAddEdit, setFormDataAddEdit] = useState({ //-- Set Form FIELDS --
        user_id: "",
        username: "",
        patient_password: "",
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
        appointment_id: "",
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

    //See which option selected to submit (ON SUBMIT)
    async function onSubmitHandleFormSelected(event) {
        console.log(formDataAddEdit); //DEBUGING PURPOSES
        console.log(receptionistOption); //DEBUGING PURPOSES

        switch (receptionistOption) {
            case "addPatient":
                console.log("Im in AddPAtient"); //DEBUGING PURPOSES

                // //-- TEST --
                // useEffect(async () => {
                //     const result = await fetch("/appointment/getProcedure"); //"localhost:3000/appointment/getProcedure"
                //     if (result.ok) {
                //       return (await result.json());
                //     }
                // }, []);

                const responseAdd = await fetch("localhost:3000/appointment/getProcedure");
                if (responseAdd.ok){  // => false
                    console.log("Please work  " + responseAdd.ok);
                }  
                else if (!responseAdd.ok) {
                    console.log("NOOONONONN  work!  ");
                    const message = `An error has occured: ${responseAdd.status}`;
                    throw new Error(message);
                }
                console.log(responseAdd)
                const results = await responseAdd.json();
                return results;
                

                // if(formDataAddEdit.userId){  //TODO: Doesnt exist in database

                // }
                // const responseAdd = await fetch("/patient/addPatient", {
                //     method: "POST",
                //     headers: {
                //         "Content-Type": "application/json",
                //     },
                //     body: JSON.stringify(formDataAddEdit),
                // });

                // console.log(responseAdd)
                // return await responseAdd.json();

                break;
            case "editPatient":

                // console.log("Im in EditPatient"); //DEBUGING PURPOSES

                // const responseEdit = await fetch("/patient/editPatient", {
                //     method: "PUT",
                //     headers: {
                //       "Content-Type": "application/json",
                //     },
                //     body: JSON.stringify(formDataAddEdit),
                //   });

                // console.log(response)

                break;
            case "setPatientAppointment":

                // console.log("Im in SetPatient"); //DEBUGING PURPOSES

                // const responseSet = await fetch("/appointment/add", {
                //     method: "POST",
                //     headers: {
                //       "Content-Type": "application/json",
                //     },
                //     body: JSON.stringify(formDataSet),
                //   });

                // console.log(response)

                break;
            default:
                console.log("Nothing Happened.")
                break;
        }
    }
    //// function handleReceptionistSubmit(formObject){
    ////     if(formData.id === null){
    ////       // if "id" is null its mean its a new record
    ////       //... create the unique ID here
    ////     }else{
    ////      //  edit the existing record and update
    ////     }
    //// }

    // // const { id } = match.params; 
    // const { id } = 1;
    // const isAddMode = id;  //&& id does not exist in database


    //-- FRONTEND UI --
    const receptionistForm = (
        <div className="container-fluid" >
            <div>
                <p> (Receptionist PAGE) </p>
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

                onSubmit={onSubmitHandleFormSelected}
            >
                {/* ADD or EDIT Patient MODE */}
                {!isSetPatientAppt &&
                    <div className="col mt-5" style={{ background: "linear-gradient(#a7acd9, #9e8fb2)" }}>
                        {/* UserId, Username, Password (TODO: USERID Dropdown iterating existing db) */}
                        <div className="form-row">
                            <div className="form-group col">
                                <label>UserId: &nbsp; </label>
                                {/* <input name="userId" type="text" readOnly ref={register} className={`form-control ${errors.userId ? 'is-invalid' : ''}`} /> */}
                                {isEditPatient &&
                                    <input name="user_id" type="text" onChange={handleFormChangeAddEdit} />
                                }
                                {/* <div className="invalid-feedback">{errors.userId?.message}</div> */}
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
                                    <input name="patient_password" type="password" onChange={handleFormChangeAddEdit} />
                                    {/* <div className="invalid-feedback">{errors.password?.message}</div> */}
                                    {isEditPatient &&
                                        (!showPassword
                                            ? <span> -- <a onClick={() => setShowPassword(!showPassword)} className="text-primary"> Show </a></span>
                                            : <em> {formDataAddEdit.password} </em>
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
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>
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
                        <label style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}> <b> User IDs &nbsp; </b> </label>
                        <div className="form-group row">
                            <div className="form-group col">
                                <label>Appointment: &nbsp; </label>
                                <input name="appointment_userId" type="text" onChange={handleFormChangeSet} />
                            </div>
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
                        <label style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}> <b> Appointment Details &nbsp; </b> </label>
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
                    <Link to={isEditPatient ? '.' : (isSetPatientAppt ? '..' : '...')} className="btn btn-link">Cancel</Link>
                </div>
            </form>
        </div>
    );
    return receptionistForm;
}
export default Receptionist;