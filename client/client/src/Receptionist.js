import { Form } from "react-bootstrap";

function Receptionist(props){


    const receptionistForm = (
        <div className="container-fluid">
          
          {/* Title [Edit or Add] */}
          <h2 className="text-center"> Patient Info</h2>

          <div className="row mt-5">

            <Form
              className="col-8 m-auto "
              id="receptionistForm"
            //   onSubmit={handleReceptionistSubmit}
            >
              {/* <div className="form-group">
                <label htmlFor="username">Username</label>
                <input
                  type="text"
                  className="form-control"
                  id="username"
                  placeholder="Enter username"
                  onChange={(event) => setUser(event.target.value)}
                />
    
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input
                  type="password"
                  className="form-control"
                  id="exampleInputPassword1"
                  placeholder="Password"
                  onChange={(event) => setPassword(event.target.value)}
                />
              </div>
    
              <div className="mb-auto"> 
                <select id="roleSelector" aria-labelledby="dropdownMenuButton">
                  <option value="receptionist">Receptionist</option>
                  <option value="employee">Employee</option>
                  <option value="dentist">Dentist</option>
                  <option value={"hygienist"}>
                    Hygienist
                  </option>
                  <option value={"manager"}>
                    Branch Manager
                  </option>
                </select>
              </div>
              
              &nbsp;
              
              <div>
                <button type="submit" className="btn btn-primary" value={"Submit"}>
                    Submit
                </button>
              </div> */}
            </Form>
          </div>
        </div>
      );

    return receptionistForm;

}

export default Receptionist;