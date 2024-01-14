package com.berk.service;

import com.berk.repository.ICityRepository;
import com.berk.repository.entity.City;
import com.berk.repository.entity.District;
import com.berk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService extends ServiceManager<City,String> {
    private ICityRepository cityRepository;

    public CityService(ICityRepository cityRepository){
        super(cityRepository);
        this.cityRepository=cityRepository;
    }
    public List<String> getDistrictsByCityName(String cityName) {
        List<City> allCities = cityRepository.findAll();
        return allCities.stream()
                .filter(city -> city.getText().equalsIgnoreCase(cityName))
                .findFirst()
                .map(city -> city.getDistricts().stream()
                        .map(District::getText) // Her bir District nesnesinin text alanını al
                        .collect(Collectors.toList())) // Sonuçları bir listeye topla
                .orElse(null); // İl bulunamazsa null döndür
    }



    public List<String> getAllCities() {
        List<City> allCities = cityRepository.findAll(); // Veritabanından tüm City nesnelerini al
        return allCities.stream()
                .map(City::getText) // Her City nesnesinin text alanını al
                .collect(Collectors.toList()); // Elde edilen text'leri bir listeye dönüştür
    }
}
