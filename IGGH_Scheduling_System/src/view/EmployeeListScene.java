package view;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeListScene  extends Application
{
 

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		//GUI CREATION
		//Parent e = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		//Root Instantiation
		BorderPane border = new BorderPane();
			
		//------------------------------------------------------
		//TOOLBAR INSTANTIATION
		ToolBar tool = new ToolBar();
		
		//TOOLBAR NODES
		
		//Sub-Menu Items
		MenuItem staffList = new MenuItem("View StaffList");
		MenuItem schedule = new MenuItem("View Schedule");
		
		//Menu Buttons
		MenuButton employeeListBtn = new MenuButton("Employee List", null, staffList);
		MenuButton scheduleBtn = new MenuButton("Schedule", null, schedule);
		
		tool.getItems().addAll(employeeListBtn, scheduleBtn);
		
		//--------------TEXTFIELDS-------------------------------------
		
		TextField firstName = new TextField();
		TextField address = new TextField();
		TextField phoneNumber = new TextField();
		TextField availability = new TextField();
		TextField company = new TextField();
		TextField unionized = new TextField();
		TextField shiftPreference = new TextField();
		TextField vacationDate = new TextField();
		TextField filter = new TextField();
		TextField deleteField = new TextField();
		
  		Button update = new Button("Update");
		Button delete = new Button("Delete");
		Button refresh = new Button("Refresh");
		
		
		GridPane gPane = new GridPane();
		gPane.setHgap(5); 
		gPane.setVgap(5);
		gPane.prefWidthProperty().bind(border.widthProperty());
		//gPane.prefHeightProperty().bind(border.heightProperty());
		 
		//gPane.setGridLinesVisible(true);
		gPane.add(new Label("Update by Name: "), 1, 0);
		gPane.add(firstName, 2, 0);
		gPane.add(update, 3,0 );
		gPane.add(new Label("	Filter: "), 1, 3);
		gPane.add(filter, 2, 3);
		gPane.add(new Label("Delete by Name: "),1,5);
		gPane.add(deleteField, 2, 5);
		gPane.add(delete, 3, 5);
		gPane.add(new Label("Address: "), 5, 0);
		gPane.add(address, 6, 0);
		gPane.add(new Label("Phone Number: "), 5, 2);
		gPane.add(phoneNumber, 6, 2);
		gPane.add(new Label("Availability: "), 5, 3);
		gPane.add(availability, 6, 3);
		gPane.add(new Label("Company: "), 8, 0);
		gPane.add(company, 9, 0);
		gPane.add(new Label("Unionized: "), 8, 2);
		gPane.add(unionized, 9, 2);
		gPane.add(new Label("Shift Preference: "), 8, 3);
		gPane.add(shiftPreference, 9, 3);
		gPane.add(new Label("Vacation Date: "), 8, 5);
		gPane.add(vacationDate, 9, 5);
		gPane.add(refresh, 13, 5);

 		
		//--------------JTABLE INSTANTIATION-------------------------------------------
	 
		//DATA FOR TABLE
	 
		
		//COLUMN NAMES
		String[] columnName = {"ID","First","Last","Address","Phone Number", "Availability","Company","Unionized","Shift Preference","Vacation Date"};
		
		//Connect to the Database
		System.out.println("Connect to Database");
		Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_m22_sy_81", "password");
		System.out.println("Database Connected");

		//Create a SQL Statement
		Statement sql_stmt = conn.createStatement();
		
		//Create SQL Query
		String sql = " SELECT* FROM EMPLOYEES";
				 
		//Execute SQL Query
		ResultSet rset = sql_stmt.executeQuery(sql);
 
		
		//Display info from Game Table
		JTable table = new JTable();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
		TableRowSorter sorter = new TableRowSorter(model);
		table.setAutoCreateRowSorter(true);
		table.setRowSorter(sorter);
//		table.setAutoResizeMode(5);
		//table.setSize(800, 200);
		
		for(int i = 0; i < columnName.length;i++)
		{
			
				model.addColumn(columnName[i]);
		}
		
 
		 while (rset.next()) 
		 {
			model.addRow(new String[] {Integer.toString(rset.getInt(1)),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),rset.getString(7),rset.getString(8),rset.getString(9),rset.getString(10) });		 
 		 } 
			
	 
		 
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
		
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.createHorizontalScrollBar();
		panel.add(tableContainer, BorderLayout.CENTER);
		SwingNode s = new SwingNode();
		
		s.setContent(tableContainer);
		
		 
			 
		//Close the ResultSet and Statement
//		rset.close();
//		sql_stmt.close();
//		conn.close();
		//---------------------------------------------------------------------------------
		
		//ADD NODES TO ROOT
		border.setTop(tool);
		border.setCenter(s);
		border.setBottom(gPane);
		BorderPane.setAlignment(gPane,Pos.BOTTOM_RIGHT);
		//CREATE SCENE
		Scene main = new Scene(border,1000,500);

	 
		//Scene e = new Scene(root);
		
		//ADD SCENE TO STAGE
		primaryStage.setScene(main);
		primaryStage.setTitle("PSW Scheduling System");
		//primaryStage.setResizable(false);
		primaryStage.show();
		 
		
		
	}
}
