package com.berk.service;

import com.berk.repository.entity.responce.MessageResponseDto;
import com.berk.exception.ErrorType;
import com.berk.exception.UserServiceException;
import com.berk.mapper.IUserMapper;
import com.berk.rabbitmq.model.*;
import com.berk.rabbitmq.producer.CreateFootballFieldProducer;
import com.berk.repository.IUserRepository;
import com.berk.repository.entity.User;
import com.berk.repository.enums.ERole;
import com.berk.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,String> {

    private final IUserRepository userRepository;
    private final CreateFootballFieldProducer createFootballFieldProducer;


    public UserService(IUserRepository userRepository, CreateFootballFieldProducer createFootballFieldProducer) {
        super(userRepository);
        this.userRepository=userRepository;
        this.createFootballFieldProducer = createFootballFieldProducer;
    }

    public MessageResponseDto createUser(CreateUserModel model) {
        User user = IUserMapper.INSTANCE.toUser(model);
        user.setAuthid(model.getId());
        save(user);
        return MessageResponseDto.builder().message("Başarıyla kaydedildi.").build();
    }
    public MessageResponseDto updatePassword(UpdateUserPasswordModel model){
       Optional<User> user = userRepository.findByEmail(model.getEmail());
       if (user.isEmpty())
           throw new UserServiceException(ErrorType.EMAIL_ERROR);
        user.get().setPassword(model.getPassword());
        update(user.get());
        return MessageResponseDto.builder().message("Şifre değiştirme işlemi başarıyla tamamlandı.").build();
    }

    public MessageResponseDto deleteUser(DeleteUserModel model) {
        Optional<User> user = userRepository.findByAuthid(model.getId());
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        user.get().setStatus(model.getStatus());
        update(user.get());
        return MessageResponseDto.builder().message("Kullanıcı başarıyla silinmiştir.").build();
    }

    public MessageResponseDto updateUser(UpdateUserModel model) {
        Optional<User> user = userRepository.findByAuthid(model.getId());
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        user.get().setName(model.getName());
        user.get().setSurname(model.getSurname());
        user.get().setEmail(model.getEmail());
        user.get().setTelNo(model.getTelNo());
        user.get().setRole(model.getRole());
        update(user.get());
        return MessageResponseDto.builder().message("Kullanıcı başarıyla güncellenmiştir.").build();
    }

    /**
     * Bu metodu sadece halısaha yonetıcısı rolune sahıp kısıler kullanabılır.
     * @param model
     * @return
     */
    public MessageResponseDto createFootballField(RegisterFootballFieldModel model) {
        Optional<User> user = userRepository.findById(model.getUserid());
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        if (user.get().getRole()!= ERole.EXECUTIVE)
            throw new UserServiceException(ErrorType.INVALID_ROLE);
        createFootballFieldProducer.createFootballField(RegisterFootballFieldModel.builder().userid(model.getUserid())
                .city(model.getCity()).district(model.getDistrict()).price(model.getPrice()).email(model.getEmail()).name(model.getName()).telephoneNumber(model.getTelephoneNumber())
                .address(model.getAddress()).build());
        return MessageResponseDto.builder().message("Talep gönderilmiştir.").build();
    }

    /**
     * Authid den userid dondugumuz method.
     * @param id
     * @return
     */
    public String findByUserid(String id) {
        Optional<User> user = userRepository.findByAuthid(id);
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        return  user.get().getId();
    }

    public User findByUser(String userid) {
        Optional<User> user = userRepository.findById(userid);
        if (user.isEmpty())
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        return user.get();
    }
}
