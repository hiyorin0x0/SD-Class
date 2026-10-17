# Exercise 9 - Singleton cho GameModel

Bài thực hành theo Exercise 9, Chapter 4 của DesignBook. `GameModel` là Singleton: constructor được đặt `private`, đối tượng duy nhất được giữ trong trường `static final`, và client truy cập qua `getInstance()`. Mỗi lần gọi `newGame()`, model tạo một `Deck` mới.

`Deck` tạo đủ 52 lá bài và xáo trộn chúng. `Main` minh họa rằng hai lần gọi `getInstance()` trả về cùng một `GameModel`, còn hai lần bắt đầu ván sẽ tạo hai đối tượng `Deck` khác nhau.