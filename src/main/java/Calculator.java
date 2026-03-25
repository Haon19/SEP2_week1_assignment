import java.lang.Object;
import java.util.*;


public class Calculator {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Select a language:");
        System.out.println("1:English");
        System.out.println("2:Francais");
        System.out.println("3:Suomi");
        System.out.println("4:Japanese");

        String lang = in.nextLine();

        Locale locale = switch (lang) {
            case "2" -> new Locale("fr", "FR");
            case "3" -> new Locale("fi", "FI");
            case "4" -> new Locale("ja", "JP");
            default -> new Locale("en", "UK");
        };

        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle",locale);

        String prompt1 = rb.getString("prompt1");
        String prompt2 = rb.getString("prompt2");
        String prompt3 = rb.getString("prompt3");
        String sum = rb.getString("sum");

        System.out.println(prompt1);
        int a = Integer.parseInt(in.nextLine());
        double total = 0;
        for (int i = 0; i < a; i++){
            System.out.println(prompt2);
            double x = Double.parseDouble(in.nextLine());
            System.out.println(prompt3);
            double y = Double.parseDouble(in.nextLine());
            total = total + (x*y);
        }
        System.out.println(sum+total);
    }
}
