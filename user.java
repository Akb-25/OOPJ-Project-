public class user {
    public static final double SEDENTARY = 1.2;
    public static final double LIGHTLY_ACTIVE = 1.375;
    public static final double MODERATELY_ACTIVE = 1.55;
    public static final double VERY_ACTIVE = 1.725;
    public static final double EXTRA_ACTIVE = 1.9;
    String name;
    int age;
    double height;
    double weight;
    char gender;

    public user(String n, int a, double h, double w, char g) {
        this.name = n;
        this.age = a;
        this.height = h;
        this.weight = w;
        this.gender = g;
    }

    public double returnBmi() {
        double heightInMetres = height / 100;
        return weight / (heightInMetres * heightInMetres);
    }

    public static double calculateBMR(double weight, double height, int age, char gender) {
        double bmr;
        if (gender == 'M') {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
        return bmr;
    }

    public static double calculateTDEE(double bmr, double activityLevel) {
        return bmr * activityLevel;
    }

    public static void main(String[] args) {
        // Example usage
        user u = new user("John", 30, 175.0, 70.0, 'M');
        double bmi = u.returnBmi();
        System.out.println("BMI: " + bmi);

        double bmr = calculateBMR(u.weight, u.height, u.age, u.gender);
        double tdee = calculateTDEE(bmr, MODERATELY_ACTIVE);


        System.out.println("Basal Metabolic Rate (BMR): " + bmr + " calories/day");
        System.out.println("Total Daily Energy Expenditure (TDEE): " + tdee + " calories/day");
    }
}