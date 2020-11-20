package boundary;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.entity.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CatalogoBoundary implements PaneStrategy, ProdutorComando {

    private AssinanteComando assinanteComando;

    private Pane pane = new Pane();

    private Button buttonComprar = new Button("Comprar");

    private TableView<Product> tableViewProducts = new TableView();
    private TableColumn tableColumnModelo = new TableColumn("Modelo");
    private TableColumn tableColumnMarca = new TableColumn("Marca");
    private TableColumn tableColumnPreco = new TableColumn("Preco");

    private Image image = new Image(new FileInputStream(System.getProperty("user.dir") +"/images/info-icon.png"), 48, 48, false , false);
    private ImageView imageView = new ImageView(image);

    public CatalogoBoundary() throws FileNotFoundException {
        pane.getChildren().addAll(buttonComprar, tableViewProducts, imageView);

        imageView.relocate(115, 269);
        imageView.resize(48, 48);
        imageView.setOnMouseClicked(e -> this.acionarComando("detalhes"));

        buttonComprar.relocate(223, 264);
        buttonComprar.setPrefSize(192, 58);
        buttonComprar.setOnAction(e -> this.acionarComando("comprar"));

        tableViewProducts.setEditable(false);
        tableViewProducts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewProducts.relocate(115, 98);
        tableViewProducts.setPrefSize(309, 126);
        tableViewProducts.getColumns().addAll(tableColumnModelo, tableColumnMarca, tableColumnPreco);

        tableColumnModelo.setCellValueFactory(new PropertyValueFactory<Product, String>("modelo"));
        tableColumnMarca.setCellValueFactory(new PropertyValueFactory<Product, String>("marca"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Product, String>("preco"));

        tableViewProducts.setItems(FXCollections.observableArrayList(
                new Product("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco íris", "Gabriel's", "R$ 53,99"),
                new Product("Camisa Arco íris", "Gabriel's", "R$ 53,99")
        ));
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void setAssinanteComando(AssinanteComando assinanteComando) {
        this.assinanteComando = assinanteComando;
    }

    @Override
    public void acionarComando(String comando) {
        this.assinanteComando.executarComando(comando);
    }
}
