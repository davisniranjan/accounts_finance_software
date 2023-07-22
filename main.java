import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AccountAndFinanceApp extends Application {

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

        GridPane loginGrid = new GridPane();
        loginGrid.setPadding(new Insets(10, 10, 10, 10));
        loginGrid.setVgap(5);
        loginGrid.setHgap(5);

        Label lblUsername = new Label("Username:");
        GridPane.setConstraints(lblUsername, 0, 0);

        Label lblPassword = new Label("Password:");
        GridPane.setConstraints(lblPassword, 0, 1);

        TextField txtUsername = new TextField();
        GridPane.setConstraints(txtUsername, 1, 0);

        PasswordField txtPassword = new PasswordField();
        GridPane.setConstraints(txtPassword, 1, 1);

        Button btnLogin = new Button("Login");
        GridPane.setConstraints(btnLogin, 1, 2);

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

    private void showDashboard() {
        GridPane dashboardGrid = new GridPane();
        dashboardGrid.setPadding(new Insets(10, 10, 10, 10));
        dashboardGrid.setVgap(5);
        dashboardGrid.setHgap(5);

        Button btnAccountDetails = new Button("Account Details");
        Button btnTransactions = new Button("Transactions");
        Button btnBudgeting = new Button("Budgeting");
        Button btnTaxManagement = new Button("Tax Management");
        Button btnLogout = new Button("Logout");

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

    private void showAccountDetails() {
        GridPane accountDetailsGrid = new GridPane();
        accountDetailsGrid.setPadding(new Insets(10, 10, 10, 10));
        accountDetailsGrid.setVgap(5);
        accountDetailsGrid.setHgap(5);
    
        Label lblName = new Label("Name: " + loggedInUser.getName());
        Label lblAddress = new Label("Address: " + loggedInUser.getAddress());
        Label lblContactNumber = new Label("Contact Number: " + loggedInUser.getContactNumber());
        
        accountDetailsGrid.add(lblName, 0, 0);
        accountDetailsGrid.add(lblAddress, 0, 1);
        accountDetailsGrid.add(lblContactNumber, 0, 2);
    
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        accountDetailsGrid.add(btnBack, 0, 3);
    
        accountDetailsScene = new Scene(accountDetailsGrid, 400, 200);
        primaryStage.setScene(accountDetailsScene);
    }

    private void showTransactions() {
        GridPane transactionsGrid = new GridPane();
        transactionsGrid.setPadding(new Insets(10, 10, 10, 10));
        transactionsGrid.setVgap(5);
        transactionsGrid.setHgap(5);
    
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
    
        Button btnSubmit = new Button("Submit Transaction");
        GridPane.setConstraints(btnSubmit, 1, 3);
    
        btnSubmit.setOnAction(e -> {
            String type = txtType.getText();
            String description = txtDescription.getText();
            double amount = Double.parseDouble(txtAmount.getText());
    
            Transaction newTransaction = new Transaction(type, description, amount);
    
            loggedInUser.getDashboard().addTransaction(newTransaction);
    
            txtType.clear();
            txtDescription.clear();
            txtAmount.clear();
        });
    
        transactionsGrid.getChildren().addAll(lblType, txtType, lblDescription, txtDescription, lblAmount, txtAmount, btnSubmit);
    
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        transactionsGrid.add(btnBack, 0, 3);
    
        transactionsScene = new Scene(transactionsGrid, 400, 200);
        primaryStage.setScene(transactionsScene);
    }

    private void showBudgeting() {
        GridPane budgetingGrid = new GridPane();
        budgetingGrid.setPadding(new Insets(10, 10, 10, 10));
        budgetingGrid.setVgap(5);
        budgetingGrid.setHgap(5);
    
        Label lblCategory1 = new Label("Category 1:");
        GridPane.setConstraints(lblCategory1, 0, 0);
    
        TextField txtCategory1Budget = new TextField();
        GridPane.setConstraints(txtCategory1Budget, 1, 0);
    
        Label lblCategory2 = new Label("Category 2:");
        GridPane.setConstraints(lblCategory2, 0, 1);
    
        TextField txtCategory2Budget = new TextField();
        GridPane.setConstraints(txtCategory2Budget, 1, 1);
    
        Button btnSubmit = new Button("Submit Budget");
        GridPane.setConstraints(btnSubmit, 1, 2);
    
        btnSubmit.setOnAction(e -> {
            double category1Budget = Double.parseDouble(txtCategory1Budget.getText());
            double category2Budget = Double.parseDouble(txtCategory2Budget.getText());
    
            loggedInUser.getDashboard().updateBudget(category1Budget, category2Budget);
    
            txtCategory1Budget.clear();
            txtCategory2Budget.clear();
        });
    
        budgetingGrid.getChildren().addAll(lblCategory1, txtCategory1Budget, lblCategory2, txtCategory2Budget, btnSubmit);
    
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        budgetingGrid.add(btnBack, 0, 2);
    
        budgetingScene = new Scene(budgetingGrid, 400, 200);
        primaryStage.setScene(budgetingScene);
    }

        budgetingScene = new Scene(new GridPane(), 400, 200);
        primaryStage.setScene(budgetingScene);
    }

    private void showTaxManagement() {
        GridPane taxManagementGrid = new GridPane();
        taxManagementGrid.setPadding(new Insets(10, 10, 10, 10));
        taxManagementGrid.setVgap(5);
        taxManagementGrid.setHgap(5);
    
        Label lblIncome = new Label("Income:");
        GridPane.setConstraints(lblIncome, 0, 0);
    
        TextField txtIncome = new TextField();
        GridPane.setConstraints(txtIncome, 1, 0);
    
        Label lblTaxRate = new Label("Tax Rate:");
        GridPane.setConstraints(lblTaxRate, 0, 1);
    
        TextField txtTaxRate = new TextField();
        GridPane.setConstraints(txtTaxRate, 1, 1);

        Button btnCalculateTax = new Button("Calculate Tax");
        GridPane.setConstraints(btnCalculateTax, 1, 2);
    
        Label lblTaxAmount = new Label("Tax Amount:");
        GridPane.setConstraints(lblTaxAmount, 0, 3);
    
        Label lblCalculatedTax = new Label();
        GridPane.setConstraints(lblCalculatedTax, 1, 3);
    
        btnCalculateTax.setOnAction(e -> {
            double income = Double.parseDouble(txtIncome.getText());
            double taxRate = Double.parseDouble(txtTaxRate.getText());
    
            double taxAmount = income * (taxRate / 100);
    
            lblCalculatedTax.setText(String.format("%.2f", taxAmount));
        });
    
        taxManagementGrid.getChildren().addAll(lblIncome, txtIncome, lblTaxRate, txtTaxRate, btnCalculateTax, lblTaxAmount, lblCalculatedTax);
    
        Button btnBack = new Button("Back to Dashboard");
        btnBack.setOnAction(e -> showDashboard());
        taxManagementGrid.add(btnBack, 0, 2);
    
        taxManagementScene = new Scene(taxManagementGrid, 400, 200);
        primaryStage.setScene(taxManagementScene);
    }

    
    private void logout() {
        primaryStage.setScene(loginScene);
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
    
        alert.showAndWait();
    
    }
}

