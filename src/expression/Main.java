package expression;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        if (args == null || args.length == 0) {
            System.err.println("Invalid arguments: " + Arrays.toString(args));
            return;
        }
        int x;
        try {
            x = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Not parseable integer: " + Arrays.toString(args));
            return;
        }


        System.out.println(
                new Add(
                        new Subtract(
                                new Multiply(
                                        new Variable("x"),
                                        new Variable("x")
                                ),
                                new Multiply(
                                        new Const(2),
                                        new Variable("x")
                                )
                        ),
                        new Const(1)
                ).evaluate(x)
        );
    }
}