import java.sql.*;
public class exerciseTracker {
    public static void insertExercise(String name, int caloriesPerRep) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "INSERT INTO exercise (name, caloriesPerRep) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, caloriesPerRep);
            statement.executeUpdate();
            System.out.println("Exercise inserted successfully.");
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
    public static Exercise searchExercise(String exerciseName){
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        try {
            connection = DatabaseConnector.connect();
            String sql = "SELECT * FROM exercise WHERE name = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, exerciseName);
            rs=statement.executeQuery();
            if(rs.next()){
                String name=rs.getString("name");
                int caloriesPerRep=rs.getInt("caloriesPerRep");
                return new Exercise(name,caloriesPerRep);
            }
        } catch (SQLException e){
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
    public static int calculateCaloriesBurned(Exercise[] exercises, int[] reps){
        int totalCaloriesBurned = 0;
        for (int i = 0; i < exercises.length; i++) {
            totalCaloriesBurned += exercises[i].getCaloriesPerRep() * reps[i];
        }
        return totalCaloriesBurned;
    }
    public static void main(String[] args) {
        // Inserting exercises
        exerciseTracker.insertExercise("Crunches", 5);
        exerciseTracker.insertExercise("Push-ups", 3);
        exerciseTracker.insertExercise("Pull-ups", 7);
        exerciseTracker.insertExercise("Squats", 4);
        exerciseTracker.insertExercise("Lunges", 6);

        // Searching for an exercise
        Exercise exercise = exerciseTracker.searchExercise("Squats");
        if (exercise != null) {
            System.out.println("Exercise found: " + exercise.getName() + ", Calories per Rep: " + exercise.getCaloriesPerRep());
        } else {
            System.out.println("Exercise not found.");
        }
    }

}
class Exercise{
    private String name;
    private int caloriesPerRep;

    public Exercise(String n,int c){
        this.name=n;
        this.caloriesPerRep=c;
    }
    public String getName(){
        return name;
    }
    public int getCaloriesPerRep(){
        return caloriesPerRep;
    }
}