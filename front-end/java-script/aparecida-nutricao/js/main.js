window.onload = function () {
    inicializarListeners();
    preencherImcDosPacientes();
}

function preencherImcDosPacientes() {
    var pacientes = document.querySelectorAll(".paciente");

    for (var i = 0; i < pacientes.length; i++) {
        preencherImc(pacientes[i]);
    }
}

function preencherImc(paciente) {
    var peso = paciente.querySelector(".info-peso").textContent;
    var altura = paciente.querySelector(".info-altura").textContent;
    var imcView = paciente.querySelector(".info-imc");

    if (peso <= 0 || peso >= 1000) {
        imcView.textContent = "Peso inválido";
        paciente.classList.add("paciente-invalido");
        return;
    }

    if (altura <= 0 || altura >= 3) {
        imcView.textContent = "Altura inválida";
        paciente.classList.add("paciente-invalido");
        return;
    }

    imcView.textContent = calcularIMC(peso, altura);
}

function calcularIMC(peso, altura) {
    var imc = peso / (altura * altura);
    return imc.toFixed(2);
}

function inicializarListeners() {
    var btnAdicionar = document.querySelector(".bto-principal");
    btnAdicionar.addEventListener("click", function (event) {
        event.preventDefault();
        console.log("Oi, eu sou o botão!")
    });
}