package com.equal.dgwproject.application

import com.equal.dgwproject.CreditDataModule
import dagger.Module

/**
 * Created by Thoai Nguyen on 3/4/2020.
 */
@Module(includes = [CreditDataModule::class])
class DataModule
