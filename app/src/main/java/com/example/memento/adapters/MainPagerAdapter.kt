package com.example.memento.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.memento.R
import com.example.memento.fragments.CompletedFragment
import com.example.memento.fragments.NewsFragment
import com.example.memento.fragments.PendingFragment
import kotlin.reflect.KClass

class MainPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    data class FragmentItem(val titleRes: Int, val iconRes: Int, val fragmentClass: KClass<*>)

    val fragmentItems = listOf(
        FragmentItem(
            R.string.tasks_pending,
            R.drawable.baseline_assignment_late_24,
            PendingFragment::class
        ),
        FragmentItem(
            R.string.tasks_completed,
            R.drawable.baseline_assignment_turned_in_24,
            CompletedFragment::class
        ),
        FragmentItem(
            R.string.news,
            R.drawable.outline_news_24,
            NewsFragment::class
        ),
    )

    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]
                .fragmentClass
                .java
                .getDeclaredConstructor()
                .newInstance() as Fragment
    }

    override fun getItemCount(): Int = fragmentItems.size
}
