package com.canoo.dolphin.client.android;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.canoo.dolphin.event.ValueChangeEvent;
import com.canoo.dolphin.event.ValueChangeListener;
import com.canoo.dolphin.mapping.Property;

public class DolphinBindingDefinition {

    private DolphinBindingDefinition() {
    }

    @BindingAdapter({"app:progressBinding"})
    public static void bindViewEnabledToBooleanProperty(final ProgressBar view, final Property<Integer> property) {

        property.onChanged(new ValueChangeListener<Integer>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Integer> evt) {
                int newValue = evt.getNewValue() == null ? 0 : evt.getNewValue();
                if(newValue != view.getProgress()) {
                    view.setProgress(newValue);
                }
            }
        });
        int newValue = property.get() == null ? 0 : property.get();
        if(newValue != view.getProgress()) {
            view.setProgress(newValue);
        }
    }

    @BindingAdapter({"app:enabledBinding"})
    public static void bindViewEnabledToBooleanProperty(final View view, final Property<Boolean> property) {

        property.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> evt) {
                boolean newValue = evt.getNewValue() == null ? false : evt.getNewValue();
                if(newValue != view.isEnabled()) {
                    view.setEnabled(newValue);
                }
            }
        });
        boolean newValue = property.get() == null ? false : property.get();
        if(newValue != view.isEnabled()) {
            view.setEnabled(newValue);
        }
    }

    @BindingAdapter({"app:checkedBinding"})
    public static void bindCompoundButtonCheckedToBooleanProperty(final CompoundButton view, final Property<Boolean> property) {

        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                property.set(b);
            }
        });


        property.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> evt) {
                boolean newValue = evt.getNewValue() == null ? false : evt.getNewValue();
                if(newValue != view.isChecked()) {
                    view.setChecked(newValue);
                }
            }
        });
        boolean newValue = property.get() == null ? false : property.get();
        if(newValue != view.isChecked()) {
            view.setChecked(newValue);
        }
    }

    @BindingAdapter({"app:textBinding"})
    public static void bindTextViewTextToStringProperty(final TextView view, final Property<String> property) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                property.set(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        property.onChanged(new ValueChangeListener<String>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends String> evt) {
                if (evt.getNewValue() == null || view.getText() == null) {
                    view.setText(evt.getNewValue());
                } else if (view.getText() != null) {
                    if (!view.getText().toString().equals(evt.getNewValue())) {
                        view.setText(evt.getNewValue());
                    }
                } else {
                    if (view.getText() != null) {
                        view.setText(evt.getNewValue());
                    }
                }
            }
        });
        view.setText(property.get());
    }
}
