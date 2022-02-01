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
                String Delim = DelimString.split("//")[1];
                String numbs = splitString[1];
                numbersList = Arrays.asList(numbs.split(Delim));
            } else {
                numbersList = Arrays.asList(numbers.split("\\W"));
            }

        }
            String nString = "";
            for (String number: numbersList){
                if(!number.equals("")){
                    int num = Integer.parseInt(number);
                    if ( num > -1){
                        ans += num;
                    } else {
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