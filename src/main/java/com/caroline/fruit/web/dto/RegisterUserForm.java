package com.caroline.fruit.web.dto;

import com.caroline.fruit.validators.PasswordConstraintValidator;
import com.caroline.fruit.validators.PasswordMatchesValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@PasswordMatchesValidator.PasswordMatches
@NoArgsConstructor
public class RegisterUserForm {

  @NotEmpty
  @NotBlank
  @Email
  @NotNull
  private String username;

  @NotBlank
  @NotEmpty
  @NotNull
  @PasswordConstraintValidator.ValidPassword
  private String password;

  @NotBlank
  @NotEmpty
  @NotNull
  private String passwordConfirm;

}
