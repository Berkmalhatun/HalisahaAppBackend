package com.berk.service;

import com.berk.dto.request.LoginUserRequestDto;
import com.berk.dto.request.RegisterUserRequestDto;
import com.berk.dto.request.UpdateUserPasswordRequestDto;
import com.berk.dto.request.UpdateUserRequestDto;
import com.berk.dto.responce.MessageResponseDto;
import com.berk.dto.responce.UpdateUserResponseDto;
import com.berk.exception.AuthServiceException;
import com.berk.exception.ErrorType;
import com.berk.mapper.IAuthMapper;
import com.berk.rabbitmq.model.CreateUserModel;
import com.berk.rabbitmq.model.DeleteUserModel;
import com.berk.rabbitmq.model.UpdateUserModel;
import com.berk.rabbitmq.model.UpdateUserPasswordModel;
import com.berk.rabbitmq.producer.CreateUserProducer;
import com.berk.rabbitmq.producer.DeleteUserProducer;
import com.berk.rabbitmq.producer.UpdatePasswordProducer;
import com.berk.rabbitmq.producer.UpdateUserProducer;
import com.berk.repository.IAuthRepository;
import com.berk.repository.entity.Auth;
import com.berk.repository.enums.ERole;
import com.berk.repository.enums.EStatus;
import com.berk.utility.JwtTokenManager;
import com.berk.utility.ServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,String> {
    private final IAuthRepository authRepository;
    private final CreateUserProducer createUserProducer;
    private final UpdatePasswordProducer updatePasswordProducer;
    private final DeleteUserProducer deleteUserProducer;
    private final UpdateUserProducer updateUserProducer;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, CreateUserProducer createUserProducer, UpdatePasswordProducer updatePasswordProducer, DeleteUserProducer deleteUserProducer, UpdateUserProducer updateUserProducer, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.createUserProducer = createUserProducer;
        this.updatePasswordProducer = updatePasswordProducer;
        this.deleteUserProducer = deleteUserProducer;
        this.updateUserProducer = updateUserProducer;
        this.jwtTokenManager = jwtTokenManager;
    }

    public MessageResponseDto register(RegisterUserRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findByEmail(dto.getEmail());
        if (authOptional.isPresent())
            throw new AuthServiceException(ErrorType.EMAIL_ERROR);
       if(!dto.getPassword().equals(dto.getRePassword()))
           throw new AuthServiceException(ErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setCreateDate(System.currentTimeMillis());
        auth.setUpdateDate(System.currentTimeMillis());
        save(auth);
        createUserProducer.createUser(CreateUserModel.builder().id(auth.getId()).name(auth.getName()).surname(auth.getSurname())
                .email(auth.getEmail()).telNo(auth.getTelNo()).password(auth.getPassword()).status(auth.getStatus()).role(auth.getRole()).build());
        return MessageResponseDto.builder().message("Kayıt işlemi başarıyla tamamlandı").build();
    }

    public MessageResponseDto updateUser(UpdateUserRequestDto dto) {
        Optional<Auth> auth = authRepository.findById(dto.getId());
                if(auth.isEmpty())
                    throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        auth.get().setUpdateDate(System.currentTimeMillis());
        auth.get().setEmail(dto.getEmail());
        auth.get().setName(dto.getName());
        auth.get().setSurname(dto.getSurname());
        auth.get().setTelNo(dto.getTelNo());
        auth.get().setRole(dto.getRole());
        update(auth.get());
        updateUserProducer.updateUser(UpdateUserModel.builder().id(auth.get().getId()).name(auth.get().getName())
                .surname(auth.get().getSurname()).email(auth.get().getEmail()).telNo(auth.get().getTelNo()).role(auth.get().getRole()).build());
        return MessageResponseDto.builder().message("Guncelleme işlemi başarıyla tamamlandı.").build();

    }

    public MessageResponseDto updateUserPassword(UpdateUserPasswordRequestDto dto) {
        Optional<Auth> auth = authRepository.findById(dto.getId());
        System.out.println(auth.get().getName());
        if(auth.isEmpty())
            throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        if (!auth.get().getPassword().equals(dto.getPassword()))
            throw new AuthServiceException(ErrorType.PASSWORD_ERROR);
        if (auth.get().getPassword().equals(dto.getNewPassword()))
            throw new AuthServiceException(ErrorType.NEW_PASSWORD_ERROR);
        if(!dto.getNewPassword().equals(dto.getReNewPassword()))
            throw  new AuthServiceException(ErrorType.RE_NEW_PASSWORD_ERROR);
        auth.get().setPassword(dto.getNewPassword());
        update(auth.get());
        updatePasswordProducer.resetPassword(UpdateUserPasswordModel.builder().id(auth.get().getId())
               .email(auth.get().getEmail()).password(auth.get().getPassword()).build());
        return MessageResponseDto.builder().message("Şifre değiştirme işlemi başarıyla tamamlandı.").build();
    }

public MessageResponseDto deleteUser(String id) {
    Optional<Auth> auth = authRepository.findById(id);
    if (auth.isEmpty())
        throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);

    auth.get().setStatus(EStatus.DELETED);
    update(auth.get());
    deleteUserProducer.deleteUser(DeleteUserModel.builder().id(auth.get().getId()).status(auth.get().getStatus()).build());
    return MessageResponseDto.builder().message("Kaydınız başarıyla silinmiştir.").build();
}

    /**
     * Kullanıcı rolu admın mı degıl mı kontrol eden metot.
     * @param token
     */
    private ERole ensureUserHasAdminRole(String token) {
        ERole roles = jwtTokenManager.getRoleFromToken(token);
        if (roles!=ERole.ADMIN) {
            throw new AuthServiceException(ErrorType.UNAUTHORIZED_ACCESS);
        }
        return roles;
    }
    public MessageResponseDto login(LoginUserRequestDto dto) {
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_EMAIL_PASSWORD);
        if(auth.get().getStatus()==EStatus.DELETED)
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_EMAIL_PASSWORD);
        String authStatus = auth.get().getStatus().toString();
        String token = jwtTokenManager.createToken(auth.get().getId(),auth.get().getRole(), auth.get().getStatus(),auth.get().getEmail())
                .orElseThrow(()->{
            throw new AuthServiceException(ErrorType.TOKEN_NOT_CREATED);
        });
        System.out.println("token==>"+token);
        return MessageResponseDto.builder().token(token).message("Giriş başarılı").build();
    }

    public MessageResponseDto updateActiveUser(String id) {
        Optional<Auth> auth = authRepository.findById(id);
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        auth.get().setStatus(EStatus.ACTIVE);
        update(auth.get());
        return MessageResponseDto.builder().message("Kaydınız başarıyla aktifleşmiştir.").build();
    }

    public ResponseEntity<?> validateToken(String token) {
        boolean isValid = jwtTokenManager.verifyToken(token);
        if (isValid){
            return ResponseEntity.ok().body("Token is valid.");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        }
    }

    public UpdateUserResponseDto findByUserDetailsFromId(String id) {
        Optional<Auth> auth = authRepository.findById(id);
        if (auth.isEmpty())
            throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        UpdateUserResponseDto userDetails = IAuthMapper.INSTANCE.toUserDetails(auth.get());
        return userDetails;
    }
}
