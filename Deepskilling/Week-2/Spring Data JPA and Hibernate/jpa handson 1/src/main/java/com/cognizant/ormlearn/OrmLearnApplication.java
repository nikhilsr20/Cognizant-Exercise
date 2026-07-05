package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        LOGGER.info("Inside main");

        testGetAllCountries();
        testGetCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testFindByNameContaining();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    private static void testGetCountryByCode() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testAddCountry() {
        LOGGER.info("Start");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("Test Country");
        countryService.addCountry(country);
        try {
            Country added = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Added country:{}", added);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found after add: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country");
            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Updated country:{}", updated);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found for update: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.debug("Country still exists after delete - unexpected");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country successfully deleted. Exception as expected: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testFindByNameContaining() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesByNameContaining("land");
        LOGGER.debug("Countries matching 'land':{}", countries);
        LOGGER.info("End");
    }
}
