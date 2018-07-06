package com.kgregorczyk.library.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.kgregorczyk.library.validators.PasswordMatchesValidator.PasswordMatches;
import com.kgregorczyk.library.web.dto.RegisterUserForm;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

public class PasswordMatchesValidator
    implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context) {
    RegisterUserForm user = (RegisterUserForm) obj;
    return user.getPassword().equals(user.getPasswordConfirm());
  }

  @Target({TYPE, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Constraint(validatedBy = PasswordMatchesValidator.class)
  @Documented
  public @interface PasswordMatches {

    String message() default "Passwords don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
  }
}