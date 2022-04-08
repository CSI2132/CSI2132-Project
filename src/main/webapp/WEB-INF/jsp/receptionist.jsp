<!-- !<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%> -->
<!DOCTYPE html>
<html>
   <head>
      <title>Spring Page Redirection</title>
   </head>

   <body>
      <h2>Spring Page Redirection</h2>
      <p>Click below button to redirect the result to new page</p>
      

      <!-- Add patient -->
      <c:url var="add_patient_url" value="/patient/"/>
      <form:form action="${add_patient_url}" method="post" modelAttribute="patient">
          <form:label path="patient_address">Patient Address: </form:label> <form:input path="patient_address"/>
          <form:label path="first_name">First Name: </form:label> <form:input path="first_name"/>
          <form:label path="last_name">Last Name: </form:label> <form:input path="last_name"/>
          <form:label path="gender">Gender: </form:label> <form:input path="gender"/>
          <form:label path="insurance">Insurance: </form:label> <form:input path="insurance"/>
          <form:label path="ssn">SSN: </form:label> <form:input path="ssn"/>
          <form:label path="email_address">Email Address: </form:label> <form:input path="email_address"/>
          <form:label path="date_of_birth">Date of Birth: </form:label> <form:input type="date" path="date_of_birth"/>

          <input type="submit" value="submit"/>
      </form:form>

      <!-- Edit Patient -->
      


      <!-- Set patient appointments -->
      <c:url var="set_patientAppt_url" value="/appointment/add"/>
      <form:form action="${set_patientAppt_url}" method="post" modelAttribute="appointment">
          <form:label path="patient_user_id">Patient User ID: </form:label> <form:input path="patient_user_id" aria-readonly="true"/>
          <form:label path="dentist_user_id">Dentist User ID: </form:label> <form:input path="dentist_user_id" aria-readonly="true"/>
          <form:label path="hygienist_user_id">Hygienist User ID: </form:label> <form:input path="hygienist_user_id" aria-readonly="true"/>
          <form:label path="appointment_date">Appointment Date: </form:label> <form:input path="appointment_date" type="date"/>
          <form:label path="start_time">Start Time: </form:label> <form:input path="start_time"/>
          <form:label path="end_time">End Time: </form:label> <form:input path="end_time"/>
          <form:label path="appointment_type">Appointment Type: </form:label> <form:input path="appointment_type"/>
          <form:label path="appointment_status">Appointment Status: </form:label> <form:input path="appointment_status"/>
          <form:label path="assigned_room">Assigned Room: </form:label> <form:input path="assigned_room"/>

          <input type="submit" value="submit"/>
      </form:form>


   </body>
   
</html>