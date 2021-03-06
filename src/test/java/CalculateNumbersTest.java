import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculateNumbersTest {
    CalculateNumbers calculateNumbers = new CalculateNumbers();

    @Test
    public void addNumbersTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("");
        assertEquals(result, 0);
        result = calculateNumbers.Add("1,3");
        assertEquals(result, 4);
        result = calculateNumbers.Add("1,9");
        assertEquals(result, 10);
        result = calculateNumbers.Add("1,2,3,4,5");
        assertEquals(result, 15);
    }
    @Test
    public void addNumbersWithMultipleDelimiterTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("1,2,3,4,5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1,2\n3,4,5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1,2\n3;4,5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1,2\n3;4@5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1!2\n3;4,5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1!2\n3;4*5");
        assertEquals(result, 15);
    }
    @Test
    public void addNumbersWithMultipleDelimitersTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("//;\n1;2");
        assertEquals(result, 3);
        result = calculateNumbers.Add("//@\n1@2@5@9@100");
        assertEquals(result, 117);
        result = calculateNumbers.Add("//@\n1@2@500@9@100");
        assertEquals(result, 612);


    }
    @Test
    public void addNumbersWithNegativeNumbersTest() throws  Exception{
        int result;
        try{
            result = calculateNumbers.Add("//?\n1?2?5?9?-100");
        } catch (Exception e){
            Boolean exceptionThrown = e.toString().contains("-100");
            System.out.println(e.toString());
            assertTrue(exceptionThrown);
        }
    }
    @Test
    public void addNumbersWithMultipleNegativeNumbersTest() throws  Exception{
        int result;
        try{
            result = calculateNumbers.Add("//?\n1?2?5?9?-100?-4?-8");
        } catch (Exception e){
            Boolean exceptionThrown = e.toString().contains("-100,-4,-8");
            System.out.println(e.toString());
            assertTrue(exceptionThrown);
        }
        try{
            result = calculateNumbers.Add("//*\n1*2*5*9*-100*-4*-8");
        } catch (Exception e){
            Boolean exceptionThrown = e.toString().contains("-100,-4,-8");
            System.out.println(e.toString());
            assertTrue(exceptionThrown);
        }
        try{
            result = calculateNumbers.Add("//)\n1)5)9)-100)-9)-8");
        } catch (Exception e){
            Boolean exceptionThrown = e.toString().contains("-100,-9,-8");
            System.out.println(e.toString());
            assertTrue(exceptionThrown);
        }
    }
    @Test
    public void addNumbersWithAlphabetTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("1P2,3,4,5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1A2,3,4,5");
        assertEquals(result, 15);
        result = calculateNumbers.Add("1B2,3,4,5");
        assertEquals(result, 15);
    }
    @Test
    public void addNumbersGreaterThan1000Test() throws  Exception{
        int result;
        result = calculateNumbers.Add("1000,2,3,4,5");
        assertEquals(result, 1014);

        result = calculateNumbers.Add("1001,2,3,4,5");
        assertEquals(result, 14);

        result = calculateNumbers.Add("1001,2000,3,4,5");
        assertEquals(result, 12);

        result = calculateNumbers.Add("1001,2000,3,4");
        assertEquals(result, 7);
    }

    @Test
    public void delimitersWithListFormatTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("//[,]\n2,3,4,5");
        assertEquals(result, 14);
        result = calculateNumbers.Add("//[,,,]\n1,,,2");
        assertEquals(result, 3);
    }

    @Test
    public void delimitersWithMultipleListFormatTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("//[,][%]\n2,3%4,5");
        assertEquals(result, 14);
        result = calculateNumbers.Add("//[.][?]\n1.2");
        assertEquals(result, 3);
        result = calculateNumbers.Add("//[.][?]\n1.2?3");
        assertEquals(result, 6);
        result = calculateNumbers.Add("//[.][?]\n1?2.3");
        assertEquals(result, 6);
    }

    @Test
    public void longerLengthDelimitersWithMultipleListFormatTest() throws  Exception{
        int result;
        result = calculateNumbers.Add("//[,,,,][%]\n2,,,,3%4,5");
        assertEquals(result, 14);
        result = calculateNumbers.Add("//[****][????]\n1****2????3");
        assertEquals(result, 6);
        result = calculateNumbers.Add("//[aaa][?]\n1?2aaa3aaa1009");
        assertEquals(result, 6);
    }
}
