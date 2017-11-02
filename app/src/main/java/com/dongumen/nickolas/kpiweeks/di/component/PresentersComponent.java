package com.dongumen.nickolas.kpiweeks.di.component;


import com.dongumen.nickolas.kpiweeks.activities.GroupSearch;
import com.dongumen.nickolas.kpiweeks.di.module.PresentersModule;
import com.dongumen.nickolas.kpiweeks.di.scopes.Scope;
import com.dongumen.nickolas.kpiweeks.di.scopes.Scopes;
import com.dongumen.nickolas.kpiweeks.fragments.WeekFragment;

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
