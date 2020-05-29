package UI;

import cnode.CNode;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import sortingalgorithms.AbstractSort;
import sortingalgorithms.BubbleSort;
import sortingalgorithms.HeapSort;
import sortingalgorithms.QuickSort;
import util.RandomCNodes;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static final int RPANE_WIDTH = 800;
    public static final int RPANE_HEIGHT = 500;
    public static final int XGAP = 5;
    public static int NO_OF_CNODES=4;
    public static int timeSpd;
    private static AbstractSort abstractSort;
    private CNode[] cnodes;
    @FXML
    private TextField inputSpeedTxt;
    @FXML
    private Button sortBtn;
    @FXML
    private Button pauseBtn;
    @FXML
    private Button resumeBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Slider numElementSlider;
    @FXML
    private ChoiceBox<AbstractSort> choiceBox;
    @FXML
    private Label numElLabel;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedLabel;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        timeSpd=1;
        AbstractSort.DX = RPANE_WIDTH / NO_OF_CNODES;
        List<AbstractSort> abstractSortList = new ArrayList<>();
        abstractSortList.add(new BubbleSort());
        abstractSortList.add(new QuickSort());
        abstractSortList.add(new HeapSort());
        choiceBox.setItems(FXCollections.observableArrayList(abstractSortList));
        cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);
        rightPane.getChildren().addAll(Arrays.asList(cnodes));
        pauseBtn.setDisable(true);
        resumeBtn.setDisable(true);
        choiceBox.getSelectionModel().select(0);
        choiceBox.setConverter(new StringConverter<AbstractSort>() {
            @Override
            public String toString(AbstractSort abstractSort) {
                if(abstractSort==null){
                    return "";
                }else{
                    return abstractSort.getClass().getSimpleName();
                }
            }
            @Override
            public AbstractSort fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void resetArray(ActionEvent event) {
        sortBtn.setDisable(false);
        rightPane.getChildren().clear();
        cnodes=RandomCNodes.randomCNodes(NO_OF_CNODES);
        rightPane.getChildren().addAll(Arrays.asList(cnodes));
    }

    @FXML
    void setNoOfElements(MouseEvent event) {
        sortBtn.setDisable(true);
        numElementSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                numElLabel.setText(String.valueOf(newValue.intValue()));
                NO_OF_CNODES=newValue.intValue();
                AbstractSort.DX = RPANE_WIDTH / NO_OF_CNODES;
            }
        });
    }


    @FXML
    void sort(ActionEvent event) {
        sortBtn.setDisable(true);
        pauseBtn.setDisable(false);
        resumeBtn.setDisable(false);
        abstractSort = choiceBox.getSelectionModel().getSelectedItem();
        SequentialTransition sq = new SequentialTransition();
        sq.getChildren().addAll(abstractSort.startSort(cnodes));
        sq.play();
        pauseBtn.setOnAction(e->{
            sq.pause();
        });
        resumeBtn.setOnAction(e->{
            sq.play();
        });
        sq.setOnFinished(e->{
            pauseBtn.setDisable(true);
            resumeBtn.setDisable(true);
        });
    }

    private void resetArray() {
        sortBtn.setDisable(false);
        pauseBtn.setDisable(true);
        resumeBtn.setDisable(true);
        rightPane.getChildren().clear();
        cnodes=RandomCNodes.randomCNodes(NO_OF_CNODES);
        rightPane.getChildren().addAll(Arrays.asList(cnodes));
    }

    @FXML
    void setSpeed(MouseEvent event) {
        sortBtn.setDisable(true);
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                speedLabel.setText(String.valueOf(newValue.intValue()));
                timeSpd = newValue.intValue();
            }
        });
    }

}