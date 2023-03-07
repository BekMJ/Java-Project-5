package application;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.Slider;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.application.Application;
import javafx.scene.control.CheckBox;

/**
 * @author askub
 *
 */
public class Main extends Application {

  /**
 * all private varaibles that are used throughout the project
 */
			  private static ComboBox < String > CB = new ComboBox < > ();;
			  private static ComboBox < String > CB1;
			  private static ComboBox < String > CB2;
			  private static ComboBox < String > CB3;
			  
			  private static PieChart pieChart;
			  
			  private static Slider one = new Slider(15049308, 15049407, 15049308);
			  private static Slider two = new Slider(one.getValue(), 15049407, 15049308);
			
			  private static ObservableList < PieChart.Data > data;
			
			  private static List < PieChart.Data > data1= new ArrayList < Data > ();
			
			  private static LinkedHashMap < String, Integer > uniq = new LinkedHashMap < String, Integer > ();
			  
			  static BorderPane layout = new BorderPane();
			  static BorderPane layout2 = new BorderPane();
			  static BorderPane layout3 = new BorderPane();
			  static BorderPane layout4 = new BorderPane();
			  
			  static Scene firstScene = new Scene(layout2, 1500, 900);
			  static Scene secondScene = new Scene(layout3, 1500, 900);
			  static Scene thirdScene = new Scene(layout4, 1500, 900);
			  
			  static GridPane check = new GridPane();
				static CheckBox c1 = new CheckBox("Average Transaction Cost");
				static CheckBox c2 = new CheckBox("Lowest Transaction Cost");
				static CheckBox c3 = new CheckBox("Highest Transaction Cost");
				
				static GridPane grid = new GridPane();
				static TextField b1 = new TextField();
				static TextField b2 = new TextField();
				static Button add = new Button("Add");
				static XYChart.Series firstSeries = new XYChart.Series();
				static XYChart.Series secondSeries = new XYChart.Series();
				static XYChart.Series thirdSeries = new XYChart.Series();
				static XYChart.Series fourthSeries = new XYChart.Series();
				
				static XYChart.Series fifthSeries = new XYChart.Series();
				
				static XYChart.Series sixthSeries = new XYChart.Series();
		
  
  /**
 * @param args
 * @throws IOException
 */
public static void main(String[] args) throws IOException {

	    launch(args);
	  }
  
  
  /**
 * @param stage the frame we are using
 * this method creates the interface for default option --selectChart--
 */
public void selectChart(Stage stage) {
	  CB.setPromptText("--select chart--");
	    CB.getItems().addAll("--select chart--", "Unique Miners", "Transaction Cost", "Time Difference");

	    layout.setTop(CB);
	    layout.setAlignment(CB, Pos.CENTER_LEFT);
	    Scene initialScene = new Scene(layout, 1200, 850);
	    stage.setScene(initialScene);
	    stage.show();
	    stage.setTitle("Project 5 - Bek Mijiddorj");
	    
	    CB.setOnAction(new EventHandler < ActionEvent > () {
	        public void handle(ActionEvent ae) {
	          if (CB.getValue().equalsIgnoreCase("Unique Miners")) {
	            stage.setScene(firstScene);
	          } else if (CB.getValue().equalsIgnoreCase("Transaction Cost")) {
	            stage.setScene(secondScene);
	          } else if (CB.getValue().equalsIgnoreCase("Time Difference")) {
	            stage.setScene(thirdScene);
	          }
	        }
	      });
  }


