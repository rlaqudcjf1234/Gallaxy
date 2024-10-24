CREATE TABLE "USERS" (	
	"USER_ID" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"USER_PW" VARCHAR2(100 BYTE), 
	"USER_NAME" VARCHAR2(100 BYTE), 
	"USER_NICK_NAME" VARCHAR2(100 BYTE), 
	"USER_GENDER" VARCHAR2(100 BYTE), 
	"USER_EMAIL" VARCHAR2(100 BYTE), 
	"USER_REG_DT" DATE DEFAULT SYSDATE, 
	"USER_LUP_DT" DATE DEFAULT SYSDATE, 
	CONSTRAINT "USERS_PK" PRIMARY KEY ("USER_ID")
)