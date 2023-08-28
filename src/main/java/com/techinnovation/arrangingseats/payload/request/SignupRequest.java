package com.techinnovation.arrangingseats.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {

    @NotBlank
    @Size(min =3, max = 11)
    private String loginId;

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
