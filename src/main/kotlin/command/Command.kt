package command

private const val HELP_COMMAND = "help"
private const val EXIT_COMMAND = "exit"
private const val REVERT_COMMAND = "revert"

enum class Command(val textCommand: String) {
    HELP(HELP_COMMAND), EXIT(EXIT_COMMAND), REVERT(REVERT_COMMAND)
}