import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
// https://jasonwatmore.com/post/2020/10/14/react-hook-form-combined-add-edit-create-update-form-example ???

function Receptionist(){ 

    //-- Set Form FIELDS --
    const [formData, setFormData] = useState({
        userId:  "",
        username: "",
        patientPassword: "",
        patient_address: "",
        firstName: "",
        lastName: "",
        gender: "",
        insurance: "",
        ssn: "",
        email: "",
        dob: "",
    });
    const [showPassword, setShowPassword] = useState(false); //-- Show password function --

    //-- Gets entire FORM DATA as payload --
    function handleFormChange(event)
     {
         setFormData({
             ...formData, //... = spread operator 
             [event.target.name]: event.target.value
         })
     }

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


    //-- DETERMINING DROPDOWN SELECTED: ADD when no patientId exists,   EDIT selected patient id,   set patient appt --
    const [receptionistOption, setReceptionistOption] = useState("");  
    console.log(receptionistOption); //DEBUGING PURPOSES
    //-- Helper vars --
    // const isAddPatient = receptionistOption === "addPatient"; 
    const isEditPatient = receptionistOption === "editPatient"; 
    const isSetPatientAppt = receptionistOption === "setPatientAppointment"; 

    //See which option selected to submit (ON SUBMIT)
    async function onSubmitHandleFormSelected (event){
        console.log(formData); //DEBUGING PURPOSES
        console.log(receptionistOption); //DEBUGING PURPOSES

        switch (receptionistOption) {
            case "addPatient":
                <p>HELLOW</p>

                break;
            case "editPatient":
                <p>World</p>

                break;
            case "setPatientAppointment":
                <p>TEST</p>

                // window.location.href("/patientAppointment");
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
        <div className="container-fluid">
            <div> 
                <p> (Receptionist Selected) </p>
                <h2 className="text-center"> {isEditPatient ? '[EDIT]' : (isSetPatientAppt ? '[SET]' : '[ADD]') } Patient {isSetPatientAppt ? 'Appointment' : 'Information' } </h2>
                <div>
                    <div className="mb-auto text-center">  <i> Select Functionality: </i>
                        <select id="receptionistFunctions" aria-labelledby="dropdownMenuButton"  onChange={(event)=> {setReceptionistOption(event.target.value)}}>
                            <option value="addPatient">Add Patient</option>
                            <option value="editPatient">Edit Patient</option>
                            <option value="setPatientAppointment">Set Patient Appointment</option>
                        </select>
                    </div>
                </div>
            </div>

            <div className="col mt-5">
                <form
                    className="col-8 m-auto"
                    id="receptionistForm"
                    onSubmit={onSubmitHandleFormSelected} 
                >
                    {/* UserId, Username, Password */}
                    <div className="form-row">
                        <div className="form-group col">
                            <label>UserId: &nbsp; </label>
                            {/* <input name="userId" type="text" readOnly ref={register} className={`form-control ${errors.userId ? 'is-invalid' : ''}`} /> */}
                           {isEditPatient &&
                            <input name="userId" type="text" onChange={handleFormChange} />
                           }
                            {/* <div className="invalid-feedback">{errors.userId?.message}</div> */}
                        </div>
                        <div className="form-group col">
                            <label>Username: &nbsp; </label>
                            {/* <input name="Username" type="text" ref={register} className={`form-control ${errors.firstName ? 'is-invalid' : ''}`} /> */}
                            <input name="username" type="text" onChange={handleFormChange} />
                            {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                        </div>

                        {/* Password */}  
                        <div className="form-col">
                            <div className="form-group col">
                                <label>
                                    Password: &nbsp; 
                                </label>
                                {/* <input name="password" type="password" ref={register} className={`form-control ${errors.password ? 'is-invalid' : ''}`} /> */}
                                <input name="password" type="password" onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.password?.message}</div> */}
                                {isEditPatient &&
                                    (!showPassword
                                        ? <span> -- <a onClick={() => setShowPassword(!showPassword)} className="text-primary"> Show </a></span>
                                        : <em> {formData.password} </em>
                                    )
                                }
                            </div>
                            {isEditPatient &&
                                <div className="form-group col">
                                    <label>Confirm Password: &nbsp; </label>
                                    {/* <input name="confirmPassword" type="password" ref={register} className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`} /> */}
                                    <input name="confirmPassword" type="password" onChange={handleFormChange} />
                                    {/* <div className="invalid-feedback">{errors.confirmPassword?.message}</div> */}
                                    <p> (Leave blank to keep the same password) </p>
                                </div>
                            }
                        </div>
                    </div>
                    
                    {/* First Name, Last Name, Gender, Email Address */}
                    <div className="form-row">
                        <div className="form-group col">
                            <label>First Name: &nbsp; </label>
                            <div>
                                {/* <input name="firstName" type="text" ref={register} className={`form-control ${errors.firstName ? 'is-invalid' : ''}`} /> */}
                                <input name="firstName" type="text" onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Last Name: &nbsp; </label>
                            <div>
                                {/* <input name="lastName" type="text" ref={register} className={`form-control ${errors.lastName ? 'is-invalid' : ''}`} /> */}
                                <input name="lastName" type="text" onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.lastName?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Email: &nbsp; </label>
                            <div>
                            {/* <input name="email" type="text" ref={register} className={`form-control ${errors.email ? 'is-invalid' : ''}`} /> */}
                                <input name="email" type="text"  onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.email?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Gender: &nbsp; {"\n"} </label>
                            {/* <select name="gender" ref={register} className={`form-control ${errors.gender ? 'is-invalid' : ''}`}> */}
                            <div>
                                <select name="gender" onChange={handleFormChange}>
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
                                <input name="address" type="text" onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.address?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Date Of Birth: &nbsp; </label>
                            <div>
                                {/* <input name="dob" type="text" ref={register} className={`form-control ${errors.dob ? 'is-invalid' : ''}`} /> */}
                                <input name="dob" type="date"  onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.dob?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>SSN: &nbsp; </label>
                            <div>
                                {/* <input name="ssn" type="text" ref={register} className={`form-control ${errors.ssn ? 'is-invalid' : ''}`} /> */}
                                <input name="ssn" type="number"  onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.ssn?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Insurance: &nbsp; </label>
                            <div>
                            {/* <input name="insurance" type="text" ref={register} className={`form-control ${errors.insurance ? 'is-invalid' : ''}`} /> */}
                                <input name="insurance" type="text" onChange={handleFormChange} />
                                {/* <div className="invalid-feedback">{errors.insurance?.message}</div> */}
                            </div>
                        </div>
                    </div>


                    <div className="form-group">
                        {/* <button type="submit" disabled={formState.isSubmitting} className="btn btn-primary"> */}
                        <button type="submit" className="btn btn-primary" > 
                            {/* {formState.isSubmitting && <span className="spinner-border spinner-border-sm mr-1"></span>} */}
                            {isEditPatient ? 'Save' : (isSetPatientAppt ? 'Set' : 'Add')} Details
                        </button>
                        <Link to={isEditPatient ? '.' : (isSetPatientAppt ? '..' : '...')} className="btn btn-link">Cancel</Link>
                    </div>                    
                    

                </form>
            </div>
        </div>
    );
    
    return receptionistForm;
}

export default Receptionist;