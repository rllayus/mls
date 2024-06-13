package com.laredo.mls;

import com.laredo.Bank;
import com.laredo.enums.BankStatus;
import com.laredo.enums.EstadoUsuario;
import com.laredo.UserEntity;
import com.laredo.repository.BankRespository;
import com.laredo.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Inicializer implements CommandLineRunner {
    private  final UserRespository userRespository;
    private  final PasswordEncoder passwordEncoder;
    private final BankRespository bankRespository;



    @Override
    public void run(String... args) throws Exception {
        if(this.userRespository.findAll().isEmpty()){
            UserEntity userEntity = new UserEntity();
            userEntity.setName("Ricardo Laredo");
            userEntity.setUserName("rlaredo");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setId(1l);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));

            this.userRespository.save(userEntity);
        }
        if(bankRespository.findAll().isEmpty()){
            this.bankRespository.save(Bank.builder().code((short) 1001).abbreviation("BISA").name("Banco BISA").status(BankStatus.ACTIVO).build());

        }
    }
}
