# 실행방법
```bash
docker-compose up
```


# API
## 전화번호 인증 코드 생성
```bash
curl -XPOST http://localhost:8080/api/v1/mobile/generate -H 'Content-Type: application/json' \
  --data '{"mobile":"010-0000-0000"}'

docker logs ably_app # 로그를 찾아 인증번호를 볼 수 있습니다.
```

## 전화번호 인증
```bash
curl -XPOST http://localhost:8080/api/v1/mobile/verify -H 'Content-Type: application/json' \
  --data '{"forSignUp": true, "token": "000000", "mobile":"010-0000-0000"}'
```

## 회원 가입
```bash
curl -XPOST http://localhost:8080/api/v1/auth/signup -H 'Content-Type: application/json'\
  --data '{"email": "abc@gmail.com", "password":"abcd123$", "name": "james", "nickName": "james", "mobile":"010-0000-0000"}'
```

## 로그인
```bash
curl -XPOST http://localhost:8080/api/v1/auth/signin -H 'Content-Type: application/json'\
  --data '{"email": "abc@gmail.com", "password":"abcd123$"}'

```

## 비밀번호 변경
```bash
# 전화번호 인증에서 forSignUp=false로 인증합니다.
curl -XPOST http://localhost:8080/api/v1/mobile/verify -H 'Content-Type: application/json' \
  --data '{"forSignUp": false, "token": "000000", "mobile":"010-0000-0000"}'

curl -XPOST http://localhost:8080/api/v1/users/change/password -H 'Content-Type: application/json'\
  -H 'X-AUTH-TOKEN:'  \
  --data '{"oldPassword": "abcd123$", "newPassword": "abcd234$"}'
```

## 나의 정보 조회
```bash
curl -XGET http://localhost:8080/api/v1/users/me \
  -H 'X-AUTH-TOKEN:'
```