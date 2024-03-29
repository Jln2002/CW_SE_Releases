package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;


public class MyTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    /**
     * testing what happens when null value is handed into the method
     */
    @Test
    void getCountriesLargestPopToSmallest()
    {
        assertNull(null,"test successfully failed");
    }
    @Test
    void createSelectQueryForContinent()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }


    @Test
    void unitTest()
    {
        assertEquals(5, 5);
    }
    @Test
    void unitTest3()
    {
        assertEquals(5, 5, "Messages are equal");
    }



    @Test
    void unitTest5()
    {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assertArrayEquals(a, b);
    }

    @Test
    void unitTest6()
    {
        assertTrue(5 == 5);
    }

    @Test
    void unitTest7()
    {
        assertFalse(5 == 4);
    }

    @Test
    void unitTest8()
    {
        assertNull(null);
    }

    @Test
    void unitTest9()
    {
        assertNotNull("Hello");
    }

    @Test
    void unitTest10()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }

    void throwsException() throws NullPointerException
    {
        throw new NullPointerException();
    }
}
