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

    private final ModelMapper modelMapper;
    private MPSolver solver;
    private double infinity;
    MPObjective objective;
    private FoodRequiredNutrientsServiceModel foodRequirements;
    private List<CustomFoodServiceModel> foods;
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
    List<MPVariable> variables;

    public FoodsLowestCostSolver(ModelMapper modelMapper, FoodRequiredNutrientsServiceModel foodRequirements, List<CustomFoodServiceModel> foods) {
        this.modelMapper = modelMapper;
        solver = MPSolver.createSolver("LinearProgrammingExample", "GLOP");
        infinity = java.lang.Double.POSITIVE_INFINITY;
        objective = solver.objective();
        objective.setMinimization();
        this.foodRequirements = foodRequirements;
        this.foods = foods;
        variables = new ArrayList<>();
        setConstraints();
        setVariables();
    }

    private void setConstraints() {
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

    private void setVariables() {

        int index = 0;
        for (CustomFoodServiceModel food : foods) {
            MPVariable variable = solver.makeNumVar(0.0, infinity, index + "");
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
            //set price per 100 grams
            objective.setCoefficient(variable, (100 * food.getPrice()) / food.getProductWeight());
            variables.add(variable);
            index++;
        }

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

    private Float notNull(Float value) {
        return value == null ? 0f : value;
    }

}
