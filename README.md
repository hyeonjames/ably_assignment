# 실행방법
```bash
docker-compose up
```


# API
## 전화번호 인증 코드 생성
```bash
curl -XPOST http://localhost:8080/api/v1/mobile/generate -H 'Content-Type: application/json' \
  --data '{"mobile":"010-0000-0000"}'
# {"ok":true,"data":true,"error":null}
docker logs ably_app # 로그를 찾아 인증번호를 볼 수 있습니다.

# ably_app | 2021-11-06 14:56:54.873  INFO 1 --- [nio-8080-exec-1] j.a.a.app.service.MobileVerifyService    : token generated for 010-0000-0000: 293005
```

## 전화번호 인증
```bash
curl -XPOST http://localhost:8080/api/v1/mobile/verify -H 'Content-Type: application/json' \
  --data '{"forSignUp": true, "token": "000000", "mobile":"010-0000-0000"}'
# {"ok":true,"data":true,"error":null}
```

## 회원 가입
```bash
curl -XPOST http://localhost:8080/api/v1/auth/signup -H 'Content-Type: application/json'\
  --data '{"email": "abc@gmail.com", "password":"abcd123$", "name": "james", "nickName": "james", "mobile":"010-0000-0000"}'
# {"ok":true,"data":true,"error":null}
```

## 로그인
```bash
curl -XPOST http://localhost:8080/api/v1/auth/signin -H 'Content-Type: application/json'\
  --data '{"email": "abc@gmail.com", "password":"abcd123$"}'
# {"ok":true,"data":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjM2MjEwNjkzLCJleHAiOjE2MzYyMTQyOTN9.QE8ck2E5CJV8GcRfMA7i84NeLpVn7Oauf2uN1HWnrSc","error":null}
```

## 비밀번호 변경
```bash
# 전화번호 인증에서 forSignUp=false로 인증합니다.
curl -XPOST http://localhost:8080/api/v1/mobile/verify -H 'Content-Type: application/json' \
  --data '{"forSignUp": false, "token": "000000", "mobile":"010-0000-0000"}'
# {"ok":true,"data":true,"error":null}

curl -XPOST http://localhost:8080/api/v1/users/change/password -H 'Content-Type: application/json'\
  -H 'X-AUTH-TOKEN:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjM2MjEwNjkzLCJleHAiOjE2MzYyMTQyOTN9.QE8ck2E5CJV8GcRfMA7i84NeLpVn7Oauf2uN1HWnrSc'  \
  --data '{"oldPassword": "abcd123$", "newPassword": "abcd234$"}'
# {"ok":true,"data":true,"error":null}
```

## 나의 정보 조회
```bash
curl -XGET http://localhost:8080/api/v1/users/me \
  -H 'X-AUTH-TOKEN:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjM2MjEwNjkzLCJleHAiOjE2MzYyMTQyOTN9.QE8ck2E5CJV8GcRfMA7i84NeLpVn7Oauf2uN1HWnrSc'
# {"ok":true,"data":{"email":"abc@gmail.com","nickName":"james","name":"james","mobile":"010-0000-0000"},"error":null}
```