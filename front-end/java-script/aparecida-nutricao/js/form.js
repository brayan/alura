const onClickAddPatient = () => {
    clearErrorMessage();
    const form = document.querySelector("#form-adiciona");
    const patient = createPatientFromForm(form);

    console.log(form);
    console.log(patient);

    if (checkAndHandleErrors(patient)) {
        return;
    }

    addPatientInTheTable(patient);
    form.reset();
}

const createPatientFromForm = (form) => {
    return {
        nome: form.nome.value,
        peso: form.peso.value,
        altura: form.altura.value,
        gordura: form.gordura.value,
        imc: calcularIMC(form.peso.value, form.altura.value)
    };
}

const createTableRow = (patient) => {
    const patientTr = document.createElement("tr");
    patientTr.classList.add("paciente");

    patientTr.appendChild(createTableColumn(patient.nome, "info-nome"));
    patientTr.appendChild(createTableColumn(patient.peso, "info-peso"));
    patientTr.appendChild(createTableColumn(patient.altura, "info-altura"));
    patientTr.appendChild(createTableColumn(patient.gordura, "info-gordura"));
    patientTr.appendChild(createTableColumn(patient.imc, "info-imc"));

    return patientTr;
}

const createTableColumn = (content, newClass) => {
    const column = document.createElement("td");
    column.textContent = content;
    column.classList.add(newClass);
    return column;
}

const checkAndHandleErrors = (patient) => {
    const errorMessage = document.querySelector("#errorMessage");

    if (!isPesoValido(patient.peso)) {
        errorMessage.textContent = "O Peso é inválido";
        return true;
    }

    if (!isAlturaValida(patient.altura)) {
        errorMessage.textContent = "A Altura é inválida";
        return true;
    }
}

const clearErrorMessage = () => {
    document.querySelector("#errorMessage").textContent = "";
    console.log("clearErrorMessage");
}

const addPatientInTheTable = (patient) => {
    const patientTr = createTableRow(patient);
    const patientsTable = document.querySelector("#tabela-pacientes");
    patientsTable.appendChild(patientTr);

    console.log(patientTr);
    console.log(patientsTable);
}