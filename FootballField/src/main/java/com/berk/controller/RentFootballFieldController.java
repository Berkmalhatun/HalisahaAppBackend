package com.berk.controller;

import com.berk.dto.request.GuestRentFootballFieldRequestDto;
import com.berk.dto.request.RentFootballFieldRequestDto;
import com.berk.dto.request.CancelRentFootballFieldRequestDto;
import com.berk.dto.responce.MessageResponseDto;
import com.berk.repository.IRentFootballFieldRepository;
import com.berk.repository.entity.RentFootballField;
import com.berk.service.RentFootballFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.berk.constants.EndPoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(RENT_FOOTBALL_FIELD)
public class RentFootballFieldController {
    private final RentFootballFieldService rentFootballFieldService;
    private final IRentFootballFieldRepository rentFootballFieldRepository;

    @PostMapping(RENT)
    @CrossOrigin("*")
    public ResponseEntity<MessageResponseDto> rentFootballField(@RequestBody RentFootballFieldRequestDto dto){
        return ResponseEntity.ok(rentFootballFieldService.rentFootballField(dto));
    }
    @PutMapping(CANCEL)
    @CrossOrigin("*")
    public ResponseEntity<MessageResponseDto> cancelField(String id){
        return ResponseEntity.ok(rentFootballFieldService.cancelField(id));
    }
    @GetMapping(GETALL)
    public List<RentFootballField> getAll(){
        return rentFootballFieldService.findAll();
    }

    @GetMapping(FILTER_FILLED)
    @CrossOrigin("*")
    public ResponseEntity<List<RentFootballField>> filterFilled(){
        return ResponseEntity.ok(rentFootballFieldService.filterFilled());
    }
    @GetMapping(HOURS_FILTER_FILLED)
    @CrossOrigin("*")
    public ResponseEntity<List<RentFootballField>> getRentFootballFieldByDateRange(String footballFieldId){
        return ResponseEntity.ok(rentFootballFieldService.getRentFootballFieldByDateRange(footballFieldId));
    }
//    @GetMapping("/rent-football-field/{footballFieldId}")
//    @CrossOrigin("*")
//    public ResponseEntity<List<RentFootballField>> getRentFootballFieldByDateRange(
//            @PathVariable String footballFieldId,
//            @RequestParam Date startDate,
//            @RequestParam Date endDate) {
//        List<RentFootballField> rentFootballFields =
//                rentFootballFieldRepository.findByFootballFieldidAndStartDateAndEndDate(footballFieldId, startDate, endDate);
//        return ResponseEntity.ok(rentFootballFields);
//    }
    @GetMapping(HISTORY)
    @CrossOrigin("*")
    public ResponseEntity<List<RentFootballField>> getHistory( String userid){
        return ResponseEntity.ok(rentFootballFieldService.getHistory(userid));

    }
    @PostMapping(GUEST_RENT_FOOTBALL_FIELD)
    @CrossOrigin("*")
    public ResponseEntity<RentFootballField> guestRentFootballField(@RequestBody GuestRentFootballFieldRequestDto dto){
        return ResponseEntity.ok(rentFootballFieldService.guestRentFootballField(dto));
    }
    @GetMapping(FIND_BY_ID)
    @CrossOrigin("*")
    public ResponseEntity<RentFootballField> findByRentFootballFeieldId(String id){
        return ResponseEntity.ok(rentFootballFieldService.findByRentFootballFeieldId(id));
    }
}
