package edu.hw11.task3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicFibCalculatorClassTest {

    @Test
    public void fib_CreateDynamicFibCalculatorAndInputNum_ResultFibNum()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Given
        int num = 20;
        long expected = 6765L;

        // When
        Class<?> createdClass = createClass();
        long actual = (long) createdClass.getDeclaredMethod("fib", int.class)
            .invoke(createdClass.getDeclaredConstructor().newInstance(), num);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private Class<?> createClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("DynamicFibCalculator")
            .defineMethod("fib", long.class, Modifier.PUBLIC).withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public @NotNull ByteCodeAppender appender(@NotNull Target target) {
                    return (mv, context, methodDescription) -> {
                        Label label = new Label();

                        mv.visitCode();

                        // if (n < 2)
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, label);

                        //  return n;
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        //return fib(n - 1) + fib(n - 2);
                        mv.visitLabel(label);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "DynamicFibCalculator", "fib", "(I)J");
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "DynamicFibCalculator", "fib", "(I)J");
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();

                        return new Size(5, 2);
                    };
                }

                @Override
                public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
    }
}
