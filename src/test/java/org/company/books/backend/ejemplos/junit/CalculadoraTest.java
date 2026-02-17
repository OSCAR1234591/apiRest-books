package org.company.books.backend.ejemplos.junit;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;

public class CalculadoraTest {

    Calculadora calculadora;

    @BeforeAll
    public static void primero(){
        System.out.println("primero");
    }
    @AfterAll
    public static void ultimo(){
        System.out.println("ultimo");
    }

    @BeforeEach
    public void instanciaObjeto(){
        calculadora= new Calculadora();
        System.out.println("@BeforeEach");
    }
    @AfterEach
    public void despuesTest(){
        System.out.println("@AfterEach");
    }

    @Test
    @DisplayName("prueba que ocupa assertEquals")
    @Disabled("Esta prueba no se ejecutara")
    public void  calculadoraAssertEqualTest(){

        Assertions.assertEquals(2,calculadora.sumar(1,1));
        Assertions.assertEquals(1,calculadora.restar(4,3));
        Assertions.assertEquals(9,calculadora.multiplicar(3,3));
        Assertions.assertEquals(2,calculadora.dividir(4,2));
    }
    @Test
    public void  calculadoraTrueFalse(){

        Assertions.assertTrue(calculadora.sumar(1,1)==2);
        Assertions.assertTrue(calculadora.restar(4,3)==1);
        Assertions.assertFalse(calculadora.multiplicar(3,3)!=9);
        Assertions.assertFalse(calculadora.dividir(4,2)!=2);
    }
}
