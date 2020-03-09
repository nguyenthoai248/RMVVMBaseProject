package com.equal.dgwproject.login.data

import com.equal.base.presentation.common.BaseViewModel
import com.equal.base.presentation.common.extentions.filterTo
import com.equal.base.presentation.common.navigator.Navigator
import com.equal.base.presentation.common.navigator.NavigatorFactory
import com.equal.dgwproject.login.domain.usecases.Login
import com.equal.dgwproject.login.presentation.LoginViewState
import com.equal.dgwproject.login.rmvvm.LoginClickedAction
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

/**
 * Created by Thoai Nguyen on 3/7/20.
 */

class LoginViewModel @Inject constructor(
    private val useCase: Login,
    private val navigatorFactory: NavigatorFactory
) : BaseViewModel() {
    private val TAG: String =
        LoginViewModel::class.java.simpleName

    val navigateViewState = PublishProcessor.create<Navigator>()
    val viewState: BehaviorProcessor<LoginViewState> by lazy {
        val processor = BehaviorProcessor.create<LoginViewState>()
        processor.onNext(LoginViewState(false, 0, null))
        processor
    }

    private val operated = AtomicBoolean(false)

//    private val config by lazy {
//        PagedList.Config.Builder()
//            .setPageSize(AlbumsRepository.PAGE_SIZE)
//            .setInitialLoadSizeHint(
//                AlbumsRepository.PAGE_SIZE * AlbumsRepository.DEFAULT_INITIAL_PAGE_MULTIPLIER
//            )
//            .setPrefetchDistance(AlbumsRepository.PAGE_SIZE / 2)
//            .setEnablePlaceholders(false)
//            .build()
//    }

    init {
        // Reactive way to handling View actions
        actionStream.filterTo(LoginClickedAction::class.java)
            .throttleFirst(
                1,
                TimeUnit.SECONDS
            ) // Avoid double click(Multi view click) less than one second
            .subscribe(::loginClicked) { /*If reach here log an assertion because it should never happen*/ }
            .track()

//        actionStream.filterTo(GetAlbumAction::class.java)
//            .filter { operated.compareAndSet(false, true) || it.force }
//            .toFlowable(BackpressureStrategy.LATEST)
//            .flatMap(::sendEventToDeeperLayers)
//            .subscribe(viewState::onNext)
//            .track()
    }

    private fun loginClicked(clickedAction: LoginClickedAction) {
//        val navigator = navigatorFactory.create(HomePageNavigator::class.java)
//        navigator.setAlbumId(clickedAction.albumId)
        Timber.tag(TAG).d(clickedAction.userName + "----" + clickedAction.password)
//        navigateViewState.onNext(navigator)
    }
}