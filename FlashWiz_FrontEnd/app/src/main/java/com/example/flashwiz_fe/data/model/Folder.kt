package  com.example.flashwiz_fe.data.model

data class Folder(
    val name: String,
    val descriptions: String, // Thêm trường mô tả
    val userId: Int, // Thêm trường id của người dùng
//    val flashcards: List<Flashcard> // Danh sách các flashcard thuộc folder
)