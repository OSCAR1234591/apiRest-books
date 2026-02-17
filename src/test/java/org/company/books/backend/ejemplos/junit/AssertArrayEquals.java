package org.company.books.backend.ejemplos.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertArrayEquals {

    @Test
    public void pruebaArregloIguales(){

        String [] arre1 ={"a","b"};
        String [] arre2 ={"a","b"};
        String [] arre3 ={"a","b","c"};

        Assertions.assertArrayEquals(arre1,arre2);
        //Assertions.assertArrayEquals(arre1,arre3);
        //Assertions.assertArrayEquals(arre2,arre3);
    }

}
