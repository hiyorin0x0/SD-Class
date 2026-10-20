# Exercise 8 - Lazy Card Flyweights

Bài thực hành so sánh hai cách lưu 52 đối tượng Card theo mẫu Flyweight:

- `PreinitializedCardFactory` tạo trước toàn bộ 52 thẻ trong constructor.
- `LazyCardFactory` chỉ tạo thẻ ở lần gọi `getCard(rank, suit)` đầu tiên rồi lưu vào Map.

`BenchmarkMain` kiểm tra mỗi cặp rank/suit luôn trả cùng một tham chiếu và có đủ 52 đối tượng phân biệt theo identity. Benchmark dùng `System.currentTimeMillis()`, làm nóng cả hai chiến lược rồi đo 10.000 lượt; mỗi lượt tạo một factory và lấy đủ 52 lá, nên thời gian gồm cả phần khởi tạo factory và thao tác lấy bài.