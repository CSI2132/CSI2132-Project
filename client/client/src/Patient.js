function Patient(props) {
  const [pastAppointments, setPastAppointments] = useState([]);
  useEffect(async () => {
    const result = await fetch("someApi Endpoint");
    if (result.ok) {
      setPastAppointments(await result.json());
    }
  });

  const [upcomingAppointments, setUpcomingAppointments] = useState([]);
  useEffect(async () => {
    const result = await fetch("/appointment/getAppointment");
    if (result.ok) {
      setUpcomingAppointments(await result.json());
    }
  });

  return 
  <div>
    <div>
      <h2>
        Past Appointments
      </h2>

      {getPastAppointments(pastAppointments)}
    </div>

    <div>
      <h2>
        Upcoming Appointments
      </h2>

      {getUpcomingAppointments(upcomingAppointments)}
    </div>
  </div>
}

function getPastAppointments(appointments) {
  const result = [];
  for (const appointment of appointments) {
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
      <div>
      </div>
    );
  }
}

export default Patient;