# DataBindingAdapters
Utils to implement databinding in ListViews and RecyclerViews

### [Demo](https://github.com/navasmdc/DemoApp)

### Configuration

```groovy
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}

dependencies {
    implementation 'com.github.navasmdc:databinidingadapters:1.0.0'
}

```

### Integration


This library will be your life easier when you try to implement an adapater through _Databinding_. You just to bind your list and which layout you want to inflate,  to one of the `ListView`or `RecyclerView` that the library provides and the library will do the work for you.

```xml
<com.gc.databinidingadapters.DataBindingRecyclerView
            android:id="@+id/lvProducts"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:data="@{viewModel.products}"
            app:itemLayout="@layout/product_item"
            app:onItemClickListener="@{viewModel.itemClickListener}"/>

```
##### product_item.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="viewModel"
            type="ProductItem" />
    </data>
    ...
```

>If you want to have your list always updated, don´t worry, the library provides an `ObservableList` which works together with the adapters helping for the listviews are updated. You just to _add_, _remove_ or _update_ an item and the list view will be refreshed.

***
 
>Any way altough you don't want to use a list from the library because you have a custom view whick works with a adapter, you are able to use an adapter from the library, for that, you just have to provide it two parameters and an optional listener if you want to process item click:

```kotlin
val adapter = DataBindingAdapter<Item>(list, R.layout.view_item, OnItemClickListener { item -> } )

```
