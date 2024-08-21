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
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco de Nacional de Bolivia");
            userEntity.setUserName("bnb");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco Santander");
            userEntity.setUserName("std");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco Union");
            userEntity.setUserName("bu");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco Sol");
            userEntity.setUserName("bsol");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco Mercantil");
            userEntity.setUserName("bmsc");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco Ganadero");
            userEntity.setUserName("bg");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);


            userEntity = new UserEntity();
            userEntity.setName("Banco Bisa");
            userEntity.setUserName("bisa");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);

            userEntity = new UserEntity();
            userEntity.setName("Banco Economico");
            userEntity.setUserName("baneco");
            userEntity.setEstadoUsuario(EstadoUsuario.ACTIVO);
            userEntity.setPassword(this.passwordEncoder.encode("123456"));
            this.userRespository.save(userEntity);
        }
        if(bankRespository.findAll().isEmpty()){
            this.bankRespository.save(Bank.builder().code((short) 1000).abbreviation("BISA").name("Banco BISA").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1005).abbreviation("BG").name("Banco Ganader").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1003).abbreviation("BSOL").name("Banco SOL").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1006).abbreviation("STD").name("Banco Santander").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1004).abbreviation("BU").name("Banco Union").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1002).abbreviation("BANECO").name("Banco Economico").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1001).abbreviation("BMSC").name("Banco Mercantil").status(BankStatus.ACTIVO).build());
            this.bankRespository.save(Bank.builder().code((short) 1007).abbreviation("BNB").name("Banco Nacional de Bolivia").status(BankStatus.ACTIVO).build());
        }
       // this.bankRespository.save(Bank.builder().code((short) 1008).abbreviation("BCB").name("Banco Central de Bolivia").status(BankStatus.ACTIVO).build());
    }
}
