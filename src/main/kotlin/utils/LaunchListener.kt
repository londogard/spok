package utils

import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener

class LaunchListener: NativeKeyListener {
    var isPressed: Boolean = false
    var pressedBtns: Array<Boolean> = arrayOf(false, false, false)

    override fun nativeKeyTyped(e: NativeKeyEvent?) = Unit

    override fun nativeKeyPressed(e: NativeKeyEvent?) {
        isPressed = true
        when (e?.keyCode) {
            29 -> pressedBtns[0] = true
            56 -> pressedBtns[1] = true
            57 -> pressedBtns[2] = true
        }
        if (isPressed && pressedBtns.all { true })
            println("Key Pressed: ${e?.keyCode}")
    }

    override fun nativeKeyReleased(e: NativeKeyEvent?) {
        isPressed = false
        pressedBtns.indices.forEach { pressedBtns[it] = false }
        println("Key Released: ${e?.keyCode}")
    }
}