import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(workingDir: File): Process? {
    return try {
        val parts = this.split("\\s".toRegex())
        ProcessBuilder("/bin/bash", "-c", *(this.split(" ").toTypedArray()))
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        //proc.waitFor(60, TimeUnit.MINUTES)
        //val procOut = proc.inputStream.bufferedReader().readText()
        //println("Proc: $procOut")
        //procOut
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun String.runCommandGetOutput(proc: Process?): String? {
    proc?.waitFor(60, TimeUnit.MINUTES)
    val procOut = proc?.inputStream?.bufferedReader()?.readText()
    println("Proc: $procOut")
    return procOut
}