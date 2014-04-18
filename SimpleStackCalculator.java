/**
 * Created by nathan on 4/18/14.
 */
import java.util.*;
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
                if(i.equals("p")){
                    System.out.println(stack.peek());
                    System.out.println();
                }else if(i.equals("P")){
                    stack.pop();
                }else if(i.equals("c")){
                    stack.clear();
                }else if(i.equals("d")){
                    stack.push(Float.valueOf(stack.peek()));
                }else if (i.equals("r")){
                    float top = stack.pop();
                    float next = stack.pop();
                    stack.push(top);
                    stack.push(next);
                }else if(i.equals("f")){
                    printStack(stack);
                }
                else if (i.equals("+")){
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

    public static boolean isFloat(String str)
    {
        try
        {
            float f = Float.parseFloat(str);
        }
        catch(NumberFormatException nfe)
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
