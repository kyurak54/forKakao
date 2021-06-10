package com.example.spring.model;

import lombok.*;
import javax.persistence.*;

@Getter	// getter 메소드 생성
@Builder	// 빌더 패턴을 사용할 수 있음
@NoArgsConstructor // 파라미터를 받지 않는 생성자를 만듬
@Entity
@Table(name="keyinfo")
public class KeyInfo {

	@Id // pk명시
	private String keyCode;
	
	private String description;
	
	@Column(nullable = false)
	private String type;
	
	private String generator;
	
	@JoinColumn(name="min_length")
	private String min_length;

	public KeyInfo(String keyCode, String description, String type, String generator, String min_length) {
		super();
		this.keyCode = keyCode;
		this.description = description;
		this.type = type;
		this.generator = generator;
		this.min_length = min_length;
	}

}
