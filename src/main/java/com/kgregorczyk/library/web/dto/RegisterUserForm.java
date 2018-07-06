package com.kgregorczyk.library.web.dto;

import com.kgregorczyk.library.validators.PasswordConstraintValidator.ValidPassword;
import com.kgregorczyk.library.validators.PasswordMatchesValidator.PasswordMatches;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@PasswordMatches
@NoArgsConstructor
public class RegisterUserForm {

  @NotEmpty
  @NotBlank
  @Email
  @NotNull
  private String email;

  @NotBlank
  @NotEmpty
  @NotNull
  @ValidPassword
  private String password;

  @NotBlank
  @NotEmpty
  @NotNull
  private String passwordConfirm;

}
