package edu.hw10.task1;

import edu.hw10.task1.entity.Person;
import edu.hw10.task1.entity.RecordPerson;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomObjectGeneratorTest {

    RandomObjectGenerator rog;

    @BeforeEach
    public void setUp() {
        rog = new RandomObjectGenerator();
    }

    @RepeatedTest(50)
    public void nextObject_InputRecordPersonClass_ResultRandomRecordPersonObject()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        // Given
        int maxNameLength = 40;
        int minAge = 0;
        int maxAge = 100;

        // When
        RecordPerson actual = rog.nextObject(RecordPerson.class);

        // Then
        assertThat(actual.age()).isBetween(minAge, maxAge);
        assertThat(actual.name()).isNotNull();
        assertThat(actual.name()).isNotEmpty();
        assertThat(actual.name().length()).isLessThan(maxNameLength);
    }

    @RepeatedTest(50)
    public void nextObject_InputClassAndMethodName_ResultRandomClassObject()
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        // Given
        int maxNameLength = 40;
        int minAge = 0;
        int maxAge = 100;

        // When
        Person actual = rog.nextObject(Person.class, "create");

        // Then
        assertThat(actual.getAge()).isBetween(minAge, maxAge);
        assertThat(actual.getName()).isNotNull();
        assertThat(actual.getName()).isNotEmpty();
        assertThat(actual.getName().length()).isLessThan(maxNameLength);
    }
}
