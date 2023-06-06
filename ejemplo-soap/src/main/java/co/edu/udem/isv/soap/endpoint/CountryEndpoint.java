package co.edu.udem.isv.soap.endpoint;


import co.edu.udem.isv.soap.*;
import co.edu.udem.isv.soap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;


@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://udem.edu.co/isv/soap";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createCountryRequest")
    @ResponsePayload
    public CreateCountryResponse createCountry(@RequestPayload CreateCountryRequest request) {
        CreateCountryResponse response = new CreateCountryResponse();
        Country country = new Country();
        country.setName(request.getCountry().getName());
        country.setPopulation(request.getCountry().getPopulation());
        country.setCapital(request.getCountry().getCapital());
        country.setCurrency(request.getCountry().getCurrency());
        countryRepository.createCountry(country);
        response.setCountry(country);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCountriesRequest")
    @ResponsePayload
    public GetAllCountriesResponse getAllCountries() {
        GetAllCountriesResponse response = new GetAllCountriesResponse();
        List<Country> countries = countryRepository.getAllCountries();
        response.getCountries().addAll(countries);
        return response;
    }

}