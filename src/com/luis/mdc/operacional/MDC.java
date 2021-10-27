package com.luis.mdc.operacional;

import java.util.Arrays;

public class MDC {

    private int[] numeros;

    public MDC(int[] numeros) {
        this.numeros = numeros;
    }

    public int calcular() {
        int primoAtual = 2;
        int mdc = 1;

        if (todosOsNumerosSaoIguais()) {
            return numeros[0];
        }
        
        while (numerosSaoDiferentesDeUm()) {
            if (primoAtualEhDivisorDeAlgumNumero(primoAtual)) {
                if (primoAtualEhDivisorDeTodosOsNumeros(primoAtual)) {
                    mdc *= primoAtual;
                }

                dividirNumerosPeloPrimoAtual(primoAtual);
            } else {
                primoAtual = obterProximoPrimo(primoAtual);
            }
        }

        return mdc;
    }
    
    private boolean todosOsNumerosSaoIguais() {
        int primeiroNumero = numeros[0];
        return Arrays.stream(numeros).allMatch(numero -> numero == primeiroNumero);
    }

    private boolean numerosSaoDiferentesDeUm() {
        return Arrays.stream(numeros)
                .anyMatch(numero -> numero != 1);
    }

    private void dividirNumerosPeloPrimoAtual(int primoAtual) {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % primoAtual == 0) {
                numeros[i] /= primoAtual;
            }
        }
    }

    private boolean primoAtualEhDivisorDeAlgumNumero(int primoAtual) {
        return Arrays.stream(numeros)
                .anyMatch(numero -> numero % primoAtual == 0);
    }

    private boolean primoAtualEhDivisorDeTodosOsNumeros(int primoAtual) {
        return Arrays.stream(numeros)
                .allMatch(numero -> numero % primoAtual == 0);
    }

    private int obterProximoPrimo(int primoAtual) {
        int numeroAtual = primoAtual + 1;

        while (!numeroEhPrimo(numeroAtual)) {
            numeroAtual++;
        }

        return numeroAtual;
    }

    private boolean numeroEhPrimo(int numero) {
        if (numero < 2) {
            return false;
        }

        if (numero > 2) {
            for (int i = 2; i < (numero / 2) + 1; i++) {
                if (numero % i == 0) {
                    return false;
                }
            }
        }

        return true;
    }

}
