# Klipy - CLI/GUI toolsets for the clipboard
WARNING: This is still under development and most of the functionalities are not yet implemented.

**Klipy** is a CLI command that writes or reads data to/from the clipboard. When started as a daemon, it monitors the user's copy (Ctrl-C) and records the clipboard history. Users can refer to the contents of the previous clipboard. While in daemon mode, the contents of the clipboard are stored in SQLite3.

## How to install
[WIP]

## How to use
### [WIP] Copy data to clipboard
If you want to write data to the clipboard, please use redirect or pipe.
```
$ echo "test" | klipy 
copy 'test' to clipboard
```

### [WIP] Read data from clipboard
If you want to read data from the clipboard, please use the `--output` option.
```
$ klipy --output
test
```

### [WIP] Start daemon (GUI mode)
If you want to start daemon mode, please use the `--daemon` option.
I haven't created anything yet.
```
$ klipy --daemon
```

## Contributing
First off, thanks for taking the time to contribute! Contributions are not only related to development. For example, GitHub Star motivates me to develop!

## Contact
If you would like to send comments such as "find a bug" or "request for additional features" to the developer, please use one of the following contacts.

- [GitHub Issue](https://github.com/nao1215/Klipy/issues)

## LICENSE
The klipy project is licensed under the terms of the [MIT LICENSE](./LICENSE).