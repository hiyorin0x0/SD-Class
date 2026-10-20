# Exercise 8 - Lazy Card Flyweights

Bài thực hành so sánh hai cách lưu 52 đối tượng Card theo mẫu Flyweight:

- `PreinitializedCardFactory` tạo trước toàn bộ 52 thẻ trong constructor.
- `LazyCardFactory` chỉ tạo thẻ ở lần gọi `getCard(rank, suit)` đầu tiên rồi lưu vào Map.

Exercise 8 ghi “code written in Exercise 9”, nhưng Exercise 9 trên cùng trang nói về GameModel Singleton. Vì Exercise 7 mô tả Card flyweights khởi tạo trước và Exercise 8 yêu cầu làm lazy cho 52 lá, phần thực hành này đối chiếu hai cách tạo Card theo nội dung cụ thể của Exercise 8.

`BenchmarkMain` kiểm tra mỗi cặp rank/suit luôn trả cùng một tham chiếu và có đủ 52 đối tượng phân biệt theo identity. Benchmark dùng `System.currentTimeMillis()`, làm nóng cả hai chiến lược rồi đo 10.000 lượt; mỗi lượt tạo một factory và lấy đủ 52 lá, nên thời gian gồm cả phần khởi tạo factory và thao tác lấy bài. Các số đo có thể khác theo máy/JVM.

## Chạy chương trình

Yêu cầu JDK 8 trở lên. Chạy `run.bat`, hoặc tại thư mục này chạy:

```text
javac -encoding UTF-8 -d build src\*.java
java -cp build BenchmarkMain
```
