package irrelevant;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class MainPageController 
{
	@FXML
	private TableView table;
	@FXML
	private TableColumn firstColumn;
	@FXML
	private TableColumn lastColumn;
	@FXML
	private TableColumn addressColumn;
	@FXML
	private TableColumn phoneColumn;
	@FXML
	private TableColumn availabilityColumn;
	@FXML
	private TableColumn companyColumn;
	@FXML
	private TableColumn unionizedColumn;
	@FXML
	private TableColumn shiftPreferenceColumn;
	@FXML
	private TableColumn vacationDateColumn;
	@FXML
	private TextField nameUpdateField;
	@FXML
	private Button updateBtn;
	@FXML
	private TextField filterField;
	@FXML
	private TextField deleteNameField;
	@FXML
	private Button deleteBtn;
	@FXML
	private TextField addressField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField availabilityField;
	@FXML
	private TextField companyField;
	@FXML
	private TextField unionizedField;
	@FXML
	private TextField shiftPreferenceField;
	@FXML
	private TextField vacationDateField;

	
	
	// Event Listener on Button[#updateBtn].onAction
	@FXML
	public void updateBtnClicked(ActionEvent event) 
	{
			 //Update tableview 
		
			//Update Database
	}
	
	// Event Listener on TextField[#filterField].onAction
	@FXML
	public void onType(ActionEvent event) 
	{
		// TODO Autogenerated
	}
	
	// Event Listener on Button[#deleteBtn].onAction
	@FXML
	public void deleteBtnClicked(ActionEvent event) 
	{
		// TODO Autogenerated
	}
}
