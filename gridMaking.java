package javaFx_projects;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gridMaking extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	int GRID_SIZE=9;
	int PANE_SIZE=50;
	int[][] matrix=new int[GRID_SIZE][GRID_SIZE];
	Color[][] colorMatrix=new Color[GRID_SIZE][GRID_SIZE];
	ArrayList<Color> colorList=new ArrayList<Color>();
	int index=0;
	boolean flag=false;
	
	Line top=new Line(25, 25, 25, -25);
	Line left=new Line(25, 25, -25, 25);
	Line right=new Line(25, 25, 50, 25);
	Line bottom=new Line(25, 25, 25, 50);
	
	PathTransition toTop=new PathTransition();
	PathTransition toLeft=new PathTransition();
	PathTransition toRight=new PathTransition();
	PathTransition toBottom=new PathTransition();
	
	GridPane sudokuGrid=new GridPane();
	Pane[][] sudokuCells=new Pane[GRID_SIZE][GRID_SIZE];
	
	@Override
	public void start(Stage stage) throws Exception {
		
		colorList.addAll(Arrays.asList(Color.RED, Color.GREEN, Color.BLUE));
		
		for(int i=0;i<GRID_SIZE;i++) {
			for (int j=0;j<GRID_SIZE;j++) {
				sudokuCells[i][j]=new Pane();
				sudokuCells[i][j].setPrefSize(PANE_SIZE, PANE_SIZE);
				sudokuGrid.add(sudokuCells[i][j], j, i);
			}
		}
		sudokuGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
	            int x=getPoint((int) e.getX(), PANE_SIZE);
	            int y=getPoint((int) e.getY(), PANE_SIZE);

	            matrix[x-1][y-1]+=1;
	            
	            if (matrix[x-1][y-1]==1) {
	            	//matrix[x-1][y-1]+=1;
	            	addMoreBalls(matrix, x, y, index);
		            flag=true;
	            }
	            else if (matrix[x-1][y-1]>1) {
	            	if (checkMatrixCorner(x, y) && checkMatrixColor(colorMatrix, x, y, colorList, index)) {
		            	split(matrix, x, y, index);
		            	flag=true;
		            }
		            else if (checkMatrixEdge(x, y) && checkMatrixColor(colorMatrix, x, y, colorList, index)) {
		            	if (matrix[x-1][y-1]<=2) {
		            		//matrix[x-1][y-1]+=1;
		            		addMoreBalls(matrix, x, y, index);
		            	}
		            	else
		            		split(matrix, x, y, index);
		            	flag=true;
		            }
		            else if (colorMatrix[x-1][y-1].equals(colorList.get(index))){
		            	if (matrix[x-1][y-1]<=4) {
		            		//matrix[x-1][y-1]+=1;
		            		addMoreBalls(matrix, x, y, index);
		            	}
		            	else
		            		split(matrix, x, y, index);
		            	flag=true;
		            }
	            }
	            
	            if (index==colorList.size()-1 && flag){
	            	index=0;
	            	flag=false;
	            }
	            else if (index<colorList.size()-1 && flag) {
					index++;
					flag=false;
				}
			}
		});
		sudokuGrid.setGridLinesVisible(true);
		Scene scene=new Scene(sudokuGrid);
		scene.setFill(Color.LIGHTGREEN);
		stage.setTitle("Sudoko Grid");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public int getPoint(int point, int paneSize) {
		if (point<paneSize)
			return 1;
		else {
			return Math.abs(point/paneSize)+1;
		}
		
	}
	
	public boolean checkMatrixCorner(int x, int y) {
		if (x==1 && y==1)                     //LT
			return true;
		else if (x==GRID_SIZE && y==GRID_SIZE)//RB
			return true;
		else if (x==GRID_SIZE && y==1)        //RT
			return true;
		else if (x==1 && y==GRID_SIZE)        //LB
			return true;
		return false;
	}
	
	public boolean checkMatrixEdge(int x, int y) {
		if (x>1 && x<GRID_SIZE-1 && y==1)     //T
			return true;
		else if (x==1 && y>1 && y<GRID_SIZE-1)//L
			return true;
		else if (x>1 && x<GRID_SIZE-1 && y==GRID_SIZE)//B
			return true;
		else if (x==GRID_SIZE && y>1 && y<GRID_SIZE-1)//R
			return true;
		return false;
	}
	
	public boolean checkMatrixColor(Color[][] colorMatrix, int x, int y, ArrayList<Color> colorList, int index) {
		if (colorMatrix[x-1][y-1].equals(colorList.get(index)))
			return true;
		return false;
	}
	
	public void addMoreBalls(int[][] matrix, int x, int y, int index) {
		if (matrix[x-1][y-1]==1) {
			Sphere gola=new Sphere(9.25);
			PhongMaterial material=new PhongMaterial();
			material.setDiffuseColor(colorList.get(index));
			gola.setMaterial(material);
			gola.setLayoutX(25);
			gola.setLayoutY(25);
            
            colorMatrix[x-1][y-1]=colorList.get(index);
            sudokuCells[y-1][x-1].getChildren().add(gola);
            
            Line path=new Line(25-0.125, 25, 25+0.125, 25);
            
            PathTransition rt=new PathTransition();
	    	rt.setNode(sudokuCells[y-1][x-1]);
	    	rt.setPath(path);
	    	rt.setAutoReverse(true);
	    	rt.setDuration(Duration.millis(150));
	    	rt.setCycleCount(Animation.INDEFINITE);
	    	rt.play();
		}
		else if (matrix[x-1][y-1]==2) {
        	sudokuCells[y-1][x-1].getChildren().clear();
        	
        	Sphere gola1=new Sphere(9.25);
        	PhongMaterial material1=new PhongMaterial();
        	material1.setDiffuseColor(colorList.get(index));
        	gola1.setMaterial(material1);
        	gola1.setLayoutX(25-8.125);
        	gola1.setLayoutY(25);
        	
        	Sphere gola2=new Sphere(9.25);
        	PhongMaterial material2=new PhongMaterial();
        	material2.setDiffuseColor(colorList.get(index));
        	gola2.setMaterial(material2);
        	gola2.setLayoutX(25+8.125);
        	gola2.setLayoutY(25);
        	
        	colorMatrix[x-1][y-1]=colorList.get(index);
        	sudokuCells[y-1][x-1].getChildren().addAll(gola1, gola2);
        	
        	RotateTransition rt=new RotateTransition();
	    	rt.setNode(sudokuCells[y-1][x-1]);
	    	rt.setDuration(Duration.seconds(3));
	    	rt.setFromAngle(0);
	    	rt.setToAngle(360);
	    	rt.setCycleCount(Animation.INDEFINITE);
	    	rt.play();
        }
        else if (matrix[x-1][y-1]==3) {
        	sudokuCells[y-1][x-1].getChildren().clear();
        	
        	Sphere gola1=new Sphere(9.25);
        	PhongMaterial material1=new PhongMaterial();
        	material1.setDiffuseColor(colorList.get(index));
        	gola1.setMaterial(material1);
        	gola1.setLayoutX(25-8.125);
        	gola1.setLayoutY(25);
        	
        	Sphere gola2=new Sphere(9.25);
        	PhongMaterial material2=new PhongMaterial();
        	material2.setDiffuseColor(colorList.get(index));
        	gola2.setMaterial(material2);
        	gola2.setLayoutX(25+8.125);
        	gola2.setLayoutY(25);
        	
        	Sphere gola3=new Sphere(9.25);
        	PhongMaterial material3=new PhongMaterial();
        	material3.setDiffuseColor(colorList.get(index));
        	gola3.setMaterial(material3);
        	gola3.setLayoutX(25);
        	gola3.setLayoutY(25-8.125);
        	
        	colorMatrix[x-1][y-1]=colorList.get(index);
        	sudokuCells[y-1][x-1].getChildren().addAll(gola1, gola2, gola3);
        	
        	RotateTransition rt=new RotateTransition();
	    	rt.setNode(sudokuCells[y-1][x-1]);
	    	rt.setDuration(Duration.seconds(2));
	    	rt.setFromAngle(0);
	    	rt.setToAngle(360);
	    	rt.setCycleCount(Animation.INDEFINITE);
	    	rt.play();
        }
        else if (matrix[x-1][y-1]==4) {
        	sudokuCells[y-1][x-1].getChildren().clear();
        	
        	Sphere gola1=new Sphere(9.25);
        	PhongMaterial material1=new PhongMaterial();
        	material1.setDiffuseColor(colorList.get(index));
        	gola1.setMaterial(material1);                   //Left
        	gola1.setLayoutX(25-8.125);
        	gola1.setLayoutY(25);
        	
        	Sphere gola2=new Sphere(9.25);
        	PhongMaterial material2=new PhongMaterial();
        	material2.setDiffuseColor(colorList.get(index));
        	gola2.setMaterial(material2);                   //Right
        	gola2.setLayoutX(25+8.125);
        	gola2.setLayoutY(25);
        	
        	Sphere gola3=new Sphere(9.25);
        	PhongMaterial material3=new PhongMaterial();
        	material3.setDiffuseColor(colorList.get(index));
        	gola3.setMaterial(material3);                   //Bottom
        	gola3.setLayoutX(25);
        	gola3.setLayoutY(25-8.125);
        	
        	Sphere gola4=new Sphere(9.25);
			PhongMaterial material4=new PhongMaterial();
			material4.setDiffuseColor(colorList.get(index));
			gola4.setMaterial(material4);                   //Top
			gola4.setLayoutX(25);
			gola4.setLayoutY(25+8.125);
            
			colorMatrix[x-1][y-1]=colorList.get(index);
        	sudokuCells[y-1][x-1].getChildren().addAll(gola1, gola2, gola3, gola4);
        	
        	RotateTransition rt=new RotateTransition();
	    	rt.setNode(sudokuCells[y-1][x-1]);
	    	rt.setDuration(Duration.seconds(1.5));
	    	rt.setFromAngle(0);
	    	rt.setToAngle(360);
	    	rt.setCycleCount(Animation.INDEFINITE);
	    	rt.play();
        }
	}
	
	public void split(int[][] matrix, int x, int y, int index) {
		if (checkMatrixCorner(x, y) && matrix[x-1][y-1]>1) {
			System.out.println("I'm in");
			if (x==1 && y==1) {                     //LT
				matrix[x][y-1]+=1;
				matrix[x-1][y]+=1;
				System.out.println("Now spliting LT");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x+1, y, index);
				addMoreBalls(matrix, x, y+1, index);
				if (matrix[x][y-1]>2)
					split(matrix, x+1, y, index);
				if (matrix[x-1][y]>2)
					split(matrix, x, y+1, index);
			}
			else if (x==GRID_SIZE && y==GRID_SIZE) {//RB
				matrix[x-2][y-1]+=1;
				matrix[x-1][y-2]+=1;
				System.out.println("Now spliting RB");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x-1, y, index);
				addMoreBalls(matrix, x, y-1, index);
				if (matrix[x-2][y-1]>2)
					split(matrix, x-1, y, index);
				if (matrix[x-1][y-2]>2)
					split(matrix, x, y-1, index);
			}
			else if (x==1 && y==GRID_SIZE) {        //LB
				matrix[x-1][y-2]+=1;
				matrix[x][y-1]+=1;
				System.out.println("Now spliting LB");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x, y-1, index);
				addMoreBalls(matrix, x+1, y, index);
				if (matrix[x-1][y-2]>2)
					split(matrix, x, y-1, index);
				if (matrix[x][y-1]>2)
					split(matrix, x+1, y, index);
			}
			else if (x==GRID_SIZE && y==1) {        //RT
				matrix[x-2][y-1]+=1;
				matrix[x-1][y]+=1;
				System.out.println("Now spliting RT");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x-1, y, index);
				addMoreBalls(matrix, x, y+1, index);
				if (matrix[x-2][y-1]>2)
					split(matrix, x-1, y, index);
				if (matrix[x-1][y]>2)
					split(matrix, x, y+1, index);
			}
		}
		else if (checkMatrixEdge(x, y) && matrix[x-1][y-1]>2) {
			if (x>1 && x<GRID_SIZE-1 && y==1) {    //T
				matrix[x-2][y-1]+=1;
				matrix[x][y-1]+=1;
				matrix[x-1][y]+=1;
				System.out.println("now splitting Top");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x-1, y, index);
				addMoreBalls(matrix, x+1, y, index);
				addMoreBalls(matrix, x, y+1, index);
				if (matrix[x-2][y-1]>2)
					split(matrix, x-1, y, index);
				if (matrix[x][y-1]>2)
					split(matrix, x+1, y, index);
				if (matrix[x-1][y]>4)
					split(matrix, x, y+1, index);
			}
			else if (x==1 && y>1 && y<GRID_SIZE-1) {//L
				matrix[x-1][y-2]+=1;
				matrix[x][y-1]+=1;
				matrix[x-1][y]+=1;
				System.out.println("now splitting Left");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x, y-1, index);
				addMoreBalls(matrix, x+1, y, index);
				addMoreBalls(matrix, x, y+1, index);
				if (matrix[x-1][y-2]>2)
					split(matrix, x, y-1, index);
				if (matrix[x][y-1]>4)
					split(matrix, x+1, y, index);
				if (matrix[x-1][y]>2)
					split(matrix, x, y+1, index);
			}
			else if (x>1 && x<GRID_SIZE-1 && y==GRID_SIZE) {//B
				matrix[x-2][y-1]+=1;
				matrix[x-1][y-2]+=1;
				matrix[x][y-1]+=1;
				System.out.println("now splitting Bottom");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x-1, y, index);
				addMoreBalls(matrix, x, y-1, index);
				addMoreBalls(matrix, x+1, y, index);
				if (matrix[x-2][y-1]>2)
					split(matrix, x-1, y, index);
				if (matrix[x-1][y-2]>4)
					split(matrix, x, y-1, index);
				if (matrix[x][y-1]>2)
					split(matrix, x+1, y, index);
			}
			else if (x==GRID_SIZE && y>1 && y<GRID_SIZE-1) {//R
				matrix[x-2][y-1]+=1;
				matrix[x-1][y-2]+=1;
				matrix[x-1][y]+=1;
				System.out.println("now splitting Right");
				sudokuCells[y-1][x-1].getChildren().clear();
				matrix[x-1][y-1]=0;
				addMoreBalls(matrix, x-1, y, index);
				addMoreBalls(matrix, x, y-1, index);
				addMoreBalls(matrix, x, y+1, index);
				if (matrix[x-2][y-1]>4)
					split(matrix, x-1, y, index);
				if (matrix[x-1][y-2]>2)
					split(matrix, x, y-1, index);
				if (matrix[x-1][y]>2)
					split(matrix, x, y+1, index);
			}
		}
		else {
			matrix[x-1][y-2]+=1;
			matrix[x-1][y]+=1;
			matrix[x-2][y-1]+=1;
			matrix[x][y-1]+=1;
			System.out.println("Now splitting middle one");
			sudokuCells[y-1][x-1].getChildren().clear();
			matrix[x-1][y-1]=0;
			addMoreBalls(matrix, x, y-1, index);
			addMoreBalls(matrix, x, y+1, index);
			addMoreBalls(matrix, x-1, y, index);
			addMoreBalls(matrix, x+1, y, index);
		}
	}

}

