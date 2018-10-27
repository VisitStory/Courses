package net.tecgurus.courses.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CourseCreateDTO {

    @NotEmpty(message = "The course name can not be blank")
    @ApiModelProperty(notes = "A course name", required = true)
    private String name;

    @NotEmpty(message = "The course description can not be blank")
    @ApiModelProperty(notes = "A course name", required = false)
    private String description;

}
