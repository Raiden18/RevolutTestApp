package com.example.domain.revoluttestapp.presentation.screens.core.mvi

import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class CoreMviViewModel<A : CoreAction, S : CoreState> : ViewModel() {
    protected val actions: PublishSubject<A> = PublishSubject.create<A>()
    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected abstract val initialState: S
    protected val state = BehaviorRelay.create<S>()
    val observableState: Observable<S> = state
    protected abstract fun bindActions()

    fun dispatch(action: A) {
        actions.onNext(action)
    }

    override fun onCleared() {
        disposables.clear()
    }
}