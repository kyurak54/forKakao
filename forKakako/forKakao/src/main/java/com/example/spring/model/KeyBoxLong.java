package com.example.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter	// getter 메소드 생성
@Builder	// 빌더 패턴을 사용할 수 있음
@NoArgsConstructor // 파라미터를 받지 않는 생성자를 만듬
@Entity
@Table(name="keyboxlong")
public class KeyBoxLong {

	@Id // pk명시
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long generateKey;

	@JoinColumn(name="keycode")
	@Column(nullable = false)
	private String keyCode;

	public KeyBoxLong(long generateKey, String keyCode) {
		super();
		this.generateKey = generateKey;
		this.keyCode = keyCode;
	}
	

}
