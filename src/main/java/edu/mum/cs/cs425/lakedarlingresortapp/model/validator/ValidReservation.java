package edu.mum.cs.cs425.lakedarlingresortapp.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidReservationChecker.class)
public @interface ValidReservation {
    String message() default "{edu.mum.cs.cs425.lakedarlingresortapp.model.validator.ValidReservation.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
