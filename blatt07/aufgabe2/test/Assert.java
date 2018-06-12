package test;

import java.util.Iterator;

/**
 * Assertion-methods which compare 2 arguments by optionally using several
 * additional help-arguments. If the correctness of the assertion is not given, a
 * failure will be printed on the standard system output. Else nothing happens.
 * <br>
 *
 * @author Mathias Menninghaus (mathias.mennighaus@uos.de)
 */
public class Assert {

   public static void assertEquals(String[] expected, String[] actual) {
      if (expected.length != actual.length) {
         System.out.println("FAIL expected length " + expected.length
               + " but was " + actual.length);
      } else {
         boolean fail = false;
         for (int i = 0; i < expected.length && !fail; i++) {
            if (!expected[i].equals(actual[i])) {
               System.out.println("FAIL at pos " + i + " expected "
                     + expected[i] + " but was " + actual[i]);
               fail = true;
            }
         }
      }
   }

   public static void assertEquals(String expected, String actual) {
      if (!expected.equals(actual)) {
         System.out.println("FAIL [expected: " + expected + " but was "
               + actual + "]");
      }
   }

   public static void assertEquals(Object expected, Object actual) {
      if (!expected.equals(actual)) {
         System.out.println("FAIL [expected: " + expected + " but was "
               + actual + "]");
      }
   }

   public static void assertEquals(double expected, double actual, double
         epsilon) {
      if (expected != actual && Math.abs(expected - actual) > epsilon) {
         System.out.println("FAIL expected " + expected + " but was " + actual);
      }
   }

   public static void assertEquals(int expected, int actual) {
      if (expected != actual) {
         System.out.println("FAIL expected " + expected + " but was " + actual);
      }
   }

   public static void assertTrue(boolean actual) {
      if (!actual) {
         System.out.println("FAIL not true");
      }
   }

   public static void assertFalse(boolean actual) {
      if (actual) {
         System.out.println("FAIL not false");
      }
   }

   /**
    * Compares two arrays with the same length element by element if they are
    * equal to each other.
    *
    * @param expected the expected array of elements
    * @param actual   the array of elements which should be tested on equality
    */
   public static <E> void arrayEquals(E[] expected, E[] actual) {
      if (expected.length != actual.length) {
         System.out.println("FAIL length expected: " + expected.length
               + " but was " + actual.length);
      } else {
         for (int i = 0; i < actual.length; i++) {
            if (expected[i] != actual[i]) {
               System.out.println("FAIL at pos " + i + " expected "
                     + expected[i] + " but was " + actual[i]);
               break;
            }
         }
      }
   }

   /**
    * Compares an array to an iterable with the same length element by element
    * if they are equal to each other.
    *
    * @param expected the expected array of elements
    * @param actual   the iterable of elements which should be tested on
    *                 equality
    */
   public static <E> void assertEquals(E[] expected, Iterable<E> actual) {
      int i = 0;
      Iterator<E> iter = actual.iterator();
      while (i < expected.length && iter.hasNext()) {
         Assert.assertEquals(expected[i], iter.next());
         i++;
      }
      if ((i != expected.length) != iter.hasNext()) {
         System.out.println("FAIL expected for hasNext "
               + (i != expected.length) + " but was " + iter.hasNext());
      }
   }
}