  /**
 * @param stage
 * draws piegraph
 */
public void pie(Stage stage) {
	  CB1 = new ComboBox < > ();
	    CB1.setPromptText("Unique Miners");
	    CB1.getItems().addAll("Unique Miners", "Transaction Cost", "Time Difference");
	    CB1.setOnAction(new EventHandler < ActionEvent > () {
	      public void handle(ActionEvent ae) {
	    	  if (CB.getValue().equalsIgnoreCase("Unique Miners")) {
	              stage.setScene(firstScene);
	            } else if (CB.getValue().equalsIgnoreCase("Transaction Cost")) {
	              stage.setScene(secondScene);
	            } else if (CB.getValue().equalsIgnoreCase("Time Difference")) {
	              stage.setScene(thirdScene);
	            }
	      }
	    });
	    one.setMajorTickUnit(25);
	    two.setMajorTickUnit(25);

	    one.setMinorTickCount(25 / 5);
	    two.setMinorTickCount(25 / 5);

	    layout2.setTop(CB1);
	    layout2.setAlignment(CB1, Pos.CENTER_LEFT);


	    data = FXCollections.observableArrayList();
	    pieChart = new PieChart(data);
	    
	    pieChart.setLegendVisible(true);
	    pieChart.setLabelsVisible(true);
	    
	    pieChart.setTitle("Each Unique Miner and its Frequenc");
	    layout2.setCenter(pieChart);
	
	    one.setShowTickMarks(true);
		    one.setShowTickLabels(true);
			    one.setSnapToTicks(true);
			    one.setPrefWidth(400);
		
		    two.setShowTickMarks(true);
		    two.setShowTickLabels(true);
		    two.setSnapToTicks(false);
		    two.setPrefWidth(400);
		
		    GridPane sliders = new GridPane();
		    one.setTranslateX(200);
		    two.setTranslateX(200);
		    sliders.setVgap(20);
		    sliders.add(two, 0, 0);
		    sliders.add(one, 0, 1);
		    layout2.setLeft(sliders);

						    one.valueProperty().addListener((observableValue, oldValue, newValue) -> {
						      two.setMin(one.getValue());
						  try {
							setData();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						      one.setValue(Math.round(newValue.doubleValue()));
						    });
						    two.valueProperty().addListener((observableValue, oldValue, newValue) -> {
						    two.setValue(Math.round(newValue.doubleValue()));
						      try {
								setData();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    });
  }
  
  /**
 * @param stage
 * for drawing transactionCost graph
 * @throws IOException 
 * @throws FileNotFoundException 
 */
public void transactionCost(Stage stage) throws FileNotFoundException, IOException {
				  CB2 = new ComboBox < > ();
				    CB2.setPromptText("Transaction Cost");
				    CB2.getItems().addAll("Unique Miners", "Transaction Cost", "Time Difference");
				    CB2.setOnAction(new EventHandler < ActionEvent > () {
				      public void handle(ActionEvent ae) {
				    	  if (CB.getValue().equalsIgnoreCase("Unique Miners")) {
				              stage.setScene(firstScene);
				            } else if (CB.getValue().equalsIgnoreCase("Transaction Cost")) {
				              stage.setScene(secondScene);
				            } else if (CB.getValue().equalsIgnoreCase("Time Difference")) {
				              stage.setScene(thirdScene);
				            }
				      }
								    });
			layout3.setTop(CB2);
			layout3.setAlignment(CB2, Pos.CENTER_LEFT);
			
			NumberAxis xAxis = new NumberAxis(15049308, 15049322, 1);
			NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("Block Number");
			yAxis.setLabel("Transaction Cost(ETH)");
			LineChart < Number, Number > lineChart = new LineChart < Number, Number > (xAxis, yAxis);
			lineChart.setTitle("Transaction Cost of Blocks");
			lineChart.setPrefSize(1000, 800);
			lineChart.setMinSize(1000, 800);
			lineChart.setMaxSize(1000, 800);
			
			
			firstSeries.setName("Average Transaction Cost");
			for (int i = 15049308; i <= 15049322; i++) {firstSeries.getData().add(new XYChart.Data(i, Blocks.getBlockByNumber(i).avgTransactionCost()));}
			
			
			secondSeries.setName("Lowest Transaction Cost");
			for (int i = 15049308; i <= 15049322; i++) {
			double min = Blocks.getBlockByNumber(i).getTransactions().get(0).transactionCost();
			
			for (int j = 1; j < Blocks.getBlockByNumber(i).getTransactions().size(); j++) {if (Blocks.getBlockByNumber(i).getTransactions().get(j).transactionCost() < min) {min = Blocks.getBlockByNumber(i).getTransactions().get(j).transactionCost();
			  }
			}
			secondSeries.getData().add(new XYChart.Data(i, min));
			}
			
			
			thirdSeries.setName("Highest Transaction Cost");
			for (int i = 15049308; i <= 15049322; i++) {double max = Blocks.getBlockByNumber(i).getTransactions().get(0).transactionCost();
			
			for (int j = 1; j < Blocks.getBlockByNumber(i).getTransactions().size(); j++) {if (Blocks.getBlockByNumber(i).getTransactions().get(j).transactionCost() > max) {max = Blocks.getBlockByNumber(i).getTransactions().get(j).transactionCost();
			}
			}
			thirdSeries.getData().add(new XYChart.Data(i, max));
			}
			
			layout3.setCenter(lineChart);
			
			
			
			c1.selectedProperty().addListener((ObservableValue < ? extends Boolean > ov, Boolean old_val, Boolean new_val) -> {
			if (c1.isSelected()) {
			lineChart.getData().add(firstSeries);
			} else {
			lineChart.getData().remove(firstSeries);
			}
			});
			
			c2.selectedProperty().addListener((ObservableValue < ? extends Boolean > ov, Boolean old_val, Boolean new_val) -> {
			if (c2.isSelected()) {
			lineChart.getData().add(secondSeries);
			} else {
			lineChart.getData().remove(secondSeries);
			}
			});
			
			c3.selectedProperty().addListener((ObservableValue < ? extends Boolean > ov, Boolean old_val, Boolean new_val) -> {
			if (c3.isSelected()) {
			lineChart.getData().add(thirdSeries);
			} else {
			lineChart.getData().remove(thirdSeries);
			}
			});
			
			check.add(c1, 0, 0);
			check.add(c2, 0, 1);
			check.add(c3, 0, 2);
			
			
			check.setTranslateX(50);
			check.setTranslateY(300);
			
			check.setVgap(10);
			
			layout3.setLeft(check);
			  }
  
  /**
 * @param stage
 * draws the timeDifference bars
 */
public void timeDifference(Stage stage) {
				  CB3 = new ComboBox < String > ();
				    CB3.setPromptText("Time Difference");
				    CB3.getItems().addAll("Unique Miners", "Transaction Cost", "Time Difference");
				    CB3.setOnAction(new EventHandler < ActionEvent > () {
				
				      public void handle(ActionEvent ae) {
				        if (CB3.getValue().equals("Unique Miners")) {
				          stage.setScene(firstScene);
				        } else if (CB3.getValue().equals("Transaction Cost")) {
				          stage.setScene(secondScene);
				        } else if (CB3.getValue().equals("Time Difference")) {
				          stage.setScene(thirdScene);
				        }
				      }
			
			});
			layout4.setTop(CB3);
			layout4.setAlignment(CB3, Pos.CENTER_LEFT);
			
			
			grid.add(b1, 0, 0);
			grid.add(b2, 1, 0);
			grid.add(add, 2, 0);
			
			grid.setTranslateX(50);
			grid.setTranslateY(300);
			layout4.setLeft(grid);
				
				CategoryAxis xAxis1 = new CategoryAxis();
				NumberAxis yAxis1 = new NumberAxis(0, 60, 2);
				
				xAxis1.setLabel("Blocks");
				yAxis1.setLabel("Time Units(hrs, mins, secs)");
					
					BarChart < String, Number > bc = new BarChart < String, Number > (xAxis1, yAxis1);
					bc.setTitle("Time Difference Between Blocks");
					bc.setPrefSize(1000, 700);
					bc.setMinSize(1000, 700);
					bc.setMaxSize(1000, 700);
			
					fourthSeries.setName("hours");
			
			fifthSeries.setName("minutes");
	
			sixthSeries.setName("seconds");
			
			xAxis1.setAnimated(false);
			yAxis1.setAnimated(true);
			bc.setAnimated(true);
			
			
			
			
			add.setOnAction(new EventHandler < ActionEvent > () {
			@Override
			public void handle(ActionEvent e) {
			
			  int[] test = null;
			try {
				test = Blocks.getTimeDiff(Blocks.getBlockByNumber(Integer.parseInt(b1.getText())),
				    Blocks.getBlockByNumber(Integer.parseInt(b2.getText())));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			  fourthSeries.getData().add(new XYChart.Data(b1.getText() + " - " + b2.getText(), test[0]));
			  fifthSeries.getData().add(new XYChart.Data(b1.getText() + " - " + b2.getText(), test[1]));
			  sixthSeries.getData().add(new XYChart.Data(b1.getText() + " - " + b2.getText(), test[2]));
			  bc.getData().addAll(fourthSeries, fifthSeries, sixthSeries);
			}
			});
			
			layout4.setCenter(bc);
  }

  
  @Override
  public void start(Stage stage) throws IOException {
			    Blocks.readFile("ethereumP1data.csv");
			    Blocks.sortBlocksByNumber();
			    
			    
			    selectChart(stage);

			    
			    pie(stage);
			    
			    transactionCost(stage);
			    
			    timeDifference(stage);
		
		   

  }

		  /**
		 * setting the data required to draw charts
		 * @throws IOException 
		 * @throws FileNotFoundException 
		 */
		public void setData() throws FileNotFoundException, IOException {
			  data.clear();
			    data1.clear();
			    uniq.clear();
		    uniq = Blocks.calUniqMiners((int) one.getValue(), (int) two.getValue());
		
		    for (Map.Entry < String, Integer > entry: uniq.entrySet()) {
		      String key = entry.getKey();
		      Integer value = entry.getValue();
		      
		      data1.add(new PieChart.Data(key, value));
		    }
		    data = FXCollections.observableArrayList(data1);
		    
		    data.forEach(
		      data -> data.nameProperty().bind(Bindings.concat(data.getName(), ": ", (int) data.getPieValue()))
		    );
		    
		    pieChart.setData(data);
		
		  }

  
}