function addDoubleClickListener() {
    let patients = document.querySelectorAll(".paciente");
    patients.forEach(function (patient) {
        patient.addEventListener("dblclick", function () {
            this.classList.add("fadeOut");
            setTimeout(function() {
                patient.remove();
            }, 500);
        });
    });
    
}