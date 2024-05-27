package org.sparta.spring_basic_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Size(min=4, max=10)
    @Pattern(regexp = "(?=.*[a-z]).{4,10}", message = "최소 4자 이상, 10자 이하,알파벳 소문자(a~z), 숫자(0~9)로 입력하세요.")
    private String username;
    @NotBlank
    @Size(min=8, max=15)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}", message = "최소 8자 이상, 15자 이하, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 입력하세요.")
    private String password;
    @NotBlank
    private String name;
    private boolean admin = false;
    private String adminToken = "";
}