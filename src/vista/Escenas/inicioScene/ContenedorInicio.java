package vista.Escenas.inicioScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContenedorInicio extends VBox {
    public ContenedorInicio(Stage stage) {
        Image imagen = new Image("file:src/vista/assets/images/background_inicio.jpg");
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(imagenDeFondo));

        Image img = new Image("file:src/vista/assets/images/logo.png");

        ContenedorBotones cont = new ContenedorBotones(stage);
        cont.setMaxWidth(500L);
        cont.setAlignment(Pos.CENTER);
        cont.setSpacing(10);
        cont.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(new ImageView(img), cont);
    }
}
