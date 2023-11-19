package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PortScannerTest {

    private static Stream<Arguments> checkPorts_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(0, 1, 2, 3, 80,135, 137, 17500)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("checkPorts_ProvideParameters")
    public void checkPorts_InputNumPorts_ResultListPorts(List<Integer> ports) {
        // When
        List<Port> actual = PortScanner.checkPorts(ports);
        PortScanner.printPortsInfo(actual);

        // Then
        assertThat(actual).isNotEmpty();
        assertThat(actual.size()).isGreaterThanOrEqualTo(ports.size());
    }

    @Test
    public void checkPorts_InputInvalidNumPorts_ResultIllegalArgumentException() {
        assertThatThrownBy(() -> PortScanner.checkPorts(List.of(-10)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Port must be in range [0, 49151]");
    }
}
