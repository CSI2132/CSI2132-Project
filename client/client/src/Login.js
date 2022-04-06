import { useState } from "react";
import { Form } from "react-bootstrap";

async function handleLogin(event) {
  const fd = new FormData(this.state.value); ///Get form data
  const obj = {};
  fd.forEach((value, key) => (obj[key] = value));

  const data = {
    username: document.getElementById("exampleInputEmail1").value,
    password: document.getElementById("exampleInputPassword1").value,
  };

  const response = await fetch("localhost:3000/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (response.ok) {
    const role = document.getElementById("roleSelector").value;
    switch (role.value) {
      case "employee":
    }
    window.location.href("/home");
  }
}



function Login(props) {
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const loginForm = (
    <div className="container-fluid">
      <h2 className="text-center">Modal Login Form</h2>
      <div className="row mt-5">
        <Form
          className="col-8 m-auto "
          id="myForm"
          method="POST"
          onSubmit={(user,password,userType) => handleLogin(user,password,userType)}
        >
          <div className="form-group">
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
          </div>
        </Form>
      </div>
    </div>
  );

  return loginForm;
}

export default Login;
