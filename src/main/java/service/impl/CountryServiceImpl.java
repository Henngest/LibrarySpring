package service.impl;

import lombok.AllArgsConstructor;
import model.Country;
import org.springframework.stereotype.Service;
import repository.CountryRepository;
import service.CountryService;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
