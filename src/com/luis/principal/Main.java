package com.luis.principal;

import com.luis.mdc.operacional.MDC;

public class Main {

    public static void main(String[] args) {
        int[] numeros = {18, 60};
        MDC mdc = new MDC(numeros);
        
        int resultado = mdc.calcular();

        System.out.println("MDC: " + resultado);
    }
}
