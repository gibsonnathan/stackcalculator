/**
 * SimpleStackCalculator takes input from the user and evaluates expressions.
 * If given a number it is pushed onto the stack, if given an operator the
 * result of the operation is pushed onto the stack. "p" prints the top
 * element of the stack, "P" pops the stack, "c" clears the stack,
 * "d" duplicates the top element on the stack, "r" reverses the top entries
 * on the stack
 */

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SimpleStackCalculator {

    public static void main(String[]args){
        //reads the input from the user
        Scanner sc = new Scanner(System.in);
        //stack containing the floats entered by the user
        Stack<Float> stack = new Stack<Float>();
        //loop must be terminated from the command-line via ctrl-c
        while(sc.hasNext()){
            String input = sc.next();
            //splits the line into elements, using empty space as the delimiter
            //and then iterates over each element
            for(String i : input.split(" ")){
                //checks to see if i is a number,
                // if so it is pushed onto the stack
                if(isFloat(i)){
                    stack.push(Float.parseFloat(i));
                }
                //if i is a fraction, the result of the fraction's
                // division is pushed onto the stack
                if(isFraction(i)){
                    String[] fraction = i.split("/");
                    float num = Float.parseFloat(fraction[0]);
                    float den = Float.parseFloat(fraction[1]);
                    try{
                        stack.push(divide(den, num));
                    }catch(DivisionByZeroException e){
                        System.out.println(e.getMessage());
                    }
                }
                //if i is equal to "p", print peek to the screen
                if(i.equals("p")){
                    try{
                        System.out.println(stack.peek());
                    }catch(EmptyStackException e){
                        System.err.println("Empty Stack");
                    }
                //if i is equal to "P", pop the stack
                }else if(i.equals("P")){
                    try{
                        stack.pop();
                    }catch (EmptyStackException e){
                        System.err.println("Empty Stack");
                    }
                //if i is equal to "c", clear the stack
                }else if(i.equals("c")){
                    stack.clear();
                //if i is equal to "d", duplicate the top element on the
                // stack
                }else if(i.equals("d")){
                    try{
                        stack.push(Float.valueOf(stack.peek()));
                    }catch(EmptyStackException e){
                        System.err.println("Empty Stack");
                    }
                }
                //if i is equal to "r", reverse the top two elements on
                // the stack
                else if (i.equals("r")){
                    if(stack.size() >= 2){
                        float top = stack.pop();
                        float second = stack.pop();
                        stack.push(top);
                        stack.push(second);
                    }else{
                        System.err.println("Not enough elements on stack to " +
                                "perform this operation");
                    }
                 }
                //if i is equal to "f", the stack is printed out
                else if(i.equals("f")){
                    printStack(stack);
                }
                //checks to see if i is equal to an operator,
                // if so it checks to make sure that there are at least two
                // elements on the stack
                if(i.equals("+") || i.equals("-") || i.equals("/") || i
                        .equals("*")){
                    if(stack.size() < 2){
                        System.err.println("Not enough elements on stack to " +
                                "perform this operation");
                        break;
                    }
                    //adds the top elements on the stack
                    if (i.equals("+")){
                        float num1 = stack.pop();
                        float num2 = stack.pop();
                        stack.push(num1 + num2);
                    //subtracts the top two elements on the stack
                    }else if (i.equals("-")){
                        float num1 = stack.pop();
                        float num2 = stack.pop();
                        stack.push(num2 - num1);
                    }
                    //multiplies the top two elements on the stack
                    else if (i.equals("*")){
                        float num1 = stack.pop();
                        float num2 = stack.pop();
                        stack.push(num1 * num2);
                    }
                    //divides the top two elements on the stack
                    else if (i.equals("/")){
                        float num1 = stack.pop();
                        float num2 = stack.pop();
                        try{
                            stack.push(divide(num1, num2));
                        }catch(DivisionByZeroException e){
                            System.out.println(e.getMessage());
                            stack.push(num2);
                            stack.push(num1);
                        }

                    }
                }
            }
        }
    }

    //takes two float arguments, checks to make sure that there wont be
    // division by zero, if there will be then an exception is thrown,
    // else the division is performed and returned
    public static float divide(float num1,
                               float num2) throws DivisionByZeroException{
        if(num1 == 0){
            throw new DivisionByZeroException("Division by Zero error");
        }
        else{
            return num2 / num1;
        }
    }

    //test to see if a string is in fraction format, ie 1/2 returns true
    public static boolean isFraction(String str){
        Pattern p = Pattern.compile("[-]?[\\d]+[//][\\d]+");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        return b;
    }

    //test to see if a string is a float by attempting to caste the string to
    // a float, if successful it returns true, else returns false
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

    //prints out each element in a stack of type Float
    public static void printStack(Stack<Float> s){
        //System.out.println("");
        //System.out.println("Stack Size: " + s.size());
        //System.out.println("------");
        for(int i = s.size() -1; i >= 0; i--){
            System.out.println(s.elementAt(i));
        }
        //System.out.println("");
    }
}
