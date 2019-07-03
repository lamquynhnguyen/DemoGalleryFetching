package com.example.demogalleryfetching

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class DragManagerAdapter(context: Context, adapter: ItemAdapter, dragDirs: Int, swipeDirs: Int) :
		ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
	private val itemAdapter = adapter

	override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder):
			Boolean {
		itemAdapter.onMove(p1.adapterPosition, p2.adapterPosition)
		return true
	}

	override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
		itemAdapter.onSwipe(p0.adapterPosition)
	}
}
