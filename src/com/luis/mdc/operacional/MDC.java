package com.luis.mdc.operacional;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class MDC {

    private int[] numeros;
    private int mdc;
    private int primoAtual;
    private Vector<Integer> divisoresDeTodos;

    public MDC(int[] numeros) {
        this.numeros = numeros;
        mdc = 1;
        primoAtual = 2;
        divisoresDeTodos = new Vector<>(numeros.length);
    }

    public int calcular() {
        if (todosOsNumerosSaoIguais()) {
            return numeros[0];
        }

        while (numerosSaoDiferentesDeUm()) {
            if (primoAtualEhDivisorDeAlgumNumero()) {
                exibirMacete();

                if (primoAtualEhDivisorDeTodosOsNumeros()) {
                    mdc *= primoAtual;
                    divisoresDeTodos.add(primoAtual);
                }

                dividirNumerosPeloPrimoAtual();
            } else {
                primoAtual = obterProximoPrimo();
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

    private void dividirNumerosPeloPrimoAtual() {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % primoAtual == 0) {
                numeros[i] /= primoAtual;
            }
        }
    }

    private boolean primoAtualEhDivisorDeAlgumNumero() {
        return Arrays.stream(numeros)
                .anyMatch(numero -> numero % primoAtual == 0);
    }

    private boolean primoAtualEhDivisorDeTodosOsNumeros() {
        return Arrays.stream(numeros)
                .allMatch(numero -> numero % primoAtual == 0);
    }

    private int obterProximoPrimo() {
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

    private void exibirMacete() {
        String[] numerosEmString = converterNumerosParaArrayDeString();
        String macete = String.join(", ", numerosEmString) + " | " + primoAtual;
        System.out.print(macete);

        if (primoAtualEhDivisorDeTodosOsNumeros()) {
            System.out.println(" -> divisor de todos");
        } else {
            System.out.println("");
        }
    }

    private String[] converterNumerosParaArrayDeString() {
        String[] array = new String[numeros.length];

        for (int i = 0; i < numeros.length; i++) {
            array[i] = Integer.toString(numeros[i]);
        }

        return array;
    }

    public void exibirResultadoDaMultiplicacaoDosNumerosSeHouverMaisDeUmNumeroQueDividePorTodos() {
        if (divisoresDeTodos.size() > 1) {
            String multiplicacao = divisoresDeTodos.stream()
                    .map(numero -> Integer.toString(numero))
                    .collect(Collectors.joining(" * "));

            System.out.println("\n" + multiplicacao + " = " + mdc + "\n");
        } else {
            System.out.println("\nMDC: " + mdc);
        }
    }
}
