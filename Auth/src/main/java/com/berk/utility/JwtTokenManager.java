package com.berk.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.berk.dto.responce.GetIdRoleStatusEmailFromTokenResponseDto;
import com.berk.exception.AuthServiceException;
import com.berk.exception.ErrorType;
import com.berk.repository.enums.ERole;
import com.berk.repository.enums.EStatus;

import com.berk.exception.AuthServiceException;
import com.berk.exception.ErrorType;
import com.berk.repository.enums.EStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JwtTokenManager {
    private final String issuer = "Halisahaapp";
    private final String secretKey = "Halisahaapp";
    private final String audience = "audience";
    public Optional<String> createToken(String id, ERole role, EStatus status, String email){
        String token= null;
        Long exDate = 1000L*60*150;
        try{

            token = JWT.create().withAudience(audience)
                    .withClaim("id",id)
                    .withClaim("howtopage","AuthMicroService")
                    .withClaim("lastjoin", System.currentTimeMillis())
                    .withClaim("role", role.name())
                    .withClaim("status", status.name())
                    .withClaim("email",email)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }
    }

//    public Boolean verifyToken(String token){
//        try{
//            Algorithm algorithm = Algorithm.HMAC512(secretKey);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer(issuer).withAudience(audience).build();
//            DecodedJWT decodedJWT = verifier.verify(token);
//            if(decodedJWT == null)
//                return false;
//        }catch (Exception exception){
//            return false;
//        }
//        return true;
//    }

    public Boolean verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .withAudience(audience)
                    .build();
            verifier.verify(token);
            // Eğer buraya kadar gelindiyse, token başarılı bir şekilde doğrulandı.
            return true;
        } catch (Exception exception){
//            exception.printStackTrace(); // bu sekılde yazılabılır ya da loglama yapılabılır.Logger ile...
            return false;
        }
    }


    public Optional<String> getIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            String id = decodedJWT.getClaim("id").asString();
            String howToPage = decodedJWT.getClaim("howtopage").asString();
            System.out.println("howtopage....: "+ howToPage);
           return Optional.of(id);
       }catch (Exception exception){
           return Optional.empty();
       }
    }

    public ERole getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new AuthServiceException(ErrorType.INVALID_TOKEN);
            }
            String roleStr = decodedJWT.getClaim("role").asString();
            ERole role = ERole.fromString(roleStr);
            return role;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
    }
//
//    public EStatus getStatusFromToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC512(secretKey);
//            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
//            DecodedJWT decodedJWT = verifier.verify(token);
//            if (decodedJWT == null) {
//                throw new AuthServiceException(ErrorType.INVALID_TOKEN);
//            }
//            EStatus status = decodedJWT.getClaim("status").as(EStatus.class);   //DANIŞ
//            return status;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
//
//        }
//    }
    /////////// buna donus yapılabılır.
    public GetIdRoleStatusEmailFromTokenResponseDto getIdRoleStatusEmailFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null) {
                throw new AuthServiceException(ErrorType.INVALID_TOKEN);
            }
            EStatus status = decodedJWT.getClaim("status").as(EStatus.class);
            ERole role = ERole.valueOf(decodedJWT.getClaim("role").asString());
            String id = decodedJWT.getClaim("id").asString();
            String email = decodedJWT.getClaim("email").asString();
            GetIdRoleStatusEmailFromTokenResponseDto getIdRoleStatusEmailFromTokenResponseDto = GetIdRoleStatusEmailFromTokenResponseDto.builder()
                    .status(status)
                    .email(email)
                    .id(id)
                    .role(role)
                    .build();
            return getIdRoleStatusEmailFromTokenResponseDto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
    }

    public Optional<String> createTokenForQr(String id){
        String token= null;
        Long exDate = 1000L*60*150;
        try{
            token = JWT.create().withAudience(audience)
                    .withClaim("id",id)
                    .withClaim("howtopage","AuthMicroService")
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+exDate))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }
    }
}
