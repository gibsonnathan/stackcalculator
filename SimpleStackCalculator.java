/**
 * Created by nathan on 4/18/14.
 */
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SimpleStackCalculator {

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        Stack<Float> stack = new Stack<Float>();
        while(sc.hasNext()){
            String input = sc.next();
            for(String i : input.split(" ")){

                if(isFloat(i)){
                    stack.push(Float.parseFloat(i));
                }

                if(isFraction(i)){
                    String[] fraction = i.split("/");
                    int num = Integer.parseInt(fraction[0]);
                    int den = Integer.parseInt(fraction[1]);
                    stack.push((float) num / den);

                }

                if(i.equals("p")){
                    try{
                        System.out.println(stack.peek());
                    }catch(EmptyStackException e){
                        System.err.println("Empty Stack");
                    }
                }else if(i.equals("P")){
                    try{
                        stack.pop();
                    }catch (EmptyStackException e){
                        System.err.println("Empty Stack");
                    }
                }else if(i.equals("c")){
                    stack.clear();
                }else if(i.equals("d")){
                    try{
                        stack.push(Float.valueOf(stack.peek()));
                    }catch(EmptyStackException e){
                        System.err.println("Empty Stack");
                    }
                }

                else if (i.equals("r")){
                    if(stack.size() >= 2){
                        float top = stack.pop();
                        float second = stack.pop();
                        stack.push(second);
                        stack.push(top);
                    }else{
                        System.err.println("Not enough elements on stack to " +
                                "perform this operation");
                    }
                 }
                else if(i.equals("f")){
                    printStack(stack);
                }
                if(i.equals("+") || i.equals("-") || i.equals("/") || i
                        .equals("*")){
                    if(stack.size() < 2){
                        System.err.println("Not enough elements on stack to " +
                                "perform this operation");
                        break;
                    }
                    if (i.equals("+")){
                        stack.push(stack.pop() + stack.pop());
                    }else if (i.equals("-")){
                        stack.push(stack.pop() - stack.pop());
                    }
                    else if (i.equals("*")){
                        stack.push(stack.pop() * stack.pop());
                    }
                    else if (i.equals("/")){
                        stack.push(stack.pop() / stack.pop());
                    }
                }


            }
        }
    }

    public static boolean isFraction(String str){
        Pattern p = Pattern.compile("[\\d]+[//][\\d]+");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        return b;
    }

    public static boolean isFloat(String str)
    {
        try
        {
            float f = Float.parseFloat(str);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    public static void printStack(Stack<Float> s){
        System.out.println("");
        System.out.println("Stack Size: " + s.size());
        System.out.println("------");
        for(int i = s.size() -1; i >= 0; i--){
            System.out.println(s.elementAt(i));
        }
        System.out.println("");
    }
}
