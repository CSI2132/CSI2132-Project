import { useState } from "react";
import { Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

async function handleLogin(event, navigate, username, password, userType) {
  event.preventDefault();

  const data = {
    username,
    password,
    role: getRoleNumber(userType)
  };
  console.log("made to here navi")

  const response = await fetch("http://localhost:8080/login", {
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
      // localStorage.getItem("userId")
      console.log("made to here navi")

      if(userType == "dentist"){
        console.log("made to dentist navi")
        navigate("/dentist", { userId: data.userId });
      }

      if (userType === "patient") {
        console.log("made to patient navi")
        navigate("/patient", { userId: data.userId });
      }
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
  const [userType, setUserType] = useState("patient");
  const navigate = useNavigate();

  const loginForm = (
    <div className="container-fluid">
      <h2 className="text-center">Modal Login Form</h2>
      <div className="row mt-5">
        <Form
          className="col-8 m-auto "
          id="myForm"
          method="POST"
          onSubmit={(e) => handleLogin(e, navigate, user, password, userType)}
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
            <select id="roleSelector" aria-labelledby="dropdownMenuButton" onChange={e => setUserType(e.target.value)}>
              <option value="patient">Patient</option>
              <option value="receptionist">Receptionist</option>
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

function getRoleNumber(role) {
  switch (role) {
    case "patient":
      return 0;
    case "dentist":
      return 1;
    case "hygienist":
      return 2;
    case "receptionist":
      return 3;
    case "branchManager":
      return 4;
  }
}

export default Login;
