package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class RWLPersonDatabaseTest {

    private final PersonDatabase database = new RWLPersonDatabase();

    @BeforeEach
    public void setUp() {
        database.add(new Person(1, "n1", "adr1", "pn1"));
        database.add(new Person(2, "n1", "adr1", "pn2"));
        database.add(new Person(3, "n1", "adr2", "pn3"));
        database.add(new Person(4, "n1", "adr2", "pn1"));
        database.add(new Person(5, "n2", "adr3", "pn2"));
        database.add(new Person(6, "n2", "adr3", "pn3"));
        database.add(new Person(7, "n2", "adr1", "pn1"));
        database.add(new Person(8, "n2", "adr1", "pn2"));
        database.add(new Person(9, "n3", "adr2", "pn3"));
        database.add(new Person(10, "n3", "adr2", "pn1"));
        database.add(new Person(11, "n3", "adr3", "pn2"));
        database.add(new Person(12, "n3", "adr3", "pn3"));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1, 13",
        "10, 22",
        "100, 112",
    })
    public void add_MultipleThreadsAddPersons_ResultSize(int countThreads, int expected) throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(countThreads);

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(countThreads);
        CountDownLatch startLatch = new CountDownLatch(1);

        int startId = database.size();

        // When
        for (int i = 1; i <= countThreads; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                database.add(new Person(
                        startId + finalI,
                        "n-t" + finalI,
                        "adr-t" + finalI,
                        "pn-t" + finalI
                    )
                );

                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        var actual = database.size();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> delete_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                7
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
                0
            )
        );
    }

    @ParameterizedTest
    @MethodSource("delete_ProvideParameters")
    public void delete_MultipleThreadsDeletePersonsById_ResultSizeAndNullPersons(List<Integer> listId, int expectedSize)
        throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(listId.size());

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(listId.size());
        CountDownLatch startLatch = new CountDownLatch(1);

        // When
        for (var i : listId) {
            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                database.delete(i);
                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        List<Person> actualNullPersons = new ArrayList<>();
        for (var i : listId) {
            actualNullPersons.add(database.getById(i));
        }

        var actualSize = database.size();

        // Then
        assertThat(actualSize).isEqualTo(expectedSize);
        assertThat(actualNullPersons).containsOnlyNulls();
    }

    private static Stream<Arguments> findByName_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of("n1", "n3"),
                List.of(
                    List.of(
                        new Person(1, "n1", "adr1", "pn1"),
                        new Person(2, "n1", "adr1", "pn2"),
                        new Person(3, "n1", "adr2", "pn3"),
                        new Person(4, "n1", "adr2", "pn1")
                    ),
                    List.of(
                        new Person(9, "n3", "adr2", "pn3"),
                        new Person(10, "n3", "adr2", "pn1"),
                        new Person(11, "n3", "adr3", "pn2"),
                        new Person(12, "n3", "adr3", "pn3")
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findByName_ProvideParameters")
    public void findByName_FindInMultipleThreads_ResultListWithFindingPersons(
        List<String> listFindName,
        List<List<Person>> expected
    ) throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(listFindName.size());

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(listFindName.size());
        CountDownLatch startLatch = new CountDownLatch(1);

        // When
        List<List<Person>> actual = new ArrayList<>(listFindName.size());

        for (int i = 0; i < listFindName.size(); i++) {
            int finalI = i;
            actual.add(null);
            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                actual.set(finalI, database.findByName(listFindName.get(finalI)));

                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> findByAddress_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of("adr2", "adr3"),
                List.of(
                    List.of(
                        new Person(3, "n1", "adr2", "pn3"),
                        new Person(4, "n1", "adr2", "pn1"),
                        new Person(9, "n3", "adr2", "pn3"),
                        new Person(10, "n3", "adr2", "pn1")
                    ),
                    List.of(
                        new Person(5, "n2", "adr3", "pn2"),
                        new Person(6, "n2", "adr3", "pn3"),
                        new Person(11, "n3", "adr3", "pn2"),
                        new Person(12, "n3", "adr3", "pn3")
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findByAddress_ProvideParameters")
    public void findByAddress_FindInMultipleThreads_ResultListWithFindingPersons(
        List<String> listFindAddress,
        List<List<Person>> expected
    ) throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(listFindAddress.size());

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(listFindAddress.size());
        CountDownLatch startLatch = new CountDownLatch(1);

        // When
        List<List<Person>> actual = new ArrayList<>(listFindAddress.size());

        for (int i = 0; i < listFindAddress.size(); i++) {
            int finalI = i;
            actual.add(null);
            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                actual.set(finalI, database.findByAddress(listFindAddress.get(finalI)));

                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> findByPhone_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of("pn1", "pn2"),
                List.of(
                    List.of(
                        new Person(1, "n1", "adr1", "pn1"),
                        new Person(4, "n1", "adr2", "pn1"),
                        new Person(7, "n2", "adr1", "pn1"),
                        new Person(10, "n3", "adr2", "pn1")
                    ),
                    List.of(
                        new Person(2, "n1", "adr1", "pn2"),
                        new Person(5, "n2", "adr3", "pn2"),
                        new Person(8, "n2", "adr1", "pn2"),
                        new Person(11, "n3", "adr3", "pn2")
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findByPhone_ProvideParameters")
    public void findByPhone_FindInMultipleThreads_ResultListWithFindingPersons(
        List<String> listFindPhone,
        List<List<Person>> expected
    ) throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(listFindPhone.size());

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(listFindPhone.size());
        CountDownLatch startLatch = new CountDownLatch(1);

        // When
        List<List<Person>> actual = new ArrayList<>(listFindPhone.size());

        for (int i = 0; i < listFindPhone.size(); i++) {
            int finalI = i;
            actual.add(null);
            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                actual.set(finalI, database.findByPhone(listFindPhone.get(finalI)));

                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
