package com.mvc.springprojections.dto;

import com.mvc.springprojections.dto.markers.Create;
import com.mvc.springprojections.dto.markers.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.mvc.springprojections.constants.ValidationConstants.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    @Null(groups = {Create.class}, message = "ID не должен быть указан при создании департамента")
    private Long id;

    @NotBlank(groups = {Create.class, Update.class}, message = "Название департамента обязательно")
    @Size(groups = {Create.class, Update.class}, min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH, message = "Название департамента должно быть от 2 до 50 символов")
    private String name;
}
