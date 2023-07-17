package com.hdsam.springclouddemo.springsecuritydemo.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yeo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private Integer studentId;
	private String studentName;

}