package com.msmi.angkringansaid.helper;

import android.app.Application;
import android.content.Context;

import com.msmi.angkringansaid.helper.LocaleHelper;

public class LanguageHelper extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "in"));
    }
}
