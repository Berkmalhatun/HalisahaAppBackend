package com.berk.service;

import com.berk.dto.request.DeleteFootballFieldRequestDto;
import com.berk.dto.request.UpdateFootballFieldRequestDto;
import com.berk.dto.responce.MessageResponseDto;
import com.berk.exception.ErrorType;
import com.berk.exception.FootballFieldServiceException;
import com.berk.mapper.IFootballFieldMapper;
import com.berk.rabbitmq.model.RegisterFootballFieldModel;
import com.berk.repository.IFootballFieldRepository;
import com.berk.repository.entity.FootballField;
import com.berk.repository.enums.EStatus;
import com.berk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class FootballFieldService extends ServiceManager<FootballField,String> {
    private IFootballFieldRepository footballFieldRepository;

    public FootballFieldService(IFootballFieldRepository footballFieldRepository) {
        super(footballFieldRepository);
        this.footballFieldRepository=footballFieldRepository;
    }

    /**
     * Bu methodda user mıcroservısınde gonderılerek oluşturuldu. Halısaha olusturmak ıcın gereklı rol kontrolu user
     * microservısınde yapıldı.Gerektıgı zaman dısarıdan alınan token ıle burada yapılabılır.
     * @param model
     * @return
     */
    public MessageResponseDto register(RegisterFootballFieldModel model) {
        Optional<FootballField> footballFieldOptional = footballFieldRepository.findByEmail(model.getEmail());
        if (footballFieldOptional.isPresent())
            throw new FootballFieldServiceException(ErrorType.EMAIL_ERROR);
        FootballField footballField = IFootballFieldMapper.INSTANCE.toFootballField(model);
        footballField.setCity(capitalizeFirstLetter(footballField.getCity()));
        footballField.setDistrict(capitalizeFirstLetter(footballField.getDistrict()));
        save(footballField);
        return MessageResponseDto.builder().message("Kayıt işlemi başarıyla tamamlandı.").build();
    }

    public MessageResponseDto deleteField(DeleteFootballFieldRequestDto dto) {
        Optional<FootballField> footballField = footballFieldRepository.findById(dto.getId());
        if (footballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.HALISAHA_BULUNAMADI);
        footballField.get().setStatus(EStatus.DELETED);
        update(footballField.get());
        return MessageResponseDto.builder().message("Halısaha başarıyla silindi.").build();
    }

    /**
     * Filtrelemede ilk harfi buyuk gerı kalanı kucuk yapan metottur.
     * @param input
     * @return
     */
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase(new Locale("tr", "TR")) +
                input.substring(1).toUpperCase(new Locale("tr", "TR"));
    }

    /**
     * Filtreleme yaparken buyuk kucuk harf'den dolayı bulamama sorunu cozmek ıcın şu şekilde ayarlama yapılacak...
     * Ankara , İstanbul , İzmir , Adana , Malatya
     * @param city
     * @param district
     * @return
     */
    public List<FootballField> getFilterCityAndDistrict(String city, String district) {
        city= capitalizeFirstLetter(city);
        district=capitalizeFirstLetter(district);
        List<FootballField> filterFootballFieldList = footballFieldRepository.findByCityAndDistrict(city,district);
        if (filterFootballFieldList.isEmpty())
            throw new FootballFieldServiceException(ErrorType.HALISAHA_BULUNAMADI);
        return filterFootballFieldList;
    }

    public List<FootballField> getFootballField(String userid) {
        List<FootballField> footballFieldList = footballFieldRepository.findByUserid(userid);
        return footballFieldList;
    }

    public MessageResponseDto updateFootballField(UpdateFootballFieldRequestDto dto) {
        Optional<FootballField> optionalFootballField = footballFieldRepository.findById(dto.getId());
        if (optionalFootballField.isEmpty())
            throw new FootballFieldServiceException(ErrorType.HALISAHA_BULUNAMADI);
        FootballField footballField = IFootballFieldMapper.INSTANCE.updateFootballField(dto);
        footballField.setUserid(optionalFootballField.get().getUserid());
        footballField.setStatus(optionalFootballField.get().getStatus());
        update(footballField);
        return MessageResponseDto.builder().message("Halısaha başarıyla güncellendi.").build();
    }
}
