import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class function_app {

    public static void main(String[] args) {
        String name;
        int age;
        double height;
        double weight;
        String gender;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = sc.nextLine();

        System.out.println("Enter your age: ");
        age = sc.nextInt();

        System.out.println("Enter your height in cm: ");
        height = sc.nextDouble();

        System.out.println("Enter your weight in kg: ");
        weight = sc.nextDouble();

        sc.nextLine();

        System.out.println("Enter your gender (M/F): ");
        gender = sc.nextLine().toUpperCase();

        System.out.println("Enter your activity level:");
        System.out.println("1. Sedentary");
        System.out.println("2. Lightly Active");
        System.out.println("3. Moderately Active");
        System.out.println("4. Very Active");
        System.out.println("5. Extra Active");
        int choice = sc.nextInt();
        double activityLevel;

        if (choice == 1) {
            activityLevel = 1.2;
        } else if (choice == 2) {
            activityLevel = 1.375;
        } else if (choice == 3) {
            activityLevel = 1.55;
        } else if (choice == 4) {
            activityLevel = 1.725;
        } else if (choice == 5) {
            activityLevel = 1.9;
        } else {
            System.out.println("Invalid choice, setting activity level to Sedentary.");
            activityLevel = 1.2;
        }


        user u = new user(name, age, height, weight, gender.charAt(0));

        double bmi = u.returnBmi();


        double bmr = user.calculateBMR(u.weight, u.height, u.age, u.gender);
        double tdee = user.calculateTDEE(bmr, activityLevel);


        boolean continueEntering = true;
        int totalCalories = 0;
        int totalProtein = 0;
        int totalFat = 0;
        int totalCarbs = 0;

        while (continueEntering) {
            System.out.println("Enter the name of the food item you consumed (or type 'done' to finish): \n Rice (Basmati), Roti (Whole Wheat), Dal (Yellow Lentils), Chicken Curry, Vegetable Pulao, Paneer Tikka, Samosa, Dosa, Gulab Jamun, Pani Puri");
            String itemName = sc.nextLine();

            if (itemName.equalsIgnoreCase("done")) {
                continueEntering = false;
                break;
            }

            // Search for the food item in the database
            FoodItem item = foodTracker.searchFoodItem(itemName);

            if (item != null) {
                System.out.println("Food item found: " + item.getName());
                totalCalories += item.getCalories();
                totalProtein += item.getProtein();
                totalFat += item.getFat();
                totalCarbs += item.getCarbohydrates();
            } else {
                System.out.println("Food item not found in the database.");
            }
        }

        // Display total nutritional information for the day


        List<String> exercises = new ArrayList<>();
        List<Integer> reps = new ArrayList<>();
        boolean addMore = true;

        while (addMore) {
            System.out.println("Enter the name of the exercise: \n Crunches, Push-ups, Pull-ups, Lunges, Squats ");
            String exercise = sc.nextLine();
            exercises.add(exercise);

            System.out.println("Enter the number of reps for " + exercise + ": ");
            int rep = sc.nextInt();
            reps.add(rep);

            sc.nextLine(); // Consume newline left-over

            System.out.println("Do you want to add more exercises? (yes/no): ");
            String choice1 = sc.nextLine();
            addMore = choice1.equalsIgnoreCase("yes");
        }

        // Calculate total calories burned
        int totalCaloriesBurned = 0;
        for (int i = 0; i < exercises.size(); i++) {
            String exerciseName = exercises.get(i);
            int repCount = reps.get(i);
            Exercise exercise = exerciseTracker.searchExercise(exerciseName);
            if (exercise != null) {
                int caloriesPerRep = exercise.getCaloriesPerRep();
                totalCaloriesBurned += caloriesPerRep * repCount;
            }
        }




        System.out.println("Your daily report is ");
        System.out.println("Name: "+name+"\nAge: "+age+"\nGender: "+gender+"\nWeight: "+weight+"\nHeight: "+height);

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("BMI: " + bmi);
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Basal Metabolic Rate (BMR): " + bmr + " calories/day");
        System.out.println("Total Daily Energy Expenditure (TDEE): " + tdee + " calories/day");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Total Nutritional Information for the Day:");
        System.out.println("Total Calories: " + totalCalories);
        System.out.println("Total Protein: " + totalProtein + " grams");
        System.out.println("Total Fat: " + totalFat + " grams");
        System.out.println("Total Carbohydrates: " + totalCarbs + " grams");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Total calories burned: " + totalCaloriesBurned);

    }
}