package com.laredo.mls.controller;

import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "transacciones", description = "API para realizar transacciones de prueba")
@RestController
@RequestMapping("v1/api/transferencia")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Método para realizar transferencia",
            description = "Este método sirve para realizar transacciones ",
            tags = {"transacciones"},
            responses = {
                    @ApiResponse(description = "Operación satisfactorio", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransferResponseDto.class))),

                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Fallo de autentificación", content = @Content(schema = @Schema(hidden = true))),
            }, security = @SecurityRequirement(name = "bearerToken"))
    @PostMapping
    public ResponseEntity<TransferResponseDto> transfer(
            @Parameter(schema = @Schema(implementation = TransferRequestDto.class), description = "Request para la transacción")
            @RequestBody TransferRequestDto dto) {

        try {
            return ResponseEntity.ok(transactionService.transfer(dto));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }


}
