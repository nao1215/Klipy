package com.github.nao1215.Klipy.config

import kotlinx.cli.*

class ArgumentParser {
    private val parser = ArgParser("Klipy")

    val daemon: Boolean by
            parser.option(
                            ArgType.Boolean,
                            fullName = "daemon",
                            shortName = "d",
                            description = "Enable daemon mode"
                    )
                    .default(false)

    val output: Boolean by
            parser.option(
                            ArgType.Boolean,
                            fullName = "output",
                            shortName = "o",
                            description = "Output clipboard content"
                    )
                    .default(false)

    fun parse(args: Array<String>) {
        parser.parse(args)
        // TODO: check if args are valid
        // TODO: check if ArgParseResult is valid
    }
}
