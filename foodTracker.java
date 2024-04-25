import java.lang.String;
import java.sql.*;
public class foodTracker {
    public static void insertFoodItem(String name, int calories, int protein, int fat, int carbohydrates) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO food_items (name, calories, protein, fat, carbs) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, calories);
            statement.setInt(3, protein);
            statement.setInt(4, fat);
            statement.setInt(5, carbohydrates);
            statement.executeUpdate();
            System.out.println("Food item inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) DatabaseConnector.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static FoodItem searchFoodItem(String itemName){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        try{
            connection=DatabaseConnector.connect();
            String sql="SELECT * FROM food_items WHERE name= ?";
            statement=connection.prepareStatement(sql);
            statement.setString(1,itemName);
            rs=statement.executeQuery();
            if(rs.next()){
                String name=rs.getString("name");
                int calories=rs.getInt("calories");
                int protein=rs.getInt("protein");
                int fat=rs.getInt("fat");
                int carbs=rs.getInt("carbs");
                return new FoodItem(name,calories,protein,fat,carbs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if(rs!=null) rs.close();
                if(statement!=null) statement.close();
                if(connection!=null) DatabaseConnector.closeConnection(connection);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    public static NutritionalInfo calculateNutritionalInfo(FoodItem... items){
        int totalCalories=0;
        int totalProtein=0;
        int totalFat=0;
        int totalCarbs=0;
        for (FoodItem item: items){
            totalCalories+=item.getCalories();
            totalProtein+=item.getProtein();
            totalFat+=item.getFat();
            totalCarbs+=item.getCarbohydrates();
        }
        return new NutritionalInfo(totalCalories, totalProtein,totalFat,totalCarbs);
    }
    public static void main(String[] args) {

        foodTracker.insertFoodItem("Rice (Basmati)", 200, 4, 1, 45);
        foodTracker.insertFoodItem("Roti (Whole Wheat)", 80, 2, 1, 15);
        foodTracker.insertFoodItem("Dal (Yellow Lentils)", 150, 10, 5, 20);
        foodTracker.insertFoodItem("Chicken Curry", 250, 30, 10, 5);
        foodTracker.insertFoodItem("Vegetable Pulao", 300, 5, 10, 55);
        foodTracker.insertFoodItem("Paneer Tikka", 200, 20, 15, 5);
        foodTracker.insertFoodItem("Samosa", 150, 4, 10, 15);
        foodTracker.insertFoodItem("Dosa", 150, 2, 5, 25);
        foodTracker.insertFoodItem("Gulab Jamun", 150, 2, 10, 30);
        foodTracker.insertFoodItem("Pani Puri", 20, 0, 1, 4);
    }
}

class FoodItem {
    private String name;
    private int calories;
    private int protein;
    private int fat;
    private int carbohydrates;

    public FoodItem(String name, int calories, int protein, int fat, int carbohydrates) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }
}

class NutritionalInfo {
    private int totalCalories;
    private int totalProtein;
    private int totalFat;
    private int totalCarbs;

    public NutritionalInfo(int totalCalories, int totalProtein, int totalFat, int totalCarbs) {
        this.totalCalories = totalCalories;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;
        this.totalCarbs = totalCarbs;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public int getTotalCarbs() {
        return totalCarbs;
    }
}