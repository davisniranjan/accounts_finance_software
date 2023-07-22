import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccountAndFinanceApp extends Application {
    // Assuming you have already created instances of User and Dashboard as before
    // For simplicity, let's assume there is only one user in the system

    private User loggedInUser = users.get(0);

    private Stage primaryStage;
    private Scene loginScene, dashboardScene, accountDetailsScene, transactionsScene, budgetingScene, taxManagementScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Accounting and Finance App");

        // Create login screen
        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(10, 10, 10, 10));
        loginGrid.setVgap(5);
        loginGrid.setHgap(5);

        // Labels
        Label lblUsername = new Label("Username:");
        GridPane.setConstraints(lblUsername, 0, 0);

        Label lblPassword = new Label("Password:");
        GridPane.setConstraints(lblPassword, 0, 1);

        // TextFields
        TextField txtUsername = new TextField();
        GridPane.setConstraints(txtUsername, 1, 0);

        PasswordField txtPassword = new PasswordField();
        GridPane.setConstraints(txtPassword, 1, 1);

        // Login Button
        Button btnLogin = new Button("Login");
        GridPane.setConstraints(btnLogin, 1, 2);

        // Event handler for login button
        btnLogin.setOnAction(e -> {
            String enteredUsername = txtUsername.getText();
            String enteredPassword = txtPassword.getText();

            if (loggedInUser.verifyUser(enteredUsername, enteredPassword)) {
                showDashboard();
            } else {
                showError("Invalid username or password. Please try again.");
            }
        });

        loginGrid.getChildren().addAll(lblUsername, lblPassword, txtUsername, txtPassword, btnLogin);
        loginScene = new Scene(loginGrid, 300, 150);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Method to show the dashboard screen
    private void showDashboard() {
        // Create dashboard screen
        GridPane dashboardGrid = new GridPane();
        dashboardGrid.setPadding(new Insets(10, 10, 10, 10));
        dashboardGrid.setVgap(5);
        dashboardGrid.setHgap(5);

        // Create buttons for navigation to other screens
        Button btnAccountDetails = new Button("Account Details");
        Button btnTransactions = new Button("Transactions");
        Button btnBudgeting = new Button("Budgeting");
        Button btnTaxManagement = new Button("Tax Management");
        Button btnLogout = new Button("Logout");

        // Event handlers for navigation buttons
        btnAccountDetails.setOnAction(e -> showAccountDetails());
        btnTransactions.setOnAction(e -> showTransactions());
        btnBudgeting.setOnAction(e -> showBudgeting());
        btnTaxManagement.setOnAction(e -> showTaxManagement());
        btnLogout.setOnAction(e -> logout());

        dashboardGrid.add(btnAccountDetails, 0, 0);
        dashboardGrid.add(btnTransactions, 1, 0);
        dashboardGrid.add(btnBudgeting, 0, 1);
        dashboardGrid.add(btnTaxManagement, 1, 1);
        dashboardGrid.add(btnLogout, 0, 2);

        dashboardScene = new Scene(dashboardGrid, 400, 200);
        primaryStage.setScene(dashboardScene);
    }

    // Method to show the account details screen
    private void showAccountDetails() {
        // Create account details screen
        GridPane accountDetailsGrid = new GridPane();
        accountDetailsGrid.setPadding(new Insets(10, 10, 10, 10));
        accountDetailsGrid.setVgap(5);
        accountDetailsGrid.setHgap(5);
    
        // Display the user's account details
        Label lblName = new Label("Name: " + loggedInUser.getName());
        Label lblAddress = new Label("Address: " + loggedInUser.getAddress());
        Label lblContactNumber = new Label("Contact Number: " + loggedInUser.getContactNumber());
    
        // Add labels to the grid
        accountDetailsGrid.add(lblName, 0, 0);
        accountDetailsGrid.add(lblAddress, 0, 1);
        accountDetailsGrid.add(lblContactNumber, 0, 2);
    
        // Add a back button to return to the dashboard
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        accountDetailsGrid.add(btnBack, 0, 3);
    
        // Set the scene for the account details screen
        accountDetailsScene = new Scene(accountDetailsGrid, 400, 200);
        primaryStage.setScene(accountDetailsScene);
    }

    // Method to show the transactions screen
    private void showTransactions() {
        // Create transactions screen
        GridPane transactionsGrid = new GridPane();
        transactionsGrid.setPadding(new Insets(10, 10, 10, 10));
        transactionsGrid.setVgap(5);
        transactionsGrid.setHgap(5);
    
        // Labels and TextFields for transaction entry
        Label lblType = new Label("Type (Income/Expense):");
        GridPane.setConstraints(lblType, 0, 0);
    
        TextField txtType = new TextField();
        GridPane.setConstraints(txtType, 1, 0);
    
        Label lblDescription = new Label("Description:");
        GridPane.setConstraints(lblDescription, 0, 1);
    
        TextField txtDescription = new TextField();
        GridPane.setConstraints(txtDescription, 1, 1);
    
        Label lblAmount = new Label("Amount:");
        GridPane.setConstraints(lblAmount, 0, 2);
    
        TextField txtAmount = new TextField();
        GridPane.setConstraints(txtAmount, 1, 2);
    
        // Button to submit the transaction
        Button btnSubmit = new Button("Submit Transaction");
        GridPane.setConstraints(btnSubmit, 1, 3);
    
        // Event handler for the submit button
        btnSubmit.setOnAction(e -> {
            String type = txtType.getText();
            String description = txtDescription.getText();
            double amount = Double.parseDouble(txtAmount.getText());
    
            // Create a new transaction instance (assuming you have a Transaction class)
            Transaction newTransaction = new Transaction(type, description, amount);
    
            // Add the transaction to the Dashboard's transactions list
            loggedInUser.getDashboard().addTransaction(newTransaction);
    
            // Clear the input fields after submitting the transaction
            txtType.clear();
            txtDescription.clear();
            txtAmount.clear();
        });
    
        // Add components to the grid
        transactionsGrid.getChildren().addAll(lblType, txtType, lblDescription, txtDescription, lblAmount, txtAmount, btnSubmit);
    
        // Add a back button to return to the dashboard
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        transactionsGrid.add(btnBack, 0, 3);
    
        // Set the scene for the transactions screen
        transactionsScene = new Scene(transactionsGrid, 400, 200);
        primaryStage.setScene(transactionsScene);
    }

    // Method to show the budgeting screen
    private void showBudgeting() {
        // Create budgeting screen
        GridPane budgetingGrid = new GridPane();
        budgetingGrid.setPadding(new Insets(10, 10, 10, 10));
        budgetingGrid.setVgap(5);
        budgetingGrid.setHgap(5);
    
        // Labels and TextFields for budget entry
        Label lblCategory1 = new Label("Category 1:");
        GridPane.setConstraints(lblCategory1, 0, 0);
    
        TextField txtCategory1Budget = new TextField();
        GridPane.setConstraints(txtCategory1Budget, 1, 0);
    
        Label lblCategory2 = new Label("Category 2:");
        GridPane.setConstraints(lblCategory2, 0, 1);
    
        TextField txtCategory2Budget = new TextField();
        GridPane.setConstraints(txtCategory2Budget, 1, 1);
    
        // Button to submit the budget
        Button btnSubmit = new Button("Submit Budget");
        GridPane.setConstraints(btnSubmit, 1, 2);
    
        // Event handler for the submit button
        btnSubmit.setOnAction(e -> {
            // Get the budget values entered by the user
            double category1Budget = Double.parseDouble(txtCategory1Budget.getText());
            double category2Budget = Double.parseDouble(txtCategory2Budget.getText());
    
            // Update the budget values in the Dashboard
            loggedInUser.getDashboard().updateBudget(category1Budget, category2Budget);
    
            // Clear the input fields after submitting the budget
            txtCategory1Budget.clear();
            txtCategory2Budget.clear();
        });
    
        // Add components to the grid
        budgetingGrid.getChildren().addAll(lblCategory1, txtCategory1Budget, lblCategory2, txtCategory2Budget, btnSubmit);
    
        // Add a back button to return to the dashboard
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        budgetingGrid.add(btnBack, 0, 2);
    
        // Set the scene for the budgeting screen
        budgetingScene = new Scene(budgetingGrid, 400, 200);
        primaryStage.setScene(budgetingScene);
    }

        budgetingScene = new Scene(new GridPane(), 400, 200);
        primaryStage.setScene(budgetingScene);
    }

    // Method to show the tax management screen
    private void showTaxManagement() {
        // Create tax management screen
        GridPane taxManagementGrid = new GridPane();
        taxManagementGrid.setPadding(new Insets(10, 10, 10, 10));
        taxManagementGrid.setVgap(5);
        taxManagementGrid.setHgap(5);
    
        // Labels and TextFields for tax entry
        Label lblIncome = new Label("Income:");
        GridPane.setConstraints(lblIncome, 0, 0);
    
        TextField txtIncome = new TextField();
        GridPane.setConstraints(txtIncome, 1, 0);
    
        Label lblTaxRate = new Label("Tax Rate:");
        GridPane.setConstraints(lblTaxRate, 0, 1);
    
        TextField txtTaxRate = new TextField();
        GridPane.setConstraints(txtTaxRate, 1, 1);
    
        // Button to calculate tax
        Button btnCalculateTax = new Button("Calculate Tax");
        GridPane.setConstraints(btnCalculateTax, 1, 2);
    
        // Label to display the calculated tax
        Label lblTaxAmount = new Label("Tax Amount:");
        GridPane.setConstraints(lblTaxAmount, 0, 3);
    
        Label lblCalculatedTax = new Label();
        GridPane.setConstraints(lblCalculatedTax, 1, 3);
    
        // Event handler for the calculate tax button
        btnCalculateTax.setOnAction(e -> {
            // Get the income and tax rate entered by the user
            double income = Double.parseDouble(txtIncome.getText());
            double taxRate = Double.parseDouble(txtTaxRate.getText());
    
            // Calculate the tax amount
            double taxAmount = income * (taxRate / 100);
    
            // Display the calculated tax amount
            lblCalculatedTax.setText(String.format("%.2f", taxAmount));
        });
    
        // Add components to the grid
        taxManagementGrid.getChildren().addAll(lblIncome, txtIncome, lblTaxRate, txtTaxRate, btnCalculateTax, lblTaxAmount, lblCalculatedTax);
    
        // Add a back button to return to the dashboard
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        taxManagementGrid.add(btnBack, 0, 2);
    
        // Set the scene for the tax management screen
        taxManagementScene = new Scene(taxManagementGrid, 400, 200);
        primaryStage.setScene(taxManagementScene);
    }

    

    // Method to logout and return to the login screen
    private void logout() {
        primaryStage.setScene(loginScene);
    }

    // Method to show error messages (to be implemented)
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
    
        alert.showAndWait();
    
    }
}

