package com.equal.dgwproject.home.presentation;

import com.equal.base.injection.modules.ActivityModule;
import com.equal.base.injection.scopes.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface HomeActivityComponent {

    void inject(HomeActivity homeActivity);
}
