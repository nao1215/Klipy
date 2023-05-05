package com.github.nao1215.Klipy

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import com.github.nao1215.Klipy.clipboard.Clipboard
import com.github.nao1215.Klipy.config.ArgumentParser
import com.github.nao1215.Klipy.database.DatabaseManager
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import kotlinx.cli.*

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main(args: Array<String>) {
    val argParser = ArgumentParser()
    argParser.parse(args)

    if (argParser.daemon) {
        println("daemon mode")
        GlobalScreen.registerNativeHook()
        GlobalScreen.addNativeKeyListener(CustomKeyListener())
        return
    } else if (argParser.output) {
        println(Clipboard.read())
        return
    } else {
        if (System.`in`.available() <= 0) {
            println("no input data. please see --help. exit")
            return
        }
        val input = generateSequence(::readLine).joinToString("\n")
        println("copy '$input' to clipboard")
        Clipboard.write(input)
    }
}

// TODO: For daemon mode.
class CustomKeyListener : NativeKeyListener {
    override fun nativeKeyPressed(e: NativeKeyEvent) {
        if (e.keyCode == NativeKeyEvent.VC_C && e.modifiers and NativeKeyEvent.CTRL_MASK != 0) {
            println("Ctrl-C")
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            val transferable: Transferable = clipboard.getContents(null)
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                val text = transferable.getTransferData(DataFlavor.stringFlavor) as String
                DatabaseManager.addClipBoard(text)
            }
        } else if (e.keyCode == NativeKeyEvent.VC_D && e.modifiers and NativeKeyEvent.CTRL_MASK != 0
        ) {
            val clipBoards = DatabaseManager.getClipBoards()
            for (board in clipBoards) {
                println("${board.id}, ${board.content}")
            }
        }
    }
}
