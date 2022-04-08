window.addEventListener( "load", function () {
    async function sendData(form) {
 
    const fd = new FormData( form);
    const obj = {};
    fd.forEach((value,key) => obj[key] = value);
    const response =   await  fetch("http://localhost:8080/login",{
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            body: JSON.stringify(obj)
        }
    })

j    }
  
    // Access the form element...
    const form = document.getElementById( "myForm" );
  
    // ...and take over its submit event.
    form.addEventListener( "submit", function ( event ) {
      event.preventDefault();
        sendData(form);
    } );
  } );

