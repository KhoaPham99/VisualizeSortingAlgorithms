package sortingalgorithms;

import cnode.CNode;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class RadixSort extends AbstractSort {


    private ArrayList<Transition> transitions;

    private final static Paint[] colors = {Color.BLACK, Color.GREEN, Color.BLUE, Color.RED,
            Color.GRAY, Color.ORANGE, Color.CORAL, Color.PINK, Color.PURPLE, Color.MAGENTA};


    public RadixSort(){
        this.transitions=new ArrayList<>();
    }

    public void radixSort(CNode[] arr){
        // degree o day la so chu so.
        int degree = findDegree(arr);
        // Tao 9 Buckets tuong ung 0->9 de luu cac index cua so
        // Vi du 105 co index = 3 va co hang don vi la 5 thi add 3 vao bucket 5.
        // Buckets o day ta dung LinkedList
        LinkedList<Integer>[] buckets = new LinkedList[10];
        // tao 10 buckets degree lan`
        for(int i = 1; i<degree;i*=10){
            for(int k=0;k<10;++k){
                buckets[k] = new LinkedList<>();
            }
            for(int j = 0; j<arr.length;++j){
                this.transitions.add(colorCNode(arr,SELECT_COLOR,j));
                System.out.println(arr[j].getValue());
                // Them index vao cac bucket tuong ung
                switch((arr[j].getValue()/i)%10){
                    case 0:
                        buckets[0].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 1:
                        buckets[1].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 2:
                        buckets[2].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 3:
                        buckets[3].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 4:
                        buckets[4].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 5:
                        buckets[5].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 6:
                        buckets[6].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 7:
                        buckets[7].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 8:
                        buckets[8].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                    case 9:
                        buckets[9].add(Arrays.asList(arr).indexOf(arr[j]));
                        break;
                }
            }
            int count=0;
            // tao 1 array index de luu thu tu cac index sau khi lay ra tu buckets.
            int[] index = new int[arr.length];
            for(int j = 0; j<10;++j){
                for(int k =0; k<buckets[j].size();++k){
//                    System.out.println("\t");
//                    System.out.println(buckets[j].get(k));
                    index[count]=buckets[j].get(k);
                    transitions.add(colorCNode(arr, (Color) colors[j],index[count]));
//                    if(checkSorted(arr)==false)
                    count+=1;
                }
            }
            this.transitions.addAll(swapCNodes(arr,index));
        }
//        System.out.println(a);
//        System.out.println("\n");
    }
    // function tim so chu so
    private int findDegree(CNode[] arr){
        int degree = 10;
        while(arr.length/degree >0){
            degree*=10;
        }
        return degree;
    }

    // Transition de swap array CNode can sort ve giong voi index array o tren.
    private ArrayList<Transition> swapCNodes(CNode[] arr, int[] index){
        ArrayList<Transition> transitions = new ArrayList<>();
        for (int i = 0; i < arr.length;i++){
            if(i!=index[i]){
                transitions.add(swap(arr,i,index[i]));
                swapArray(index,i,index[i]);
            }
        }
        return transitions;
    }
    // function de swap cac phan tu trong array index, khi swap array CNode theo index array
    // thi ta cung can phai swap phan tu tuong ung cua index array.
    private void swapArray(int[] index, int i , int j){
        int a=0,b=0;
        for (int k=0; k <index.length;k++){
            if (index[k]==i){
                a = k;
            } else if (index[k]==j) {
                b = k;
            }
        }
        int temp = index[a];
        index[a]=index[b];
        index[b]=temp;
    }

//    private boolean checkSorted(CNode[] arr){
//        for (int i=0;i<arr.length-1;i++){
//            if(arr[i].getValue()>arr[i+1].getValue())
//                return false;
//        }
//        return true;
//    }

    @Override
    public ArrayList<Transition> startSort(CNode[] arr) {
        radixSort(arr);
        this.transitions.add(colorCNode(Arrays.asList(arr),SORTED_COLOR));
        return this.transitions;
    }

}
