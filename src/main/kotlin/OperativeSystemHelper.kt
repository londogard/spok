import java.io.File


object OperativeSystemHelper {
    val OS: OperativeSystem = System
        .getProperty("os.name")
        .toLowerCase()
        .let { osName ->
            when {
                osName.contains("linux") -> OperativeSystem.Linux
                else -> OperativeSystem.Unsupported
            }
        }

    fun getHomeDir() = File(System.getProperty("user.home"))
    fun showProperties() = println(System.getProperties().list(System.out))
}

fun readFilesForDesktop(directory: File): List<DesktopItem> = directory
    .walk()
    .maxDepth(1)
    .filter { it.isFile }
    .map { file ->
        file
            .readLines()
            .dropWhile { line -> !line.startsWith("[Desktop Entry]") }
            .takeWhile { line -> !line.isBlank() }  // path + iconPath + name
            .fold(DesktopItem(), ::foldLinuxDesktop) // TODO instead of fold just take while not blank..!?
    }
    .toList()

fun foldLinuxDesktop(desktopItem: DesktopItem, line: String): DesktopItem =
    when {
        line.startsWith("Name") -> desktopItem.copy(name = line.split("=").drop(1).joinToString("="))
        line.startsWith("Exec") -> desktopItem.copy(path = line.split("=").drop(1).joinToString("="))
        line.startsWith("Icon") -> desktopItem.copy(imagePath = line.split("=").drop(1).joinToString("="))
        line.startsWith("Comment") -> desktopItem.copy(comment = line.split("=").drop(1).joinToString("="))
        else -> desktopItem
    }