package com.canoo.dolphin.client.android;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.canoo.dolphin.event.ValueChangeEvent;
import com.canoo.dolphin.event.ValueChangeListener;
import com.canoo.dolphin.mapping.Property;

public class DolphinBindingDefinition {

    private DolphinBindingDefinition() {
    }

    @BindingAdapter({"app:binding"})
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
