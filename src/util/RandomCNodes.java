package util;

import UI.Controller;
import cnode.CNode;
import javafx.scene.paint.Color;

import java.util.Random;

public class RandomCNodes {

    public RandomCNodes() {

    }

    public static CNode[] randomCNodes(int n) {
        CNode[] arr = new CNode[n];
        Random r = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new CNode(1 + r.nextInt(arr.length));
            arr[i].setX(i * (Controller.RPANE_WIDTH / arr.length));
            arr[i].setFill(Color.DARKRED);
            setCNodeDim(arr[i], arr.length);
        }
        return arr;

    }

    public static CNode createCNode(int n, int i, int l){
        CNode rect =  new CNode(n);
        rect.setX(i*(Controller.RPANE_WIDTH/l));
        rect.setFill(Color.DARKRED);
        setCNodeDim(rect,l);
        return rect;
    }


    private static void setCNodeDim(CNode cnode, int n) {
        cnode.setWidth(Controller.RPANE_WIDTH / n -
                Controller.XGAP);
        cnode.setHeight(((Controller.RPANE_HEIGHT)/ n)*cnode.getValue());
    }
}
