package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SchedulingSystemController extends Application
{


	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		MenuItem addStaff = new MenuItem("Add Staff");
		MenuItem schedule = new MenuItem("View Schedule");
		
		//Menu Buttons
		MenuButton employeeListBtn = new MenuButton("Employee List", null, staffList,addStaff);
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

		//CREATE SCENE 2--------------------------------------------------------------------
		BorderPane bPane = new BorderPane();
		ToolBar tool2 = new ToolBar(tool);
		GridPane gPane2 = new GridPane();
		
		TextField first = new TextField();
		//.add(variable, column, row)
		gPane2.add(first, 1, 0);
		gPane2.add(new Label("First Name: "), 0, 0);
		
		TextField last = new TextField();
		gPane2.add(new Label("Last Name: "), 3, 0);
		gPane2.add(last, 4, 0);
	
		TextField addi = new TextField();
		gPane2.add(new Label("Address: "), 0, 2);
		gPane2.add(addi, 1, 2);
		
		TextField phoneNum = new TextField();
		gPane2.add(new Label("Phone Number: "), 3, 2);
		gPane2.add(phoneNum, 4, 2);
		
		
		TextField avail = new TextField();
		gPane2.add(new Label("Availability: "), 0, 3);
		gPane2.add(avail, 1, 3);
		
		TextField comp = new TextField();
		gPane2.add(new Label("Company: "), 3, 3);
		gPane2.add(comp, 4, 3);
		
		TextField uni = new TextField();
		gPane2.add(new Label("Unionized: "), 0, 4);
		gPane2.add(uni, 1, 4);
		
		TextField shiftPref = new TextField();
		gPane2.add(new Label("Shift Preference: "), 3, 4);
		gPane2.add(shiftPref, 4, 4);
		
		Button create = new Button("Create Employee");
		gPane2.add(create, 2, 5);
		
		
		
		//gPane2.setGridLinesVisible(true);
	 
	 
		
		bPane.setTop(tool2);
		bPane.setCenter(gPane2);
 ;
 		 

		Scene scene2 = new Scene(bPane,800,500);
		
		//ADD SCENE TO STAGE
		primaryStage.setScene(main);
		primaryStage.setTitle("PSW Scheduling System");
		//primaryStage.setResizable(false);
		primaryStage.show();
		 
		
		//Create Staff Objects to Populate 
		 
		//--------------------------------------------------------
		//EVENTLIST
		filter.setOnKeyTyped((e)->
		{
 			String text = filter.getText().toUpperCase();
			 
				
			for (int row = 0; row < model.getRowCount();row++)
			{
					sorter.setRowFilter(new RowFilter() 
					{
						@Override
						public boolean include(Entry entry) 
						{
							//get column number which is 0 then searched by text
							for(int column=0; column < model.getColumnCount(); column++)
							{
								if (entry.getStringValue(column).indexOf(text) >= 0)
									{
									return true;
									}
									
							}
							return false;
							 
						}
					});
					

			}
			
 
		});
		
		//Delete Function 
		delete.setOnAction((e) ->
		{
			
				String name = deleteField.getText().toUpperCase();
				String[] parsed = {};
				try 
				{
 					 
 					if(name.matches("(.*)\\s+(.*)"))
 					{
 						String query = "DELETE FROM EMPLOYEES WHERE FIRST_NAME = ? and LAST_NAME = ? ";
 	 					PreparedStatement stmt2 = conn.prepareStatement(query);
 						parsed = name.split("\\s+");
 						stmt2.setString(1,parsed[0]);
 						stmt2.setString(2,parsed[1]);
 						int v = stmt2.executeUpdate();
 	 					System.out.print(v + "WORKED");
 					}
 					else 
 					{
 						String query = "DELETE FROM EMPLOYEES WHERE FIRST_NAME = ? ";
 	 					PreparedStatement stmt2 = conn.prepareStatement(query);
 	 					
 	 					stmt2.setString(1, name);
 	 					
 	 					int v = stmt2.executeUpdate();
 	 					System.out.print(v + "WORKED2");
 					}
 	 				    model.fireTableDataChanged();
 	 				    table.repaint();
 					 
				} catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  
		});
 
		update.setOnAction(e ->
		{
			try 
			{
 				
			PreparedStatement stmt = conn.prepareStatement("UPDATE EMPLOYEES SET ADDRESS = (?), PHONE_NUMBER=(?), AVAILABILITY =(?),COMPANY=(?),UNIONIZED=(?),SHIFT_PREFERENCE=(?),VACATION_DATE=(?) WHERE FIRST_NAME=(?)");
			PreparedStatement get = conn.prepareStatement( "SELECT ADDRESS, PHONE_NUMBER, AVAILABILITY, COMPANY, UNIONIZED, SHIFT_PREFERENCE, VACATION_DATE FROM EMPLOYEES WHERE FIRST_NAME = ? ");
			
			String value = firstName.getText().toUpperCase();
			get.setString(1, value );
			
			ResultSet rs = get.executeQuery();
		 
	 
			
			
			String add = "";
			String number = "";
			String available = "";
			String compy = "";
			String union= "";
			String shift= "";
			String vacay= "";
			
		 
			
			while (rs.next())
			{
				add = rs.getString(1) ;
				number = rs.getString(2);
				available = rs.getString(3);
				compy = rs.getString(4);
				union = rs.getString(5);
				shift = rs.getString(6);
				vacay = rs.getString(7);
				
			}
			
			System.out.print(add + "" + number);
			
			 stmt.setString(1, address.getText().replaceAll("\\s+$", "").equals("") ? add: address.getText().toUpperCase()  );
			 stmt.setString(2, phoneNumber.getText().replaceAll("\\s+$", "").equals("")  ? number: phoneNumber.getText().toUpperCase() );
			 stmt.setString(3, availability.getText().replaceAll("\\s+$", "").equals("") ? available: availability.getText().toUpperCase() );
			 stmt.setString(4, company.getText().replaceAll("\\s+$", "").equals("") ? compy: company.getText().toUpperCase()  );
			 stmt.setString(5, unionized.getText().replaceAll("\\s+$", "").equals("") ? union: unionized.getText().toUpperCase()  );
			 stmt.setString(6, shiftPreference.getText().replaceAll("\\s+$", "").equals("") ? shift: shiftPreference.getText().toUpperCase()  );
			 stmt.setString(7, vacationDate.getText().replaceAll("\\s+$", "").equals("") ? vacay: vacationDate.getText().toUpperCase()  );
			 stmt.setString(8, value);

			stmt.executeQuery();
			
 	        
	   	 
  
			} catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			
		});
		
		 
		{
			try {
			model.setRowCount(0);
			
			Statement stmt = conn.createStatement();
	
			
			//Create SQL Query
			String query = " SELECT* FROM EMPLOYEES";
					 
			//Execute SQL Query
			ResultSet rs = stmt.executeQuery(query);
	 
			
			//Display info from Game Table
	        DefaultTableModel model2 = (DefaultTableModel)table.getModel();
			
			 
			 while (rs.next()) 
			 {
				model2.addRow(new String[] {Integer.toString(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });		 
	 		 } 
				
			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		};
		
		refresh.setOnAction((e) -> {
			//Create a SQL Statement
			try {
				
				
			model.setRowCount(0);
			
			Statement stmt = conn.createStatement();
	
			
			//Create SQL Query
			String query = " SELECT* FROM EMPLOYEES";
					 
			//Execute SQL Query
			ResultSet rs = stmt.executeQuery(query);
	 
			
			//Display info from Game Table
	        DefaultTableModel model2 = (DefaultTableModel)table.getModel();
			
			 
			 while (rs.next()) 
			 {
				model2.addRow(new String[] {Integer.toString(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });		 
	 		 } 
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
	 
			
			
		});
		
		
		//GET THE FIELDS AND SEND TO THE DB BUT ALSO GET THE PREVIOUS ID FROM THE TABLE, and then send ID + 1
		create.setOnAction(e ->
		{
			try 
			{
 				
				Statement id = conn.createStatement();
				String query = "SELECT EMPLOYEE_ID FROM EMPLOYEES";
				
	 	        ResultSet rs = id.executeQuery(query);
	 	        
	 	        int newEmpId = 0;
	 	        
	 	        while (rs.next())
	 	        {
	
	 	        	newEmpId = (rs.getInt(1) + 1);
	 	        }
		   	 
	 	        System.out.print(newEmpId);
	 	        
	 	        String query2 = "INSERT into EMPLOYEES values (?,?,?,?,?,?,?,?,?,?)";
	 	        PreparedStatement newEmp = conn.prepareStatement(query2);
	 	        
	 	         newEmp.setInt(1, newEmpId );
	 	         newEmp.setString(2, first.getText().replaceAll("\\s+$", "").toUpperCase()  );
				 newEmp.setString(3, last.getText().replaceAll("\\s+$", "").toUpperCase() );
				 newEmp.setString(4, addi.getText().toUpperCase());
				 newEmp.setString(5, phoneNum.getText().replaceAll("\\s+$", "").toUpperCase()  );
				 newEmp.setString(6, avail.getText().replaceAll("\\s+$", "").toUpperCase()  );
				 newEmp.setString(7, comp.getText().replaceAll("\\s+$", "").toUpperCase()  );
				 newEmp.setString(8,uni.getText().replaceAll("\\s+$", "").toUpperCase()  );
				 newEmp.setString(9, shiftPref.getText().replaceAll("\\s+$", "").toUpperCase()  );
				 newEmp.setString(10, "");
				 
				  newEmp.executeUpdate();
  
			} catch (SQLException e1) 
			{
				 e1.printStackTrace();
			}
			
		});
		
		//--------------------------------CHANGE SCENE-------------------------
		addStaff.setOnAction(event -> 
		{
			primaryStage.setScene(scene2);
			primaryStage.setTitle("Add Employee");
			bPane.setTop(tool);
		});
		
		staffList.setOnAction(event ->
		{
 		 
			primaryStage.setScene(main);
			border.setTop(tool);
		});

		
 
 	}


}
