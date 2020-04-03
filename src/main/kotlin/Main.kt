import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import tornadofx.*

class MyApp : App(MyView::class)

/**
 * 1. We need to index data/files
 * 2. We need to cache in DB (?)
 * 3. We need to be able to search
 * 4. We need to understand difference
 * 5. Add plugins such as Google/DDG/Maps/Spotify etc
 */
class MyView : View() {
    private val controller = MyController()
    override val root = vbox {
        form {
            fieldset(labelPosition = Orientation.VERTICAL) {
                field("Search") {
                    textfield().apply { bind(controller.keyword) }
                    addEventHandler(KeyEvent.KEY_PRESSED) {
                        if (it.code == KeyCode.ENTER) {
                            println("HI ${controller.keyword}")
                            controller.searchResults.add("HI")
                        }
                    }
                }
                button("Search") {
                    action { println("Handle button press") }
                }
            }
        }

        listview(controller.searchResults) {
            addEventHandler(KeyEvent.KEY_PRESSED) {
                if (it.code == KeyCode.ENTER) println("HI ${this.selectedItem}")
            }
        }
    }
}

class MyController : Controller() {
    val keyword = SimpleStringProperty()
    val searchResults = FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta")

}

fun main(args: Array<String>) {
    println(OperativeSystemHelper.showProperties())
    //launch<MyApp>(args)
}