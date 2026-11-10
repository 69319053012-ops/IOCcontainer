# Student API

ตัวอย่างงาน Spring Boot ตามใบงาน Student

## เรียกใช้งาน

ใช้ Java 25 (LTS) และ Maven 3.9 ขึ้นไป แล้วรัน:

```bash
mvn clean package
java -jar target/student-api-0.0.1-SNAPSHOT.jar
```

## API

- `GET /students` แสดงนักเรียนทั้งหมด
- `GET /student?id=2` ค้นหาด้วยรหัสนักเรียน

ข้อมูลเริ่มต้นในหน่วยความจำ:

- id `1`: John, grade `3.5`
- id `2`: kewin, grade `3.8`

ถ้าไม่ส่ง `id` หรือส่งค่าที่ไม่ใช่ตัวเลข Spring จะตอบ `400 Bad Request` และถ้าไม่พบรหัสจะตอบ `404 Not Found` ข้อมูลจะเริ่มต้นใหม่ทุกครั้งที่ปิดแล้วเปิดโปรแกรม
