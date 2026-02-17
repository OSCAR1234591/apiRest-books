package org.company.books.backend.ejemplos.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTrueFalseTeoria {

    @Test
    public void test1(){

        Assertions.assertTrue(true);
        Assertions.assertFalse(false);

    }

    @Test
    public void test2(){

        boolean expresion= 4==4;
        boolean expresion2= 4==43;

        Assertions.assertTrue(expresion);
        Assertions.assertFalse(expresion2);
    }
}
