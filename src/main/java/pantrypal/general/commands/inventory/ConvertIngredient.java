package pantrypal.general.commands.inventory;

import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.mealplan.MealPlanManager;
import pantrypal.recipe.RecipeManager;
import pantrypal.shoppinglist.ShoppingList;
import pantrypal.inventory.Unit;
import java.util.Scanner;

public class ConvertIngredient extends InventoryCommand {
    private String name;
    private Unit targetUnit;

    public ConvertIngredient() {
        super("convertIngredient <name> <targetUnit>",
                "Convert the ingredient quantity to the target unit");
    }

    public ConvertIngredient(String name, Unit targetUnit) {
        this.name = name;
        this.targetUnit = targetUnit;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList list, RecipeManager recipes,
                        MealPlanManager plans, Scanner in) {

        inventory.convertIngredient(name, targetUnit);
    }
}
