import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
// import { useForm } from "react-hook-form";
// import { yupResolver } from '@hookform/resolvers/yup';
// import * as Yup from 'yup';

//---- NEEED TO REBUILD TO GET/DOWNLOAD NEEDED DEPENDENCIES ----

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

    //-- Determine MODE Receptionist Page is in: ADD or EDIT patient info --
    // const { id } = match.params; //(ADD when no patientId exists, otherwise EDIT)
    const { id } = 1;
    const isAddMode = !id;

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

    const receptionistForm = (
        <div className="container-fluid">
            
            {/* Title [Edit or Add] */}
            <div> 
                <p> (Receptionist Selected) </p>
                <h2 className="text-center"> Patient Information </h2>
                <h5 className="text-center"> Status: {isAddMode ? '[ADDING]' : '[EDITING]'} </h5>
            </div>

            <div className="col mt-5">
                <form
                    className="col-8 m-auto"
                    id="receptionistForm"
                    // onSubmit={handleReceptionistSubmit(onSubmit)} onReset={reset}
                >

                    <div className="form-row">
                        <div className="form-group col">
                            <label>UserId</label>
                            {/* <input name="userId" type="text" ref={register} className={`form-control ${errors.userId ? 'is-invalid' : ''}`} /> */}
                            <input name="userId" type="text" readonly />
                            {/* <div className="invalid-feedback">{errors.userId?.message}</div> */}
                        </div>
                        <div className="form-group col-5">
                            <label>Username</label>
                            {/* <input name="Username" type="text" ref={register} className={`form-control ${errors.firstName ? 'is-invalid' : ''}`} /> */}
                            <input name="firstName" type="text" />
                            {/* <div className="invalid-feedback">{errors.firstName?.message}</div> */}
                        </div>
                        <div className="form-group col-5">
                            <label>Password</label>
                            {/* <input name="lastName" type="text" ref={register} className={`form-control ${errors.lastName ? 'is-invalid' : ''}`} /> */}
                            <input name="lastName" type="text" />
                            {/* <div className="invalid-feedback">{errors.lastName?.message}</div> */}
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col">
                            <label>UserId</label>
                            {/* <select name="title" ref={register} className={`form-control ${errors.title ? 'is-invalid' : ''}`}> */}
                            <select name="userId">
                                <option value=""></option>
                                <option value="Mr">Mr</option>
                                <option value="Mrs">Mrs</option>
                                <option value="Miss">Miss</option>
                                <option value="Ms">Ms</option>
                            </select>
                            {/* <div className="invalid-feedback">{errors.title?.message}</div> */}
                        </div>
                        <div className="form-group col-7">
                            <label>Email</label>
                            {/* <input name="email" type="text" ref={register} className={`form-control ${errors.email ? 'is-invalid' : ''}`} /> */}
                            <input name="email" type="text" />
                            {/* <div className="invalid-feedback">{errors.email?.message}</div> */}
                        </div>
                        <div className="form-group col">
                            <label>Role</label>
                            {/* <select name="role" ref={register} className={`form-control ${errors.role ? 'is-invalid' : ''}`}> */}
                            <select name="role">
                                <option value=""></option>
                                <option value="User">User</option>
                                <option value="Admin">Admin</option>
                            </select>
                            {/* <div className="invalid-feedback">{errors.role?.message}</div> */}
                        </div>
                    </div>
                    {!isAddMode &&
                        <div>
                            <h3 className="pt-3">Change Password</h3>
                            <p>Leave blank to keep the same password</p>
                        </div>
                    }
                    <div className="form-row">
                        <div className="form-group col">
                            <label>
                                Password
                                {!isAddMode &&
                                    (!showPassword
                                        ? <span> - <a onClick={() => setShowPassword(!showPassword)} className="text-primary">Show</a></span>
                                        : <em> - {patient.password}</em>
                                    )
                                }
                            </label>
                            {/* <input name="password" type="password" ref={register} className={`form-control ${errors.password ? 'is-invalid' : ''}`} /> */}
                            <input name="password" type="password" />
                            {/* <div className="invalid-feedback">{errors.password?.message}</div> */}
                        </div>
                        <div className="form-group col">
                            <label>Confirm Password</label>
                            {/* <input name="confirmPassword" type="password" ref={register} className={`form-control ${errors.confirmPassword ? 'is-invalid' : ''}`} /> */}
                            <input name="confirmPassword" type="password"  />
                            {/* <div className="invalid-feedback">{errors.confirmPassword?.message}</div> */}
                        </div>
                    </div>
                    <div className="form-group">
                        {/* <button type="submit" disabled={formState.isSubmitting} className="btn btn-primary"> */}
                        <button type="submit" className="btn btn-primary">
                            {/* {formState.isSubmitting && <span className="spinner-border spinner-border-sm mr-1"></span>} */}
                            Save
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