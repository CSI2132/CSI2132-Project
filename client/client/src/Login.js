import { useState } from "react";
import { Form } from "react-bootstrap";

async function handleLogin(event, username, password) {
  event.preventDefault();

  const data = {
    username,
    password
  };

  const response = await fetch("/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (response.ok) {
    const data = await response.json();
    if (data && data.userId) {
      localStorage.setItem("userId", data.userId);
      // const role = document.getElementById("roleSelector").value;
      // switch (role.value) {
      //   case "employee":
      // }
      // window.location.href("/home");
    }
  }
  //todo: show when login fails
}



function Login(props) {
  const [user, setUser] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const loginForm = (
    <div className="container-fluid">
      <h2 className="text-center">Modal Login Form (Login.js)</h2>
      <div className="row mt-5">
        <Form
          className="col-8 m-auto "
          id="myForm"
          method="POST"
          onSubmit={(e) => handleLogin(e, user, password)}
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
