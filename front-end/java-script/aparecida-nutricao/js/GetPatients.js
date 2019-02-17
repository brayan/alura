function getPatients(onSuccess, onFail) {
    fetch("http://api-pacientes.herokuapp.com/pacientes")
    .then(response => response.json())
    .then(patients => onSuccess(patients))
    .catch(error => onFail("error :("));
}