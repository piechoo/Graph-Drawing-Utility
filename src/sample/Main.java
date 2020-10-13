package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.lang.Math.abs;

public class Main extends Application {
    Grapf g1 = new Grapf();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();



        primaryStage.setTitle("Graph drawing utility");
        Label labelW = new Label("Wybierz liczbe wierzcholkow");
        Label labelK = new Label("Wybierz liczbe krawedzi");

        Button acc = new Button("Akceptuj");




        Separator separator = new Separator(Orientation.HORIZONTAL);

        Slider sliderW = new Slider(1, 20, 1);
        sliderW.setMajorTickUnit(1.0);
        sliderW.setMinorTickCount(0);
        sliderW.setSnapToTicks(true);
        sliderW.setShowTickMarks(true);
        sliderW.setShowTickLabels(true);


        Slider sliderK = new Slider(1, 20, 1);
        sliderK.setMajorTickUnit(1.0);
        sliderK.setMinorTickCount(0);
        sliderK.setSnapToTicks(true);
        sliderK.setShowTickMarks(true);
        sliderK.setShowTickLabels(true);

        CheckBox checkBox1 = new CheckBox("Skierowany");
        CheckBox checkBox2 = new CheckBox("Etykietowane krawedzie");
        CheckBox checkBox3 = new CheckBox("Etykietowane wierzcholki");

        VBox vbox = new VBox(labelW, sliderW);
        vbox.getChildren().add(labelK);
        vbox.getChildren().add(sliderK);
        vbox.setSpacing(10);
        vbox.getChildren().add(checkBox1);
        vbox.getChildren().add(checkBox2);
        vbox.getChildren().add(checkBox3);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.getChildren().add(acc);

        Scene scene = new Scene(vbox,400,300);
        //scene.getChildren().add(sliderK);
        primaryStage.setScene(scene);
        primaryStage.show();


