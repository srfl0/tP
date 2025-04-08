package pantrypal.mealplan;

import pantrypal.general.control.Ui;

import java.util.ArrayList;

public class MealPlanManager {
    private Plan[] weeklyPlans = new Plan[7]; //7 days in a week
    private ArrayList<Plan> planList = new ArrayList<>();


    public MealPlanManager() {}

    /**
     * Adds a new plan to planList
     *
     * @param planName name of plan as entered by the user
     */

    public void addPlan(String planName) {
        Plan plan = new Plan(planName);
        planList.add(plan);
    }

    /**
     * Removes a plan from planList
     *
     * @param planIndex corresponding index of the plan
     *                  in planList
     */

    public void removePlan(int planIndex) {
        Plan plan = planList.get(planIndex);
        planList.remove(plan);
        //Remove plan from Weekly Plan if it exists
        for (int i = 0; i < weeklyPlans.length; i++) {
            if (weeklyPlans[i] != null && weeklyPlans[i].equals(plan)) {
                weeklyPlans[i] = null;
            }
        }
    }

    /**
     * Substantiates an index of weeklyPlans with plan details
     *
     * @param planIndex the corresponding index of a plan in planList
     * @param day corresponding enum related to the index of weeklyPlans
     */

    public void addPlanToDay(int planIndex, Day day) {
        int dayIndex = day.ordinal();
        if (planIndex < 0 || planIndex >= planList.size()) {
            throw new IllegalArgumentException("Invalid plan index provided. Please enter a valid plan index.");
        }
        Plan plan = planList.get(planIndex);
        if (weeklyPlans[dayIndex] == null) {
            weeklyPlans[dayIndex] = plan;
        } else {
            throw new IllegalArgumentException("Plan already exists for this day.");
        }
    }

    /**
     * Clears the plans currently saved to the corresponding
     * day if applicable
     *
     * @param day corresponding enum related to the index of weeklyPlans
     */

    public void removePlanFromDay(Day day) {
        int dayIndex = day.ordinal();
        if (weeklyPlans[dayIndex] != null) {
            weeklyPlans[dayIndex] = null;
            Ui.showMessage("Plan removed from " + day);
        } else {
            Ui.showMessage("There is no plan for " + day);
        }
    }

    /**
     * Provide a detailed view of one day in weeklyPlans
     *
     * @param day corresponding enum related to the index of weeklyPlans
     */

    public void viewDayPlan(Day day) {
        int dayIndex = day.ordinal();
        if (weeklyPlans[dayIndex] != null) {
            Ui.showMessage("Plan for " + day + ": " + weeklyPlans[dayIndex]);
        } else {
            Ui.showMessage("There is no plan for " + day);
        }
    }

    /**
     * Provide a summarised view containing only the plans' names
     * in weeklyPlans
     */

    public void viewPlanForWeek() {
        Ui.printLine();
        for (int i = 0; i < weeklyPlans.length; i++) {
            if (weeklyPlans[i] != null) {
                System.out.println(Day.values()[i].name() + ": \n" + weeklyPlans[i].toString());
            } else {
                System.out.println("No plan for " + Day.values()[i].name());
            }
            Ui.printLine();
        }
    }

    /**
     * Provides a summarised view of all currently created plans
     */

    public void viewPlanList() {
        if (planList.isEmpty()) {
            Ui.showMessage("No plans available.");
        } else {
            Ui.printLine();
            for (int i = 0; i < planList.size(); i++) {
                System.out.println((i + 1) + ": " + planList.get(i).getPlanName());
            }
            Ui.printLine();
        }
    }

    /**
     * Provides a detailed view of one created plan
     *
     * @param planIndex corresponding index of a plan in planList
     */

    public void viewPlan(int planIndex) {
        if (planIndex < 0 || planIndex >= planList.size()) {
            Ui.showMessage("Invalid plan index.");
            return;
        }
        Plan plan = planList.get(planIndex);
        Ui.showMessage(plan.toString());
    }

    /**
     * Locate a plan based on the search key provided.
     * The plan's index will also be indicated
     *
     * @param planName search key for finding plan
     * @return boolean for matching plan found
     */

    public boolean findPlan(String planName) {
        boolean found = false;
        for (Plan plan : planList) {
            if (plan.getPlanName().equalsIgnoreCase(planName)) {
                if (!found) {
                    Ui.showMessage("Here are the plans that match your search: ");
                }
                found = true;
                Ui.showMessage((planList.indexOf(plan)+1) + //plus 1 for matching to user view
                        ": " + plan.getPlanName());
            }
        }
        return found;
    }

    public Plan[] getWeeklyPlans() {
        return weeklyPlans;
    }

    public ArrayList<Plan> getPlanList() {
        return planList;
    }

    public Plan getPlanForDay(Day day) {
        return weeklyPlans[day.ordinal()];
    }

    public Plan getPlan(int planIndex) {
        if (planIndex < 0 || planIndex >= planList.size()) {
            return null;
        }
        return planList.get(planIndex);
    }

    /**
     * Obtain details related to plan
     *
     * @param planName
     * @return
     */

    public Plan getPlanDetails(String planName) {
        for (Plan plan : planList) {
            if (plan.getPlanName().equalsIgnoreCase(planName)) {
                return plan;
            }
        }
        return null; // Plan not found
    }

    public int getPlanIndex(String planName) {
        for (int i = 0; i < planList.size(); i++) {
            if (planList.get(i).getPlanName().equalsIgnoreCase(planName)) {
                return i;
            }
        }
        return -1; // Plan not found
    }
}
