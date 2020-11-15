package diet.dietgenerator.utils.linearsolver;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import diet.dietgenerator.service.models.food.CustomFoodDynamicWeightServiceModel;
import diet.dietgenerator.service.models.food.CustomFoodServiceModel;
import diet.dietgenerator.service.models.food.FoodRequiredNutrientsServiceModel;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class FoodsLowestCostSolver {

    private ModelMapper modelMapper;
    private MPSolver solver;
    private double infinity;
    private MPObjective objective;
    private FoodRequiredNutrientsServiceModel foodRequirements;
    private List<CustomFoodServiceModel> foods;

    private MPConstraint maxCholesterol;
    private MPConstraint maxVitaminA;
    private MPConstraint maxVitaminB3;
    private MPConstraint maxVitaminB6;
    private MPConstraint maxVitaminC;
    private MPConstraint maxVitaminD;
    private MPConstraint maxVitaminE;
    private MPConstraint maxVitaminB9;
    private MPConstraint maxVitaminK;
    private MPConstraint maxCalcium;
    private MPConstraint maxCopper;
    private MPConstraint maxIron;
    private MPConstraint maxManganese;
    private MPConstraint maxPhosphorus;
    private MPConstraint maxSelenium;
    private MPConstraint maxZinc;
    private MPConstraint maxCalories;

    private MPConstraint minCalories;
    private MPConstraint minProtein;
    private MPConstraint minCarbs;
    private MPConstraint minFat;
    private MPConstraint minFiber;
    private MPConstraint minSodium;
    private MPConstraint minCholesterol;
    private MPConstraint minSugars;
    private MPConstraint minAddedSugar;
    private MPConstraint minSaturatedFats;
    private MPConstraint minTransFats;
    private MPConstraint minMonounsaturatedFats;
    private MPConstraint minPolyunsaturatedFats;
    private MPConstraint minOmega3;
    private MPConstraint minOmega6;
    private MPConstraint minVitaminB1;
    private MPConstraint minVitaminB2;
    private MPConstraint minVitaminB3;
    private MPConstraint minVitaminB5;
    private MPConstraint minVitaminB6;
    private MPConstraint minVitaminB9;
    private MPConstraint minVitaminB12;
    private MPConstraint minVitaminA;
    private MPConstraint minVitaminC;
    private MPConstraint minVitaminD;
    private MPConstraint minVitaminE;
    private MPConstraint minVitaminK;
    private MPConstraint minCalcium;
    private MPConstraint minCopper;
    private MPConstraint minIron;
    private MPConstraint minMagnesium;
    private MPConstraint minManganese;
    private MPConstraint minPhosphorus;
    private MPConstraint minPotassium;
    private MPConstraint minSelenium;
    private MPConstraint minZinc;
    private List<MPVariable> variables;

    public FoodsLowestCostSolver(FoodRequiredNutrientsServiceModel foodRequirements, List<CustomFoodServiceModel> foods) {
        solver = MPSolver.createSolver("LinearProgrammingExample", "GLOP");
        infinity = java.lang.Double.POSITIVE_INFINITY;
        objective = solver.objective();
        objective.setMinimization();
        this.foodRequirements = foodRequirements;
        this.foods = foods;
        variables = new ArrayList<>();
        modelMapper = new ModelMapper();
        setConstraints();
        setVariables();
    }

    public List<CustomFoodDynamicWeightServiceModel> getLowestCostFoods() {
        List<CustomFoodDynamicWeightServiceModel> foodsResult = new ArrayList<>();

        final MPSolver.ResultStatus resultStatus = solver.solve();
        // Check that the problem has an optimal solution.
        if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
            System.err.println("The problem does not have an optimal solution!");
            return null;
        }

        for (MPVariable variable : variables) {
            double solutionValue = variable.solutionValue();
            if (solutionValue != 0) {
                CustomFoodDynamicWeightServiceModel food = modelMapper.map(foods.get(Integer.parseInt(variable.name())), CustomFoodDynamicWeightServiceModel.class);
                food.setDynamicProductWeight((int) (solutionValue * 100));
                foodsResult.add(food);
            }
        }

        System.out.println("Optimal objective value = " + solver.objective().value());

        return foodsResult;
    }

    private void setConstraints() {
        setMaxConstraints();
        setMinConstraints();
    }

    private void setMinConstraints() {
        minCalories = solver.makeConstraint(foodRequirements.getGoalCalories(), infinity, "c0");
        minProtein = solver.makeConstraint(foodRequirements.getGoalProtein(), infinity, "c1");
        minCarbs = solver.makeConstraint(foodRequirements.getGoalCarbs(), infinity, "c2");
        minFat = solver.makeConstraint(foodRequirements.getGoalFat(), infinity, "c3");
        minFiber = solver.makeConstraint(notNull(foodRequirements.getGoalFiber()), infinity, "c4");
        minSodium = solver.makeConstraint(notNull(foodRequirements.getGoalSodium()), infinity, "c5");
        minCholesterol = solver.makeConstraint(notNull(foodRequirements.getGoalCholesterol()), infinity, "c6");
        minSugars = solver.makeConstraint(notNull(foodRequirements.getGoalSugars()), infinity, "c7");
        minAddedSugar = solver.makeConstraint(notNull(foodRequirements.getGoalAddedSugar()), infinity, "c8");
        minSaturatedFats = solver.makeConstraint(notNull(foodRequirements.getGoalSaturatedFats()), infinity, "c9");
        minTransFats = solver.makeConstraint(notNull(foodRequirements.getGoalTransFats()), infinity, "c10");
        minMonounsaturatedFats = solver.makeConstraint(notNull(foodRequirements.getGoalMonounsaturatedFats()), infinity, "c11");
        minPolyunsaturatedFats = solver.makeConstraint(notNull(foodRequirements.getGoalPolyunsaturatedFats()), infinity, "c12");
        minOmega3 = solver.makeConstraint(notNull(foodRequirements.getGoalOmega3()), infinity, "c13");
        minOmega6 = solver.makeConstraint(notNull(foodRequirements.getGoalOmega6()), infinity, "c14");
        minVitaminB1 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB1()), infinity, "c15");
        minVitaminB2 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB2()), infinity, "c16");
        minVitaminB3 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB3()), infinity, "c17");
        minVitaminB5 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB5()), infinity, "c18");
        minVitaminB6 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB6()), infinity, "c19");
        minVitaminB9 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB9()), infinity, "c20");
        minVitaminB12 = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminB12()), infinity, "c21");
        minVitaminA = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminA()), infinity, "c22");
        minVitaminC = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminC()), infinity, "c23");
        minVitaminD = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminD()), infinity, "c24");
        minVitaminE = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminE()), infinity, "c25");
        minVitaminK = solver.makeConstraint(notNull(foodRequirements.getGoalVitaminK()), infinity, "c26");
        minCalcium = solver.makeConstraint(notNull(foodRequirements.getGoalCalcium()), infinity, "c27");
        minCopper = solver.makeConstraint(notNull(foodRequirements.getGoalCopper()), infinity, "c28");
        minIron = solver.makeConstraint(notNull(foodRequirements.getGoalIron()), infinity, "c29");
        minMagnesium = solver.makeConstraint(notNull(foodRequirements.getGoalMagnesium()), infinity, "c30");
        minManganese = solver.makeConstraint(notNull(foodRequirements.getGoalManganese()), infinity, "c31");
        minPhosphorus = solver.makeConstraint(notNull(foodRequirements.getGoalPhosphorus()), infinity, "c32");
        minPotassium = solver.makeConstraint(notNull(foodRequirements.getGoalPotassium()), infinity, "c33");
        minSelenium = solver.makeConstraint(notNull(foodRequirements.getGoalSelenium()), infinity, "c34");
        minZinc = solver.makeConstraint(notNull(foodRequirements.getGoalZinc()), infinity, "c35");
    }

    private void setMaxConstraints() {
        maxCalories = solver.makeConstraint(-infinity, foodRequirements.getMaxCalories(), "cc0");
        maxCholesterol = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxCholesterol()), "cc1");
        maxVitaminA = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminA()), "cc2");
        maxVitaminB3 = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminB3()), "cc3");
        maxVitaminB6 = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminB6()), "cc4");
        maxVitaminC = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminC()), "cc5");
        maxVitaminD = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminD()), "cc6");
        maxVitaminE = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminE()), "cc7");
        maxVitaminB9 = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminB9()), "cc8");
        maxVitaminK = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxVitaminK()), "cc9");
        maxCalcium = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxCalcium()), "cc10");
        maxCopper = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxCopper()), "cc11");
        maxIron = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxIron()), "cc12");
        maxManganese = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxManganese()), "cc13");
        maxPhosphorus = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxPhosphorus()), "cc14");
        maxSelenium = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxSelenium()), "cc15");
        maxZinc = solver.makeConstraint(-infinity, nullToInfinity(foodRequirements.getMaxZinc()), "cc16");
    }

    private void setVariables() {

        int index = 0;
        for (CustomFoodServiceModel food : foods) {
            MPVariable variable = solver.makeNumVar(0.0, infinity, index + "");
            setMaxVariables(food, variable);
            setMinVariables(food, variable);
            //set price per 100 grams
            objective.setCoefficient(variable, (100 * food.getPrice()) / food.getProductWeight());
            variables.add(variable);
            index++;
        }

    }

    private void setMinVariables(CustomFoodServiceModel food, MPVariable variable) {
        minCalories.setCoefficient(variable, food.getCalories());
        minProtein.setCoefficient(variable, food.getProtein());
        minCarbs.setCoefficient(variable, food.getCarbohydrates());
        minFat.setCoefficient(variable, food.getFat());
        minFiber.setCoefficient(variable, notNull(food.getFiber()));
        minSugars.setCoefficient(variable, notNull(food.getSugars()));
        minAddedSugar.setCoefficient(variable, notNull(food.getAddedSugar()));
        minSodium.setCoefficient(variable, notNull(food.getSodium()));
        minCholesterol.setCoefficient(variable, notNull(food.getCholesterol()));
        minSaturatedFats.setCoefficient(variable, notNull(food.getSaturatedFats()));
        minTransFats.setCoefficient(variable, notNull(food.getTransFats()));
        minMonounsaturatedFats.setCoefficient(variable, notNull(food.getMonounsaturatedFats()));
        minPolyunsaturatedFats.setCoefficient(variable, notNull(food.getPolyunsaturatedFats()));
        minOmega3.setCoefficient(variable, notNull(food.getOmega3()));
        minOmega6.setCoefficient(variable, notNull(food.getOmega6()));
        minVitaminB1.setCoefficient(variable, notNull(food.getVitaminB1()));
        minVitaminB2.setCoefficient(variable, notNull(food.getVitaminB2()));
        minVitaminB3.setCoefficient(variable, notNull(food.getVitaminB3()));
        minVitaminB5.setCoefficient(variable, notNull(food.getVitaminB5()));
        minVitaminB6.setCoefficient(variable, notNull(food.getVitaminB6()));
        minVitaminB9.setCoefficient(variable, notNull(food.getVitaminB9()));
        minVitaminB12.setCoefficient(variable, notNull(food.getVitaminB12()));
        minVitaminA.setCoefficient(variable, notNull(food.getVitaminA()));
        minVitaminC.setCoefficient(variable, notNull(food.getVitaminC()));
        minVitaminD.setCoefficient(variable, notNull(food.getVitaminD()));
        minVitaminE.setCoefficient(variable, notNull(food.getVitaminE()));
        minVitaminK.setCoefficient(variable, notNull(food.getVitaminK()));
        minCalcium.setCoefficient(variable, notNull(food.getCalcium()));
        minCopper.setCoefficient(variable, notNull(food.getCopper()));
        minIron.setCoefficient(variable, notNull(food.getIron()));
        minMagnesium.setCoefficient(variable, notNull(food.getMagnesium()));
        minManganese.setCoefficient(variable, notNull(food.getManganese()));
        minPhosphorus.setCoefficient(variable, notNull(food.getPhosphorus()));
        minPotassium.setCoefficient(variable, notNull(food.getPotassium()));
        minSelenium.setCoefficient(variable, notNull(food.getSelenium()));
        minZinc.setCoefficient(variable, notNull(food.getZinc()));
    }

    private void setMaxVariables(CustomFoodServiceModel food, MPVariable variable) {
        maxCholesterol.setCoefficient(variable, notNull(food.getCholesterol()));
        maxVitaminA.setCoefficient(variable, notNull(food.getVitaminA()));
        maxVitaminB3.setCoefficient(variable, notNull(food.getVitaminB3()));
        maxVitaminB6.setCoefficient(variable, notNull(food.getVitaminB6()));
        maxVitaminC.setCoefficient(variable, notNull(food.getVitaminC()));
        maxVitaminD.setCoefficient(variable, notNull(food.getVitaminD()));
        maxVitaminE.setCoefficient(variable, notNull(food.getVitaminE()));
        maxVitaminB9.setCoefficient(variable, notNull(food.getVitaminB9()));
        maxVitaminK.setCoefficient(variable, notNull(food.getVitaminK()));
        maxCalcium.setCoefficient(variable, notNull(food.getCalcium()));
        maxCopper.setCoefficient(variable, notNull(food.getCopper()));
        maxIron.setCoefficient(variable, notNull(food.getIron()));
        maxManganese.setCoefficient(variable, notNull(food.getManganese()));
        maxPhosphorus.setCoefficient(variable, notNull(food.getPhosphorus()));
        maxSelenium.setCoefficient(variable, notNull(food.getSelenium()));
        maxZinc.setCoefficient(variable, notNull(food.getZinc()));
        maxCalories.setCoefficient(variable, food.getCalories());
    }

    private Float notNull(Float value) {
        return value == null ? 0f : value;
    }

    private Float nullToInfinity(Float value) {
        return value == null ? (float) infinity : value;
    }

}
