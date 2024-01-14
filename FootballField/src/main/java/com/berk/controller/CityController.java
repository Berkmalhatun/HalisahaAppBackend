package com.berk.controller;

import com.berk.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.berk.constants.EndPoints.FOOTBALL_FIELD;

@RestController
@RequiredArgsConstructor
@RequestMapping("city")
public class
CityController {
    private final CityService cityService;

    @GetMapping("/{cityName}/districts")
    @CrossOrigin("*")
    public List<String> getDistrictsByCityName(@PathVariable String cityName) {
        return cityService.getDistrictsByCityName(cityName);
    }
    @GetMapping("/{cities}/city")
    @CrossOrigin("*")
    public List<String> getAllCities(){
        return cityService.getAllCities();
    }
}
