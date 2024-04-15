package com.my.fakestoredemo.common.state
/**
 * The ViewState data class is a generic container used in Kotlin to represent the state of a UI component or screen in an application. It's designed to encapsulate various pieces of information about the current state, making it easier to manage and observe changes in the UI. Here's a breakdown of its properties:
 *
 *  isLoading: A Boolean flag indicating whether a loading process is ongoing. If true, it typically means some data is being fetched or an operation is being performed, and the UI might show a loading indicator to the user.
 * dataState: A generic type T that can hold any type of data. This property is used to store the actual data needed by the UI. For example, if the UI displays a list of items, dataState could hold that list. It's nullable (?), meaning it can also be null if there's no data to display.
 * message: An optional String that can hold a message intended for the user. This could be used for success messages, informational notes, or any other text the UI needs to display that isn't an error.
 * errorMessage: Similar to message, but specifically intended for error messages. If something goes wrong (e.g., a network request fails), the error message can be stored here and displayed to the user.
 *
 * In simple terms, ViewState is like a snapshot of what's happening on a screen at any given moment—whether it's loading, what data is currently shown, and any messages for the user. By observing changes to this state, the UI can react accordingly, updating what's displayed based on the latest state.
 * */
data class ViewState<T>(
    val isLoading: Boolean = false,
    var dataState: T? = null,
    val message: String? = null,
    val errorMessage: String? = null
)
