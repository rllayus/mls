package com.laredo.mls.controller;

import com.laredo.dto.OKAuthDto;
import com.laredo.dto.UserResponseDto;
import com.laredo.dto.response.BankResponseDto;
import com.laredo.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "bancos", description = "API para listar los Bancos")
@RestController
@RequestMapping("/api/v1/bancos")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping()
    @Operation(summary = "Método para listar Bancos",
            description = "Este método sirve para listar los bancos ",
            tags = {"bancos"},
            responses = {
                    @ApiResponse(description = "Operación satisfactorio", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BankResponseDto.class)))),

                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Fallo de autentificación", content = @Content(schema = @Schema(hidden = true))),
            }, security = @SecurityRequirement(name = "bearerToken"))
    public ResponseEntity<List<BankResponseDto>> getAll() {
        return ResponseEntity.ok(bankService.listAll());
    }
}
