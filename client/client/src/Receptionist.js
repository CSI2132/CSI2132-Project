import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
// import { useForm } from "react-hook-form";
// import { yupResolver } from '@hookform/resolvers/yup';
// import * as Yup from 'yup';

function Receptionist(){ //({history, match})
    //// const [formData, setFormData] = useState({
    ////     user_id:  null,
    ////     username: "",
    ////     patient_password: "",
    ////     patient_address: "",
    ////     first_name: "",
    ////     last_name: "",
    ////     gender: "",
    ////     insurance: "",
    ////     SSN: "",
    ////     email_address: "",
    ////     date_of_birth: "",
    //// });
    //// function handleReceptionistSubmit(formObject){
    ////     if(formData.id === null){
    ////       // if "id" is null its mean its a new record
    ////       //... create the unique ID here
    ////     }else{
    ////      //  edit the existing record and update
    ////     }
    //// }

    //-- DETERMINE MODE: ADD when no patientId exists,   EDIT selected patient id,   set patient appt) --
    // const { id } = match.params; 
    const { id } = 1;
    const isAddMode = !id;  //&& id does not exist in database

    function onSubmit(data){
        return isAddMode ? addPatientInfo(data) : updatePatientInfo(id, data);
    }

    //-- Add to database --
    function addPatientInfo(data){ 
        // return userService.create(data).then( () => 
        // {
        //     alertService.success('New Patient Added', { keepAfterRouteChange: true });
        //     history.push('.');
        // })
        // .catch(alertService.error); 

        return "addPatientInfo Function"
    }
    
    //-- Modify in database --     
    function updatePatientInfo(id, data){
        // return userService.update(id, data)
        //     .then(() => {
        //         alertService.success('Patient Details Updated', { keepAfterRouteChange: true });
        //         history.push('..');
        //     })
        //     .catch(alertService.error);

        return "updatePatientInfo Function"
    }

    //-- Attributes within Form --
    const [patient, setPatient] = useState({});
    const [showPassword, setShowPassword] = useState(false);
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


    async function handleReceptionistOptions(event){
        const selectedFunction = document.getElementById("receptionistFunctions").value;
        switch (selectedFunction.value) {
            case "addPatient":
                return 'hi';
                break;
            case "editPatient":
                <p>World</p>

                break;
            case "setPatientAppointment":
                // window.location.href("/patientAppointment");
                break;
        } 
    }

    const receptionistForm = (
        <div className="container-fluid">
            <div> 
                <p> (Receptionist Selected) </p>
                <h2 className="text-center"> {isAddMode ? '[ADD]' : '[EDIT]'} Patient Information </h2>
                <div>
                    <div className="mb-auto text-center">  <i> Select Functionality: </i>
                        <select id="receptionistFunctions" aria-labelledby="dropdownMenuButton" onChange={handleReceptionistOptions}>
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
                    // onSubmit={handleReceptionistSubmit(onSubmit)} onReset={reset}
                >
                    {/* UserId, Username, Password */}
                    <div className="form-row">
                        <div className="form-group col">
                            <label>UserId: &nbsp; </label>
                            {/* <input name="userId" type="text" ref={register} className={`form-control ${errors.userId ? 'is-invalid' : ''}`} /> */}
                            <input name="userId" type="text" readOnly />
                            {/* <div className="invalid-feedback">{errors.userId?.message}</div> */}
                        </div>
                        <div className="form-group col">
                            <label>Username: &nbsp; </label>
                            {/* <input name="Username" type="text" ref={register} className={`form-control ${errors.firstName ? 'is-invalid' : ''}`} /> */}
                            <input name="firstName" type="text" />
                            {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                        </div>

                        {/* Password */}  
                        <div className="form-col">
                            <div className="form-group col">
                                <label>
                                    Password: 
                                    {!isAddMode &&
                                        (!showPassword
                                            ? <span> -- <a onClick={() => setShowPassword(!showPassword)} className="text-primary">Show</a></span>
                                            : <em> -- {patient.password}</em>
                                        )
                                    }
                                </label>
                                {/* <input name="password" type="password" ref={register} className={`form-control ${errors.password ? 'is-invalid' : ''}`} /> */}
                                <input name="password" type="password" />
                                {/* <div className="invalid-feedback">{errors.password?.message}</div> */}
                            </div>
                            {!isAddMode &&
                                <div className="form-group col">
                                    <label>Confirm Password: &nbsp; </label>
                                    {/* <input name="confirmPassword" type="password" ref={register} className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`} /> */}
                                    <input name="confirmPassword" type="password"  />
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
                                <input name="firstName" type="text" />
                                {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Last Name: &nbsp; </label>
                            <div>
                                {/* <input name="lastName" type="text" ref={register} className={`form-control ${errors.lastName ? 'is-invalid' : ''}`} /> */}
                                <input name="lastName" type="text" />
                                {/* <div className="invalid-feedback">{errors.lastName?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Email: &nbsp; </label>
                            <div>
                            {/* <input name="email" type="text" ref={register} className={`form-control ${errors.email ? 'is-invalid' : ''}`} /> */}
                                <input name="email" type="text" />
                                {/* <div className="invalid-feedback">{errors.email?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Gender: &nbsp; {"\n"} </label>
                            {/* <select name="gender" ref={register} className={`form-control ${errors.gender ? 'is-invalid' : ''}`}> */}
                            <div>
                                <select name="gender">
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
                                <input name="address" type="text" />
                                {/* <div className="invalid-feedback">{errors.address?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Date Of Birth: &nbsp; </label>
                            <div>
                                {/* <input name="dob" type="text" ref={register} className={`form-control ${errors.dob ? 'is-invalid' : ''}`} /> */}
                                <input name="dob" type="date" />
                                {/* <div className="invalid-feedback">{errors.dob?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>SSN: &nbsp; </label>
                            <div>
                                {/* <input name="ssn" type="text" ref={register} className={`form-control ${errors.ssn ? 'is-invalid' : ''}`} /> */}
                                <input name="ssn" type="number" />
                                {/* <div className="invalid-feedback">{errors.ssn?.message}</div> */}
                            </div>
                        </div>
                        <div className="form-group col">
                            <label>Insurance: &nbsp; </label>
                            <div>
                            {/* <input name="insurance" type="text" ref={register} className={`form-control ${errors.insurance ? 'is-invalid' : ''}`} /> */}
                                <input name="insurance" type="text" />
                                {/* <div className="invalid-feedback">{errors.insurance?.message}</div> */}
                            </div>
                        </div>
                    </div>


                    <div className="form-group">
                        {/* <button type="submit" disabled={formState.isSubmitting} className="btn btn-primary"> */}
                        <button type="submit" className="btn btn-primary">
                            {/* {formState.isSubmitting && <span className="spinner-border spinner-border-sm mr-1"></span>} */}
                            {isAddMode ? 'Add' : 'Save'}
                        </button>
                        <Link to={isAddMode ? '.' : '..'} className="btn btn-link">Cancel</Link>
                    </div>                    
                    

                </form>
            </div>
        </div>
    );
      
    // // functions to build form returned by useForm() hook
    // const { register, handleReceptionistSubmit, reset, setValue, getValues, errors, formState } = useForm({
    //     resolver: yupResolver(validationSchema)
    // });

    return receptionistForm;

}

export default Receptionist;