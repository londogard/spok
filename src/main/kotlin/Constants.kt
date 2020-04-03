enum class OperativeSystem {
    Linux,
    Unsupported
}

data class DesktopItem(val path: String? = null, val imagePath: String? = null, val name: String? = null, val comment: String? = null)

data class SearchObject(val searchGroups: List<SearchGroup>)
data class SearchGroup(val anyMatch: Boolean, val fullmatch: Boolean, val name: String)