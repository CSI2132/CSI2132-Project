window.addEventListener("load", function () {
  async function sendData(form) {
    const fd = new FormData(form);
    const obj = {};
    fd.forEach((value, key) => (obj[key] = value));

    const data = {
      username: document.getElementById("exampleInputEmail1").value,
      password: document.getElementById("exampleInputPassword1").value
    };

    const response = await fetch("/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data),
    });
    if (response.ok){
        window.location.href('/home');
    }
  }

  // Access the form element...
  const form = document.getElementById("myForm");

  // ...and take over its submit event.
  form.addEventListener("submit", function (event) {
    event.preventDefault();
    sendData(form);
  });
});
