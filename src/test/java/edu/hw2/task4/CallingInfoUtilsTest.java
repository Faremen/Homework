package edu.hw2.task4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoUtilsTest {

    @Test
    public void callingInfo_CallMethod_ResultCallingInfo() {
        // Given
        String expectedClassName = "edu.hw2.task4.CallingInfoUtilsTest";
        String expectedMethodName = "callingInfo_CallMethod_ResultCallingInfo";

        // When
        CallingInfo callingInfo = CallingInfoUtils.getCallingInfo();
        String actualClassName = callingInfo.className();
        String actualMethodName = callingInfo.methodName();

        // Then
        assertThat(actualClassName).isEqualTo(expectedClassName);
        assertThat(actualMethodName).isEqualTo(expectedMethodName);
    }
}
