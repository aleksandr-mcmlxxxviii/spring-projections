package com.mvc.springprojections.dto;

import com.mvc.springprojections.dto.markers.Create;
import com.mvc.springprojections.dto.markers.Update;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.mvc.springprojections.constants.ValidationConstants.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @Null(groups = {Create.class}, message = "ID не должен быть указан при создании сотрудника")
    private Long id;

    @NotBlank(groups = {Create.class}, message = "Имя обязательно")
    @Size(groups = {Create.class, Update.class}, min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Имя должно быть от 2 до 50 символов")
    private String firstName;

    @NotBlank(groups = {Create.class}, message = "Фамилия обязательно")
    @Size(groups = {Create.class, Update.class}, min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Фамилия должно быть от 2 до 50 символов")
    private String lastName;

    @NotBlank(groups = {Create.class}, message = "Должность обязательно")
    @Size(groups = {Create.class, Update.class}, min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Должность должна быть от 2 до 50 символов")
    private String position;

    @NotNull(groups = {Create.class}, message = "Зарплата обязательно")
    @Positive(groups = {Create.class, Update.class}, message = "Зарплата должна быть > 0")
    private BigDecimal salary;

    @NotNull(groups = {Create.class}, message = "ID отдела обязателен")
    @Positive(groups = {Create.class, Update.class}, message = "Id Департамента должно быть > 0")
    private Long departmentId;
}
