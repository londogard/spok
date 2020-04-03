import java.io.File

object ApplicationFinder {
    private val linuxApplicationPaths = listOf(
        File("/usr/share/applications/"),
        File("/var/lib/snapd/desktop/applications/")
    )

    //fun findApplications(): List<String> = when (OperativeSystemHelper.OS) {
    //    OperativeSystem.Linux -> linuxApplicationPaths.flatMap(::fetchApplicationFromDir)
    //    else -> emptyList()
    //}

    private fun fetchApplicationFromDir(directory: File) = directory
        .walk()
}