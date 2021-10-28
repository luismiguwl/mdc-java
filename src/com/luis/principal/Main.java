package com.luis.principal;

import com.luis.mdc.operacional.MDC;

public class Main {

    public static void main(String[] args) {
        int[] numeros = {12, 21, 24};
        MDC mdc = new MDC(numeros);

        int resultado = mdc.calcular();
        mdc.exibirResultadoDaMultiplicacaoDosNumerosSeHouverMaisDeUmNumeroQueDividePorTodos();
    }
}
