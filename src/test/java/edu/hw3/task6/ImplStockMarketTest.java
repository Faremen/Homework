package edu.hw3.task6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImplStockMarketTest {

    private ImplStockMarket stockMarket;

    @BeforeEach
    public void setUp() {
        stockMarket = new ImplStockMarket();
    }

    private static Stream<Arguments> add_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock(1000)
                },
                new ArrayList<>(
                    List.of(
                        new Stock(1000)
                    )
                )
            ),
            Arguments.of(
                new Stock[] {
                    new Stock(0),
                    new Stock(100),
                    new Stock(200),
                },
                new ArrayList<>(
                    List.of(
                        new Stock(0),
                        new Stock(100),
                        new Stock(200)
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("add_ProvideParameters")
    void add_InputArrayStocks_ResultListWithStocks(Stock[] stocks, ArrayList<Stock> expected) {
        // When
        for (var i : stocks) {
            stockMarket.add(i);
        }
        var actual = stockMarket.getStocks();

        // Then
        Assertions.assertThat(actual)
            .containsOnly(expected.toArray(new Stock[0]));
    }

    private static Stream<Arguments> remove_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock(1000)
                },
                new Stock[] {
                    new Stock(1000)
                },
                new ArrayList<>()
            ),
            Arguments.of(
                new Stock[] {
                    new Stock(0),
                    new Stock(100),
                    new Stock(200),
                },
                new Stock[] {
                    new Stock(100),
                    new Stock(200)
                },
                new ArrayList<>(
                    List.of(
                        new Stock(0)
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("remove_ProvideParameters")
    void remove_InputArrayStocksAndRemoveStocks_ResultListWithStocks(
        Stock[] stocks,
        Stock[] removeStocks,
        ArrayList<Stock> expected
    ) {
        // When
        for (var i : stocks) {
            stockMarket.add(i);
        }
        for (var i : removeStocks) {
            stockMarket.remove(i);
        }

        var actual = stockMarket.getStocks();

        // Then
        Assertions.assertThat(actual)
            .containsOnly(expected.toArray(new Stock[0]));
    }

    private static Stream<Arguments> mostValuableStock_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                new Stock[] {
                    new Stock(1000)
                },
                new Stock(1000)
            ),
            Arguments.of(
                new Stock[] {
                    new Stock(0),
                    new Stock(100),
                    new Stock(200),
                },
                new Stock(200)
            ),
            Arguments.of(
                new Stock[] {},
                null
            )
        );
    }

    @ParameterizedTest
    @MethodSource("mostValuableStock_ProvideParameters")
    public void mostValuableStock_InputArrayStocks_ResultMostValuableStock(Stock[] stocks, Stock expected) {
        // When
        for (var i : stocks) {
            stockMarket.add(i);
        }
        var actual = stockMarket.mostValuableStock();

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
