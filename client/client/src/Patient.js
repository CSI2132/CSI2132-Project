function Patient(props) {
  const [medicalHistory, setMedicalHistory] = useState([]);
  useEffect(async () => {
    const result = await fetch("someApi Endpoint");
    if (result.ok) {
      setMedicalHistory(await result.json());
    }
  });

  const [upcomingAppointments, setUpcomingAppointments] = useState([]);
  useEffect(async () => {
    const result = await fetch("/appointment/getAppointmentByPatientId");
    if (result.ok) {
      setUpcomingAppointments(await result.json());
    }
  });

  return 
  <div>
    <div>
      <h2>
        Upcoming Appointments
      </h2>

      {getUpcomingAppointments(upcomingAppointments)}
    </div>

    <div>
      <h2>
        Medical History
      </h2>

      {getMedicalHistory(medicalHistory)}
    </div>
  </div>
}

function getMedicalHistory(records) {
  const labels = [

  ];

  const keys = [
    "appointment_type",
    "treatment_type",
    "medication",
    "symptoms",
    "tooth",
    "comments",
    "progress_notes",
    "treatment_date",
    "treatment_description",
    "tooth_involved",

  ];

  // use <summary>

  const result = [];
  for (const record of records) {
    result.push(
      <div>
      </div>
    );
  }
}

function getUpcomingAppointments(appointments) {
  const result = [];
  for (const appointment of appointments) {
    result.push(
      <tr>
        <td>
          {appointment["aa.appointment_date"]}
        </td>
        <td>
          {appointment["aa.start_time"]}
        </td>
        <td>
          {appointment["aa.end_time"]}
        </td>
        <td>
          {appointment["aa.assigned_room"]}
        </td>
        <td>
          {appointment["aa.appointment_type"]}
        </td>
        <td>
          {appointment["dentist.first_name"] + appointment["dentist.last_name"]}
        </td>
        <td>
          {appointment["hygenist.first_name"] + appointment["hygenist.last_name"]}
        </td>
      </tr>
    );
  }

  return 
    <table>
      <thead>
        <tr>
            <th>Appointment Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Room</th>
            <th>Type</th>
            <th>Dentist</th>
            <th>Hygenist</th>
        </tr>
      </thead>
      <tbody>
        {result}
      </tbody>
    </table>
}

export default Patient;