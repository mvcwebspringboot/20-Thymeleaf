package com.study.boot.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DataDTO {
	
	@NotBlank
	private String name;
	
	@PositiveOrZero(message = "양수, 또는 0만 가능합니다.")
	private int age;
	
	@NotEmpty
	private String address;
}
