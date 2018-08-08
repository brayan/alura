console.log("Hello World");
var titulo = document.querySelector(".titulo");
console.log(titulo);


var paciente = document.querySelector(".paciente");
var peso = paciente.querySelector(".info-peso").textContent;
var altura = paciente.querySelector(".info-altura").textContent;
paciente.querySelector(".info-imc").textContent = calcularIMC(peso, altura);

validarPeso(peso);
validarAltura(altura);

function calcularIMC(peso, altura) {
    return peso / (altura * altura);
}

function validarPeso(peso) {
    if (peso <= 0 || peso >= 1000) {
        console.log("Peso inválido");
    }
}

function validarAltura(altura) {
    if (altura <= 0 || altura >= 3) {
        console.log("Altura inválida");
    }
}