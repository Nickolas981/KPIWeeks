package com.example.nickolas.kpiweeks.di.component;


import com.example.nickolas.kpiweeks.activities.GroupSearch;
import com.example.nickolas.kpiweeks.di.module.PresentersModule;
import com.example.nickolas.kpiweeks.di.scopes.Scope;
import com.example.nickolas.kpiweeks.di.scopes.Scopes;
import com.example.nickolas.kpiweeks.fragments.WeekFragment;

import dagger.Component;

@Scope(Scopes.VIEW)
@Component(
        modules = {PresentersModule.class},
        dependencies = {AppComponent.class}
)
public interface PresentersComponent {


//    void inject(LogInFragment logInFragment);
        void inject(GroupSearch groupSearch);
        void inject(WeekFragment weekFragment);
}
