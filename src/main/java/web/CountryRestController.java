package web;

import lombok.AllArgsConstructor;
import model.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@AllArgsConstructor
public class CountryRestController {
    private final CountryService countryService;

    @GetMapping
    public List<Country> getCountries(){
        return countryService.findAll();
    }
}
