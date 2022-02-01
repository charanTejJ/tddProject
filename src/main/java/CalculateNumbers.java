import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculateNumbers {
    public int Add(String numbers) throws Exception{
        int ans = 0;
        List<String>  numbersList = new ArrayList<>();
        if (numbers.contains("-")) {
            String[] Numbs = numbers.split("[^-0-9]");
            StringBuilder nString = new StringBuilder();
            for (String num: Numbs){
                if(!num.equals("")){
                    if(Integer.parseInt(num)<0){
                        numbersList.add(num);
                    }
                }
            }
        } else {
            String[] splitString = numbers.split("\n");
            if (splitString[0].contains("//")) {
                String DelimString = splitString[0];
                String[] Delims = DelimString.split("[// \\[ \\] ]");
                String DelimValue = "[";
                for (String  value: Delims){
                    DelimValue += value;
                }
                DelimValue+= "]";
                String numbs = splitString[1];
                numbersList = Arrays.asList(numbs.split(DelimValue));
            } else {
                numbersList = Arrays.asList(numbers.split("[^0-9]"));
            }

        }
            String nString = "";
            for (String number: numbersList){
                if(!number.equals("")){
                    int num = Integer.parseInt(number);
                    if ( num > -1 && num <1001){
                        ans += num;
                    } else if (num < 0) {
                       nString =  nString+ num + ",";
                    }
                }
            }
            if (nString!=""){
                throw new Exception("Found Negative Numbers : "+ nString.substring(0,nString.length()-1) + " in given number string"+ numbers);
            }
            return ans;
    }
}
