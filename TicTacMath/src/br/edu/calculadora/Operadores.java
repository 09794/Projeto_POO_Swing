/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.calculadora;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author ArthurDevA
 */

public class Operadores {
    
    private String operador="igual", situacao="a", acumulador, valor;
    private final BigDecimal zero = new BigDecimal("0.0");
    private BigDecimal acc = new BigDecimal("0.0");
    private BigDecimal vlr = new BigDecimal("0.0");
    
    protected String getSituacao() {
        return situacao;
    }
    
    protected void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    protected void setOperador(String operador){
        this.operador = operador;
    }
    
    protected void OperadorBotao(String operador, javax.swing.JTextField telaNumerica) {
        if (!telaNumerica.getText().equals("")){
            if ((situacao.equals("a") || situacao.equals("b")) && (!operador.equals("igual"))){
                acumulador = telaNumerica.getText();
                situacao = "";
                try {
                acc = new BigDecimal(acumulador);
                telaNumerica.setText("");
                } catch (NumberFormatException e){
                    telaNumerica.setText("Erro de formatação");
                    situacao = "b";
                }
            } else if (!operador.equals("igual")){
                valor = telaNumerica.getText();
                try {
                vlr = new BigDecimal(valor);
                situacao = "b";
                telaNumerica.setText(intDoubleBigDecimal(operacoes(this.operador, acc , vlr, telaNumerica)).toPlainString());
                } catch (NumberFormatException e){
                    telaNumerica.setText("Erro de formatação");
                    situacao = "b";
                }
            } else if (!situacao.equals("a") && (operador.equals("igual"))) {
                valor = telaNumerica.getText();
                try {
                vlr = new BigDecimal(valor);
                situacao = "b";
                telaNumerica.setText(intDoubleBigDecimal(operacoes(this.operador, acc , vlr, telaNumerica)).toPlainString());
                } catch (NumberFormatException e){
                    telaNumerica.setText("Erro de formatação");
                    situacao = "b";
                }
            }
            this.operador = operador;
        }
    }
    
    private boolean isInt(BigDecimal num){
        BigDecimal num2 = new BigDecimal(num.intValue());
        BigDecimal resto = num.subtract(num2);
        return resto.compareTo(zero) == 0;
    }
    
    private BigDecimal intDoubleBigDecimal(BigDecimal num) {
        if (isInt(num)) {
            return new BigDecimal(num.intValue());
        } else {
            String numString = num.toPlainString();
            while ((numString.substring(numString.length() -1, numString.length())).equals("0")) {
                numString = numString.substring(0, numString.length() - 1);
            }
            num = new BigDecimal(numString);
            return num;
        }
    }
    
    private BigDecimal operacoes(String operacao, BigDecimal acc, BigDecimal valor, javax.swing.JTextField telaNumerica) {
        switch (operacao) {
            case "mais":
                return acc.add(valor);
            case "menos":
                return acc.subtract(valor);
            case "vezes":
                return acc.multiply(valor);
            case "dividir":
                try {
                    return acc.divide(valor, 9, RoundingMode.CEILING);
                } catch (ArithmeticException e) {
                    telaNumerica.setText("Impossível dividir por 0");
                }
            default:
                return null;
        }
            
    }
}