package com.inditex.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inditex.challenge.converter.PriceConverter;
import com.inditex.challenge.dto.DatesToApply;
import com.inditex.challenge.dto.PriceDTO;
import com.inditex.challenge.dto.PriceResultDTO;
import com.inditex.challenge.repository.IPricesRepository;
import com.inditex.challenge.repository.model.Price;
import com.inditex.challenge.service.impl.PriceServiceImpl;
import com.inditex.challenge.utils.JsonUtils;
import com.inditex.challenge.validator.ChainValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PriceServiceTest {

    @Autowired private IPricesRepository pricesRepository;

    private PriceServiceImpl priceService;

    @BeforeEach
    private void setup() {
        priceService = new PriceServiceImpl(pricesRepository);
    }

    @ParameterizedTest
    @Sql(scripts={"classpath:data.sql"})
    @MethodSource("provideArgumentsAndExpectedNumber")
    public void CHALLENGE_TESTS_see_provideArgumentsAndExpectedNumber_method_below(
            String date, Integer expected
    ) throws JsonProcessingException {
        assertDoesNotThrow(() -> {
            PriceResultDTO result = priceService.process(date, 35455, 1);
            checkAsserts(result, getRecordNumber(expected));
        });
    }

    private PriceResultDTO getRecordNumber(Integer number) {
        //(1,'2020-06-14-00.00.00','2020-12-31-23.59.59', 1, 35455, 0, 35.50, 'EUR'),
        //(1,'2020-06-14-15.00.00','2020-06-14-18.30.00', 2, 35455, 1, 25.45, 'EUR'),
        //(1,'2020-06-15-00.00.00','2020-06-15-11.00.00', 3, 35455, 1, 30.50, 'EUR'),
        //(1,'2020-06-15-16.00.00','2020-12-31-23.59.59', 4, 35455, 1, 38.95, 'EUR');
        return switch (number) {
            case 1 -> createPriceResultDTO("35.50", 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59");
            case 2 -> createPriceResultDTO("25.45", 2, "2020-06-14-15.00.00", "2020-06-14-18.30.00");
            case 3 -> createPriceResultDTO("30.50", 3, "2020-06-15-00.00.00", "2020-06-15-11.00.00");
            case 4 -> createPriceResultDTO("38.95", 4, "2020-06-15-16.00.00", "2020-12-31-23.59.59");
            default -> createPriceResultDTO("", 1, "", "");
        };
    }

    private void checkAsserts(PriceResultDTO result, PriceResultDTO expected) {
        assertEquals(result.getFinalPrice(), expected.getFinalPrice());
        assertEquals(result.getChainId(), expected.getChainId());
        assertEquals(result.getProductId(), expected.getProductId());
        assertEquals(result.getDateToApply().getStartDate(), expected.getDateToApply().getStartDate());
        assertEquals(result.getDateToApply().getEndDate(), expected.getDateToApply().getEndDate());
    }

    private PriceResultDTO createPriceResultDTO(
            String finalPrice,
            Integer priceList,
            String start,
            String end
    ){
        return PriceResultDTO.builder()
                .finalPrice(new BigDecimal(finalPrice))
                .dateToApply(new DatesToApply(start, end))
                .productId(35455)
                .chainId(1)
                .aliquotToApply(priceList)
                .build();
    }

    private static Stream<Arguments> provideArgumentsAndExpectedNumber() {
        //   1) Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        //   2) Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        //   3) Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        //   4) Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        //   5) Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        return Stream.of(
                Arguments.of("2020-06-14-10.00.00", 1),
                Arguments.of("2020-06-14-16.00.00", 2),
                Arguments.of("2020-06-14-21.00.00", 1),
                Arguments.of("2020-06-15-10.00.00", 3),
                Arguments.of("2020-06-16-16.00.00", 4)
        );
    }
}