        acc.setOnAction(value ->  {
            ((Stage)((Button)value.getSource()).getScene().getWindow()).setIconified(true);
            g1.setKierownica(checkBox1.isSelected());
            g1.setWaga_k(checkBox2.isSelected());
            g1.setWaga_w(checkBox3.isSelected());
            g1.setNumbers((int)sliderW.getValue(), (int)sliderK.getValue());
            g1.addEdges();
            g1.addNodes();
            VBox vBoxer = new VBox(new Label("Podaj zrodla (wierzcholki wychodzace), cele (wierzcholki wchodzace) oraz koszty krawedzi:"));
            vBoxer.getChildren().add(new Label("Krawedzie: "+(int)sliderK.getValue()+" Wierzcholki: "+(int)sliderW.getValue() ));


            //utworzenie tabeli krawedzi do edycji

            TableView<Edge> tableView = new TableView<Edge>();
            tableView.setEditable(true);

            TableView<Node> tableView1 = new TableView<Node>();
            tableView1.setEditable(true);


            TableColumn<Edge, Integer> column1 = new TableColumn<>("Zrodlo Krawedzi");
            column1.setCellValueFactory(new PropertyValueFactory<Edge,Integer>("from"));

            column1.setCellFactory(
                    TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

            column1.setOnEditCommit(t -> ((Edge) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setFrom(t.getNewValue()));


            TableColumn<Edge, Integer> column2 = new TableColumn<>("Cel Krawedzi");
            column2.setCellValueFactory(new PropertyValueFactory<>("to"));
            column2.setCellFactory(
                    TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

            column2.setOnEditCommit(t -> ((Edge) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setTo(t.getNewValue()));


            TableColumn<Edge, String> column3 = new TableColumn<>("Waga Krawedzi");
            column3.setCellValueFactory(new PropertyValueFactory<>("wage"));
            column3.setCellFactory(TextFieldTableCell.<Edge>forTableColumn());

            column3.setOnEditCommit(t -> ((Edge) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setWage(t.getNewValue()));


            TableColumn<Node, String> column4 = new TableColumn<>("Waga Wierzcholka");
            column4.setCellValueFactory(new PropertyValueFactory<>("wage"));
            column4.setCellFactory(TextFieldTableCell.<Node>forTableColumn());

            column4.setOnEditCommit(t -> ((Node) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setWage(t.getNewValue()));

            tableView.getColumns().add(column1);
            tableView.getColumns().add(column2);
            if(g1.waga_k==true)
                tableView.getColumns().add(column3);

            for(int i=0;i<g1.edgeNumber;i++)
            {
                tableView.getItems().add(g1.krawedzie.get(i));
            }

            vBoxer.getChildren().add(tableView);
            Button acp = new Button("Akceptuj");
            vbox.setSpacing(10);
            vBoxer.setAlignment(Pos.BOTTOM_CENTER);
            vBoxer.getChildren().add(acp);
            Scene scena = new Scene(vBoxer);
            Stage stage = new Stage();
            stage.setScene(scena);
            stage.initModality(Modality.NONE);

            stage.initOwner(primaryStage);
            stage.show();


            if(g1.waga_w==true) {
                tableView1.getColumns().add(column4);
                for(int i=0;i<g1.nodeNumber;i++)
                {
                    tableView1.getItems().add(g1.wezly.get(i));
                }

                VBox vBoxer1 = new VBox(new Label("Podaj wagi wierzcholkow:"));
                vBoxer1.getChildren().add(new Label(" Ilosc wierzcholkow: "+(int)sliderW.getValue() ));
                vBoxer1.getChildren().add(tableView1);
                Button acp1 = new Button("Akceptuj");
                vBoxer1.setSpacing(10);
                vBoxer1.setAlignment(Pos.BOTTOM_CENTER);
                vBoxer1.getChildren().add(acp1);
                Scene scena1 = new Scene(vBoxer1);
                Stage stage1= new Stage();
                stage1.setScene(scena1);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.show();
                //stage1.initOwner(primaryStage);

                acp1.setOnAction(event ->{
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                });

            }





            acp.setOnAction(event ->{
                int in,out;
                for(int i=0; i<g1.edgeNumber;i++)
                {
                    in=g1.krawedzie.get(i).getTo();
                    out=g1.krawedzie.get(i).getFrom();

                    g1.wezly.get(in).addIn(out);
                    g1.wezly.get(out).addIn(in);
                }

                //rysowanie canvasem
                Stage rysowanko = new Stage();
                rysowanko.setTitle("Rysowanie grafu");

                Canvas canvas = new Canvas(1000, 700);
                GraphicsContext gc = canvas.getGraphicsContext2D();

                //gc.setStroke(Color.CHOCOLATE);
               // gc.setLineWidth(3);
                //gc.strokeOval(960, 30, 80, 80);
                drawGraph(gc);

                var root = new Pane();
                //Group group = new Group(canvas);

                // create a scene
                //Scene scenka = new Scene(group, 1000, 700);

                root.getChildren().add(canvas);
                // set the scene
                rysowanko.setScene(new Scene(root,1000,700,Color.WHITESMOKE));

                rysowanko.show();


                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
            });

        });
    }

    private void drawGraph(GraphicsContext gc) {

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.setFont(new Font("Verdana", 12));

        List<Integer> used = new ArrayList<Integer>();

        boolean start = false;
        int [] cordy = new int [2];
        int [] center = new int [2];
        int stp=100;
        int act=100;
        int prev=100;
        int buf = 100;
        int iter=0;

        for(int i=0;i<g1.nodeNumber;i++)
        {
            if(g1.wezly.get(i).stopien<stp) {
                stp = g1.wezly.get(i).stopien;
                act = i;
            }
        }
        used.add(act);
            cordy[0]=200;cordy[1]=200;
            gc.strokeOval(cordy[0], cordy[1], 50, 50);
            center[0]=cordy[0]+15;
            center[1]=cordy[1]+25;
            gc.fillText(g1.wezly.get(act).getWage(), center[0]-textlength(g1.wezly.get(act).getWage()), center[1]);//+15 , +35
            g1.wezly.get(act).setCords(cordy[0],cordy[1]);

            stp=0;
                    for(int i=0; i<g1.wezly.get(act).sonsiad.size();i++) {
                        if ((g1.wezly.get(g1.wezly.get(act).sonsiad.get(i)).stopien > stp)&&!(used.contains(g1.wezly.get(act).sonsiad.get(i)))) {
                            stp = g1.wezly.get(g1.wezly.get(act).sonsiad.get(i)).stopien;
                            buf = g1.wezly.get(act).sonsiad.get(i);
                        }
                    }
                    prev=act;
                    act=buf;
                    used.add(act);
                    cordy[0]=cordy[0]+120;
                    center[0]=cordy[0]+15;
                    center[1]=cordy[1]+25;
                    gc.strokeOval(cordy[0], cordy[1], 50, 50);
                    gc.fillText(g1.wezly.get(act).getWage(), center[0]-textlength(g1.wezly.get(act).getWage()), center[1]);
                    g1.wezly.get(act).setCords(cordy[0],cordy[1]);
                    stp=0;
                while(used.size()<g1.wezly.size())
                {
                    for(int i=0; i<g1.wezly.get(act).sonsiad.size();i++) {
                        if ((g1.wezly.get(g1.wezly.get(act).sonsiad.get(i)).stopien > stp)&&!(used.contains(g1.wezly.get(act).sonsiad.get(i)))) {
                            stp = g1.wezly.get(g1.wezly.get(act).sonsiad.get(i)).stopien;
                            buf = g1.wezly.get(act).sonsiad.get(i);
                        }
                    }
                    prev = act;
                    if(!(used.contains(buf))) {
                        //prev = act;
                        act = buf;
                    }
                    if(act!=prev) {
                        best(cordy, gc, act, prev, used);
                        stp=0;
                    }
                    else
                    {
                        act=used.get(iter);
                        prev=act;
                        stp=0;
                            iter++;
                            iter=iter%used.size();
                        cordy[0]=g1.wezly.get(act).cords[0];
                        cordy[1]=g1.wezly.get(act).cords[1];
                        center[0]=cordy[0]+15;
                        center[1]=cordy[1]+25;
                    }

                }
                linie(gc);


    }
    boolean conect(List<Integer> used,int act, int prev)
    {
        for(int i=0;i<g1.wezly.get(act).sonsiad.size(); i++)
        {
            if((g1.wezly.get(act).sonsiad.get(i)!=prev)&&used.contains(g1.wezly.get(act).sonsiad.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    void linie(GraphicsContext gc) {
        int[] cf= new int [2];
        int [] ct = new int [2];
        int [] scf = new int [2];
        int [] sct = new int [2];
        double []md = new double[2];
        for (int i = 0; i < g1.krawedzie.size(); i++)
        {
            cf=g1.wezly.get(g1.krawedzie.get(i).getFrom()).cords ;
            ct=g1.wezly.get(g1.krawedzie.get(i).getTo()).cords ;
            md=prosta(cf[0]+25,ct[0]+25,cf[1]+25,ct[1]+25);
            sct=skrocenie(cf[0]+25, cf[1]+25, ct[0]+25, ct[1]+25,md[0],md[1],25.0);
            scf=skrocenie(ct[0]+25, ct[1]+25, cf[0]+25, cf[1]+25,md[0],md[1],25.0);


            gc.beginPath();
            gc.moveTo(scf[0], scf[1]);
            gc.lineTo(sct[0], sct[1]);
            gc.stroke();
            if(g1.kierownica==true)// strzalki
            {

                szczalki(scf[0],scf[1],sct[0],sct[1],gc,15.0);

            }
            if(g1.waga_k==true)// wagi krawedzi
            {
                waga_k(scf[0],scf[1],sct[0],sct[1],gc,g1.krawedzie.get(i).getWage());

            }

        }


    }


    void best(int[] cor, GraphicsContext gc,int act,int prev, List<Integer> used)
    {
        List<Integer> sasiady = new ArrayList<>();
        for(int i=0;i<g1.wezly.get(act).sonsiad.size(); i++)
        {
            if((g1.wezly.get(act).sonsiad.get(i)!=prev)&&used.contains(g1.wezly.get(act).sonsiad.get(i)))
            {
                sasiady.add(g1.wezly.get(act).sonsiad.get(i));
            }
        }
        double odleglosc=1E15   ;
        int[] xs={0,120,0,-120,120,120,-120,-120};
        int[] ys={120,0,-120,0,120,-120,-120,120};
        int corx;
        int cory;
        int center[] = new int[2];
        double bufer=0;
        int najmniejszy=0;
        if(g1.wezly.get(prev).stopien<4) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < sasiady.size(); j++) {
                    corx = g1.wezly.get(sasiady.get(j)).cords[0];
                    cory = g1.wezly.get(sasiady.get(j)).cords[1];
                    bufer += c_val(cor[0] + xs[i], corx, cor[1] + ys[i], cory);
                }
                if(act == 2)
                    System.out.println(" odl "+odleglosc+" a bufer to "+bufer+" dla i = "+i);
                if ((bufer < odleglosc) && (!(point_colide(cor[0] + xs[i], cor[1] + ys[i], used,act)))) {
                    odleglosc = bufer;
                    najmniejszy = i;
                    if(act == 2)
                        System.out.println(" wybriera kordy :"+(cor[0]+xs[i])+" ,"+(cor[1]+ys[najmniejszy])+" bo ma odl "+odleglosc);

                }
                bufer = 0;
            }
        }
        else
        {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < sasiady.size(); j++) {
                    corx = g1.wezly.get(sasiady.get(j)).cords[0];
                    cory = g1.wezly.get(sasiady.get(j)).cords[1];
                    bufer += c_val(cor[0] + xs[i], corx, cor[1] + ys[i], cory);
                }


                if ((bufer < odleglosc) && (!(point_colide(cor[0] + xs[i], cor[1] + ys[i], used,act)))) {
                    odleglosc = bufer;
                    najmniejszy = i;
                }
                bufer = 0;
            }
        }

        used.add(act);
        cor[0]=cor[0]+xs[najmniejszy];
        cor[1]=cor[1]+ys[najmniejszy];
        center[0]=cor[0]+15;
        center[1]=cor[1]+25;
        gc.strokeOval(cor[0], cor[1], 50, 50);
        gc.fillText(g1.wezly.get(act).getWage(), center[0]-textlength(g1.wezly.get(act).getWage()), center[1]);//+15 , +35
        //System.out.println("wierzcholek nr "+(act+1)+" wybriera kordy :"+cor[0]+" ,"+cor[1]);
        g1.wezly.get(act).setCords(cor[0],cor[1]);

    }
    boolean point_colide(int x, int y,List<Integer> used,int act)
    {
        ArrayList<Integer> sasiedzi = new ArrayList<>();
        if(x<0||x>950||y<0||y>650)
            return true;
        for(Integer spr : used){
            if(abs(g1.wezly.get(spr).cords[0]-x)<100&&abs(g1.wezly.get(spr).cords[1]-y)<100) {

                return true;
            }
                if(g1.wezly.get(act).sonsiad.contains(spr))
                sasiedzi.add(spr);
        }
        if(sasiedzi.size()>=2) {
            ArrayList<Integer> iksy = new ArrayList<>();
            ArrayList<Integer> ygreki = new ArrayList<>();
            for (Integer wsp : sasiedzi) {

                if(!iksy.contains(g1.wezly.get(wsp).cords[0]))
                    iksy.add(g1.wezly.get(wsp).cords[0]);
                else if(iksy.contains(g1.wezly.get(wsp).cords[0])&&g1.wezly.get(wsp).cords[0]==x) {

                    return true;
                }
                if(!ygreki.contains(g1.wezly.get(wsp).cords[1]))
                    ygreki.add(g1.wezly.get(wsp).cords[1]);
                else if(ygreki.contains(g1.wezly.get(wsp).cords[1])&&g1.wezly.get(wsp).cords[1]==y) {
                    if(act==2)
                        System.out.println("tutaj kurwa ygreh sonsiad nr"+(wsp+1));
                    return true;
                }
            }
        }

        return false;
    }
    int[] skrocenie(int x1,int y1,int x2,int y2, double m, double d, double ile)
    {
        int[] ret = new int [2];
        if(x1==x2)
        {
            ret[0]= x1;
            if(y2>y1) {
                ret[1] = y2 - 25;
            }
            else {
                ret[1] = y2 + 25;
            }
            return ret;
        }
        if(y1==y2)
        {
            ret[1]= y1;
            if(x2>x1){
                ret[0] = x2-25;
            }
            else {
                ret[0] = x2 + 25;
            }
            return ret;
        }

        double []buf=dlugosc(x2,y2,m,d,ile);
        if((buf[0]<x1 && buf[0]>x2)||(buf[0]>x1 && buf[0]<x2)) {
            ret[1] = (int) buf[1];
            ret[0] = (int) buf[0];
        }
        else{
            ret[1] = (int) buf[3];
            ret[0] = (int) buf[2];
        }

        return ret;
    }

    double [] prosta(int x1, int x2, int y1, int y2)
    {
        double [] ab = new double[2];
        ab[0]=(double)(y2-y1)/(x2-x1);
        ab[1]=y1-ab[0]*x1;
        return ab;
    }

    void szczalki(int x1, int y1, int tox2, int toy2, GraphicsContext gc, double ile )
    {
        if(x1==tox2)
        {
            if(toy2>y1) {
                gc.fillPolygon(new double[]{tox2, tox2-ile,tox2+ile},
                        new double[]{toy2, toy2-ile, toy2-ile}, 3);
            }
            else {

                gc.fillPolygon(new double[]{tox2, tox2-ile,tox2+ile},
                        new double[]{toy2, toy2+ile, toy2+ile}, 3);
            }
        }
        else if(y1==toy2)
        {
            if(tox2>x1){

                gc.fillPolygon(new double[]{tox2, tox2-ile,tox2-ile},
                        new double[]{toy2, toy2-ile, toy2+ile}, 3);

            }
            else {
                gc.fillPolygon(new double[]{tox2, tox2+ile,tox2+ile},
                        new double[]{toy2, toy2-ile, toy2+ile}, 3);

            }
        }

        else
        {
            double [] md = prosta(x1,tox2,y1,toy2);
            int[] sk = new int[2];

            double []buf=dlugosc(tox2,toy2,md[0],md[1],ile);
            if((buf[0]<x1 && buf[0]>tox2)||(buf[0]>x1 && buf[0]<tox2)) {
                sk[1] = (int) buf[1];
                sk[0] = (int) buf[0];
            }
            else{
                sk[1] = (int) buf[3];
                sk[0] = (int) buf[2];
            }

            double noweD = (sk[1]+sk[0]/md[0]);
            double [] wsp = dlugosc(sk[0],sk[1],(-1/md[0]),noweD,15.0);

            gc.fillPolygon(new double[]{tox2, wsp[0],wsp[2]},
                    new double[]{toy2, wsp[1], wsp[3]}, 3);

        }

    }

    double [] dlugosc(int x, int y, double m, double d,double ile)
    {
        double [] ret = new double [4];

        double a = (Math.pow(m,2)+1.0);
        double b = (2.0*m*d - 2.0*(double)y*m - 2.0*(double)x );
        double c = ( Math.pow(d,2) + Math.pow(x,2) + Math.pow(y,2) - Math.pow(ile,2) - 2.0*(double)y*d );
        double delta_sq = Math.sqrt(( Math.pow(b,2) - 4.0*a*c ));

        ret[0] = (-b + delta_sq)/(2*a);
        ret[1] = m*ret[0]+d;
        ret[2] = (-b - delta_sq)/(2*a);
        ret[3] = m*ret[2]+d;
        return ret;

    }


    void waga_k(int x1, int y1, int x2, int y2, GraphicsContext gc, String text)
    {
        double [] md = prosta(x1,x2,y1,y2);
        int cor[]=new int[2];
        cor[0] = (x1+x2)/2;
        cor[1] = (y1+y2)/2;
        if(y1==y2) {
            cor[0] = (x1 + x2 - 15) / 2;
            cor[1] = (y1+y2)/2 - 5;
        }
        if(y1==y2)
            gc.fillText(text,cor[0] - textlength(text), cor[1]);
        if(x1==x2) {
            cor[0] = x1 + 2;
            //gc.setFont(new Font("Verdana", 12));
            gc.fillText(text, cor[0], (y1 + y2) / 2);
        }
        if(!(y1==y2)&&!(x1==x2))
        {
            if(x2>x1&&y2>y1) {
                cor[0] = x2 - 3*textlength(text);
                cor[1] = y2 - 30;
            }
            else if(x2<x1&&y2>y1) {
                cor[0] = x2 + 20 + 2 * textlength(text);
                cor[1] = y2 - 15;
            }
            else if(x2<x1&&y2<y1) {
                cor[0] = x2 - textlength(text);
                cor[1] = y2 + 30;
            }
            else  {
                cor[0] = x2 - 3*textlength(text);
                cor[1] = y2 + 30;
            }
            gc.fillText(text,cor[0], cor[1]);
        }

        }


    int textlength(String text)
    {
        return 4*(text.length()/2);
    }


    int c_val(int x1,int x2,int y1, int y2)
    {
        return (int) (Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
    }
    public static void main(String[] args) {
        launch(args);
    }
}
