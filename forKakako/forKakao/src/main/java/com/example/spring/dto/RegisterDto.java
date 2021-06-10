package com.example.spring.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor // 파라미터를 받지 않는 생성자를 만듬
@Data
public class RegisterDto {

	private String keyCode;
	
	private String description;
	
	private String type;
	
	private String generator;
	
	private long min_length;
	
	private String generateKey;
	
	private String message;

}
