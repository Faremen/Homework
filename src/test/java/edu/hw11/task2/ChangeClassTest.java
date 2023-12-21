package edu.hw11.task2;

import edu.hw11.task2.arithmetic.ArithmeticUtils;
import edu.hw11.task2.arithmetic.SomeArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeClassTest {
    @Test
    public void sum_ChangeSumToMultiply_ResultMultiply() {
        // Given
        int expected = 10;

        // When
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(SomeArithmeticUtils.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        int actual = ArithmeticUtils.sum(2, 5);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
