interface Window {
    sealed interface SingleWindow : Window {
        data class Setting(val tag: String? = null) : SingleWindow
        object Hello : SingleWindow
    }

    sealed interface MultiWindow : Window {
        object Chat : MultiWindow
    }
}