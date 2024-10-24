CREATE TABLE "BOARD" (	
	"BOARD_ID" NUMBER NOT NULL ENABLE, 
	"BOARD_TITLE" VARCHAR2(300 BYTE), 
	"BOARD_CONTENT" VARCHAR2(4000 BYTE), 
	"BOARD_WORD_YYYY" VARCHAR2(10 BYTE), 
	"BOARD_WORD_MM" VARCHAR2(10 BYTE), 
	"BOARD_WORD_DD" VARCHAR2(10 BYTE), 
	"BOARD_WORD_APM" VARCHAR2(10 BYTE), 
	"BOARD_WORD_HH" VARCHAR2(10 BYTE), 
	"BOARD_WORD_MI" VARCHAR2(10 BYTE), 
	"BOARD_FILE_PATH" VARCHAR2(100 BYTE), 
	"BOARD_REG_DT" DATE DEFAULT SYSDATE NOT NULL ENABLE, 
	"BOARD_LUP_DT" DATE DEFAULT SYSDATE NOT NULL ENABLE, 
	"USER_ID" VARCHAR2(100 BYTE), 
	CONSTRAINT "BOARD_PK" PRIMARY KEY ("BOARD_ID")
)