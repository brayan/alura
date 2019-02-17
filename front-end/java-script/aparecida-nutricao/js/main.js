window.onload = () => {
    inicializarListeners();
    preencherImcDosPacientes();
}

function preencherImcDosPacientes() {
    var pacientes = document.querySelectorAll(".paciente");
    pacientes.forEach(paciente => preencherImc(paciente));
}

function preencherImc(paciente) {
    var peso = paciente.querySelector(".info-peso").textContent;
    var altura = paciente.querySelector(".info-altura").textContent;
    var imcView = paciente.querySelector(".info-imc");

    if (!isPesoValido(peso)) {
        imcView.textContent = "Peso inválido";
        paciente.classList.add("paciente-invalido");
        return;
    }

    if (!isAlturaValida(altura)) {
        imcView.textContent = "Altura inválida";
        paciente.classList.add("paciente-invalido");
        return;
    }

    imcView.textContent = calcularIMC(peso, altura);
}

function inicializarListeners() {
    const btnAdicionar = document.querySelector("#adicionar-paciente");
    addDoubleClickListener();
    btnAdicionar.addEventListener("click", (event) => {
        event.preventDefault();
        onClickAddPatient();
        preencherImcDosPacientes();
        addDoubleClickListener();
    });

    const filter = document.querySelector("#filter-patients");
    filter.addEventListener("input", function () {
        onEnterInputText(this.value);
    });

    const btSearch = document.querySelector("#buscar-pacientes");
    btSearch.addEventListener("click", function (event) {
        event.preventDefault();
        getPatients(onGetPatientsSuccess, onGetPatientsFail);
    });
}

function onGetPatientsSuccess(patients) {
    patients.forEach(patient => addPatientInTheTable(patient));
}

function onGetPatientsFail() {
    console.log("Erro :((((((");
}