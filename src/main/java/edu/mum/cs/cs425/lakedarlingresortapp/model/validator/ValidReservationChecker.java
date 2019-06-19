package edu.mum.cs.cs425.lakedarlingresortapp.model.validator;

import edu.mum.cs.cs425.lakedarlingresortapp.model.Reservation;
import edu.mum.cs.cs425.lakedarlingresortapp.service.ICustomerService;
import edu.mum.cs.cs425.lakedarlingresortapp.service.IVillaService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class ValidReservationChecker implements ConstraintValidator<ValidReservation,Reservation> {

    @Autowired
    ICustomerService customerService;

    @Autowired
    IVillaService villaService;

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext constraintValidatorContext) {
//        null checks


//        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate()).;
//        System.out.println(constraintValidatorContext.getDefaultConstraintMessageTemplate());
        if (reservation.getCustomer()==null || reservation.getVilla()==null ||
            reservation.getStartDate()==null || reservation.getEndDate()==null){
            return false;
        }
//        date checks
        LocalDate today = LocalDate.now();
        if (reservation.getStartDate().isBefore(today) || reservation.getEndDate().isBefore(today) ||
            reservation.getEndDate().isBefore(reservation.getStartDate()) ||
            reservation.getStartDate() == reservation.getEndDate()){
            return false;
        }
//        customer and villa exist
//        if (!villaService.existsbyId(reservation.getVilla().getVillaId())){
//            return false;
//        }
//        if (!customerService.existsById(reservation.getCustomer().getCustomerId())){
//            return false;
//        }
        return true;
    }

    @Override
    public void initialize(ValidReservation constraintAnnotation) {

    }
}
