function onEnterInputText(text) {
    let patients = document.querySelectorAll(".paciente");
    patients.forEach(function (patient) {
        let tdNome = patient.querySelector(".info-nome");
        let nome = tdNome.textContent;
        const regExp = new RegExp(text, "i");

        if (regExp.test(nome)) {
            patient.classList.remove("invisible");
        } else {
            patient.classList.add("invisible");
        }
    });
}