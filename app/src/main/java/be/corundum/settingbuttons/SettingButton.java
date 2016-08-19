package be.corundum.settingbuttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * This custom component is basically a button with a title and description TextView
 */
public class SettingButton extends LinearLayout {

    public static final int BORDERS_NONE = 0;
    public static final int BORDERS_BOTTOM = 1;

    private TextView mTitle;
    private TextView mDescription;

    private int mTitleTextColor;
    private int mDescriptionTextColor;

    public SettingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadViews(attrs);
    }

    private void loadViews(AttributeSet attrs) {

        // Inflate views
        LayoutInflater.from(getContext()).inflate(R.layout.view_settings_button, this);
        mTitle = (TextView)findViewById(R.id.settings_button_title);
        mDescription = (TextView)findViewById(R.id.settings_button_description);

        // Load custom style attributes, if any
        if (attrs == null) {
            return;
        }

        TypedArray styleAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.SettingButton);
        setTitle(styleAttributes.getString(R.styleable.SettingButton_sbTitleText));
        setDescription(styleAttributes.getString(R.styleable.SettingButton_sbDescriptionText));
        setTitleTextColor(styleAttributes.getColor(R.styleable.SettingButton_sbTitleForeground, getResources().getColor(android.R.color.black)));
        setDescriptionTextColor(styleAttributes.getColor(R.styleable.SettingButton_sbDescriptionForeground, getResources().getColor(android.R.color.darker_gray)));
        setBorders(getBordersDrawableById(styleAttributes.getInt(R.styleable.SettingButton_sbBorders, 0)));

        styleAttributes.recycle();
    }

    public void setBorders(@DrawableRes int backgroundDrawable) {
        setBorders(getContext().getResources().getDrawable(backgroundDrawable));
    }

    public void setBorders(Drawable backgroundDrawable) {
        setBackgroundDrawable(backgroundDrawable);
    }

    public void setTitle(@StringRes int stringResource) {
        setTitle(getContext().getString(stringResource));
    }

    public void setTitle(String title) {
        mTitle.setText(title);

        if (!TextUtils.isEmpty(title)) {
            setTitleVisibility(VISIBLE);
        } else {
            setTitleVisibility(GONE);
        }
    }

    public void setDescription(@StringRes int stringResource) {
        setDescription(getContext().getString(stringResource));
    }

    public void setDescription(String description) {
        mDescription.setText(description);

        if (!TextUtils.isEmpty(description)) {
            setDescriptionVisibility(VISIBLE);
        } else {
            setDescriptionVisibility(GONE);
        }
    }

    public void setTitleVisibility(int visibility) {
        mTitle.setVisibility(visibility);
    }

    public void setDescriptionVisibility(int visibility) {
        mDescription.setVisibility(visibility);
    }

    public void setTitleTextColor(int color) {
        mTitleTextColor = color;
        mTitle.setTextColor(color);
    }

    public void setDescriptionTextColor(int color) {
        mDescriptionTextColor = color;
        mDescription.setTextColor(color);
    }

    public void setEnabled(boolean setEnabled) {
        if (setEnabled) {
            mTitle.setTextColor(mTitleTextColor);
            mDescription.setTextColor(mDescriptionTextColor);
        } else {
            mTitle.setTextColor(getResources().getColor(android.R.color.darker_gray));
            mDescription.setTextColor(getResources().getColor(android.R.color.darker_gray));
        }
    }

    private Drawable getBordersDrawableById(int bordersId) {
        switch (bordersId) {
            case BORDERS_BOTTOM:
                return getResources().getDrawable(R.drawable.settings_button_background_border_bottom);
            default:
                return getResources().getDrawable(R.drawable.settings_button_background_border_none);
        }
    }
}

