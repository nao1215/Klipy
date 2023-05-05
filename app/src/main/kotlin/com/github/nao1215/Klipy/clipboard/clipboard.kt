package com.github.nao1215.Klipy.clipboard

import java.awt.Toolkit
import java.awt.datatransfer.*

object Clipboard {
    fun write(content: String) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val selection = StringSelection(content)
        clipboard.setContents(selection, selection)
        while (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            Thread.sleep(10)
        }

    fun read(): String {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val transferable: Transferable = clipboard.getContents(null)
        if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return transferable.getTransferData(DataFlavor.stringFlavor) as String
        }
        return ""
    }
}
