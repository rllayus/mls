package com.laredo.mls.controller;

import com.laredo.dto.request.TransferRequestDto;
import com.laredo.dto.response.TransferResponseDto;
import com.laredo.service.TransactionService;
import com.laredo.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "transacciones", description = "API para realizar transacciones de prueba")
@RestController
@RequestMapping("/api/v1/transferencia")
@Slf4j
public class TransactionController {
    @Autowired
    private TransferService transferService;

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
            return ResponseEntity.ok(transferService.transfer(dto));
        }catch (Exception e){
            log.error("Error al realizar transferencia", e);
            return ResponseEntity.badRequest().build();
        }

    }
    @Operation(summary = "Método para verificar estado de una transaccion",
            description = "Método para verificar estado de una transaccion ",
            tags = {"transacciones"},
            responses = {
                    @ApiResponse(description = "Operación satisfactorio", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransferResponseDto.class))),

                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Fallo de autentificación", content = @Content(schema = @Schema(hidden = true))),
            }, security = @SecurityRequirement(name = "bearerToken"))
    @GetMapping("/{idTransferencia}")
    public ResponseEntity<TransferResponseDto> verifierTransaction(
            @PathVariable("idTransferencia") String idTransferencia) {
        try {
            return ResponseEntity.ok(transferService.verifi(idTransferencia));
        }catch (Exception e){
            log.error("Error al obtener la transaccion", e);
            return ResponseEntity.badRequest().build();
        }

    }


}
