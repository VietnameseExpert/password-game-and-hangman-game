import java.util.Arrays;

public class HelloJava {
    public static void main(String[] args) {
        System.out.print("ád");
        System.out.println("dss");


    }

    public static String[] strArrMethod(String[] arr)
    {
        String[] result = new String[arr.length];
        for (int j = 0; j < arr.length; j++)
        {
            String sm = arr[j];
            for (int k = j + 1; k < arr.length; k++)
            {
                if (arr[k].length() < sm.length())
                {
                    sm = arr[k];
                }
            }
            result[j] = sm;
        }
        return result;
    }

    }
