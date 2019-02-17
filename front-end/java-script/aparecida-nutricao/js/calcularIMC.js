function calcularIMC(peso, altura) {
    var imc = peso / (altura * altura);
    return imc.toFixed(2);
}

function isPesoValido(peso){
    return (peso > 0 && peso < 1000);
}

function isAlturaValida(altura){
    return (altura > 0 && altura < 3);
}