package com.berk.controller;

import static com.berk.constants.EndPoints.*;

import com.berk.dto.request.CancelRentFootballFieldRequestDto;
import com.berk.dto.request.DeleteFootballFieldRequestDto;
import com.berk.dto.request.UpdateFootballFieldRequestDto;
import com.berk.dto.responce.MessageResponseDto;
import com.berk.repository.entity.FootballField;
import com.berk.repository.entity.RentFootballField;
import com.berk.service.FootballFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(FOOTBALL_FIELD)
public class FootballFieldController {
    private final FootballFieldService footballFieldService;


    @PutMapping(DELETE)
    public ResponseEntity<MessageResponseDto> deleteField(@RequestBody DeleteFootballFieldRequestDto dto){
        return ResponseEntity.ok(footballFieldService.deleteField(dto));
    }

    @GetMapping(GETALL)
    public List<FootballField> getAll(){
        return footballFieldService.findAll();
    }

    @CrossOrigin("*")
    @GetMapping(FILTER_CITY_AND_DISTRICT_FOOTBALL_FIELD)
    public ResponseEntity<List<FootballField>> getFilterCityAndDistrict(String city, String district){
        return ResponseEntity.ok(footballFieldService.getFilterCityAndDistrict(city,district));
    }
    @CrossOrigin("*")
    @PutMapping("update-footballfield")
    public ResponseEntity<MessageResponseDto> updateFootballField(@RequestBody UpdateFootballFieldRequestDto dto){
return ResponseEntity.ok(footballFieldService.updateFootballField(dto));
    }
    @CrossOrigin("*")
    @GetMapping("get-footballfield")
    public ResponseEntity<List<FootballField>> getFootballField(String userid){
        return ResponseEntity.ok(footballFieldService.getFootballField(userid));
    }
    @CrossOrigin("*")
    @GetMapping("find-footballfield")
    public ResponseEntity<Optional<FootballField>> findById(String id){
        return ResponseEntity.ok(footballFieldService.findById(id));
    }
}
