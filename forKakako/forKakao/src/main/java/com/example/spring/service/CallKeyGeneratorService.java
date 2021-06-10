package com.example.spring.service;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.dto.RegisterDto;
import com.example.spring.equip.RandomString;
import com.example.spring.model.CallKeyGeneratorRepository;
import com.example.spring.model.CallKeyLongGeneratorRepository;
import com.example.spring.model.KeyBox;
import com.example.spring.model.KeyBoxLong;
import com.example.spring.model.KeyInfo;
import com.example.spring.model.KeyInfoRepository;

@Service
public class CallKeyGeneratorService {
	
	@Autowired
	private CallKeyGeneratorRepository callKeyGeneratorRepository;

	@Autowired
	private CallKeyLongGeneratorRepository callKeyLongGeneratorRepository;

	@Autowired
	private KeyInfoRepository keyInfoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	// 문자열 정보
	final private String stringInfo = "String";
	// 숫자열 정보	
	final private String numInfo = "number";
	// MysqlKeyGenerator의
	final private String mysqlKeyGenerator = "mysql";
	// GenericKeyGenerator의	
	final private String genericKeyGenerator = "generic";
	// 문자열 KEY 자릿수
	final private int count_word = 19;
	
	public String callKeyGenerator(RegisterDto registerDto) throws Exception {

		KeyInfo keyInfo = modelMapper.map(registerDto, KeyInfo.class);
		
		String rtValue= null;

		try {
			
			// Key정보를 DB로부터 취득
			Optional<KeyInfo> keyInfoformDB = keyInfoRepository.findById(keyInfo.getKeyCode());
			
			// key 정보 등록확인		
			if(keyInfoformDB.isEmpty()) {
				rtValue = "등록되지 않은 key 정보입니다. 다시 확인해주세요.";
				return rtValue;
			}
			
			String generateKey = null;

			// 문자열 정보인 경우
			if (keyInfoformDB.get().getType().equals(stringInfo)) {
				RandomString randString = new RandomString();
				Boolean flag = true;
				while (flag) {

					// [XXXX-XXXX-XXXX-XXXX]의 KEY 생성(X : 임의의 영어 대문자, 숫자)

					generateKey = randString.RandomString(count_word).toString();
					// 값이 존재하면 generateKey 재취득 
					flag = callKeyGeneratorRepository.existsById(generateKey);

				}
				registerDto.setGenerateKey(generateKey);
				saveKeyCode(registerDto);
				return generateKey;
				
				// 숫자열 정보 && mysql인 경우
			} else if (keyInfoformDB.get().getType().equals(numInfo) && 
					keyInfoformDB.get().getGenerator().equals(mysqlKeyGenerator)) {
				
				saveKeyLongCode(registerDto);
				Optional<KeyBoxLong> rtKeyInfoformDB = callKeyLongGeneratorRepository.findById(keyInfoformDB.get().getKeyCode());
				generateKey = Long.toString(rtKeyInfoformDB.get().getGenerateKey());
				return generateKey;
				// 숫자열 정보 && generic인 경우
			} else if (keyInfoformDB.get().getType().equals(numInfo) && 
					keyInfoformDB.get().getGenerator().equals(genericKeyGenerator)) {
				saveKeyLongCode(registerDto);
				return generateKey;
			}

			return null;

		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void saveKeyCode(RegisterDto registerDto) throws Exception {
		KeyBox keyCode = modelMapper.map(registerDto, KeyBox.class);
		// keycode테이블에 등록
		try {
			callKeyGeneratorRepository.save(keyCode);
		} catch (Exception e) {
			throw e;
		}
	}

	public void saveKeyLongCode(RegisterDto registerDto) throws Exception {
		KeyBoxLong keyBoxLong = modelMapper.map(registerDto, KeyBoxLong.class);
		// keycode테이블에 등록
		try {
			callKeyLongGeneratorRepository.save(keyBoxLong);
		} catch (Exception e) {
			throw e;
		}
	}
}
