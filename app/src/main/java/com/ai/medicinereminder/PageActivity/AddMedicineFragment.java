 package com.ai.medicinereminder.PageActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ai.medicinereminder.Database.MainDatabase;
import com.ai.medicinereminder.Database.Medicine;
import com.ai.medicinereminder.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 public class AddMedicineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // room database
    private MainDatabase mainDatabase;

    // buttons
    private Button addButton;
    private Button backButton;

    // nested Scroll view
    private NestedScrollView nestedScrollView;

    // edit texts
    private EditText nameEditText, selectTypeEditText, selectEditText, totalEditText;
    private ConstraintLayout selectTypeLayout, selectLayout, totalLayout;
    private TextView totalTextView, dueTimeTextView;

    // cross buttons
    private Button nameCrossButton;
    private LinearLayout nameCrossButtonLayout;

    // tablet
    int tabletQuantity = 1;
    private LinearLayout tabletLayout;
    private Button plusButton, minusButton;
    private TextView tabletQuantityTextView;

    // syrup
    private LinearLayout syrupLayout;
    private ConstraintLayout syrupSelectLayout;
    private EditText syrupQuantityEditText;
    private EditText syrupSelectEditText;
    private RadioButton mmRadioButton, spoonRadioButton;
    private TextView syrupTextView;
    private Button syrupButton;

    // dropper
    int dropperQuantity = 1;
    private LinearLayout dropperLayout;
    private Button dropperPlusButton, dropperMinusButton;
    private TextView dropperTextView;

    // injection
    int injectionQuantity = 1;
    private LinearLayout injectionLayout;
    private Button injectionPlusButton, injectionMinusButton;
    private TextView injectionTextView;

    // hourly
    private RadioButton hourlyRadioButton;
    int totalHour = 1;
    private LinearLayout hourlyLayout;
    private Button hourlyPlusButton, hourlyMinusButton;
    private TextView hourlyTextView;

    // session
    private RadioButton sessionRadioButton;
    private LinearLayout sessionLayout;
    private Button morningButton, noonButton, afternoonButton, eveningButton, nightButton;
    private Boolean morning = false, noon = false, afternoon = false, evening = false, night = false;

    // meal
    private LinearLayout mealLayout;
    private RadioButton beforeMealRadioButton, afterMealRadioButton;

    // optional
    private LinearLayout optionalLayout;

    // bottom sheet
    private View selectMedicineTypeBottomSheet, selectBottomSheet, syrupBottomSheet;
    private BottomSheetBehavior selectMedicineTypeBottomSheetBehavior, selectBottomSheetBehavior, syrupBottomSheetBehavior;

    // select medicine type bottom sheet
    private Button selectMedicineTypeButton;
    private TextView selectMedicineTypeResetTextView;
    private RadioButton tabletRadioButton, syrupRadioButton, dropperRadioButton, injectionRadioButton;

    // select bottom sheet
    private Button selectButton;
    private TextView selectResetTextView;
    private RadioButton dayRadioButton, monthRadioButton;

    private ConstraintLayout layout;

    public AddMedicineFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddMedicineFragment newInstance(String param1, String param2) {
        AddMedicineFragment fragment = new AddMedicineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_medicine, container, false);

        // room database
        mainDatabase = MainDatabase.getInstance(getContext());

        // buttons
        addButton = view.findViewById(R.id.buttonId_add);
        backButton = view.findViewById(R.id.back_button_id);

        nestedScrollView = view.findViewById(R.id.nestedScrollView);

        // edit text
        nameEditText = view.findViewById(R.id.editTextId_name);
        selectTypeEditText = view.findViewById(R.id.editTextId_selectType);
        selectEditText = view.findViewById(R.id.editTextId_select);
        totalEditText = view.findViewById(R.id.editTextId_total);

        totalTextView = view.findViewById(R.id.textView_total);
        dueTimeTextView = view.findViewById(R.id.textViewId_dueTime);

        selectTypeLayout = view.findViewById(R.id.constraintLayoutId_selectType);
        selectLayout = view.findViewById(R.id.constraintLayoutId_select);
        totalLayout = view.findViewById(R.id.constraintLayoutId_total);

        // cross button
        nameCrossButton = view.findViewById(R.id.buttonId_nameCrossButton);
        nameCrossButtonLayout = view.findViewById(R.id.layoutId_nameCrossButton);

        // tablet
        tabletLayout = view.findViewById(R.id.linearLayout_tabletQuantity);
        tabletQuantityTextView = view.findViewById(R.id.textViewId_tablet);
        plusButton = view.findViewById(R.id.buttonId_plusButton);
        minusButton = view.findViewById(R.id.buttonId_minusButton);

        // syrup
        syrupLayout = view.findViewById(R.id.linearLayoutId_syrupQuantity);
        syrupSelectLayout = view.findViewById(R.id.constraintLayoutId_syrupSelect);
        syrupQuantityEditText = view.findViewById(R.id.editTextId_syrup);
        syrupSelectEditText = view.findViewById(R.id.editTextId_syrupSelect);
        mmRadioButton = view.findViewById(R.id.radioButtonId_mm);
        spoonRadioButton = view.findViewById(R.id.radioButtonId_spoon);
        syrupTextView = view.findViewById(R.id.textView_syrup);
        syrupButton = view.findViewById(R.id.buttonId_syrup);

        // dropper
        dropperLayout = view.findViewById(R.id.linearLayout_dropperQuantity);
        dropperTextView = view.findViewById(R.id.textViewId_dropper);
        dropperPlusButton = view.findViewById(R.id.buttonId_dropperPlusButton);
        dropperMinusButton = view.findViewById(R.id.buttonId_dropperMinusButton);

        // injection
        injectionLayout = view.findViewById(R.id.linearLayout_injectionQuantity);
        injectionTextView = view.findViewById(R.id.textViewId_injection);
        injectionPlusButton = view.findViewById(R.id.buttonId_injectionPlusButton);
        injectionMinusButton = view.findViewById(R.id.buttonId_injectionMinusButton);

        // hourly
        hourlyRadioButton = view.findViewById(R.id.radioButtonId_hourly);
        hourlyLayout = view.findViewById(R.id.linearLayout_hourly);
        hourlyTextView = view.findViewById(R.id.textViewId_hourly);
        hourlyPlusButton = view.findViewById(R.id.buttonId_hourlyPlusButton);
        hourlyMinusButton = view.findViewById(R.id.buttonId_horulyMinusButton);

        // session
        sessionRadioButton = view.findViewById(R.id.radioButtonId_session);
        sessionLayout = view.findViewById(R.id.linearLayout_SeasonWise);
        morningButton = view.findViewById(R.id.buttonId_morningButton);
        noonButton = view.findViewById(R.id.buttonId_noonButton);
        afternoonButton = view.findViewById(R.id.buttonId_afternoonButton);
        eveningButton = view.findViewById(R.id.buttonId_eveningButton);
        nightButton = view.findViewById(R.id.buttonId_nightButton);

        // meal
        mealLayout = view.findViewById(R.id.layoutId_meal);
        beforeMealRadioButton = view.findViewById(R.id.radioButtonId_beforeMeal);
        afterMealRadioButton = view.findViewById(R.id.radioButtonId_afterMeal);

        //  optional
        optionalLayout = view.findViewById(R.id.linearLayoutId_optional);

        // bottom sheet
        selectMedicineTypeBottomSheet = view.findViewById(R.id.bottomSheetId_selectMedicineType);
        selectBottomSheet = view.findViewById(R.id.bottomSheetId_select);
        syrupBottomSheet = view.findViewById(R.id.bottomSheetId_syrupType);

        // select medicine type bottom sheet
        selectMedicineTypeButton = view.findViewById(R.id.buttonId_selectMedicineTypeSheet);
        selectMedicineTypeResetTextView = view.findViewById(R.id.textViewId_selectMedicineTypeSheetReset);
        tabletRadioButton = view.findViewById(R.id.radioButtonId_tablet);
        syrupRadioButton = view.findViewById(R.id.radioButtonId_syrup);
        dropperRadioButton = view.findViewById(R.id.radioButtonId_dropper);
        injectionRadioButton = view.findViewById(R.id.radioButtonId_injection);

        // select medicine type bottom sheet
        selectButton = view.findViewById(R.id.buttonId_select);
        selectResetTextView = view.findViewById(R.id.textViewId_selectReset);
        dayRadioButton = view.findViewById(R.id.radioButtonId_day);
        monthRadioButton = view.findViewById(R.id.radioButtonId_month);

        layout = view.findViewById(R.id.constraintLayoutId_black);

        // initialization
        setUpEditTexts();
        setUpSelectMedicineTypeBottomSheet();
        setUpSelectBottomSheet();
        setUpSelectSyrupBottomSheet();

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // set visibility of cross button
                if(nameEditText.getText().toString().trim().length() == 0){
                    nameCrossButtonLayout.setVisibility(View.GONE);
                }else{
                    nameCrossButtonLayout.setVisibility(View.VISIBLE);
                }

                showOptionalLayout();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // button on click listener

        // buttons start

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nameEditText.getText().toString().equals("")){

                    Toast.makeText(getActivity().getApplicationContext(), "Please fill up the Medicine Name filed", Toast.LENGTH_LONG).show();

                    List<Medicine> medicineList = new ArrayList<>();

                    medicineList = mainDatabase.medicineDao().getMedicineList();

                    Log.d("Medicine", medicineList.get(0).getMedicineQuantity()+"");

                }else if(selectTypeEditText.getText().toString().equals("Select a type")){

                    Toast.makeText(getActivity().getApplicationContext(), "Please select a Medicine Type", Toast.LENGTH_LONG).show();

                }else if(selectTypeEditText.getText().toString().equals("Syrup") && syrupQuantityEditText.getText().toString().equals("")){

                    Toast.makeText(getActivity().getApplicationContext(), "Please fill up the Syrup Quantity filed", Toast.LENGTH_LONG).show();

                }else if(totalEditText.getText().toString().equals("")){

                    if(selectEditText.getText().toString().equals("Day")){

                        Toast.makeText(getActivity().getApplicationContext(), "Please fill up Total Days field", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getActivity().getApplicationContext(), "Please fill up Total Months field", Toast.LENGTH_LONG).show();

                    }

                }else if(!hourlyRadioButton.isChecked() && !sessionRadioButton.isChecked()){

                    Toast.makeText(getActivity().getApplicationContext(), "Please select Hour or Session", Toast.LENGTH_LONG).show();

                }else if(!beforeMealRadioButton.isChecked() && !afterMealRadioButton.isChecked()){

                    Toast.makeText(getActivity().getApplicationContext(), "Please select Consume Time", Toast.LENGTH_LONG).show();

                }else{

                    saveMedicineToRoomDatabase();

                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        // button end

        // edit text start

        nameCrossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEditText.setText("");
            }
        });

        nameCrossButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEditText.setText("");
            }
        });

        selectTypeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalEditText.clearFocus();
                nameEditText.clearFocus();
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                layout.setVisibility(View.VISIBLE);
                // hide keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        selectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalEditText.clearFocus();
                nameEditText.clearFocus();
                selectBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                layout.setVisibility(View.VISIBLE);
                // hide keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        totalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(totalEditText, InputMethodManager.SHOW_FORCED);

                nestedScrollView.smoothScrollTo(0, totalLayout.getBottom()+60);
            }
        });

        totalEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(totalEditText, InputMethodManager.SHOW_FORCED);

                nestedScrollView.smoothScrollTo(0, totalLayout.getBottom()+60);
            }
        });

        totalEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Date d = new Date();
                CharSequence s1  = DateFormat.format("d MMMM, yyyy ", d.getTime());

                showOptionalLayout();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // edit text end

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                selectBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                syrupBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);

            }
        });

        // tablet start

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabletQuantity++;

                if(tabletQuantity == 1){
                    tabletQuantityTextView.setText(tabletQuantity+" Tablet");
                }else{
                    tabletQuantityTextView.setText(tabletQuantity+" Tablets");
                }

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tabletQuantity!=1){

                    tabletQuantity--;

                    if(tabletQuantity == 1){
                        tabletQuantityTextView.setText(tabletQuantity+" Tablet");
                    }else{
                        tabletQuantityTextView.setText(tabletQuantity+" Tablets");
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Quantity should be at least one", Toast.LENGTH_LONG).show();
                }
            }
        });

        // tablet end

        // syrup start

        syrupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syrupBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);
            }
        });

        syrupSelectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalEditText.clearFocus();
                nameEditText.clearFocus();
                syrupBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                layout.setVisibility(View.VISIBLE);
                // hide keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        mmRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syrupBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);

                spoonRadioButton.setChecked(false);
                syrupTextView.setText("mm");
            }
        });

        spoonRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syrupBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);

                mmRadioButton.setChecked(false);
                syrupTextView.setText("Spoon");
                syrupSelectEditText.setText("Spoon");
            }
        });

        // syrup end

        // dropper start

        dropperPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dropperQuantity++;

                if(dropperQuantity == 1){
                    dropperTextView.setText(dropperQuantity+" Drop");
                }else{
                    dropperTextView.setText(dropperQuantity+" Drops");
                }

            }
        });

        dropperMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dropperQuantity!=1){

                    dropperQuantity--;

                    if(dropperQuantity == 1){
                        dropperTextView.setText(dropperQuantity+" Drop");
                    }else{
                        dropperTextView.setText(dropperQuantity+" Drops");
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Quantity should be at least one", Toast.LENGTH_LONG).show();
                }
            }
        });

        // dropper end

        // dropper start

        injectionPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                injectionQuantity++;

                if(injectionQuantity == 1){
                    injectionTextView.setText(injectionQuantity+" Injection");
                }else{
                    injectionTextView.setText(injectionQuantity+" Injections");
                }

            }
        });

        injectionMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(injectionQuantity!=1){

                    injectionQuantity--;

                    if(injectionQuantity == 1){
                        injectionTextView.setText(injectionQuantity+" Injection");
                    }else{
                        injectionTextView.setText(injectionQuantity+" Injections");
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Quantity should be at least one", Toast.LENGTH_LONG).show();
                }

            }
        });

        // injection end

        // select medicine type start

        selectMedicineTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);
            }
        });

        selectMedicineTypeResetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabletRadioButton.setChecked(false);
                syrupRadioButton.setChecked(false);
                dropperRadioButton.setChecked(false);
                injectionRadioButton.setChecked(false);
                selectTypeEditText.setText("Select a type");
                tabletLayout.setVisibility(View.GONE);
            }
        });

        tabletRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTypeEditText.setText("Tablet");
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);

                tabletLayout.setVisibility(View.VISIBLE);
                syrupLayout.setVisibility(View.GONE);
                dropperLayout.setVisibility(View.GONE);
                injectionLayout.setVisibility(View.GONE);

                showOptionalLayout();
            }
        });

        syrupRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTypeEditText.setText("Syrup");
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);

                syrupLayout.setVisibility(View.VISIBLE);
                tabletLayout.setVisibility(View.GONE);
                dropperLayout.setVisibility(View.GONE);
                injectionLayout.setVisibility(View.GONE);

                showOptionalLayout();
            }
        });

        dropperRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTypeEditText.setText("Dropper");
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);


                dropperLayout.setVisibility(View.VISIBLE);
                tabletLayout.setVisibility(View.GONE);
                syrupLayout.setVisibility(View.GONE);
                injectionLayout.setVisibility(View.GONE);

                showOptionalLayout();
            }
        });

        injectionRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTypeEditText.setText("Injection");
                selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);

                tabletLayout.setVisibility(View.GONE);
                syrupLayout.setVisibility(View.GONE);
                dropperLayout.setVisibility(View.GONE);
                injectionLayout.setVisibility(View.VISIBLE);

                showOptionalLayout();
            }
        });

        // select medicine type end

        // select start

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);
            }
        });

        selectResetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayRadioButton.setChecked(false);
                monthRadioButton.setChecked(false);
                selectEditText.setText("Select");
                totalTextView.setText("Select");
            }
        });

        dayRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTextView.setText("Days");
                selectEditText.setText("Day");
                selectBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);
            }
        });

        monthRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTextView.setText("Month");
                selectEditText.setText("Month");
                selectBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                layout.setVisibility(View.GONE);
            }
        });

        // select end

        // hourly start

        hourlyRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hourlyLayout.setVisibility(View.VISIBLE);
                sessionLayout.setVisibility(View.GONE);

                showOptionalLayout();

                mealLayout.setVisibility(View.VISIBLE);

            }
        });

        hourlyPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                totalHour++;

                if(totalHour == 1){
                    hourlyTextView.setText(totalHour+" Hour");
                }else{
                    hourlyTextView.setText(totalHour+" Hours");
                }

            }
        });

        hourlyMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalHour!=1){

                    totalHour--;

                    if(totalHour == 1){
                        hourlyTextView.setText(totalHour+" Hour");
                    }else{
                        hourlyTextView.setText(totalHour+" Hours");
                    }

                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Quantity should be at least one", Toast.LENGTH_LONG).show();
                }

            }
        });

        // hourly end

        // session start

        sessionRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hourlyLayout.setVisibility(View.GONE);
                sessionLayout.setVisibility(View.VISIBLE);

                showOptionalLayout();

                mealLayout.setVisibility(View.VISIBLE);

            }
        });

        morningButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if(morning){
                    morningButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.background_string));
                    morningButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.main_color));
                    morning = false;
                }else{
                    morningButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.button));
                    morningButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
                    morning = true;
                }

            }
        });

        noonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(noon){
                    noonButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.background_string));
                    noonButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.main_color));
                    noon = false;
                }else{
                    noonButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.button));
                    noonButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
                    noon = true;
                }

            }
        });

        afternoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(afternoon){
                    afternoonButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.background_string));
                    afternoonButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.main_color));
                    afternoon = false;
                }else{
                    afternoonButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.button));
                    afternoonButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
                    afternoon = true;
                }

            }
        });

        eveningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(evening){
                    eveningButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.background_string));
                    eveningButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.main_color));
                    evening = false;
                }else{
                    eveningButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.button));
                    eveningButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
                    evening = true;
                }

            }
        });

        nightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(night){
                    nightButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.background_string));
                    nightButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.main_color));
                    night = false;
                }else{
                    nightButton.setBackgroundDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.button));
                    nightButton.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
                    night = true;
                }

            }
        });

        // session end

        // consume time start

        beforeMealRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showOptionalLayout();

            }
        });

        afterMealRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showOptionalLayout();

            }
        });

        // consume time end

        // bottom sheet behavior change
        selectMedicineTypeBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                    layout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        selectBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                    layout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return view;
    }

    private void setUpEditTexts() {

        selectTypeEditText.setSelected(false);
        selectTypeEditText.setFocusable(false);

        selectEditText.setSelected(false);
        selectEditText.setFocusable(false);

        syrupSelectEditText.setSelected(false);
        syrupSelectEditText.setFocusable(false);

        totalEditText.setClickable(false);

        // hide layouts
        tabletLayout.setVisibility(View.GONE);
        syrupLayout.setVisibility(View.GONE);
        dropperLayout.setVisibility(View.GONE);
        injectionLayout.setVisibility(View.GONE);
        optionalLayout.setVisibility(View.GONE);
        hourlyLayout.setVisibility(View.GONE);
        mealLayout.setVisibility(View.GONE);

    }

    private void setUpSelectMedicineTypeBottomSheet() {

        selectMedicineTypeBottomSheetBehavior = BottomSheetBehavior.from(selectMedicineTypeBottomSheet);
        selectMedicineTypeBottomSheetBehavior.setHideable(true);
        selectMedicineTypeBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        layout.setVisibility(View.GONE);

    }

    private void setUpSelectBottomSheet() {

        selectBottomSheetBehavior = BottomSheetBehavior.from(selectBottomSheet);
        selectBottomSheetBehavior.setHideable(true);
        selectBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

    }

    private void setUpSelectSyrupBottomSheet() {

        syrupBottomSheetBehavior = BottomSheetBehavior.from(syrupBottomSheet);
        syrupBottomSheetBehavior.setHideable(true);
        syrupBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

    }

    private void showOptionalLayout(){

        // optional layout hide
        if( !(selectTypeEditText.getText().toString().equals("Select a type")) && !(nameEditText.getText().toString().equals("")) &&
                !(totalEditText.getText().toString().equals("")) && (hourlyRadioButton.isChecked() || sessionRadioButton.isChecked()) &&
                (beforeMealRadioButton.isChecked() || afterMealRadioButton.isChecked())){

            optionalLayout.setVisibility(View.VISIBLE);

        }else{

            optionalLayout.setVisibility(View.GONE);

        }

    }

    private void saveMedicineToRoomDatabase() {

        // variables
        String name;
        int medicineType = 0;
        float medicineQuantity = 0;
        int syrupType;
        int durationType;
        int duration;
        int baseType;
        int totalHour;
        boolean morning = this.morning;
        boolean noon = this.noon;
        boolean afternoon = this.afternoon;
        boolean evening = this.evening;
        boolean night = this.night;
        int consumeTime;
        String description = null;

        // set value of the variables
        name = nameEditText.getText().toString().trim();

        // medicine type
        if(selectTypeEditText.getText().toString().equals("Tablet")){
            medicineType = 1;
            medicineQuantity = tabletQuantity;
        }else if(selectTypeEditText.getText().toString().equals("Syrup")){
            medicineType = 2;
            medicineQuantity = Integer.parseInt(syrupQuantityEditText.getText().toString().trim());
        }else if(selectTypeEditText.getText().toString().equals("Dropper")){
            medicineType = 3;
            medicineQuantity = dropperQuantity;
        }else if(selectTypeEditText.getText().toString().equals("Injection")){
            medicineType = 4;
            medicineQuantity = injectionQuantity;
        }

        // syrup type
        if(syrupSelectEditText.getText().toString().equals("mm")){
            syrupType = 1;
        }else{
            syrupType = 2;
        }

        // duration type
        if(selectEditText.getText().toString().equals("Day")){
            durationType = 1;
        }else{
            durationType = 2;
        }

        duration = Integer.parseInt(totalEditText.getText().toString().trim());

        // hourly/session
        if(hourlyRadioButton.isChecked()){
            baseType = 1;
        }else{
            baseType = 2;
        }

        totalHour = this.totalHour;

        // consume time
        if(beforeMealRadioButton.isChecked()){
            consumeTime = 1;
        }else{
            consumeTime = 2;
        }

        Medicine medicine = new Medicine();
        
        // add value to the object
        medicine.setName(name);
        medicine.setMedicineType(medicineType);
        medicine.setMedicineQuantity(medicineQuantity);
        medicine.setSyrupType(syrupType);
        medicine.setDurationType(durationType);
        medicine.setDuration(duration);
        medicine.setBaseType(baseType);
        medicine.setTotalHour(totalHour);
        medicine.setMorning(morning);
        medicine.setNoon(noon);
        medicine.setAfternoon(afternoon);
        medicine.setEvening(evening);
        medicine.setNight(night);
        medicine.setConsumeTime(consumeTime);
        medicine.setDescription(description);

        // add to room database
        mainDatabase.medicineDao().insertMedicine(medicine);

        List<Medicine> medicineList = new ArrayList<>();

        medicineList = mainDatabase.medicineDao().getMedicineList();

        for(Medicine medicine1 : medicineList){
            Log.d("Medicine", medicine1.getName());
        }

    }

}