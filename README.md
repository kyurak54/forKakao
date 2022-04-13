# forKakao
javaProject

### 개발 환경
- JDK 11
- Spring boot
- maven
- mybatis
- mysql
- postman
 ---
### 테이블 구조
![image](https://user-images.githubusercontent.com/69380457/121522149-ca39ee80-ca2f-11eb-9193-c06aba6d181a.png)
* keyinfo : 키 정보를 등록
* keybox : 새롭게 발급된 키 정보를 등록(문자열)
* keyboxlong : 새롭게 발급된 키 정보를 등록(숫자열)

---
### 문제해결 방안
* 필수 1
  + Key 값으로 들어온 정보를 기준으로 기존 DB에 중복 검사를 실시한 후, 중복대상이 없는 경우 `keyinfo`에 저장
  + 중복인 경우 경고메세지를 표시
* 필수 2
  + 필수 1에서 등록한 데이터가 존재 할 경우 `keyinfo`에서 등록한 key값을 기준으로 api를 호출
  + `keyinfo`에 등록되어진 데이터를 찾아서, type이 string 일 경우 문자열을, number 일 경우 숫자열 key 값을 취득함
