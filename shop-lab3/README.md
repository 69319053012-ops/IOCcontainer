# ใบงานที่ 3: IoC, Dependency Injection และ Profile

โปรเจกต์ Spring Boot นี้ทำตามใบงานที่ 3 โดยสร้างโครงงาน `shop` แยกไว้ เพราะในโฟลเดอร์งานเดิมมีแต่โปรเจกต์ Student และไม่มีโค้ดตั้งต้นจากใบงานที่ 2

## เริ่มโปรแกรม

ต้องใช้ JDK 21 และ Maven:

```bash
mvn spring-boot:run
```

กำหนด Profile `dev` เป็นค่าเริ่มต้น จึงเปิดที่พอร์ต 8080

## ทดลอง API

1. `GET /products` แสดงสินค้าในหน่วยความจำ
2. `GET /product/add?name=Monitor&price=4500&stock=5` เพิ่มสินค้า (ใช้ GET ตามโจทย์)
3. `GET /report` แสดงจำนวนสินค้าและมูลค่าสต็อกรวมในรูปเงินบาท
4. `GET /info` แสดงชื่อร้าน อัตรา VAT และ Profile ที่กำลังใช้

หลังเพิ่ม Monitor แล้วคาดว่าจะได้ `count: 2` และ `totalValue: ฿32,250.00` (ค่าแสดงสกุลเงินอาจต่างตาม locale ของเครื่อง)

## ผลที่ควรบันทึก

- ก่อนแก้ `new` ใน Controller แต่ละตัวสร้าง Service คนละ instance; หลังเพิ่ม Monitor `/products` มี 2 รายการ แต่ `/report` ยังนับได้ 1
- หลังใช้ `@Service` และ Constructor Injection มี Service singleton ตัวเดียวที่ทั้งสอง Controller ใช้ร่วมกัน ข้อความ log ในขั้นทดสอบควรแสดงหนึ่งครั้งต่อการเริ่มโปรแกรม ก่อนลบบรรทัด log ตามขั้นตอนสุดท้าย
- `/report` หลังเพิ่มสินค้า: `count` เป็น 2 และมูลค่ารวมเป็น 32,250 บาท
- `/info` ก่อนเปิด Profile ระบุชื่อ UTC Computer Shop, VAT 7 และไม่มี Active Profile; เมื่อตั้งค่า `dev` จะแสดง UTC Computer Shop (DEV) ที่พอร์ต 8080 ส่วน `prod` แสดง UTC Computer Shop ที่พอร์ต 9090

## คำตอบท้ายใบงาน

1. ในขั้นตอนแรก Controller แต่ละตัวเรียก `new InMemoryProductService()` จึงมี List คนละชุด การเพิ่มสินค้าผ่าน ProductController ไม่ได้เปลี่ยนข้อมูลของ ReportController
2. หลังแก้ด้วย `@Service` และฉีดผ่าน Constructor จะสร้าง Bean หนึ่งครั้งเพราะ Scope ปริยายเป็น Singleton ต่อ Spring ApplicationContext
3. IoC คือการให้ Spring Container ควบคุมการสร้าง จัดการวงจรชีวิต และเชื่อมโยง Dependency แทนที่คลาสจะสร้างสิ่งที่ต้องใช้เองด้วย `new`
4. `NumberFormat` เป็นคลาสจาก Java ที่ต้องกำหนด locale และวิธีสร้างให้ชัดเจน จึงใช้ `@Configuration`/`@Bean` เพื่อสร้างและกำหนดค่าอ็อบเจกต์ที่นำเข้ามาฉีดได้
5. Profile แยกค่าตามสภาพแวดล้อม เช่น พอร์ตและชื่อร้านของ dev/prod โดยไม่ต้องแก้โค้ด ทำให้พัฒนาทดสอบและนำขึ้นใช้งานจริงด้วยค่าที่เหมาะกับแต่ละระบบได้

## Profile สำหรับการทดสอบ

ค่าเริ่มต้นคือ `dev` รัน `prod` ได้ด้วย:

```bash
mvn -DskipTests package
java -jar target/shop-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

จากนั้นเรียก `/info` ที่ `http://localhost:9090/info`
