package edu.hw11.task1;

import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import net.bytebuddy.ByteBuddy;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloByteBuddyTest {
    @Test
    public void toString_CreateDynamicClassWithOverrideToString_ResultHelloByteBuddy()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Given
        String toString = "Hello, ByteBuddy!";
        String expected = "Hello, ByteBuddy!";

        // When
        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value(toString))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        String actual = dynamicType.getDeclaredConstructor().newInstance().toString();

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
