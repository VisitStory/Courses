package net.tecgurus.courses.dto.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseOutDTO {

    private Long id;

    @ApiModelProperty(notes = "Course name")
    private String name;

    @ApiModelProperty(notes = "Course description")
    private String description;

}
