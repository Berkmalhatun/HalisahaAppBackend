package com.berk.service;

import com.berk.dto.request.GuestRentFootballFieldRequestDto;
import com.berk.dto.request.RentFootballFieldRequestDto;
import com.berk.dto.responce.MessageResponseDto;
import com.berk.exception.ErrorType;
import com.berk.exception.FootballFieldServiceException;
import com.berk.mapper.IRentFootballFieldMapper;
import com.berk.repository.IFootballFieldRepository;
import com.berk.repository.IRentFootballFieldRepository;
import com.berk.repository.entity.FootballField;
import com.berk.repository.entity.RentFootballField;
import com.berk.repository.enums.EOccupancyStatus;
import com.berk.utility.ServiceManager;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentFootballFieldService extends ServiceManager<RentFootballField,String> {
    private final IFootballFieldRepository footballFieldRepository;
    private final IRentFootballFieldRepository rentFootballFieldRepository;


    public RentFootballFieldService(IFootballFieldRepository footballFieldRepository, IRentFootballFieldRepository rentFootballFieldRepository) {
        super(rentFootballFieldRepository);
        this.footballFieldRepository=footballFieldRepository;
        this.rentFootballFieldRepository= rentFootballFieldRepository;

    }

    /**
     * Burada userid token dan cekılecek.
     * @param dto
     * @return
     */
    public MessageResponseDto rentFootballField(RentFootballFieldRequestDto dto) {
        Optional<FootballField> footballField = footballFieldRepository.findById(dto.getFootballFieldid());
        if (footballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.HALISAHA_BULUNAMADI);
        if (dto.getUserid().isEmpty())
            throw new FootballFieldServiceException(ErrorType.USERID_NOT_FOUND);
        List<RentFootballField> rentFootballFieldList =
                rentFootballFieldRepository.findByFootballFieldidAndStartDateAndEndDate(dto.getFootballFieldid(),dto.getStartDate(),dto.getEndDate());
        if (!rentFootballFieldList.isEmpty())
            throw new FootballFieldServiceException(ErrorType.KIRALAMA_ISLEMI_YAPILAMADI);
        //date cevırmeden oncekı halı basında dto.getDate()==0L ||  bu var
        RentFootballField rentFootballField = IRentFootballFieldMapper.INSTANCE.toRentFootballField(dto);
        rentFootballField.setOccupancyStatus(EOccupancyStatus.FILLED);
        save(rentFootballField);
                return MessageResponseDto.builder().message("Belirlenen saat aralığı başarıyla kiralandı.").build();
    }

    /**
     * Burada compareTo karsılastırma yapar.Eger startDate now dan buyukse yani gelecek bır tarıhse 0 dan buyuk deger dondurur.
     * @param
     * @return
     */
    public MessageResponseDto cancelField(String id) {
        Optional<RentFootballField> rentFootballField = rentFootballFieldRepository.findById(id);
        if (rentFootballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.KIRALAMA_ISLEMI_BULUNAMADI);
        if (rentFootballField.get().getOccupancyStatus().equals(EOccupancyStatus.BLANK))
            throw new FootballFieldServiceException(ErrorType.KIRALAMA_ISLEMI_BULUNAMADI);
        Date now = new Date();
        if (rentFootballField.get().getStartDate().compareTo(now)>0){
            rentFootballField.get().setOccupancyStatus(EOccupancyStatus.BLANK);
            update(rentFootballField.get());
            return MessageResponseDto.builder().message("Belirlenen saat aralığındaki rezervasyonunuz başarıyla iptal edildi.").build();
        }else{
            throw new FootballFieldServiceException(ErrorType.KIRALAMA_ISLEMI_IPTAL_EDILEMEZ);
        }
    }


    public List<RentFootballField> filterFilled() {
    List<RentFootballField> rentFootballFieldList = rentFootballFieldRepository.findAll();
    return rentFootballFieldList.stream().filter(x-> EOccupancyStatus.FILLED.equals(x.getOccupancyStatus())).collect(Collectors.toList());
    }

    public List<RentFootballField> getRentFootballFieldByDateRange(String footballFieldId) {
        List<RentFootballField> rentFootballFieldList = rentFootballFieldRepository.findAll();
        return rentFootballFieldList.stream()
                .filter(x->x.getFootballFieldid().equals(footballFieldId))
                .filter(x-> x.getOccupancyStatus()==EOccupancyStatus.FILLED)
                .collect(Collectors.toList());
    }

    public List<RentFootballField> getHistory(String userid) {
        Sort sort = Sort.by("startDate").descending(); //ascentic ters sıralar
        List<RentFootballField> historyRentFootballField = rentFootballFieldRepository.findByUserid(userid,sort);
        if (historyRentFootballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.KIRALAMA_GECMISI_YOK);
        return historyRentFootballField.stream().filter(x-> x.getOccupancyStatus()==EOccupancyStatus.FILLED).collect(Collectors.toList());
    }

    public RentFootballField guestRentFootballField(GuestRentFootballFieldRequestDto dto) {
        Optional<FootballField> footballField = footballFieldRepository.findById(dto.getFootballFieldid());
        if (footballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.HALISAHA_BULUNAMADI);
       RentFootballField rentFootballField = IRentFootballFieldMapper.INSTANCE.toRentFootballFieldGuest(dto);
       rentFootballField.setOccupancyStatus(EOccupancyStatus.FILLED);
       save(rentFootballField);
       return rentFootballField;

    }

    public RentFootballField findByRentFootballFeieldId(String id) {
        Optional<RentFootballField> rentFootballField = rentFootballFieldRepository.findById(id);
        if (rentFootballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.KIRALAMA_ISLEMI_BULUNAMADI);
        return rentFootballField.get();
    }
}
