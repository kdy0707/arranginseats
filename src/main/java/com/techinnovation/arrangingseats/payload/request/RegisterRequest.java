package com.techinnovation.arrangingseats.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(min =3, max = 11)
    private String mobileNumber;

    @NotBlank
    @Size(min =3, max = 20)
    private String username;

    @Size(max = 50)
    private String department;

    @Size(max = 25)
    private String position;

    @Size(max = 30)
    private String project;
}
