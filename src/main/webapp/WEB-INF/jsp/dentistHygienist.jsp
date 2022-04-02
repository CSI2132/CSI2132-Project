<!-- !<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%> -->
<!DOCTYPE html>
<html>
   <head>
      <title>Spring Page Redirection</title>
   </head>

   <body>
      <h2>Spring Page Redirection</h2>
      <p>Click below button to redirect the result to new page</p>
      
            <!-- Table showing appointed Patients -->
            <table>
                <thead>
                   <tr>
                      <th>Patient Address</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Gender</th>
                      <th>Insurance</th>
                      <th>SSN</th>
                      <th>Email Address</th>
                      <th>Date of Birth</th>
                   </tr>
                </thead>
             
                <tbody>
                   <c:forEach items="${patients}" var="patient">
                      <p>
                         ${patient.getPatient_address()}
                         ${patient.getFirst_name()}
                         ${patient.getLast_name()}
                         ${patient.getGender()}
                         ${patient.getInsurance()}
                         ${patient.getSSN()}
                         ${patient.getEmail_address()}
                         ${patient.getDate_of_birth()}
                      </p>
                      </cc:forEach>
                </tbody>
             </table>

   </body>
   
</html>