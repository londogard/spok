import java.io.File

object ApplicationFinder {
    private val interestingRootFolde = listOf("home", "opt", "bin", "lib", "sbin", "usr", "var")
    private val ignoreFolders = listOf("tmp")
    // home, opt, bin, lib, sbin, usr, var
    // https://askubuntu.com/questions/27213/what-is-the-linux-equivalent-to-windows-program-files
    // /snap/bin/
    // Basically find all /bin and /applications right?
    private val linuxApplicationPaths = listOf(
        File("/usr/share/applications/"),
        File("/var/lib/snapd/desktop/applications/")
    )

    //fun findApplications(): List<String> = when (OperativeSystemHelper.OS) {
    //    OperativeSystem.Linux -> linuxApplicationPaths.flatMap(::fetchApplicationFromDir)
    //    else -> emptyList()
    //}

    private fun getApplicationFolders(): List<Any> = File.listRoots().toList()
        .flatMap {
            it.listFiles()
                ?.filter { folder -> !folder.isHidden && interestingRootFolde.any { name -> folder.name == name } }
                ?: emptyList()
        }.asSequence()
        .flatMap { folder ->
            folder.walkTopDown()
                .onEnter { child -> !child.isHidden && child.name != "tmp" }
                .filter { it.isFile && it.name.endsWith(".desktop") } // Linux specific right now...
        }
        .toList()

    private fun fetchApplicationFromDir(directory: File) = directory
        .walk()

    @JvmStatic
    fun main(args: Array<String>) {
        println(getApplicationFolders())
    }
}