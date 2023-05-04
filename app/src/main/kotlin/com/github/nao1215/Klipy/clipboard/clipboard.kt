package com.github.nao1215.Klipy.clipboard

import java.awt.Toolkit
import java.awt.datatransfer.*

object Clipboard {
    fun write(content: String) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val selection = StringSelection(content)
        clipboard.setContents(selection, selection)

        Thread.sleep(1000)
    }
}
