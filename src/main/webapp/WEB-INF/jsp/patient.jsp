<!-- !<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%> -->
<!DOCTYPE html>
<html>
   <head>
      <title>Patient Management</title>
   </head>

   <body>
      <h2>Spring Page Redirection</h2>
      <p>Click below button to redirect the result to new page</p>
      
   
      
      <!-- 
      medical history -> list of past appointments, click one to see medical details
      upcoming appointments -> shown right away
      schedule with dentists -> shown right away (hours when specific dentists are available? idk about this one, maybe not in db
       -->

       <!-- <% out.print("test") %> -->

       <!-- in controller: model.addAttribute("appointments", appointmentService.getUpcomingAppointments()); -->


       <!-- 
      <button type="medicalHistoryBttn"> Medical History </button>
      <button type="upcomingAppntBttn"> Upcoming Appointment </button>
      <button type="scheduleDentistBttn"> Dentist Schedule </button> -->


      <!-- Table showing Appointment Details -->
      <table>
         <thead>
            <tr>
               <th>Time</th>
               <th>Dentist Name</th>
               <th>Patient Name</th>
            </tr>
         </thead>
      
         <tbody>
            <c:forEach items="${appointments}" var="appointment">
               <p>
                  ${appointment.getTime()}
                  ${appointment.getDentist().getName()}
                  ${appointment.getPatient().getLastName()}
               </p>
               </cc:forEach>
         </tbody>
      </table>
       
   </body>

</html>