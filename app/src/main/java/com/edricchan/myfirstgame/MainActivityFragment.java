package com.edricchan.myfirstgame;

import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Random;

import static java.lang.System.out;

public class MainActivityFragment extends Fragment {
    /**
     * The textview of the number of times rolled text
     *
     * @see TextView
     * @since 1.0
     */
    TextView timesRolledText;
    /**
     * The button of the roll dice
     *
     * @see Button
     * @since 1.0
     */
    Button rollDiceBtn;
    /**
     * The button of the reset counter
     *
     * @see Button
     * @since 1.1
     */
    Button resetTimesCounterBtn;
    /**
     * The imageview of the first dice
     *
     * @see ImageView
     * @since 1.1
     */
    ImageView dice1Drawable;
    /**
     * The imageview of the second dice
     *
     * @see ImageView
     * @since 1.1
     */
    ImageView dice2Drawable;
    /**
     * The counter
     *
     * @see int
     * @since 1.1
     */
    int timesRolledCounter = 0;
    /**
     * The main layout
     *
     * @see LinearLayout
     * @since 1.1
     */
    LinearLayout mainLayout;
    /**
     * The number picker for counter
     *
     * @see NumberPicker
     * @since 1.2
     */
    NumberPicker noTimesRolledEdit;

    /**
     * The button to update counter
     *
     * @see Button
     * @since 1.2
     */
    Button counterUpdateBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_main_content, container, false);
        dice1Drawable = (ImageView) mView.findViewById(R.id.die_1);
        dice2Drawable = (ImageView) mView.findViewById(R.id.die_2);
        timesRolledText = (TextView) mView.findViewById(R.id.times_rolled_text);
        mainLayout = (LinearLayout) mView.findViewById(R.id.content);
        noTimesRolledEdit = (NumberPicker) mView.findViewById(R.id.noTimesRolled);
        counterUpdateBtn = (Button) mView.findViewById(R.id.update_counter_btn);
        String[] nums = new String[1000];
        for (int i = 0; i < nums.length; i++)
            nums[i] = Integer.toString(i);
        noTimesRolledEdit.setMinValue(0);
        noTimesRolledEdit.setMaxValue(1000);
        noTimesRolledEdit.setWrapSelectorWheel(false);
        noTimesRolledEdit.setDisplayedValues(nums);
        noTimesRolledEdit.setValue(0);
        // Override
        counterUpdateBtn.setOnClickListener(new View.OnClickListener() {
            int updateCounterNo = noTimesRolledEdit.getValue();

            @Override
            public void onClick(View v) {
                int timesRolledCounter2 = updateCounterNo;
                updateTextCounter("You set times rolled to: " + timesRolledCounter2 + " times");
                timesRolledCounter = timesRolledCounter2;
            }
        });
        resetTimesCounterBtn = (Button) mView.findViewById(R.id.reset_times_counter_btn);
        resetTimesCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCounter();
            }
        });
        rollDiceBtn = (Button) mView.findViewById(R.id.roll_dice_btn);
        rollDiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinDice(false);
            }
        });
        return mView;
    }

    /**
     * Resets the number of times counter
     */
    private void resetCounter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to reset?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        out.println("User clicked yes.");
                        timesRolledCounter = 0;
                        resetTimesCounterBtn.setEnabled(false);
                        updateTextCounter("Counter was reset!");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        out.println("User clicked no.");
                    }
                });
        final AlertDialog alert1 = builder.create();
        alert1.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alert1.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.red));
            }
        });
        alert1.show();
    }

    /**
     * Updates the text of the counter
     *
     * @param status The status
     */
    private void updateTextCounter(String status) {
        timesRolledText.setText(status);
    }

    /**
     * Generates a random number from 1 to 6 and assigns it to a die for 2 times
     *
     * @param isTriggeredManually Whether this method is manually triggered by some form of event (Default: false)
     * @see Random
     */
    private void spinDice(boolean isTriggeredManually) {
        resetTimesCounterBtn.setEnabled(true);
        Random rand1 = new Random();
        Random rand2 = new Random();
        int dice1 = rand1.nextInt(6) + 1;
        int dice2 = rand1.nextInt(6) + 1;
        diceDrawableHandler(dice1, dice2);
        if (isTriggeredManually) {
            out.println("spinDice was triggered manually.");
        } else {
            out.println("spinDice was triggered via button click.");
        }
    }

    /**
     * Handles the drawables for when dice is spun
     *
     * @param dice1 The number of the first dice
     * @param dice2 The number of the second dice
     * @note This is quite long, so be warned if it takes a while to open the file! :(
     * @todo Make this shorter
     * @see switch
     */
    private void diceDrawableHandler(int dice1, int dice2) {
        out.println("dice1: " + dice1 + "\ndice2: " + dice2);
        switch (dice1) {
            case 1:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot1);

                break;
            case 2:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot2);

                break;
            case 3:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot3);

                break;
            case 4:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot4);

                break;
            case 5:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot5);

                break;
            case 6:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot6);

                break;
            default:
                dice1Drawable.setImageResource(R.drawable.ic_dice_dot1);
        }
        switch (dice2) {
            case 1:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot1);

                break;
            case 2:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot2);

                break;
            case 3:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot3);

                break;
            case 4:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot4);

                break;
            case 5:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot5);

                break;
            case 6:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot6);

                break;
            default:
                dice2Drawable.setImageResource(R.drawable.ic_dice_dot1);
                break;
        }
        int diceTotal = dice1 + dice2;
        timesRolledCounter++;
        updateTextCounter("You rolled: " + diceTotal + ".\nYou rolled a total of " + timesRolledCounter + " times");
        // Achievements
        if (timesRolledCounter == 25) {
            achievementDialog("Nice and easy: Roll the dice for 25 times");
        } else if (timesRolledCounter == 50) {
            achievementDialog("Skillz: Roll the dice for 50 times");
        } else if (timesRolledCounter == 100) {
            achievementDialog("AddICtEd: Roll the dice for 100 times");
        } else if (timesRolledCounter == 250) {
            achievementDialog("ReAllY adDictED: RoLL thE diCe foR 250 tIMes");
        } else if (timesRolledCounter == 500) {
            achievementDialog("How are you even...\nRoll the dice for an incredible 500 times! :O");
        }

    }

    /**
     * Achievement dialog
     *
     * @param message The message for the dialog
     */
    private void achievementDialog(String message) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
        builder2.setTitle("Achievement Unlocked!")
                .setMessage(message)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        out.println("Dismiss triggered");
                    }
                });
        AlertDialog alert2 = builder2.create();
        alert2.show();
    }
}
