package org.sparta.spring_basic_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TodoRequestDto {
    @Size(max = 200, message = "제목을 입력해주세요.")
    private String title;
    private String content;
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String manager;
    @NotBlank(message="비밀번호를 입력해주세요.")
    private String password;
}
