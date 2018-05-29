package andywarhol;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Optional;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        FileChooser imageChooser = new FileChooser();
        imageChooser.setTitle("Select an image");
        File selectedImage;

        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.jfif")
        );
        do {
            selectedImage = imageChooser.showOpenDialog(primaryStage);
        } while (selectedImage == null);

        TextInputDialog textInputDialog = new TextInputDialog("Name the output image here");
        Optional<String> potentialNewName;
        String newFileName;
        do {
            potentialNewName = textInputDialog.showAndWait();
            newFileName = potentialNewName.get();
        }while (potentialNewName.toString().isEmpty());

        AndyWarhol andy = new AndyWarhol(selectedImage, newFileName);
    }
}
