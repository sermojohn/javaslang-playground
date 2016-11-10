package gr.iserm.java.javaslang;

import javaslang.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TryExamples {

    @Test
    public void divisionTest() {
        Try<Integer> divisionTrial = wrapInterceptors(Try.of(() -> divide(10, 2)));
        assertTrue(divisionTrial.get() == 5);

        divisionTrial = wrapInterceptors(Try.of(() -> divide(1, 0)));
        assertTrue(divisionTrial.failed().get().getClass() == ArithmeticException.class);

        divisionTrial = wrapInterceptors(Try.of(() -> divide(10,100)));
        divisionTrial.andThen((result) -> {
            System.out.println("finished.");
        });

    }


    static Integer divide(int dividend, int divisor) {
        // throws if divisor is zero
        return dividend / divisor;
    }

    private static <S> Try<S> wrapInterceptors(Try<S> tryInstance) {
        return tryInstance
                .onSuccess((success) -> System.out.println(String.format("successful result [%s].", success)))
                .onFailure((error) -> System.out.println(String.format("failure result [%s].", error)));
    }
}
