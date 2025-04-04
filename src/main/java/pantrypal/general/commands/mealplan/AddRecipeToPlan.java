package pantrypal.general.commands.mealplan;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.Recipe;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;

import java.util.Scanner;

public class AddRecipeToPlan extends MealPlanCommand {

    int planIndex;
    int recipeIndex;
    String mealType;

    public AddRecipeToPlan() {
        super("addPlanToList <plan name>", "Add a new plan to the list");
    }

    public AddRecipeToPlan(int planIndex, int recipeIndex, String mealType) {
        this.planIndex = planIndex;
        this.recipeIndex = recipeIndex;
        this.mealType = mealType;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in){
        try {
            if (recipeIndex >= recipes.getRecipeList().size()) {
                throw new ArrayIndexOutOfBoundsException("Recipe index out of bounds");
            }
            Recipe recipe = recipes.getRecipeList().get(recipeIndex);
            plans.getPlanDetails(planIndex).addRecipeToPlan(recipe, getMealType(mealType));
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid plan index");
        } catch (NullPointerException e) {
            ui.showMessage("Invalid meal type given.\nPlease enter only BREAKFAST, LUNCH OR DINNER");
        } catch (IllegalArgumentException e) {
            ui.showMessage("Invalid input given. Please refrain from unconventional datatypes");
        }
    }
}
