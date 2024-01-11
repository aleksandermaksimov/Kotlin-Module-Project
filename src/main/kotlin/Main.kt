fun main() {
    val archives = mutableListOf<Archive>()
    val mainMenuItems = listOf("Добавить архив", "Просмотреть архивы", "Выход")
    val noteMenuItems = listOf("Добавить заметку", "Просмотреть заметки", "Назад")

    var choice: Int
    var exit = false

    val mainMenu = Menu(mainMenuItems)
    val noteMenu = Menu(noteMenuItems)

    while (!exit) {
        mainMenu.displayMenu()
        choice = mainMenu.getUserChoice("Выберите действие:")
        when (choice) {
            0 -> {
                val archiveName = createArchive()
                if (archiveName.isNotEmpty()) {
                    archives.add(Archive(archiveName))
                    println("Архив \"$archiveName\" успешно создан.")
                } else{
                    println("Имя архива не может быть пустым.")
                }
            }
            1 -> viewArchives(archives, noteMenu)
            2 -> exit = true
        }
    }
}

fun createArchive(): String{
    println("Введите имя архива:")
    return readLine() ?: ""
}

fun viewArchives(archives: List<Archive>, noteMenu: Menu) {
    if (archives.isEmpty()) {
        println("Нет доступных архивов.")
        return
    }
    println("Доступные архивы:")
    for ((index, archive) in archives.withIndex()) {
        println("$index. ${archive.name}")
    }

    val choice = noteMenu.getUserChoice( "Выберите архив:")
    if (choice in 0 until archives.size) {
        val selectedArchive = archives[choice]
        var exit = false
        while (!exit) {
            noteMenu.displayMenu()
            val noteChoice = noteMenu.getUserChoice("Выберите действие:")
            when (noteChoice) {
                0 -> addNoteToArchive(selectedArchive)
                1 -> viewNotesInArchive(selectedArchive)
                2 -> exit = true
            }
        }
    }
}

fun addNoteToArchive(archive: Archive) {
    println("Введите название заметки")
    val title = readLine() ?: ""
    println("Введите текст заметки:")
    val content = readLine() ?: ""
    if (title.isNotEmpty() && content.isNotEmpty()) {
        archive.notes.add(Note(title, content))
        println("Заметка \"$title\" успешно добавлена в архив.")
    } else {
        println("Название и содержание заметки не могут быть пустыми.")
    }
}

fun viewNotesInArchive(archive: Archive) {
    if (archive.notes.isEmpty()) {
        println("В этом архиве нет заметок.")
    } else {
        println("Заметки в архиве \"${archive.name}\":")
        for ((index, note) in archive.notes.withIndex()) {
            println("$index. ${note.title}")
        }
    }
}